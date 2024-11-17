/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository.model;

import javax.swing.ImageIcon;

/**
 *
 * @author losmelli
 */
public class Contact {
    private String username;
    private int id;
    private ImageIcon profileImage;

    public Contact(String username,int id) {
        this.username = username;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getUsername() {
        return username;
    }

    public ImageIcon getProfileImage() {
        return profileImage;
    }
    
    
    
}
