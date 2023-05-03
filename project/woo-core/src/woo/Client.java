package woo;

import java.io.Serializable;
import java.util.Collections;
import java.util.*;

public class Client implements Serializable,Observer,DisplayElement{
    private String _keyClient;
    private String _name;
    private String _address;
    private int _points;
    private ClientStatus _status;
    private int _purchases;
    private int _paid;
    private ArrayList<String> _keysProd = new ArrayList<String>();
    private ArrayList<String> _notifications = new ArrayList<String>();
    
    /**
    * @param keyClient,name,address
    */
    public Client(String keyClient, String name, String address){
        _keyClient = keyClient;
        _name = name;
        _address = address;
        _points = 0;
        _status = new NormalStatus(this);
        _purchases = 0;
        _paid = 0;
    }

    /**
    * @return the key of the client
    */
    public String getKeyClient(){
        return _keyClient;
    }

    /**
    * @return the name of the client
    */
    public String getName(){
        return _name;
    }

    /**
    * @return the address of the client
    */
    public String getAddress(){
        return _address;
    }

    /**
    * @return the points of the client
    */
    public int getPoints(){
        return _points;
    }

    public String getStatus(){
        return _status.toString();
    }

    public ArrayList<String> getKeysProd(){
        return _keysProd;
    }

    public ArrayList<String> getNotifications(){
        return _notifications;
    }

    public int getPurchases(){
        return _purchases;
    }

    public int getPaid(){
        return _paid;
    }

    /**
    * @param keyClient
    */
    public void setKeyClient(String keyClient){
        _keyClient = keyClient;
    }

    /**
    * @param name
    */
    public void setName(String name){
        _name = name;
    }

    /**
    * @param address
    */
    public void setAddress(String address){
        _address = address;
    }

    public void setPurchases(int purchases){
        _purchases = purchases;
    }

    public void setPaid(int paid){
        _paid = paid;
    }

    public void setStatus(ClientStatus status){
        _status = status;
    }

    /**
    * @param points
    */
    public void setPoints(int points){
        _points = points;
    }

    public void verifyStatus(){
        _status.verifyStatus();
    }

    public void update(String keyProd,String notification){
        _keysProd.add(keyProd);
        _notifications.add(notification);
    }

    public String display(int i){
        String str="";
        str = str + _notifications.get(i) + "|" + _keysProd.get(i);
        return str;
    }

    public void removeNotifications(){
        int i;
        for(i=0;i<_keysProd.size();i++){
            _keysProd.remove(i);
            _notifications.remove(i);
        }
    }

    @Override
    @SuppressWarnings("nls")
    /**
    * @return string format of the client
    */
    public String toString(){
        String str = String.format("%s|%s|%s|%s|%d|%d", _keyClient,_name,_address,_status.toString(),_purchases,_paid);
        return str;
    }
}