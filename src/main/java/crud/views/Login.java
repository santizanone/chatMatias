/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import crud.controller.Controller;
import crud.repository.model.UserDto;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author losmelli
 */
public class Login extends javax.swing.JFrame {

    private javax.swing.JButton BotonLogin;
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JLabel EtiquetaNombreUsuario;
    private javax.swing.JLabel EtiquetaRecuperarCuenta;
    private JButton BotonRegistrarse;
    private javax.swing.JLabel LabelContraseña;
    private javax.swing.JTextField NombreUsuario;
    private javax.swing.JLabel etiquetaBienvenida;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel panel;
    private Controller controller;

    public Login() {
        initComponents2();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void initComponents2() {
        setResizable(false);
        jButton2 = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        etiquetaBienvenida = new javax.swing.JLabel();
        EtiquetaNombreUsuario = new javax.swing.JLabel();
        NombreUsuario = new javax.swing.JTextField();
        LabelContraseña = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();
        BotonLogin = new javax.swing.JButton();
        BotonRegistrarse = new javax.swing.JButton();
        EtiquetaRecuperarCuenta = new javax.swing.JLabel();
        EtiquetaRecuperarCuenta.setVisible(false);
        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(242, 240, 240));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiquetaBienvenida.setBackground(new java.awt.Color(153, 204, 255));
        etiquetaBienvenida.setFont(new java.awt.Font("Manjari", 0, 24)); // NOI18N
        etiquetaBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaBienvenida.setText("Bienvenido");
        etiquetaBienvenida.setHorizontalTextPosition(SwingConstants.CENTER);

        EtiquetaNombreUsuario.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        EtiquetaNombreUsuario.setText("Username");

        LabelContraseña.setText("Password");

        BotonLogin.setBackground(new java.awt.Color(204, 204, 204));
        BotonLogin.setText("LOGIN");
        BotonLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLoginActionPerformed(evt);
            }
        });

        BotonRegistrarse.setBackground(new java.awt.Color(204, 204, 204));
        BotonRegistrarse.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        BotonRegistrarse.setText("Registrarse");
        BotonRegistrarse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonRegistrarse.addActionListener(this::BotonRegistrarActionPerformed);

        EtiquetaRecuperarCuenta.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        EtiquetaRecuperarCuenta.setText("Recuperar cuenta");
        EtiquetaRecuperarCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(EtiquetaNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(NombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(LabelContraseña)
                                                        .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(BotonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(BotonRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(etiquetaBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(191, 191, 191))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(EtiquetaRecuperarCuenta)))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(etiquetaBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(EtiquetaNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LabelContraseña)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(BotonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(BotonRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EtiquetaRecuperarCuenta)
                                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>   

    private void BotonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = NombreUsuario.getText();
        String password = new String(Contraseña.getPassword());
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Complete the fields");
            return;
        }
        controller.login(new UserDto(username, password));
    }

    private void BotonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        controller.showRegisterView();
    }

    public void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void closeWindow() {
        this.dispose();      
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
