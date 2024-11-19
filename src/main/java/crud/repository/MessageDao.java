/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.repository;

import crud.repository.model.MessageDB;
import crud.repository.model.UserDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author losmelli
 */
public class MessageDao {

    private final String URL = "jdbc:mysql://localhost:3306/db_chatMatias?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "12345";

    public void SaveConversation(int user1Id, int user2Id) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            String query = "INSERT INTO conversation (user1_id, user2_id) VALUES (?, ?);";

            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, user1Id);
                ps.setInt(2, user2Id);
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
    
   
    
    
     public void SaveImage(String url) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            String query = "INSERT INTO images (url) VALUES (?);";

            try {
                PreparedStatement ps = con.prepareStatement(query);               
                ps.setString(1, url);
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

    public void saveMessage(int conversationId, String message, int sender_id,int photo) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            String query = "INSERT INTO messages (conversation_id, message, sender_id, created_at,photo) VALUES (?,?,?,?,?)";
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = format.format(date);
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, conversationId);
                ps.setString(2, message);
                ps.setInt(3, sender_id);
                ps.setString(4, currentDateTime);
                ps.setInt(5, photo);
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

    public int retrieveConversationId(int user1Id, int user2Id) {
        ResultSet rs = null;
        String query = "SELECT idconversation \n"
                + "FROM conversation \n"
                + "WHERE (user1_id = ? AND user2_id = ?)\n"
                + "   OR (user1_id = ? AND user2_id = ?);";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user1Id);
            ps.setInt(2, user2Id);
            ps.setInt(3, user2Id);
            ps.setInt(4, user1Id);
            rs = ps.executeQuery();
            UserDto userDto = null;
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
            con.close();

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MessageDB> retrieveMessages(int conversationId) {
        ResultSet rs = null;
        String query = "SELECT idmessages, message, sender_id, created_at,photo \n"
                + "FROM messages \n"
                + "WHERE conversation_id = ?\n"
                + "ORDER BY created_at ASC;";

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, conversationId);
            rs = ps.executeQuery();
            List<MessageDB> messageList = new ArrayList<>();
            while (rs.next()) {
                MessageDB msg  = new MessageDB(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getString(4),rs.getInt(5));
                messageList.add(msg);
            }
            ps.close();
            rs.close();
            con.close();

            return messageList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
