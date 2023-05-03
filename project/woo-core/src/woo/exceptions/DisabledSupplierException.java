package woo.exceptions;

public class DisabledSupplierException extends Exception{
    private static final long serialVersionUID = 202009200054L;
    
    private String _keySupp;

    /**
    * @param keySupp
    */
    public DisabledSupplierException(String keySupp){
        _keySupp = keySupp;
    }

    /**
    * @return the key of the supplier
    */
    public String getKeySupp(){
        return _keySupp;
    }
}