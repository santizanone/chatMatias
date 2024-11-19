package crud.repository.model;


import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 12345L;

    private String originClientName;
    private String destinationClientName;
    private String messageForDestination;
    private boolean photo = false;

    public Message(String originClientName,String destinationClientName, String messageForDestination,boolean photo) {
        this.destinationClientName = destinationClientName;
        this.messageForDestination = messageForDestination;
        this.originClientName = originClientName;
        this.photo = photo;
    }
    
    public boolean getPhoto(){
        return photo;
    }

    public String getDestinationClientName() {
        return destinationClientName;
    }

    public String getMessageForDestination() {
        return messageForDestination;
    }

    public String getOriginClientName() {
        return originClientName;
    }
}