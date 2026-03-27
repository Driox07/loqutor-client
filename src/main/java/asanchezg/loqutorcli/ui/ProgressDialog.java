package asanchezg.loqutorcli.ui;

import asanchezg.loqutorcli.ttsservice.ProgressBar;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class ProgressDialog extends javax.swing.JDialog implements ProgressBar {
    
    private volatile boolean cancelled = false;
    
    public ProgressDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
    }

    @Override
    public void updateProgress(int value, String text){
        progressBar.setValue(value);
        statusLabel.setText(text);
    }
    
    @Override
    public boolean isCancelled(){return cancelled;}
    
    @Override
    public void makeVisible(){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public void finishProgress(){
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        progressBar = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Convirtiendo...");
        setMinimumSize(new java.awt.Dimension(400, 123));
        setPreferredSize(new java.awt.Dimension(400, 123));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        progressBar.setMaximumSize(new java.awt.Dimension(32767, 16));
        progressBar.setMinimumSize(new java.awt.Dimension(146, 16));
        progressBar.setPreferredSize(new java.awt.Dimension(146, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 32, 4, 32);
        getContentPane().add(progressBar, gridBagConstraints);

        statusLabel.setText("Iniciando...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 32, 4, 32);
        getContentPane().add(statusLabel, gridBagConstraints);

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 32, 4, 32);
        getContentPane().add(cancelButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancelled = true;
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
