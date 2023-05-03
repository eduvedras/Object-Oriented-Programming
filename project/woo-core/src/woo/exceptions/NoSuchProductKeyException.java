package woo.exceptions;

public class NoSuchProductKeyException extends Exception{
    private static final long serialVersionUID = 202009192335L;
    
    private String _keyProd;

    /**
    * @param keyProd
    */
    public NoSuchProductKeyException(String keyProd){
        _keyProd = keyProd;
    }

    /**
    * @return the key of the product
    */
    public String getKeyProd(){
        return _keyProd;
    }
}