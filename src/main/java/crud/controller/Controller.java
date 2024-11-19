package crud.controller;

import crud.repository.ContactDao;
import crud.repository.IContactDao;
import crud.repository.IUserDao;
import crud.repository.MessageDao;
import crud.repository.model.Contact;
import crud.repository.model.MessageDB;
import crud.repository.model.UserDto;
import crud.views.ChatUi2;
import crud.views.JFrameGridBagLayout;
import crud.views.Login;
import crud.views.Registro;
import java.util.List;

import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private IUserDao userDao;
    private Registro registro;
    private IContactDao contactDao;
    private JFrameGridBagLayout chatUI ;

    private Login login;

    public Controller(IUserDao dao, Registro registro, Login login) {
        this.userDao = dao;      
        this.registro = registro;
        this.login = login;
    }

    public void registerUser(UserDto userDto) {
        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (userDao.retrieveUser(userDto.getUsername()) != null) {
                    return false;
                }
                userDao.registerUser(userDto);
                return true;
            }

            @Override
            protected void done() {
                try {
                    if (get() == false) {
                        registro.showMsg("User Already Exists");
                        return;
                    }
                    registro.showMsg("User Registered Correctly");
                    registro.cleanUp();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
    }

    public void login(UserDto userDto) {

        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                UserDto userDtoDB = userDao.retrieveUser(userDto.getUsername());
                if (userDtoDB == null) { // if user does not exist
                    return false;
                }
                if (userDtoDB.getPassword().equals(userDto.getPassword())) {
                    userDto.setId(userDtoDB.getId());
                    return true;
                }
                return false;
            }

            @Override
            protected void done() {
                try {
                    if (get() == false) {
                        login.showMsg("Usermame or Password invalid");
                        return;
                    }
                    login.closeWindow();
                    contactDao = new ContactDao(userDto.getUsername());
                    //chatUI = new ChatUi2(Controller.this,userDto);
                    JFrameGridBagLayout f = new JFrameGridBagLayout(Controller.this, userDto);
                    f.setVisible(true);
                    chatUI = f;
                    
                    System.out.println(chatUI);
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
    }
    
    public void ChangeMail(String oldName,String newName){
        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (userDao.retrieveUser(newName) != null) {
                    return false;
                }               
                return true;
            }
            @Override
            protected void done() {
                try {
                    if (get() == false) {
                        chatUI.showMessage("User Already Exists");
                        return;
                    }
                    userDao.changeProfilePic(oldName, newName);
                    chatUI.showMessage("nombre cambiado correctamente");
                    
                                       
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
    }
    
    
    
    
     public void ChangePassword(String username,String newPassword){
        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {                          
                return true;
            }
            @Override
            protected void done() {                           
                    userDao.changePassword(username, newPassword);                                                                          
                    chatUI.showMessage("contraseña cambiada correctamente");
            }
        };
        worker.execute();
    }

    public void showRegisterView() {
        registro.startUI();

    }

    public List<Contact> getLocalContacts() {
        return contactDao.getContacts();
    }
    
    
    public void retrieveMessagesFromConversation(int user1Id,int user2Id,String name){
        MessageDao dao = new MessageDao();
        SwingWorker worker = new SwingWorker <List<MessageDB> , Void>() {
            @Override
            protected List<MessageDB> doInBackground() throws Exception {    
                int conversationId = dao.retrieveConversationId(user1Id, user2Id);
                if ( conversationId != -1 ){
                    return dao.retrieveMessages(conversationId);
                }
                return null;
            }
            @Override
            protected void done() {                           
                try {
                    List<MessageDB> messages = get();
                    if(messages != null) {
                        for(MessageDB m : messages){
                            System.out.println(m);
                        }
                        chatUI.showMessagesDb(messages);
                        System.out.println(name);
                        chatUI.updateNameChat(name);
                    }else{
                        chatUI.updateNameChat(name);
                        System.out.println("no conversation");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        worker.execute();      
    }
    
    public void saveMessage(int senderUserId,int receiverUserId,String message,int photo){
        MessageDao dao = new MessageDao();
        int conversationId = dao.retrieveConversationId(senderUserId, receiverUserId);
        if(conversationId == -1){
            dao.SaveConversation(senderUserId, receiverUserId);
            System.out.println("saving");
        }
        conversationId = dao.retrieveConversationId(senderUserId, receiverUserId);
        System.out.println(conversationId);
        dao.saveMessage(conversationId, message, senderUserId,photo);
    }
    
    
    
    

    public void saveContactLocally(Contact contact) {                    
         SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
               UserDto user = userDao.retrieveUser(contact.getUsername());
               if (contactDao.getContact(contact.getUsername()) == null && user != null) {
                   contact.setId(user.getId());
                   return true;
               }
               return false;
            }
            
            @Override
            protected void done() {
                try {
                    if (get() == true) {
                       contactDao.saveContact(contact);                      
                       chatUI.addNewContactToPanel(contact);
                        System.out.println("saving contact");
                    }
                    else{
                        chatUI.showMessage("Usuario no existente");
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
        
        
    }
    
   

}
