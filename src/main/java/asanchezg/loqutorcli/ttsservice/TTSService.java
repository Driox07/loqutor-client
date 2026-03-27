package asanchezg.loqutorcli.ttsservice;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
        if(language != null){
            url = url + apiSettings.languageParameter + "=" + language + "&";
        }
        if(voice != null){
            url = url + apiSettings.voiceParameter + "=" + voice + "&";
        }
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

        ByteArrayOutputStream audioStream = new ByteArrayOutputStream();
        String[] fragments = subdivideText(text);

        try {
            if (progressBar != null) {
                javax.swing.SwingUtilities.invokeLater(() ->
                    progressBar.updateProgress(0, "Iniciando conversión...")
                );
            }

            for (int i = 0; i < fragments.length; i++) {
                if (progressBar != null && progressBar.isCancelled()) {
                    throw new Exception("Operación cancelada por el usuario.");
                }

                String url = buildUrl(fragments[i], language, voice, effectType, effectLevel);
                Request request = new Request.Builder().url(url).build();
                System.out.println("URL: " + request.toString());

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new Exception("Error: " + response);
                    }

                    ResponseBody body = response.body();
                    if (body == null) {
                        throw new Exception("Error: response body es null");
                    }

                    audioStream.write(body.bytes());
                }

                if (progressBar != null) {
                    int currentFragment = i + 1;
                    int progressValue = (int) Math.round((currentFragment * 100.0) / fragments.length);
                    javax.swing.SwingUtilities.invokeLater(() ->
                        progressBar.updateProgress(progressValue, "Procesando fragmento " + currentFragment + " de " + fragments.length)
                    );
                }

                Thread.sleep(REQUEST_WAIT_TIME);
            }

            cacheSignature = cs;
            cachedAudio = audioStream.toByteArray();
            return cachedAudio;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Operación interrumpida.", e);
        } finally {
            if (progressBar != null) {
                javax.swing.SwingUtilities.invokeLater(progressBar::finishProgress);
            }
        }
    }
}
