package asanchezg.loqutorcli.ttsservice;

import java.io.File;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;


/**
 *
 * @author Adrián Sánchez Galera
 */
public class APISettings{
    // Loaded settings
    private static APISettings apiSettings = null;
    
    public static APISettings getAPISettings(){
        return apiSettings;
    } 
    
    private static boolean validApiConfigured = false;
    
    public static boolean isValidApiConfigured(){
        return validApiConfigured;
    }
    
    // Settings loader
    private static final String CONFIG_FILE = "ApiSettings.json";
    private static final ObjectMapper om = new ObjectMapper();
    
    public static void loadSettings(){
        try{
            apiSettings = om.readValue(new File(CONFIG_FILE), APISettings.class);
            validApiConfigured = true;
        }catch(JacksonException e){
            validApiConfigured = false;
            System.out.println("Failed to load API settings file: " + e.getMessage());
        }
    }
    
    public static void saveSettings(APISettings settings){
        try{
            om.writerWithDefaultPrettyPrinter().writeValue(new File(CONFIG_FILE), settings);
            apiSettings = settings;
            validApiConfigured = true;
        }catch(JacksonException e){
            validApiConfigured = false;
            System.out.println("Failed to save API settings file: " + e.getMessage());
        }
    }

    public String url;
    public String engineParameter;
    public String languageParameter;
    public String voiceParameter;
    public String textParameter;
    public String formatParameter;
    public String effectParameter;
    public String levelParameter;
    public int textLimit;
    public String defaultFormat;
    
    public APISettings() {
    }
    
    public APISettings(String url, String engineParameter, String languageParameter, String voiceParameter, String textParameter, String formatParameter, String effectParameter, String levelParameter, int textLimit, String defaultFormat) {
        this.url = url;
        this.engineParameter = engineParameter;
        this.languageParameter = languageParameter;
        this.voiceParameter = voiceParameter;
        this.textParameter = textParameter;
        this.formatParameter = formatParameter;
        this.effectParameter = effectParameter;
        this.levelParameter = levelParameter;
        this.textLimit = textLimit;
        this.defaultFormat = defaultFormat;
    }
}
