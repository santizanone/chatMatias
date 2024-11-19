/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.chatmatiascliente;

import com.formdev.flatlaf.FlatLightLaf;
import crud.controller.Controller;
import crud.repository.ContactDao;
import crud.repository.IContactDao;
import crud.repository.IUserDao;
import crud.repository.UserDao;
import crud.repository.model.Contact;
import crud.repository.model.UserDto;
import crud.views.ChatBubble;
import crud.views.ChatUi2;

import crud.views.JFrameGridBagLayout;
import crud.views.Login;
import crud.views.Registro;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author losmelli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        IUserDao userDao = new UserDao();
        
        Registro registro = new Registro();
        Login login = new Login();
        Controller controller = new Controller(userDao,registro,login);
        login.setController(controller);
        registro.setController(controller);


       login.setVisible(true);
      
       
         

        // new JFrameGridBagLayout().setVisible(true);
        
    }

}
