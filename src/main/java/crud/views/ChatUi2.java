/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JViewport;
import javax.swing.UIManager;

/**
 *
 * @author losmelli
 */
public class ChatUi2 extends javax.swing.JFrame {

    /**
     * Creates new form ChatUi2
     */
    public ChatUi2() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        initComponents();
        showUsers();

    }

    private void showUsers() {
        contactUiPanel.setLayout(new BoxLayout(contactUiPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 20; i++) {
            contactUiPanel.add(new ContactUI());
        }
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelJframe = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contactUiPanel = new javax.swing.JPanel();
        jPanelChat = new javax.swing.JPanel();
        jPanelReceived = new javax.swing.JPanel();
        jPanelSended = new javax.swing.JPanel();
        jPanelTextAreaSendButton = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setDoubleBuffered(true);

        javax.swing.GroupLayout contactUiPanelLayout = new javax.swing.GroupLayout(contactUiPanel);
        contactUiPanel.setLayout(contactUiPanelLayout);
        contactUiPanelLayout.setHorizontalGroup(
            contactUiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        contactUiPanelLayout.setVerticalGroup(
            contactUiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(contactUiPanel);

        jPanelChat.setLayout(new java.awt.GridLayout(1, 2, 0, 1));

        javax.swing.GroupLayout jPanelReceivedLayout = new javax.swing.GroupLayout(jPanelReceived);
        jPanelReceived.setLayout(jPanelReceivedLayout);
        jPanelReceivedLayout.setHorizontalGroup(
            jPanelReceivedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );
        jPanelReceivedLayout.setVerticalGroup(
            jPanelReceivedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        jPanelChat.add(jPanelReceived);

        javax.swing.GroupLayout jPanelSendedLayout = new javax.swing.GroupLayout(jPanelSended);
        jPanelSended.setLayout(jPanelSendedLayout);
        jPanelSendedLayout.setHorizontalGroup(
            jPanelSendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );
        jPanelSendedLayout.setVerticalGroup(
            jPanelSendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        jPanelChat.add(jPanelSended);

        jPanelTextAreaSendButton.setPreferredSize(new java.awt.Dimension(790, 100));
        jPanelTextAreaSendButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 5));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(405, 70));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setPreferredSize(new java.awt.Dimension(232, 44));
        jScrollPane3.setViewportView(jTextArea2);

        jPanelTextAreaSendButton.add(jScrollPane3);

        jButton1.setText("Send");
        jButton1.setPreferredSize(new java.awt.Dimension(75, 45));
        jPanelTextAreaSendButton.add(jButton1);

        javax.swing.GroupLayout jPanelJframeLayout = new javax.swing.GroupLayout(jPanelJframe);
        jPanelJframe.setLayout(jPanelJframeLayout);
        jPanelJframeLayout.setHorizontalGroup(
            jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJframeLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jPanelTextAreaSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelJframeLayout.setVerticalGroup(
            jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
            .addGroup(jPanelJframeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelChat, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTextAreaSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelJframe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelJframe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatUi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatUi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatUi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatUi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatUi2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contactUiPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanelChat;
    private javax.swing.JPanel jPanelJframe;
    private javax.swing.JPanel jPanelReceived;
    private javax.swing.JPanel jPanelSended;
    private javax.swing.JPanel jPanelTextAreaSendButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
