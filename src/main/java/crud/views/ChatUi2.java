/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.views;

import com.formdev.flatlaf.FlatLightLaf;
import crud.controller.Controller;
import crud.repository.model.Contact;
import crud.repository.model.HandShake;
import crud.repository.model.Message;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author losmelli
 */
public class ChatUi2 extends javax.swing.JFrame {

    /**
     * Creates new form ChatUi2
     */
    private Controller controller;
    private Socket socket;
    private String username;
    private ObjectOutputStream outputStream;
    private List<Message> receivedMessages;
    private ContactUI currentOpenedChat;
    HashMap<String, ArrayList<Message>> messagesStorage = new HashMap<String, ArrayList<Message>>();

    public ChatUi2(Controller controller, String username) {
        this.controller = controller;
        this.username = username;
        this.currentOpenedChat = null;
        this.receivedMessages = new ArrayList<>();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
                showUsers();
                setVisible(true);
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
            controller.saveContactLocally(new Contact(msg.getOriginClientName()));
        } else {
            for (Contact contact : contacts) {
                if (contact.getUsername().equals(msg.getOriginClientName())) {
                    contactExisting = true;
                    break;
                }
            }
            if (contactExisting == false) {
                controller.saveContactLocally(new Contact(msg.getOriginClientName()));
            }

        }
        updateMessages();

    }

    private synchronized void updateMessages() {
        if (currentOpenedChat != null) {
            receivedArea.setText("");
            sendedArea.setText("");
            System.out.println("current opened chat " + currentOpenedChat.getContactName());
            for (Message message : receivedMessages) {
                if (message.getOriginClientName().equals(currentOpenedChat.getContactName())) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("entrando");
                            receivedArea.append("\n" + message.getOriginClientName() + ": " + message.getMessageForDestination());
                            System.out.println(message.getOriginClientName() + ": " + message.getMessageForDestination());
                            //receivedMessages.remove(message);
                        }
                    });
                }
                else if (message.getDestinationClientName().equals(currentOpenedChat.getContactName())) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("entrando");
                            sendedArea.append("\n" + message.getMessageForDestination());
                            System.out.println(message.getOriginClientName() + ": " + message.getMessageForDestination());
                            //receivedMessages.remove(message);
                        }
                    });
                }
            }
        }
    }
    
    

    private void showUsers() {
        contactUiPanel.setLayout(new BoxLayout(contactUiPanel, BoxLayout.Y_AXIS));
        for (Contact c : controller.getLocalContacts()) {
            addNewContactToPanel(c);
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
        jScrollPane5 = new javax.swing.JScrollPane();
        receivedArea = new javax.swing.JTextArea();
        jPanelSended = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sendedArea = new javax.swing.JTextArea();
        jPanelTextAreaSendButton = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaSendMsg = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jFieldSearchUser = new javax.swing.JTextField();
        jButtonAddUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jScrollPane1.setDoubleBuffered(true);

        javax.swing.GroupLayout contactUiPanelLayout = new javax.swing.GroupLayout(contactUiPanel);
        contactUiPanel.setLayout(contactUiPanelLayout);
        contactUiPanelLayout.setHorizontalGroup(
            contactUiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        contactUiPanelLayout.setVerticalGroup(
            contactUiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(contactUiPanel);

        jPanelChat.setLayout(new java.awt.GridLayout(1, 2, 0, 1));

        receivedArea.setColumns(20);
        receivedArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        receivedArea.setForeground(new java.awt.Color(255, 102, 102));
        receivedArea.setRows(5);
        jScrollPane5.setViewportView(receivedArea);

        javax.swing.GroupLayout jPanelReceivedLayout = new javax.swing.GroupLayout(jPanelReceived);
        jPanelReceived.setLayout(jPanelReceivedLayout);
        jPanelReceivedLayout.setHorizontalGroup(
            jPanelReceivedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReceivedLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelReceivedLayout.setVerticalGroup(
            jPanelReceivedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReceivedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelChat.add(jPanelReceived);

        sendedArea.setColumns(20);
        sendedArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sendedArea.setForeground(new java.awt.Color(0, 0, 255));
        sendedArea.setRows(5);
        jScrollPane4.setViewportView(sendedArea);

        javax.swing.GroupLayout jPanelSendedLayout = new javax.swing.GroupLayout(jPanelSended);
        jPanelSended.setLayout(jPanelSendedLayout);
        jPanelSendedLayout.setHorizontalGroup(
            jPanelSendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSendedLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelSendedLayout.setVerticalGroup(
            jPanelSendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSendedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelChat.add(jPanelSended);

        jPanelTextAreaSendButton.setPreferredSize(new java.awt.Dimension(790, 100));
        jPanelTextAreaSendButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 5));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(405, 70));

        jTextAreaSendMsg.setColumns(20);
        jTextAreaSendMsg.setRows(5);
        jTextAreaSendMsg.setPreferredSize(new java.awt.Dimension(232, 44));
        jScrollPane3.setViewportView(jTextAreaSendMsg);

        jPanelTextAreaSendButton.add(jScrollPane3);

        jButton1.setText("Send");
        jButton1.setPreferredSize(new java.awt.Dimension(75, 45));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelTextAreaSendButton.add(jButton1);

        jButtonAddUser.setText("Add");
        jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelJframeLayout = new javax.swing.GroupLayout(jPanelJframe);
        jPanelJframe.setLayout(jPanelJframeLayout);
        jPanelJframeLayout.setHorizontalGroup(
            jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJframeLayout.createSequentialGroup()
                .addGroup(jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelJframeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jFieldSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddUser)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTextAreaSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelJframeLayout.setVerticalGroup(
            jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJframeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelJframeLayout.createSequentialGroup()
                        .addGroup(jPanelJframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFieldSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAddUser))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelJframeLayout.createSequentialGroup()
                        .addComponent(jPanelChat, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTextAreaSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
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

    private void jButtonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUserActionPerformed
        Contact contact = new Contact(jFieldSearchUser.getText());
        controller.saveContactLocally(contact);
    }//GEN-LAST:event_jButtonAddUserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTextAreaSendMsg.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite Mensaje");
                return;
            }
            System.out.println("enviando " + jTextAreaSendMsg.getText());
            Message message = new Message(username, currentOpenedChat.getContactName(), jTextAreaSendMsg.getText());

            receivedMessages.add(message);

            outputStream.writeObject(message);
            outputStream.flush();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    sendedArea.append("\n" + jTextAreaSendMsg.getText());
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void addNewContactToPanel(Contact contact) {
        ContactUI contactUI = new ContactUI(contact);
        contactUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
                ContactUI c = (ContactUI) e.getSource();

                if (currentOpenedChat != c) {
                    if (currentOpenedChat != null) {
                        currentOpenedChat.setBackground(Color.WHITE);
                    }
                    c.setBackground(Color.RED);
                    currentOpenedChat = c;
                    sendedArea.setText("");
                    receivedArea.setText("");
                    updateMessages();
                }

                System.out.println(currentOpenedChat.getContactName());

                //showReceivingMessages();
            }
        });
        contactUiPanel.add(contactUI);
        contactUiPanel.revalidate();
        contactUiPanel.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contactUiPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddUser;
    private javax.swing.JTextField jFieldSearchUser;
    private javax.swing.JPanel jPanelChat;
    private javax.swing.JPanel jPanelJframe;
    private javax.swing.JPanel jPanelReceived;
    private javax.swing.JPanel jPanelSended;
    private javax.swing.JPanel jPanelTextAreaSendButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextAreaSendMsg;
    private javax.swing.JTextArea receivedArea;
    private javax.swing.JTextArea sendedArea;
    // End of variables declaration//GEN-END:variables
}
