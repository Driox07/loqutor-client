package asanchezg.loqutorcli.ui;

import asanchezg.loqutorcli.ttsservice.APISettings;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class SettingsDialog extends javax.swing.JDialog {

    public SettingsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadFields();
    }
    
    private boolean validateFields(){
        return !apiField.getText().isBlank() &&
               !engineField.getText().isBlank() &&
               !langField.getText().isBlank() &&
               !voiceField.getText().isBlank() &&
               !textField.getText().isBlank() &&
               !formatField.getText().isBlank() &&
               !effectField.getText().isBlank() &&
               !levelField.getText().isBlank() &&
               !((int)limitSpinner.getValue() <= 0);
    }
    
    private APISettings createAPISettings(){
        return new APISettings(apiField.getText(),
        engineField.getText(),
        langField.getText(),
        voiceField.getText(),
        textField.getText(),
        formatField.getText(),
        effectField.getText(),
        levelField.getText(),
        (int)limitSpinner.getValue());
    }
    
    private void loadFields(){
        if(APISettings.isValidApiConfigured()){
            APISettings apiSettings = APISettings.getAPISettings();
            apiField.setText(apiSettings.url);
            engineField.setText(apiSettings.engineParameter);
            langField.setText(apiSettings.languageParameter);
            voiceField.setText(apiSettings.voiceParameter);
            textField.setText(apiSettings.textParameter);
            formatField.setText(apiSettings.formatParameter);
            effectField.setText(apiSettings.effectParameter);
            levelField.setText(apiSettings.levelParameter);
            limitSpinner.setValue(apiSettings.textLimit);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        apiField = new javax.swing.JTextField();
        engineField = new javax.swing.JTextField();
        langField = new javax.swing.JTextField();
        voiceField = new javax.swing.JTextField();
        textField = new javax.swing.JTextField();
        effectField = new javax.swing.JTextField();
        levelField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        formatField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        limitSpinner = new javax.swing.JSpinner();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(456, 347));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("API Settings"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("API URL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Engine parameter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Language parameter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Voice parameter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Text parameter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Format field");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Level parameter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(apiField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(engineField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(langField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(voiceField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(textField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(effectField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(levelField, gridBagConstraints);

        jLabel9.setText("Effect parameter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(formatField, gridBagConstraints);

        jLabel1.setText("Text limit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        limitSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 4);
        jPanel1.add(limitSpinner, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        getContentPane().add(jPanel1, gridBagConstraints);

        cancelBtn.setText("Cancelar");
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 2);
        getContentPane().add(cancelBtn, gridBagConstraints);

        saveBtn.setText("Guardar");
        saveBtn.addActionListener(this::saveBtnActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(8, 2, 4, 4);
        getContentPane().add(saveBtn, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if(validateFields()){
            APISettings.saveSettings(createAPISettings());
            dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, "No pueden haber campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apiField;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField effectField;
    private javax.swing.JTextField engineField;
    private javax.swing.JTextField formatField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField langField;
    private javax.swing.JTextField levelField;
    private javax.swing.JSpinner limitSpinner;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField textField;
    private javax.swing.JTextField voiceField;
    // End of variables declaration//GEN-END:variables
}
