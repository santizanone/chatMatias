/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository.model;

/**
 *
 * @author losmelli
 */
public class UserDto {
    private String username;

    private String password;
    
    private int id;

    public UserDto(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    
    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }
    
    
    
}
