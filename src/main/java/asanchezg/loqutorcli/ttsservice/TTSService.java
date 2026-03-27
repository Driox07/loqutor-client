package asanchezg.loqutorcli.ttsservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import java.util.concurrent.ScheduledExecutorService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/**
 *
 * @author Adrián Sánchez Galera
 */
public class TTSService {
    private static final long REQUEST_WAIT_TIME = 500;
    private static CacheSignature cacheSignature = null;
    private static byte[] cachedAudio = null;
    private static final OkHttpClient client = new OkHttpClient();
    private static final ScheduledExecutorService scheduler = newSingleThreadScheduledExecutor();
    
    private static String buildUrl(String text, String language, String voice, String effectType, String effectLevel){
        APISettings apiSettings = APISettings.getAPISettings();
        String url = apiSettings.url + "?";
        if(effectType != null){
            url = url + apiSettings.effectParameter + "=" + effectType + "&";
        }
        if(effectLevel != null){
            url = url + apiSettings.levelParameter + "=" + effectLevel + "&";
        }
        url = url + apiSettings.textParameter + "=" + text + "&";
        url = url + apiSettings.languageParameter + "=" + language + "&";
        url = url + apiSettings.voiceParameter + "=" + voice + "&";
        url = url + apiSettings.formatParameter + "=mp3&";
        url = url + apiSettings.engineParameter + "=2";
        return url;
    }
    
    private static String[] subdivideText(String text){
        int textLimit = APISettings.getAPISettings().textLimit;
        if(text.length() <= textLimit){
            return new String[]{text};
        }
        int base = 0;
        int best = -1;
        ArrayList<String> fragments = new ArrayList<>();
        while(true){
            if(text.length() - base <= textLimit){
                fragments.add(text.substring(base));
                break;
            }
            for(int i = base; i < base + textLimit; i++){
                if(text.charAt(i) == '.'){
                    best = i;
                }
            }
            if(best > -1){
                fragments.add(text.substring(base, best));
                base = best+1;
                best = -1;
                continue;
            }
            for(int i = base; i < base + textLimit; i++){
                if(text.charAt(i) == ' '){
                    best = i;
                }
            }
            if(best > -1){
                fragments.add(text.substring(base, best));
                base = best+1;
                best = -1;
                continue;
            }
            fragments.add(text.substring(base, base+textLimit));
            base = base+textLimit;
        }
        System.out.println("Text fragmented:");
        for(String fragment : fragments){
            System.out.println("--------------------------------");
            System.out.println(fragment);
            System.out.println("--------------------------------");
        }
        return fragments.toArray(String[]::new);
    }
    
    public static byte[] textToSpeech(
            String text,
            String language,
            String voice,
            String effectType,
            String effectLevel,
            ProgressBar progressBar
    ) throws Exception {

        if (!APISettings.isValidApiConfigured()) 
            throw new Exception("Error: debe configurarse una API.\nConfigúrala en Editar > Configuración de API.");

        if (progressBar != null) {
            javax.swing.SwingUtilities.invokeLater(progressBar::makeVisible);
        }

        // Comprobación de cache rápida
        CacheSignature cs = new CacheSignature(text, language, voice, effectType, effectLevel);
        if (cs.equals(cacheSignature) && cachedAudio != null) {
            if(progressBar != null){
                javax.swing.SwingUtilities.invokeLater(progressBar::finishProgress);
            }
            return cachedAudio;
        }

        // Creamos un ByteArrayOutputStream para ir agregando fragmentos
        ByteArrayOutputStream audioStream = new ByteArrayOutputStream();
        String[] fragments = subdivideText(text);

        // Hilo de fondo
        Thread ttsThread = new Thread(() -> {
            try {
                for (int i = 0; i < fragments.length; i++) {
                    if (progressBar != null && progressBar.isCancelled()) {
                        throw new RuntimeException("Operación cancelada");
                    }

                    String url = buildUrl(fragments[i], language, voice, effectType, effectLevel);
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();

                    if (!response.isSuccessful()) 
                        throw new RuntimeException("Error: " + response);

                    ResponseBody body = response.body();
                    if (body == null) 
                        throw new RuntimeException("Error: response body es null");

                    synchronized (audioStream) {
                        audioStream.write(body.bytes());
                    }

                    if (progressBar != null) {
                        int progressValue = i + 1;
                        javax.swing.SwingUtilities.invokeLater(() ->
                            progressBar.updateProgress(progressValue, "Procesando fragmento " + progressValue + " de " + fragments.length)
                        );
                    }

                    Thread.sleep(REQUEST_WAIT_TIME);
                }

                cacheSignature = cs;
                cachedAudio = audioStream.toByteArray();

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if (progressBar != null) {
                    javax.swing.SwingUtilities.invokeLater(progressBar::finishProgress);
                }
            }
        });

        // Arrancamos el hilo
        ttsThread.start();

        // Esperamos a que termine antes de devolver el resultado
        ttsThread.join();  // ⚠️ Esto solo bloquea el hilo que llama, no el EDT si llamamos desde hilo de fondo

        return cachedAudio;
    }
}
