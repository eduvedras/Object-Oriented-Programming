package woo;

public class Container extends Box{
    private ServiceLevel _serviceLevel;
    private boolean _errorServiceLevel;

    /**
    * @param keyProd,keySupp,price,valCrit,serviceType,serviceLevel
    */
    public Container(String keyProd, String keySupp, int price, int valCrit,String serviceType, String serviceLevel){
        super(keyProd, keySupp, price, valCrit, serviceType);

        super.setN(8);

        if(serviceLevel.equals("B4")){
            _serviceLevel = ServiceLevel.B4;
            _errorServiceLevel = false;
        }
        else if(serviceLevel.equals("C4")){
            _serviceLevel = ServiceLevel.C4;
            _errorServiceLevel = false;
        }
        else if(serviceLevel.equals("C5")){
            _serviceLevel = ServiceLevel.C5;
            _errorServiceLevel = false;
        }
        else if(serviceLevel.equals("DL")){
            _serviceLevel = ServiceLevel.DL;
            _errorServiceLevel = false;
        }
        else{
            _serviceLevel = ServiceLevel.ERRO;
            _errorServiceLevel = true;
        }
    }

    /**
    * @param keyProd,keySupp,price,valCrit,serviceType,serviceLevel,stock
    */
    public Container(String keyProd, String keySupp, int price, int valCrit,String serviceType, String serviceLevel,int stock){
        this(keyProd, keySupp, price, valCrit, serviceType, serviceLevel);
        super.setStock(stock);
    }

    
    public enum ServiceLevel{
        B4,
        C4,
        C5,
        DL,
        ERRO;
    }

    /**
    * @return serviceLevel of the container
    */
    public ServiceLevel getServiceLevel(){
        return _serviceLevel;
    }

    public boolean getErrorServiceLevel(){
        return _errorServiceLevel;
    }

    @Override
    @SuppressWarnings("nls")
    /**
    * @return string format of the container
    */
    public String toString(){
        return "CONTAINER" + super.toStringc() + this._serviceLevel;
    }
}