package woo;

import java.io.Serializable;
import java.util.Collections;
import java.util.*;

public abstract class Product implements Serializable,Observable{
    private String _keyProd;
    private String _keySupp;
    private int _price;
    private int _stock = 0;
    private int _valCrit;
    private int _N=0;
    private ArrayList<Observer> _observers = new ArrayList<Observer>(); 

    /**
    * @param keyProd,keySupp,price,valCrit
    */
    public Product(String keyProd, String keySupp, int price, int valCrit){
        _keyProd = keyProd;
        _keySupp = keySupp;
        _price = price;
        _valCrit = valCrit;
    }

    /**
    * @return key of the product
    */
    public String getKeyProd(){
        return _keyProd;
    }

    /**
    * @return key of the supplier
    */
    public String getKeySupp(){
        return _keySupp;
    }

    /**
    * @return price of the product
    */
    public int getPrice(){
        return _price;
    }

    /**
    * @return stock of the product
    */
    public int getStock(){
        return _stock;
    }

    /**
    * @return valCrit of the product
    */
    public int getValCrit(){
        return _valCrit;
    }

    public int getN(){
        return _N;
    }

    public ArrayList<Observer> getObservers(){
        return _observers;
    }

     public void setN(int N){
        _N = N;
    }
    
    /**
    * @param keyProd
    */
    public void setKeyProd(String keyProd){
        _keyProd = keyProd;
    }

    /**
    * @param keySupp
    */
    public void setKeySupp(String keySupp){
        _keySupp = keySupp;
    }

    /**
    * @param price
    */
    public void setPrice(int price){
        _price = price;
    }

    /**
    * @param stock
    */
    public void setStock(int stock){
        _stock = stock;
    }

    /**
    * @param valCrit
    */
    public void setValCrit(int valCrit){
        _valCrit = valCrit;
    }

    public void registerObserver(Observer o){
        _observers.add(o);
    }

    public void removeObserver(Observer o){
        int i = _observers.indexOf(o);
        if(i >= 0){
            _observers.remove(i);
        }
    }

    public void notifyObservers(String keyProd,String notificationType){
        for(Observer observer : _observers){
            observer.update(keyProd,notificationType);
        }
    }

    @Override
    @SuppressWarnings("nls")
    /**
    * @return the string format of the product
    */
    public String toString(){
        String str = String.format("|%s|%s|%d|%d|%d|",_keyProd,_keySupp,_price,_valCrit,_stock);
        return str;
    }
}