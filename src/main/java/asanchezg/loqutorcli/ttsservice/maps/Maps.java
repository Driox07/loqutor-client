package asanchezg.loqutorcli.ttsservice.maps;

import java.io.File;
import java.util.Map;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class Maps {

    public enum MapType {MAP_LANGUAGE, MAP_VOICE, MAP_EFFECT};
    
    private static Map[] maps = new Map[MapType.values().length];
    private static final String[] fileNames = {"Languages.json", "Voices.json", "Effects.json"};    
    
    public static void loadMap(MapType mapType){
        try{
            maps[mapType.ordinal()] = new ObjectMapper().readValue(new File(fileNames[mapType.ordinal()]), new TypeReference<Map<String, String>>(){});
        }catch(JacksonException e){
            System.out.println("Failed to load " + mapType.name() + " map: " + e.getMessage());
        }
    }
    
    public static void saveSettings(MapType mapType, Map<String,String> map){
        try{
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(fileNames[mapType.ordinal()]), map);
            maps[mapType.ordinal()] = map;
        }catch(JacksonException e){
            System.out.println("Failed to save " + mapType.name() + " map: " + e.getMessage());
        }
    }
}
