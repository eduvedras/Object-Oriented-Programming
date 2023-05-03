package woo.exceptions;

public class NonUniqueProductKeyException extends Exception{
    private static final long serialVersionUID = 201709021324L;
    
    private String _keyProd;

    /**
    * @param keyProd
    */
    public NonUniqueProductKeyException(String keyProd){
        _keyProd = keyProd;
    }

    /**
    * @return the key of the product
    */
    public String getKeyProd(){
        return _keyProd;
    }
}