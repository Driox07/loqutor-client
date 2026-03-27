package asanchezg.loqutorcli;

import asanchezg.loqutorcli.ttsservice.APISettings;
import asanchezg.loqutorcli.ttsservice.TTSService;
import asanchezg.loqutorcli.ttsservice.maps.Maps;
import asanchezg.loqutorcli.ui.MainForm;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javazoom.jl.player.Player;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class Loqutor {
    public static Player player;
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error initializing UI: " + ex);
        }
        APISettings.loadSettings();
        Maps.loadAllMaps();
        MainForm mainForm = new MainForm();
        mainForm.setLocationRelativeTo(null);
        java.awt.EventQueue.invokeLater(() -> mainForm.setVisible(true));
    }
}
