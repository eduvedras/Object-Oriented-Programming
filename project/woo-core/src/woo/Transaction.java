package woo;

import java.io.Serializable;
import java.util.Collections;

public class Transaction implements Serializable{
    private int _keyTran;
    private int _date = -1;

    public Transaction(){}

    public int getKeyTran(){
        return _keyTran;
    }

    public int getDate(){
        return _date;
    }

    public void setKeyTran(int keyTran){
        _keyTran = keyTran;
    }

    public void setDate(int date){
        _date = date;
    }

    @Override
    @SuppressWarnings("nls")
    public String toString(){
        return _keyTran + "|";
    }
}