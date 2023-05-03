package woo;

import java.io.Serializable;

public abstract class ClientStatus implements Serializable{
    private Client _client;

    public ClientStatus(Client client){
        _client = client;
    }

    public Client getClient(){
        return _client;
    }

    public void verifyStatus(){}
}