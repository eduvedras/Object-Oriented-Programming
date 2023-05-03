package woo.exceptions;

public class NoSuchClientKeyException extends Exception{
    private static final long serialVersionUID = 202009192335L;
    
    private String _keyClient;

    /**
    * @param keyClient
    */
    public NoSuchClientKeyException(String keyClient){
        _keyClient = keyClient;
    }

    /**
    * @return the key of the client
    */
    public String getKeyClient(){
        return _keyClient;
    }
}