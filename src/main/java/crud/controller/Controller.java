package crud.controller;

import crud.repository.ContactDao;
import crud.repository.IContactDao;
import crud.repository.IUserDao;
import crud.repository.model.Contact;
import crud.repository.model.UserDto;
import crud.views.ChatUI;
import crud.views.ChatUi2;
import crud.views.Login;
import crud.views.Registro;
import java.util.List;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class Controller {

    private IUserDao userDao;
    private Registro registro;
    private IContactDao contactDao;
    private ChatUi2 chatUI ;

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
                    chatUI = new ChatUi2(Controller.this,userDto.getUsername());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
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

    public void saveContactLocally(Contact contact) {                    
         SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
               
               if (contactDao.getContact(contact.getUsername()) == null && userDao.retrieveUser(contact.getUsername()) != null) {
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
