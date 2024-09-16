package crud.controller;

import crud.repository.IUserDao;
import crud.repository.model.UserDto;
import crud.views.ChatUI;
import crud.views.Login;
import crud.views.Registro;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class Controller {
    private IUserDao dao;
    private Registro registro;

    private Login login;

    public Controller(IUserDao dao, Registro registro,Login login) {
        this.dao = dao;
        this.registro = registro;
        this.login = login;
    }

    public void registerUser(UserDto userDto){
        SwingWorker worker = new SwingWorker<Boolean,Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (dao.retrieveUser(userDto.getUsername()) != null){
                    return false;
                }
                dao.registerUser(userDto);
                return true;
            }

            @Override
            protected void done() {
                try {
                    if (get() == false){
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

    public void login(UserDto userDto){

        SwingWorker worker = new SwingWorker<Boolean,Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                UserDto userDtoDB = dao.retrieveUser(userDto.getUsername());
                if (userDtoDB == null){ // if user does not exist
                    return false;
                }
                if (userDtoDB.getPassword().equals(userDto.getPassword())){
                    return true;
                }
                return false;
            }
            @Override
            protected void done() {
                try {
                    if (get() == false){
                        login.showMsg("Usermame or Password invalid");
                        return;
                    }
                    login.closeWindow();
                    ChatUI chatUI = new ChatUI(userDto.getUsername());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
    }

    public void showRegisterView(){
        registro.startUI();

    }

}
