package crud.repository.model;


import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 12345L;

    private String originClientName;
    private String destinationClientName;
    private String messageForDestination;

    public Message(String originClientName,String destinationClientName, String messageForDestination) {
        this.destinationClientName = destinationClientName;
        this.messageForDestination = messageForDestination;
        this.originClientName = originClientName;
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