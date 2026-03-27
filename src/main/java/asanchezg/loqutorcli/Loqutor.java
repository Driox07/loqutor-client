package asanchezg.loqutorcli;

import asanchezg.loqutorcli.ttsservice.APISettings;
import asanchezg.loqutorcli.ttsservice.TTSService;
import asanchezg.loqutorcli.ttsservice.maps.Maps;
import asanchezg.loqutorcli.ui.MainForm;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Image;
import java.util.List;
import java.net.URL;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javazoom.jl.player.Player;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class Loqutor {
    public static Player player;
    private static List<Image> icons;
    
    private static void loadIcon(){
        // Buscamos la imagen en el classpath (dentro de resources)
        URL icon16url = Loqutor.class.getResource("/icons/icon16.png");
        URL icon32url = Loqutor.class.getResource("/icons/icon32.png");
        if (icon16url != null && icon32url != null) {
            ImageIcon icon16 = new ImageIcon(icon16url);
            ImageIcon icon32 = new ImageIcon(icon32url);
            icons = Arrays.asList(icon16.getImage(), icon32.getImage());
        } else {
            icons = null;
            System.err.println("No se pudo encontrar el icono en la ruta especificada.");
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error initializing UI: " + ex);
        }
        APISettings.loadSettings();
        Maps.loadAllMaps();
        loadIcon();
        MainForm mainForm = new MainForm();
        mainForm.setLocationRelativeTo(null);
        if(icons!=null)mainForm.setIconImages(icons);
        java.awt.EventQueue.invokeLater(() -> mainForm.setVisible(true));
    }
}
