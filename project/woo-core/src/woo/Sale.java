package woo;

public class Sale extends Transaction{
    private String _keyClient;
    private int _dateLim;
    private String _keyProd;
    private int _quantity;
    private int _actualValue=-1;

    public Sale(String keyClient,int dateLim, String keyProd, int quantity){
        _keyClient = keyClient;
        _dateLim = dateLim;
        _keyProd = keyProd;
        _quantity = quantity;
    }

    public String getKeyClient(){
        return _keyClient;
    }

    public int getDateLim(){
        return _dateLim;
    }

    public String getKeyProd(){
        return _keyProd;
    }

    public int getQuantity(){
        return _quantity;
    }

    public int getActualValue(){
        return _actualValue;
    }

    public void setKeyClient(String keyClient){
        _keyClient = keyClient;
    }

    public void setDateLim(int dateLim){
        _dateLim = dateLim;
    }

    public void setKeyProd(String keyProd){
        _keyProd = keyProd;
    }

    public void setQuantity(int quantity){
        _quantity = quantity;
    }

    public void setActualValue(int actualValue){
        _actualValue = actualValue;
    }

    @Override
    @SuppressWarnings("nls")
    public String toString(){
        String str = String.format("%s|%s|%d|",_keyClient,_keyProd,_quantity);
        return super.toString() + str;
    }
}