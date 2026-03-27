package asanchezg.loqutorcli.ui;

import asanchezg.loqutorcli.Loqutor;
import asanchezg.loqutorcli.ttsservice.TTSService;
import asanchezg.loqutorcli.ttsservice.maps.Maps;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        effectCombo.addItem("Ninguno");
        for(String effectName : effects.keySet()){
            effectCombo.addItem(effectName);
        }
    }
    
    private void openScript(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Abrir guión de Loqutor...");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de Texto (*.loq)", "loq");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                byte[] content = Files.readAllBytes(selectedFile.toPath());
                scriptArea.setText(new String(content, StandardCharsets.UTF_8));
                System.out.println("Archivo cargado: " + selectedFile.getName());

            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }
    
    private void saveScript(){
        if(scriptArea.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Se debe introducir o seleccionar texto para narrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar guión de Loqutor...");
            chooser.setFileFilter(new FileNameExtensionFilter("Guión de Loqutor (*.loq)", "loq"));
            int userSelection = chooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = chooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();
                if (!path.toLowerCase().endsWith(".loq")) {
                    fileToSave = new File(path + ".loq");
                }
                FileOutputStream fos = new FileOutputStream(fileToSave);
                fos.write(scriptArea.getText().getBytes(StandardCharsets.UTF_8));
                fos.close();
            }
        }catch(HeadlessException | IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void play(String text){
        try{
            if(text == null || text.isBlank()){
                JOptionPane.showMessageDialog(null, "Se debe introducir o seleccionar texto para narrar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String lang = Maps.getMap(Maps.MapType.MAP_LANGUAGE).get((String)langCombo.getSelectedItem());
            String voice = Maps.getMap(Maps.MapType.MAP_VOICE).get((String)voiceCombo.getSelectedItem());
            String effect = Maps.getMap(Maps.MapType.MAP_EFFECT).get((String)effectCombo.getSelectedItem());
            String level = (effect == null ? null : (String)levelSpinner.getValue());
            System.out.println(effect);
            
            byte[] audio = TTSService.textToSpeech(text, lang, voice, effect, level, new ProgressDialog(null, true));
            if(Loqutor.player != null){
                Loqutor.player.close();
            }
            Loqutor.player = new Player(new ByteArrayInputStream(audio));
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
    
    private void playScript(){
        play(scriptArea.getText());
    }
    
    private void playSelection(){
        play(scriptArea.getSelectedText());
    }
    
    private void export(String text){
        try{
            if(text == null || text.isBlank()){
                JOptionPane.showMessageDialog(null, "Se debe introducir o seleccionar texto para narrar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String lang = Maps.getMap(Maps.MapType.MAP_LANGUAGE).get((String)langCombo.getSelectedItem());
            String voice = Maps.getMap(Maps.MapType.MAP_VOICE).get((String)voiceCombo.getSelectedItem());
            String effect = Maps.getMap(Maps.MapType.MAP_EFFECT).get((String)effectCombo.getSelectedItem());
            String level = (effect == null ? null : (String)levelSpinner.getValue());
            System.out.println(effect);
            
            byte[] audio = TTSService.textToSpeech(text, lang, voice, effect, level, new ProgressDialog(null, true));
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Exportar guión completo...");
            chooser.setFileFilter(new FileNameExtensionFilter("Archivos de Audio MP3", "mp3"));
            int userSelection = chooser.showSaveDialog(this);
 
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                
                File fileToSave = chooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();
                if (!path.toLowerCase().endsWith(".mp3")) {
                    fileToSave = new File(path + ".mp3");
                }
                FileOutputStream fos = new FileOutputStream(fileToSave);
                fos.write(audio);
                fos.close();
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void exportScript(){
        export(scriptArea.getText());
    }
    
    private void exportSelection(){
        export(scriptArea.getSelectedText());
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
        openScript = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openScriptMenuItem = new javax.swing.JMenuItem();
        saveScriptMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exportScriptMenuItem = new javax.swing.JMenuItem();
        playScriptMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exportSelectionMenuItem = new javax.swing.JMenuItem();
        playSelectionMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
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
        scriptArea.setLineWrap(true);
        scriptArea.setRows(5);
        scriptArea.setWrapStyleWord(true);
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
        saveScript.addActionListener(this::saveScriptActionPerformed);
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
        exportSelection.addActionListener(this::exportSelectionActionPerformed);
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
        playSelection.addActionListener(this::playSelectionActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(playSelection, gridBagConstraints);

        openScript.setText("Abrir Guión");
        openScript.addActionListener(this::openScriptActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(openScript, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        getContentPane().add(jPanel3, gridBagConstraints);

        jMenu1.setText("Archivo");

        openScriptMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openScriptMenuItem.setText("Abrir guión");
        openScriptMenuItem.addActionListener(this::openScriptMenuItemActionPerformed);
        jMenu1.add(openScriptMenuItem);

        saveScriptMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveScriptMenuItem.setText("Guardar guión");
        saveScriptMenuItem.addActionListener(this::saveScriptMenuItemActionPerformed);
        jMenu1.add(saveScriptMenuItem);
        jMenu1.add(jSeparator1);

        exportScriptMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exportScriptMenuItem.setText("Exportar guión");
        exportScriptMenuItem.addActionListener(this::exportScriptMenuItemActionPerformed);
        jMenu1.add(exportScriptMenuItem);

        playScriptMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        playScriptMenuItem.setText("Reproducir guión");
        playScriptMenuItem.addActionListener(this::playScriptMenuItemActionPerformed);
        jMenu1.add(playScriptMenuItem);
        jMenu1.add(jSeparator2);

        exportSelectionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exportSelectionMenuItem.setText("Exportar selección");
        exportSelectionMenuItem.addActionListener(this::exportSelectionMenuItemActionPerformed);
        jMenu1.add(exportSelectionMenuItem);

        playSelectionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        playSelectionMenuItem.setText("Reproducir selcción");
        playSelectionMenuItem.addActionListener(this::playSelectionMenuItemActionPerformed);
        jMenu1.add(playSelectionMenuItem);
        jMenu1.add(jSeparator3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        apiSettingsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
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
        exportScript();
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

    private void playSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playSelectionActionPerformed
        playSelection();
    }//GEN-LAST:event_playSelectionActionPerformed

    private void exportSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportSelectionActionPerformed
        exportSelection();
    }//GEN-LAST:event_exportSelectionActionPerformed

    private void openScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openScriptActionPerformed
        openScript();
    }//GEN-LAST:event_openScriptActionPerformed

    private void saveScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveScriptActionPerformed
        saveScript();
    }//GEN-LAST:event_saveScriptActionPerformed

    private void saveScriptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveScriptMenuItemActionPerformed
        saveScript();
    }//GEN-LAST:event_saveScriptMenuItemActionPerformed

    private void openScriptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openScriptMenuItemActionPerformed
        openScript();
    }//GEN-LAST:event_openScriptMenuItemActionPerformed

    private void exportScriptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportScriptMenuItemActionPerformed
        exportScript();
    }//GEN-LAST:event_exportScriptMenuItemActionPerformed

    private void playScriptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playScriptMenuItemActionPerformed
        playScript();
    }//GEN-LAST:event_playScriptMenuItemActionPerformed

    private void exportSelectionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportSelectionMenuItemActionPerformed
        exportSelection();
    }//GEN-LAST:event_exportSelectionMenuItemActionPerformed

    private void playSelectionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playSelectionMenuItemActionPerformed
        playSelection();
    }//GEN-LAST:event_playSelectionMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutLoqutorClientMenuItem;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem apiSettingsMenuItem;
    private javax.swing.JComboBox<String> effectCombo;
    private javax.swing.JButton exportScript;
    private javax.swing.JMenuItem exportScriptMenuItem;
    private javax.swing.JButton exportSelection;
    private javax.swing.JMenuItem exportSelectionMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JComboBox<String> langCombo;
    private javax.swing.JSpinner levelSpinner;
    private javax.swing.JButton openScript;
    private javax.swing.JMenuItem openScriptMenuItem;
    private javax.swing.JButton playScript;
    private javax.swing.JMenuItem playScriptMenuItem;
    private javax.swing.JButton playSelection;
    private javax.swing.JMenuItem playSelectionMenuItem;
    private javax.swing.JButton saveScript;
    private javax.swing.JMenuItem saveScriptMenuItem;
    private javax.swing.JTextArea scriptArea;
    private javax.swing.JComboBox<String> voiceCombo;
    // End of variables declaration//GEN-END:variables
}
