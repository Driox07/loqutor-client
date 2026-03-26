package asanchezg.loqutorcli;

import asanchezg.loqutorcli.ui.MainForm;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class Loqutor {
    
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
        java.awt.EventQueue.invokeLater(() -> new MainForm().setVisible(true));
    }
}
