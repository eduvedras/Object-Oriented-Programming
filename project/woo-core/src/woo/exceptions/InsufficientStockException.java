package woo.exceptions;

public class InsufficientStockException extends Exception{
    private static final long serialVersionUID = 201709021324L;
    
    private String _keyProd;
    private int _quantity;
    private int _available;

    /**
    * @param keyProd
    */
    public InsufficientStockException(String keyProd,int quantity, int available){
        _keyProd = keyProd;
        _quantity = quantity;
        _available = available;
    }

    /**
    * @return the key of the product
    */
    public String getKeyProd(){
        return _keyProd;
    }

    public int getQuantity(){
        return _quantity;
    }

    public int getAvailable(){
        return _available;
    }
}