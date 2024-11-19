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
public class MessageDB {
    private int conversationId;
    private String message;
    private int sender_id;
    private String createdAt;
    private int photo ;

    public MessageDB(int conversationId, String message, int sender_id, String createdAt,int photo) {
        this.conversationId = conversationId;
        this.message = message;
        this.sender_id = sender_id;
        this.createdAt = createdAt;
        this.photo = photo;
    }
    
    public int getPhoto(){
        return photo;
    }

    public int getConversationId() {
        return conversationId;
    }

    public String getMessage() {
        return message;
    }

    public int getSender_id() {
        return sender_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "MessageDB{" + "conversationId=" + conversationId + ", message=" + message + ", sender_id=" + sender_id + ", createdAt=" + createdAt + '}';
    }


    
    
}
