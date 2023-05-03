package woo.exceptions;

public class IncorrectSupplierException extends Exception{
    private static final long serialVersionUID = 202009200054L;
    
    private String _keySupp;
    private String _keyProd;

    /**
    * @param keySupp
    * @param keyProd
    */
    public IncorrectSupplierException(String keySupp, String keyProd){
        _keySupp = keySupp;
        _keyProd = keyProd;
    }

    /**
    * @return the key of the supplier
    */
    public String getKeySupp(){
        return _keySupp;
    }

    /**
    * @return the key of the product
    */
    public String getKeyProd(){
        return _keyProd;
    }
}