package asanchezg.loqutorcli.ui;

import asanchezg.loqutorcli.Loqutor;
import asanchezg.loqutorcli.ttsservice.TTSService;
import asanchezg.loqutorcli.ttsservice.maps.Maps;
import java.io.ByteArrayInputStream;
import java.util.Map;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
        loadLanguageComboBox();
        loadVoiceComboBox();
        loadEffectComboBox();
    }
    
    private void loadLanguageComboBox(){
        Map<String,String> langs = Maps.getMap(Maps.MapType.MAP_LANGUAGE);
        for(String langName : langs.keySet()){
            langCombo.addItem(langName);
        }
    }
    
    private void loadVoiceComboBox(){
        Map<String,String> voices = Maps.getMap(Maps.MapType.MAP_VOICE);
        for(String voiceName : voices.keySet()){
            voiceCombo.addItem(voiceName);
        }
    }
    
    private void loadEffectComboBox(){
        Map<String,String> effects = Maps.getMap(Maps.MapType.MAP_EFFECT);
        for(String effectName : effects.keySet()){
            effectCombo.addItem(effectName);
        }
    }
    
    private void playScript(){
        try{
            String text = scriptArea.getText().trim();
            if(text.isBlank()){
                JOptionPane.showMessageDialog(null, "Se debe introducir texto para poder generar texto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            String lang = Maps.getMap(Maps.MapType.MAP_LANGUAGE).get((String)langCombo.getSelectedItem());
            String voice = Maps.getMap(Maps.MapType.MAP_VOICE).get((String)voiceCombo.getSelectedItem());
            String effect = Maps.getMap(Maps.MapType.MAP_EFFECT).get((String)effectCombo.getSelectedItem());
            
            TTSService.textToSpeech(text, lang, voice, effect, ""+levelSpinner.getValue());
            if(Loqutor.player != null){
                Loqutor.player.close();
            }
            new Thread(() -> {
                try {
                    Loqutor.player.play();
                } catch (JavaLayerException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        effectCombo = new javax.swing.JComboBox<>();
        langCombo = new javax.swing.JComboBox<>();
        voiceCombo = new javax.swing.JComboBox<>();
        levelSpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scriptArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        saveScript = new javax.swing.JButton();
        exportScript = new javax.swing.JButton();
        exportSelection = new javax.swing.JButton();
        playScript = new javax.swing.JButton();
        playSelection = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        apiSettingsMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutLoqutorClientMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loqutor Client");
        setPreferredSize(new java.awt.Dimension(679, 514));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajustes de voz"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Idioma");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Efecto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Voz");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Nivel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(effectCombo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        jPanel1.add(langCombo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 8);
        jPanel1.add(voiceCombo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        jPanel1.add(levelSpinner, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Guión"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        scriptArea.setColumns(20);
        scriptArea.setRows(5);
        jScrollPane1.setViewportView(scriptArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        saveScript.setText("Guardar Guión");
        saveScript.setMinimumSize(new java.awt.Dimension(200, 23));
        saveScript.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(saveScript, gridBagConstraints);

        exportScript.setText("Exportar Guión");
        exportScript.setMinimumSize(new java.awt.Dimension(200, 23));
        exportScript.setPreferredSize(new java.awt.Dimension(200, 23));
        exportScript.addActionListener(this::exportScriptActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(exportScript, gridBagConstraints);

        exportSelection.setText("Exportar Selección");
        exportSelection.setMinimumSize(new java.awt.Dimension(200, 23));
        exportSelection.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(exportSelection, gridBagConstraints);

        playScript.setText("Reproducir Guión");
        playScript.setMinimumSize(new java.awt.Dimension(200, 23));
        playScript.setPreferredSize(new java.awt.Dimension(200, 23));
        playScript.addActionListener(this::playScriptActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(playScript, gridBagConstraints);

        playSelection.setText("Reproducir Selección");
        playSelection.setMinimumSize(new java.awt.Dimension(200, 23));
        playSelection.setPreferredSize(new java.awt.Dimension(200, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(playSelection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        getContentPane().add(jPanel3, gridBagConstraints);

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        apiSettingsMenuItem.setText("Configuración de API");
        apiSettingsMenuItem.addActionListener(this::apiSettingsMenuItemActionPerformed);
        jMenu2.add(apiSettingsMenuItem);

        jMenuBar1.add(jMenu2);

        aboutMenu.setText("Acerca de");

        aboutLoqutorClientMenuItem.setText("Acerca de Loqutor Client");
        aboutLoqutorClientMenuItem.addActionListener(this::aboutLoqutorClientMenuItemActionPerformed);
        aboutMenu.add(aboutLoqutorClientMenuItem);

        jMenuBar1.add(aboutMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportScriptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportScriptActionPerformed

    private void aboutLoqutorClientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutLoqutorClientMenuItemActionPerformed
        AboutDialog ad = new AboutDialog(this, true);
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);
    }//GEN-LAST:event_aboutLoqutorClientMenuItemActionPerformed

    private void apiSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apiSettingsMenuItemActionPerformed
        SettingsDialog sd = new SettingsDialog(this, true);
        sd.setLocationRelativeTo(null);
        sd.setVisible(true);
    }//GEN-LAST:event_apiSettingsMenuItemActionPerformed

    private void playScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playScriptActionPerformed
        playScript();
    }//GEN-LAST:event_playScriptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutLoqutorClientMenuItem;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem apiSettingsMenuItem;
    private javax.swing.JComboBox<String> effectCombo;
    private javax.swing.JButton exportScript;
    private javax.swing.JButton exportSelection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> langCombo;
    private javax.swing.JSpinner levelSpinner;
    private javax.swing.JButton playScript;
    private javax.swing.JButton playSelection;
    private javax.swing.JButton saveScript;
    private javax.swing.JTextArea scriptArea;
    private javax.swing.JComboBox<String> voiceCombo;
    // End of variables declaration//GEN-END:variables
}
