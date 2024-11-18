/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import crud.repository.model.Contact;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JViewport;

/**
 *
 * @author losmelli
 */
public class JFrameGridBagLayout extends javax.swing.JFrame {

    /**
     * Creates new form JFrameGridBagLayout
     */
    public JFrameGridBagLayout() {
        initComponents();
        jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        YellowPanelContact.add(Box.createVerticalStrut(10)); // Adjust the height (10) for desired spacing
        for (int i = 0; i < 20; i++) {
            ContactUi2 c2 = new ContactUi2(new Contact("pepe", -1));

            YellowPanelContact.add(c2);
            YellowPanelContact.add(Box.createVerticalStrut(10)); // Adjust the height (10) for desired spacing
            JSeparator separator = new JSeparator();
            separator.setPreferredSize(new Dimension(1, 5)); // Thin horizontal separator
            YellowPanelContact.add(separator);

            YellowPanelContact.add(Box.createVerticalStrut(10)); // Adjust the height (10) for desired spacing

        }
        YellowPanelContact.revalidate();
        YellowPanelContact.repaint();

        /* JTextArea area = new JTextArea("hola como va me llamo santino estoy haceindo este programa de chat estoy cansado tengo suelo quiero dormir ayuda"
                + "hola como va me llamo santino estoy haceindo este programa de chat estoy cansado tengo suelo quiero dormir ayuda"
                + "hola como va me llamo santino estoy haceindo este programa de chat estoy cansado tengo suelo quiero dormir ayuda"
                + "hola como va me llamo santino estoy haceindo este programa de chat estoy cansado --------------------------");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setPreferredSize(new Dimension(300, 100));
        area.setVisible(true);
      jPanel3.add(area);*/
        jPanel2.add(Box.createVerticalStrut(15));
        for (int i = 0; i < 10; i++) {
            ChatBubble b = new ChatBubble();
            jPanel2.add(b);
            jPanel2.revalidate();
            jPanel2.repaint();
            jPanel2.add(Box.createVerticalStrut(15));

        }
        
        
          jPanel4.add(Box.createVerticalStrut(15));
        for (int i = 0; i < 20; i++) {
            ChatBubble b = new ChatBubble();
            b.changecolour(Color.yellow);
            jPanel4.add(b);
            jPanel4.revalidate();
            jPanel4.repaint();
            jPanel4.add(Box.createVerticalStrut(15));

        }

        jScrollPane4.getViewport().setOpaque(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatPanel1 = new crud.views.ChatPanel();
        jLabel1 = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        YellowPanelContact = new javax.swing.JPanel();
        greenPanel = new javax.swing.JPanel();
        jPanel1Blue = new javax.swing.JPanel();
        jPanelName = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 32767));
        jLabel2 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelChat = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatPanel2 = new crud.views.ChatPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2Red = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout chatPanel1Layout = new javax.swing.GroupLayout(chatPanel1);
        chatPanel1.setLayout(chatPanel1Layout);
        chatPanel1Layout.setHorizontalGroup(
            chatPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        chatPanel1Layout.setVerticalGroup(
            chatPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        Panel.setPreferredSize(new java.awt.Dimension(695, 400));
        Panel.setLayout(new javax.swing.BoxLayout(Panel, javax.swing.BoxLayout.X_AXIS));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 400));

        YellowPanelContact.setBackground(new java.awt.Color(244, 232, 232));
        YellowPanelContact.setLayout(new javax.swing.BoxLayout(YellowPanelContact, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(YellowPanelContact);

        Panel.add(jScrollPane1);

        greenPanel.setBackground(new java.awt.Color(0, 153, 153));
        greenPanel.setPreferredSize(new java.awt.Dimension(495, 400));
        greenPanel.setLayout(new javax.swing.BoxLayout(greenPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanel1Blue.setBackground(new java.awt.Color(244, 232, 232));
        jPanel1Blue.setLayout(new javax.swing.BoxLayout(jPanel1Blue, javax.swing.BoxLayout.Y_AXIS));

        jPanelName.setBackground(new java.awt.Color(244, 232, 232));
        jPanelName.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanelName.setPreferredSize(new java.awt.Dimension(495, 70));
        jPanelName.setLayout(new javax.swing.BoxLayout(jPanelName, javax.swing.BoxLayout.LINE_AXIS));
        jPanelName.add(filler2);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Santino");
        jPanelName.add(jLabel2);
        jPanelName.add(filler4);

        jPanel1Blue.add(jPanelName);

        jPanelChat.setPreferredSize(new java.awt.Dimension(495, 500));
        jPanelChat.setLayout(new javax.swing.BoxLayout(jPanelChat, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setOpaque(false);

        chatPanel2.setLayout(new java.awt.GridLayout(0, 2));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane6.setViewportView(jPanel2);

        chatPanel2.add(jScrollPane6);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane7.setViewportView(jPanel4);

        chatPanel2.add(jScrollPane7);

        jScrollPane4.setViewportView(chatPanel2);

        jPanelChat.add(jScrollPane4);

        jPanel1Blue.add(jPanelChat);

        greenPanel.add(jPanel1Blue);

        jPanel2Red.setBackground(new java.awt.Color(187, 197, 203));
        jPanel2Red.setMaximumSize(new java.awt.Dimension(32767, 120));
        jPanel2Red.setPreferredSize(new java.awt.Dimension(495, 100));
        jPanel2Red.setLayout(new javax.swing.BoxLayout(jPanel2Red, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setBackground(new java.awt.Color(187, 197, 203));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addblack.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2Red.add(jButton1);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel2Red.add(jScrollPane2);

        jButton2.setBackground(new java.awt.Color(187, 197, 203));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sendBlack.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jPanel2Red.add(jButton2);

        greenPanel.add(jPanel2Red);

        Panel.add(greenPanel);

        getContentPane().add(Panel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameGridBagLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGridBagLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGridBagLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGridBagLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameGridBagLayout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel YellowPanelContact;
    private crud.views.ChatPanel chatPanel1;
    private crud.views.ChatPanel chatPanel2;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JPanel greenPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1Blue;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2Red;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelChat;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
