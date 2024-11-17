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
                userDto = new UserDto(rs.getString(2), rs.getString(3),rs.getInt(1));
            }
            ps.close();
            rs.close();
            con.close();

            return userDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUsername(String oldName, String newName) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            String query = "update user set username = ? where username = ?";
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, newName);
                ps.setString(2, oldName);
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

    @Override
    public void changePassword(String username, String newPassword) {
         try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            String query = "update user set password = ? where username = ?";
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, newPassword);
                ps.setString(2, username);
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

}
