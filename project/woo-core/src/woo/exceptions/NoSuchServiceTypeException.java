package woo.exceptions;

public class NoSuchServiceTypeException extends Exception{
    private static final long serialVersionUID = 202009192335L;
    
    private String _serviceType;

    /**
    * @param serviceType
    */
    public NoSuchServiceTypeException(String serviceType){
        _serviceType = serviceType;
    }

    /**
    * @return the serviceType
    */
    public String getServiceType(){
        return _serviceType;
    }
}