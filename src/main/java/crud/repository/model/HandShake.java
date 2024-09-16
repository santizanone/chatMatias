package crud.repository.model;

import java.io.Serializable;

public class HandShake implements Serializable {
    private static final long serialVersionUID = 1234L;

    private String clientName;

    public HandShake(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }
}
