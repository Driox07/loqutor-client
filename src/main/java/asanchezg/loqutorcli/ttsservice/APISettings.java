package asanchezg.loqutorcli.ttsservice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class APISettings implements Serializable{
    public static void save(APISettings settings, String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(settings);
        }
    }

    public static APISettings load(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (APISettings) ois.readObject();
        }
    }
    private static final long serialVersionUID = 1L;
    public String url;
    public String engineParameter;
    public String languageParameter;
    public String voiceParameter;
    public String textParameter;
    public String formatParameter;
    public String effectParameter;
    public String levelParameter;
}
