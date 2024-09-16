/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.chatmatiascliente;

import crud.controller.Controller;
import crud.repository.IUserDao;
import crud.repository.UserDao;
import crud.views.Login;
import crud.views.Registro;

/**
 *
 * @author losmelli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IUserDao dao = new UserDao();
        Registro registro = new Registro();
        Login login = new Login();
        Controller controller = new Controller(dao,registro,login);
        login.setController(controller);
        registro.setController(controller);


        login.setVisible(true);




    }
    
}
