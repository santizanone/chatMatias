/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.UIManager;

/**
 *
 * @author losmelli
 */
public class ChatUi3 extends javax.swing.JFrame {

    /**
     * Creates new form ChatUi3
     */
    public ChatUi3() {
         try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        initComponents2();       
        showUsers();
    }
    private void showUsers() {
        jPanelContact.setLayout(new BoxLayout(jPanelContact, BoxLayout.Y_AXIS));
        for (int i = 0; i < 20; i++) {
         //   jPanelContact.add(new ContactUI());
        }
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

    }
    
    @SuppressWarnings("unchecked")
    private void initComponents2() {

        jPanelMain = new JPanel();
        jPanelChat = new JPanel();
        jScrollPane2 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        jPanelContact = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        jPanelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Chat Panel
        jPanelChat.setLayout(new BorderLayout());
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);
        jPanelChat.add(jScrollPane2, BorderLayout.CENTER);
        jPanelChat.add(jButton1, BorderLayout.SOUTH);

        gbc.gridx = 1; // Column for chat panel
        gbc.weightx = 0.5; // Give it some weight
        jPanelMain.add(jPanelChat, gbc);

        // Contact Panel
        jPanelContact.setLayout(new BoxLayout(jPanelContact, BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanelContact);

        gbc.gridx = 0; // Column for contact panel
        gbc.weightx = 0.5; // Give it some weight
        jPanelMain.add(jScrollPane1, gbc);

        add(jPanelMain, BorderLayout.CENTER);
        pack();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JButton jButton1;
        private javax.swing.JPanel contactUiPanel;

    private JPanel jPanelChat;
    private JPanel jPanelContact;
    private JPanel jPanelMain;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
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
            java.util.logging.Logger.getLogger(ChatUi3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatUi3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatUi3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatUi3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatUi3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
