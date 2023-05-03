package woo;

import java.io.Serializable;
import java.util.Collections;

public class Supplier implements Serializable{
    private String _keySupp;
    private String _name;
    private String _address;
    private boolean _activity;

    /**
    * @param keySupp,name,address
    */
    public Supplier(String keySupp, String name, String address){
        _keySupp = keySupp;
        _name = name;
        _address = address;
        _activity = true;
    }

    /**
    * @return key of the supplier
    */
    public String getKeySupp(){
        return _keySupp;
    }

    /**
    * @return name of the supplier
    */
    public String getName(){
        return _name;
    }

    /**
    * @return address of the supplier
    */
    public String getAddress(){
        return _address;
    }

    /**
    * @return activity of the supplier
    */
    public boolean getActivity(){
        return _activity;
    }

    /**
    * @param keySupp
    */
    public void setKeySupp(String keySupp){
        _keySupp = keySupp;
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

    public void setActivity(){
        if(_activity == true){
            _activity = false;
        }
        else{
            _activity = true;
        }
    }

    @Override
    @SuppressWarnings("nls")
    /**
    * @return string format of the supplier
    */
    public String toString(){
        String str = String.format("%s|%s|%s|",_keySupp,_name,_address);
        if(this.getActivity()==true){
            return str + "SIM";
        }
        else{
            return str + "NÃO";
        }
    }
}