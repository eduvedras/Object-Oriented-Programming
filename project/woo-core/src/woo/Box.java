package woo;

public class Box extends Product{
    private ServiceType _serviceType;
    private boolean _errorServiceType;

    /**
    * @param keyProd,keySupp,price,valCrit,serviceType
    */
    public Box(String keyProd, String keySupp, int price, int valCrit,String serviceType){
        super(keyProd, keySupp, price, valCrit);
        super.setN(5);
        
        if(serviceType.equals("NORMAL")){
            _serviceType = ServiceType.NORMAL;
            _errorServiceType = false;
        }
        else if(serviceType.equals("AIR")){
            _serviceType = ServiceType.AIR;
            _errorServiceType = false;
        }
        else if(serviceType.equals("EXPRESS")){
            _serviceType = ServiceType.EXPRESS;
            _errorServiceType = false;
        }
        else if(serviceType.equals("PERSONAL")){
            _serviceType = ServiceType.PERSONAL;
            _errorServiceType = false;
        }
        else{
            _serviceType = ServiceType.ERRO;
            _errorServiceType = true;
        }
    }

    /**
    * @param keyProd,keySupp,price,valCrit,serviceType,stock
    */
    public Box(String keyProd, String keySupp, int price, int valCrit,String serviceType,int stock){
        this(keyProd, keySupp, price, valCrit,serviceType);
        super.setStock(stock);
    }

    public enum ServiceType{
        NORMAL,
        AIR,
        EXPRESS,
        PERSONAL,
        ERRO
    }

    /**
    * @return the service level of the box
    */
    public ServiceType getServiceType(){
        return _serviceType;
    }

    public boolean getErrorServiceType(){
        return _errorServiceType;
    }

    /**
    * @return the string format to be used in container
    */
    public String toStringc(){
        return super.toString() + this._serviceType + "|";
    }

    @Override
    @SuppressWarnings("nls")
    /**
    * @return the string format of the box
    */
    public String toString(){
        return "BOX" + super.toString() + this._serviceType;
    }

}