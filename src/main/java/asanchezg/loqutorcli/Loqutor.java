package asanchezg.loqutorcli;

import asanchezg.loqutorcli.ttsservice.APISettings;
import asanchezg.loqutorcli.ttsservice.TTSService;
import asanchezg.loqutorcli.ui.MainForm;
import javazoom.jl.player.Player;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class Loqutor {
    public static Player player;
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Error initializing UI: " + ex);
        }
        APISettings.loadSettings();
        MainForm mainForm = new MainForm();
        mainForm.setLocationRelativeTo(null);
        java.awt.EventQueue.invokeLater(() -> mainForm.setVisible(true));
    }
}
