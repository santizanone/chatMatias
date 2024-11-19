/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import crud.controller.Controller;
import crud.repository.MessageDao;
import crud.repository.UserDao;
import crud.repository.model.Contact;
import crud.repository.model.HandShake;
import crud.repository.model.Message;
import crud.repository.model.MessageDB;
import crud.repository.model.UserDto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author losmelli
 */
public class JFrameGridBagLayout extends javax.swing.JFrame {

    private Controller controller;
    private Socket socket;
    private String username;
    private ObjectOutputStream outputStream;
    private List<Message> receivedMessages;
    private ContactUi2 currentOpenedChat;
    private UserDto user;

    public JFrameGridBagLayout(Controller controller, UserDto user) {
        this.controller = controller;
        this.username = user.getUsername();
        this.user = user;
        this.currentOpenedChat = null;
        this.receivedMessages = new ArrayList<>();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
                showUsers();
                añadirOpcionesAMenu();
                setVisible(true);

                jScrollPane6.setOpaque(false);
                jScrollPane6.getViewport().setOpaque(false);
                jPanel2.setOpaque(false);
                jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
                jScrollPane6.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
            }
        });

        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            establishConnection();
            readMessages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void establishConnection() {
        try {
            HandShake handShake = new HandShake(username);
            outputStream.writeObject(handShake);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void readMessages() throws UnknownHostException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream inputStream = null;
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    try {
                        Message message = (Message) inputStream.readObject();
                        System.out.println("recibiendo");
                        //receivedMessages.add(message);
                        showReceivingMessages(message);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    private void showReceivingMessages(Message msg) {
        boolean contactExisting = false;
        receivedMessages.add(msg);
        List<Contact> contacts = controller.getLocalContacts();
        if (contacts.size() == 0) {
            controller.saveContactLocally(new Contact(msg.getOriginClientName(), -1));
        } else {
            for (Contact contact : contacts) {
                if (contact.getUsername().equals(msg.getOriginClientName())) {
                    contactExisting = true;
                    break;
                }
            }
            if (contactExisting == false) {
                controller.saveContactLocally(new Contact(msg.getOriginClientName(), -1));
            }

        }
        updateMessages();

    }

    public void showMessagesDb(List<MessageDB> messagesDb) {
        jPanel2.removeAll();

        for (MessageDB msg : messagesDb) {
            System.out.println(msg.getMessage());
            if (msg.getSender_id() == user.getId()) {
                
                    if(msg.getPhoto() == 1){
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                displayPhotoRight(msg.getMessage());
                                
                            }
                        });                        
                        continue;
                    }
                
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JPanel messageWrapper = new JPanel();
                        messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.X_AXIS));
                        messageWrapper.setOpaque(false); // Transparent wrapper

                        // Create the text pane for the message
                        JTextPane textPane = new JTextPane();
                        textPane.setText(msg.getMessage());
                        textPane.setEditable(false);
                        textPane.setOpaque(false); // Transparent background
                        textPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

                        // Add styled alignment
                        SimpleAttributeSet attributes = new SimpleAttributeSet();
                        //
                      
                        

                        //
                        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
                        StyleConstants.setForeground(attributes, Color.BLUE); // Optional: Add color
                        messageWrapper.add(Box.createHorizontalGlue()); // Push to the right

                        textPane.setParagraphAttributes(attributes, true);

                        // Add the text pane to the wrapper
                        messageWrapper.add(textPane);

                        // Add the wrapper to the main panel
                        jPanel2.add(messageWrapper);
                        jPanel2.add(Box.createVerticalStrut(15)); // Add spacing between messages
                    }
                });
            } else {
                
                    if(msg.getPhoto() == 1){
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                 displayPhotoLeft(msg.getMessage());
                                  
                            }
                        });
                       
                        continue;
                    }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JPanel messageWrapper = new JPanel();
                        messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.X_AXIS));
                        messageWrapper.setOpaque(false); // Transparent wrapper

                        // Create the text pane for the message
                        JTextPane textPane = new JTextPane();
                        textPane.setText(msg.getMessage());
                        textPane.setEditable(false);
                        textPane.setOpaque(false); // Transparent background
                        textPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

                        // Add styled alignment
                        SimpleAttributeSet attributes = new SimpleAttributeSet();

                       
                        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
                        StyleConstants.setForeground(attributes, Color.BLACK); // Optional: Add color
                        messageWrapper.add(Box.createHorizontalGlue()); // Push to the right

                        textPane.setParagraphAttributes(attributes, true);

                        // Add the text pane to the wrapper
                        messageWrapper.add(textPane);

                        // Add the wrapper to the main panel
                        jPanel2.add(messageWrapper);
                        jPanel2.add(Box.createVerticalStrut(15)); // Add spacing between messages
                    }
                });
            }
        }
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void displayPhotoRight(String photoPath){
                    
// Load and scale the image
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoPath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel messageLabel = new JLabel(imageIcon);

// Create a wrapper panel for alignment
            JPanel wrapperPanel = new JPanel();
            wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.X_AXIS));
            wrapperPanel.setOpaque(false); // Make the wrapper transparent

// Add a horizontal glue to push the label to the right
            wrapperPanel.add(Box.createHorizontalGlue());
            wrapperPanel.add(messageLabel);

// Add the wrapper panel to the main panel
            jPanel2.add(wrapperPanel);

// Add vertical spacing between messages
            jPanel2.add(Box.createVerticalStrut(15));

// Refresh the panel
            jPanel2.revalidate();
            jPanel2.repaint();
    }
    private void displayPhotoLeft(String photoPath){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoPath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel messageLabel = new JLabel(imageIcon);

// Create a wrapper panel for alignment
            JPanel wrapperPanel = new JPanel();
            wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.X_AXIS));
            wrapperPanel.setOpaque(false); // Make the wrapper transparent

// Add the label directly without horizontal glue
            wrapperPanel.add(messageLabel);
            wrapperPanel.add(Box.createHorizontalGlue()); // Optional: Push other content right if needed

// Add the wrapper panel to the main panel
            jPanel2.add(wrapperPanel);

// Add vertical spacing between messages
            jPanel2.add(Box.createVerticalStrut(15));

// Refresh the panel
            jPanel2.revalidate();
            jPanel2.repaint();
    }
    
    private synchronized void updateMessages() {

        if (currentOpenedChat != null) {

            for (Message message : receivedMessages) {
                System.out.println("--- " + message.getMessageForDestination());
                if (message.getOriginClientName().equals(currentOpenedChat.getContactName())) {
                    
                    if(message.getPhoto() == true){
                       SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run() {
                               displayPhotoLeft(message.getMessageForDestination());
                                receivedMessages.remove(message);
                           }
                       });  
                        continue;
                    }
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JPanel messageWrapper = new JPanel();
                            messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.X_AXIS));
                            messageWrapper.setOpaque(false); // Transparent wrapper

                            // Create the text pane for the message
                            JTextPane textPane = new JTextPane();
                            textPane.setText(message.getMessageForDestination());
                            textPane.setEditable(false);
                            textPane.setOpaque(false); // Transparent background
                            textPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

                            // Add styled alignment
                            SimpleAttributeSet attributes = new SimpleAttributeSet();

                            StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
                            StyleConstants.setForeground(attributes, Color.BLACK); // Optional: Add color
                            messageWrapper.add(Box.createHorizontalGlue()); // Push to the right

                            textPane.setParagraphAttributes(attributes, true);

                            // Add the text pane to the wrapper
                            messageWrapper.add(textPane);

                            // Add the wrapper to the main panel
                            jPanel2.add(messageWrapper);
                            jPanel2.add(Box.createVerticalStrut(15)); // Add spacing between messages
                            receivedMessages.remove(message);
                        }
                    });
                } else if (message.getDestinationClientName().equals(currentOpenedChat.getContactName())) {
                    if(message.getPhoto() == true){
                       SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run() {
                               displayPhotoRight(message.getMessageForDestination());
                                receivedMessages.remove(message);
                           }
                       }); 
                        continue;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JPanel messageWrapper = new JPanel();
                            messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.X_AXIS));
                            messageWrapper.setOpaque(false); // Transparent wrapper

                            // Create the text pane for the message
                            JTextPane textPane = new JTextPane();
                            textPane.setText(message.getMessageForDestination());
                            textPane.setEditable(false);
                            textPane.setOpaque(false); // Transparent background
                            textPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

                            // Add styled alignment
                            SimpleAttributeSet attributes = new SimpleAttributeSet();

                            StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
                            StyleConstants.setForeground(attributes, Color.BLUE); // Optional: Add color
                            messageWrapper.add(Box.createHorizontalGlue()); // Push to the right

                            textPane.setParagraphAttributes(attributes, true);

                            // Add the text pane to the wrapper
                            messageWrapper.add(textPane);

                            // Add the wrapper to the main panel
                            jPanel2.add(messageWrapper);
                            jPanel2.add(Box.createVerticalStrut(15)); // Add spacing between messages
                            receivedMessages.remove(message);
                        }
                    });
                }
            }
            jPanel2.revalidate();
            jPanel2.repaint();
        }
    }

    private void showUsers() {
        YellowPanelContact.add(Box.createVerticalStrut(10)); // Adjust the height (10) for desired spacing
        for (Contact c : controller.getLocalContacts()) {
            addNewContactToPanel(c);
        }
        jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

    }
    
    public void updateNameChat(String name){
        labelNombre.setText(name);
    }

    public void addNewContactToPanel(Contact contact) {
        ContactUi2 contactUI = new ContactUi2(contact);
        contactUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("click");
                ContactUi2 contactSelected = (ContactUi2) e.getSource();

                if (currentOpenedChat != contactSelected) {
                    if (currentOpenedChat != null) {
                        currentOpenedChat.setBackground(new Color(244, 232, 232));
                    }                                                   
                    contactSelected.setBackground(Color.RED);
                  //  
                    currentOpenedChat = contactSelected;
                    jPanel2.removeAll();
                    //updateMessages(contactSelected.getContact());
                    controller.retrieveMessagesFromConversation(user.getId(), contactSelected.getContact().getId(),contactSelected.getContactName());
                   
                    //updateMessages();
                }

                // System.out.println(currentOpenedChat.getContactName());
                //showReceivingMessages();
                
               
                
            }
        });
        YellowPanelContact.add(contactUI);
        YellowPanelContact.revalidate();
        YellowPanelContact.repaint();
        
    }

    private void añadirOpcionesAMenu() {
        JMenuItem itemAñadirContacto = new JMenuItem("Añadir Contacto");
        JMenuItem itemCambiarPerfil = new JMenuItem("Cambiar Foto de perfil");
        JMenuItem itemCambiarContraseña = new JMenuItem("Cambiar Contraseña");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesion");

        itemAñadirContacto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemAñadirContactoActionPerformed();
            }
        });

        itemCambiarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemCambiarPerfil();
            }
        });

        itemCambiarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemCambiarContraseñaActionPerformed();
            }
        });

        itemCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemCerrarSesioneActionPerformed();
            }
        });

        jMenu.add(itemAñadirContacto);
        jMenu.add(itemCambiarPerfil);
        jMenu.add(itemCambiarContraseña);
        jMenu.add(itemCerrarSesion);

    }

    private void ItemAñadirContactoActionPerformed() {
        String nombre = JOptionPane.showInputDialog(null, "ingrese el nombre del contacto");
        Contact contact = new Contact(nombre, -1);
        controller.saveContactLocally(contact);
    }

      private void ItemCambiarPerfil() {
        JFileChooser fc = new JFileChooser();
         int returnVal = fc.showOpenDialog(this);
         if(returnVal == JFileChooser.APPROVE_OPTION){
             File file = fc.getSelectedFile();
             String photoPath = file.getAbsolutePath();
             new UserDao().changeProfilePic(user.getUsername(), photoPath);
             JOptionPane.showMessageDialog(null, "cambio realizado perfectamente");
         } 
        
    }

    private void ItemCambiarContraseñaActionPerformed() {
        JPasswordField pf = new JPasswordField();
        String nuevaContraseña = "";
        int valor = JOptionPane.showConfirmDialog(null, pf, "Ingrese la nueva contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (valor == JOptionPane.OK_OPTION) {
            nuevaContraseña = new String(pf.getPassword());
        }
        if (valor == JOptionPane.CANCEL_OPTION) {
            return;
        }
        if (nuevaContraseña.equals("")) {
            JOptionPane.showMessageDialog(null, "Complete el Campo");
            return;
        }
        controller.ChangePassword(username, nuevaContraseña);
    }

    private void ItemCerrarSesioneActionPerformed() {
        dispose();
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
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
        labelNombre = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanelChat = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatPanel2 = new crud.views.ChatPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel2Red = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSendMsg = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();

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

        labelNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(51, 51, 51));
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelName.add(labelNombre);
        jPanelName.add(filler4);

        jPanel1Blue.add(jPanelName);

        jPanelChat.setPreferredSize(new java.awt.Dimension(495, 500));
        jPanelChat.setLayout(new javax.swing.BoxLayout(jPanelChat, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setOpaque(false);

        chatPanel2.setLayout(new javax.swing.BoxLayout(chatPanel2, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane6.setViewportView(jPanel2);

        chatPanel2.add(jScrollPane6);

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

        jTextAreaSendMsg.setColumns(20);
        jTextAreaSendMsg.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextAreaSendMsg.setLineWrap(true);
        jTextAreaSendMsg.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSendMsg);

        jPanel2Red.add(jScrollPane2);

        sendButton.setBackground(new java.awt.Color(187, 197, 203));
        sendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sendBlack.png"))); // NOI18N
        sendButton.setBorder(null);
        sendButton.setBorderPainted(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        jPanel2Red.add(sendButton);

        greenPanel.add(jPanel2Red);

        Panel.add(greenPanel);

        getContentPane().add(Panel);

        jMenu.setText("Opciones");
        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // FUNCIONA
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String photoPath = file.getAbsolutePath();

// Load and scale the image
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoPath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            JLabel messageLabel = new JLabel(imageIcon);

// Create a wrapper panel for alignment
            JPanel wrapperPanel = new JPanel();
            wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.X_AXIS));
            wrapperPanel.setOpaque(false); // Make the wrapper transparent

// Add a horizontal glue to push the label to the right
            wrapperPanel.add(Box.createHorizontalGlue());
            wrapperPanel.add(messageLabel);

// Add the wrapper panel to the main panel
            jPanel2.add(wrapperPanel);

// Add vertical spacing between messages
            jPanel2.add(Box.createVerticalStrut(15));

// Refresh the panel
            jPanel2.revalidate();
            jPanel2.repaint();
            
            
            
            Message message = new Message(username, currentOpenedChat.getContactName(), photoPath, true);
           // receivedMessages.add(message);
            
            MessageDao dao = new MessageDao();
            dao.SaveImage(photoPath);
            controller.saveMessage(user.getId(),currentOpenedChat.getContact().getId(),photoPath,1);

            try {
                outputStream.writeObject(message);
                outputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(JFrameGridBagLayout.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
            if (jTextAreaSendMsg.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite Mensaje");
                return;
            }
            System.out.println("enviando " + jTextAreaSendMsg.getText());
            Message message = new Message(username, currentOpenedChat.getContactName(), jTextAreaSendMsg.getText(), false);

            //receivedMessages.add(message);

            outputStream.writeObject(message);
            outputStream.flush();

            // guardar mensaje db
            controller.saveMessage(user.getId(), currentOpenedChat.getContact().getId(), jTextAreaSendMsg.getText(),0);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    /*
                    JTextArea area = new JTextArea(message.getMessageForDestination());
                    area.setAlignmentX(RIGHT_ALIGNMENT);
                    
                   area.setOpaque(false);
                    jPanel2.add(area,RIGHT_ALIGNMENT);
                 

                    jPanel2.add(Box.createVerticalStrut(15));*/
                    JPanel messageWrapper = new JPanel();
                    messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.X_AXIS));
                    messageWrapper.setOpaque(false); // Transparent wrapper

                    // Create the text pane for the message
                    JTextPane textPane = new JTextPane();
                    textPane.setText(message.getMessageForDestination());
                    textPane.setEditable(false);
                    textPane.setOpaque(false); // Transparent background
                    textPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

                    // Add styled alignment
                    SimpleAttributeSet attributes = new SimpleAttributeSet();

                    StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
                    StyleConstants.setForeground(attributes, Color.BLUE); // Optional: Add color
                    messageWrapper.add(Box.createHorizontalGlue()); // Push to the right

                    textPane.setParagraphAttributes(attributes, true);

                    // Add the text pane to the wrapper
                    messageWrapper.add(textPane);

                    // Add the wrapper to the main panel
                    jPanel2.add(messageWrapper);
                    jPanel2.add(Box.createVerticalStrut(15)); // Add spacing between messages

                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_sendButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel YellowPanelContact;
    private crud.views.ChatPanel chatPanel1;
    private crud.views.ChatPanel chatPanel2;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JPanel greenPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel1Blue;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2Red;
    private javax.swing.JPanel jPanelChat;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextAreaSendMsg;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
