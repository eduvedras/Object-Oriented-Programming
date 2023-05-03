package woo.exceptions;

public class NoSuchServiceLevelException extends Exception{
    private static final long serialVersionUID = 202009192335L;
    
    private String _serviceLevel;

    /**
    * @param serviceLevel
    */
    public NoSuchServiceLevelException(String serviceLevel){
        _serviceLevel = serviceLevel;
    }

    /**
    * @return the serviceLevel
    */
    public String getServiceLevel(){
        return _serviceLevel;
    }
}