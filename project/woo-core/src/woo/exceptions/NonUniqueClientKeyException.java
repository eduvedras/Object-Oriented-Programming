package woo.exceptions;

public class NonUniqueClientKeyException extends Exception{
    private static final long serialVersionUID = 201709021324L;
    
    private String _keyClient;

    /**
    * @param keyClient
    */
    public NonUniqueClientKeyException(String keyClient){
        _keyClient = keyClient;
    }

    /**
    * @return the key of the client
    */
    public String getKeyClient(){
        return _keyClient;
    }
}