package woo.exceptions;

public class NoSuchSupplierKeyException extends Exception{
    private static final long serialVersionUID = 202009200054L;
    
    private String _keySupp;

    /**
    * @param keySupp
    */
    public NoSuchSupplierKeyException(String keySupp){
        _keySupp = keySupp;
    }

    /**
    * @return the key of the supplier
    */
    public String getKeySupp(){
        return _keySupp;
    }
}