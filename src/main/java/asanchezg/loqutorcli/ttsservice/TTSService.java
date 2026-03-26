package asanchezg.loqutorcli.ttsservice;

import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 *
 * @author Adrián Sánchez Galera
 */
public class TTSService {
    private static CacheSignature cacheSignature = null;
    private static byte[] cachedAudio = null;
    
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
        url = url + apiSettings.formatParameter + "=mp3";
        url = url + apiSettings.engineParameter + "2";
        return url;
    }
    
    public static byte[] textToSpeech(String text, String language, String voice, String effectType, String effectLevel) throws Exception {
        if(!APISettings.isValidApiConfigured()) throw new Exception("Error: debe configurarse una API.\nEsto se puede realizar en Editar > Configuración de API." );
        CacheSignature cs = new CacheSignature(text, language, voice, effectType, effectLevel);
        if(cs.equals(cacheSignature) && cachedAudio != null){
            return cachedAudio;
        }
        String url = buildUrl(text, language, voice, effectType, effectLevel);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new Exception("Error: " + response);
        cacheSignature = cs;
        cachedAudio = response.body().bytes();
        return cachedAudio;
    }
}
