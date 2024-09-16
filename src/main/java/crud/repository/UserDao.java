/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository;

import crud.repository.model.UserDto;

import java.sql.*;

/**
 * @author losmelli
 */
public class UserDao implements IUserDao {

    private final String URL = "jdbc:mysql://localhost:3306/db_chatMatias?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "12345";

    @Override
    public void registerUser(UserDto user) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Connection con = DriverManager.getConnection(URL, USER, PASS);
                    String query = "insert into user (username,password) values (?,?)";
                    try {
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, user.getUsername());
                        ps.setString(2, user.getPassword());
                        ps.executeUpdate();
                        ps.close();
                        con.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();
    }

    @Override
    public UserDto retrieveUser(String username) {
        ResultSet rs = null;
        String query = "SELECT * from user WHERE username=?";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            UserDto userDto = null;
            while (rs.next()) {
                userDto = new UserDto( rs.getString(2), rs.getString(3));
            }
            ps.close();
            rs.close();
            con.close();

            return userDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
