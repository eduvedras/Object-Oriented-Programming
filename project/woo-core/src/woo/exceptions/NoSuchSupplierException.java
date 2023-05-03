package woo.exceptions;

public class NoSuchSupplierException extends Exception{
    private static final long serialVersionUID = 202009200054L;
    
    private String _key_supplier;

    /**
    * @param key_supplier
    */
    public NoSuchSupplierException(String key_supplier){
        _key_supplier = key_supplier;
    }

    /**
    * @return the key of the supplier
    */
    public String getKey_supplier(){
        return _key_supplier;
    }
}