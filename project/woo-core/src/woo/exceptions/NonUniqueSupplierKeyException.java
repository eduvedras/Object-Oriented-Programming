package woo.exceptions;

public class NonUniqueSupplierKeyException extends Exception{
    private static final long serialVersionUID = 201709021324L;
    
    private String _keySupp;

    /**
    * @param keySupp
    */
    public NonUniqueSupplierKeyException(String keySupp){
        _keySupp = keySupp;
    }

    /**
    * @return the key of the supplier
    */
    public String getKeySupp(){
        return _keySupp;
    }
}