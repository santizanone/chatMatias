/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 *
 * @author losmelli
 */
public class ContactUI extends javax.swing.JPanel {

    /**
     * Creates new form ChatUI3
     */
    public ContactUI() {
        
        initComponents();
        System.out.println(imageLabel.getWidth());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:/Users/losmelli/Documents/javadeveloper.png").getImage().getScaledInstance(imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);

    }

    private void initComponents2() {
        imageLabel = new JLabel();
        nameLabel = new JLabel();
        jSeparator1 = new JSeparator();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 80)); // Preferable, but allow resizing

        imageLabel.setPreferredSize(new Dimension(75, 55));
        add(imageLabel, BorderLayout.WEST);

        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText("Juan");
        add(nameLabel, BorderLayout.CENTER);
        add(jSeparator1, BorderLayout.SOUTH);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setMaximumSize(new java.awt.Dimension(300, 80));
        setPreferredSize(new java.awt.Dimension(300, 80));
        setLayout(new java.awt.BorderLayout());

        imageLabel.setMaximumSize(new java.awt.Dimension(75, 55));
        imageLabel.setMinimumSize(new java.awt.Dimension(35, 35));
        imageLabel.setPreferredSize(new java.awt.Dimension(75, 55));
        add(imageLabel, java.awt.BorderLayout.WEST);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("Juan");
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nameLabel.setMaximumSize(new java.awt.Dimension(170, 50));
        nameLabel.setMinimumSize(new java.awt.Dimension(39, 50));
        nameLabel.setPreferredSize(new java.awt.Dimension(170, 50));
        add(nameLabel, java.awt.BorderLayout.CENTER);
        add(jSeparator1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}