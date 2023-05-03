package woo;
import java.util.*;

public class Order extends Transaction{
    private String _keySupp;
    private ArrayList<String> _keysProd;
    private ArrayList<Integer> _quantities;
    private int _baseValue;

    public Order(String keySupp){
        _keySupp = keySupp;
        _keysProd = new ArrayList<String>();
        _quantities = new ArrayList<Integer>();
    }

    public void putsInOrder(String keyProd,int quantity){
        if(_keysProd.contains(keyProd)==false){
            _keysProd.add(keyProd);
            _quantities.add(quantity);
        }
        else{
            int i;
            int newQuantity;
            i = _keysProd.indexOf(keyProd);
            newQuantity = _quantities.get(i) + quantity;
            _quantities.set(i,newQuantity);
        }
    }

    public String getKeySupp(){
        return _keySupp;
    }

    public int getBaseValue(){
        return _baseValue;
    }

    public void setBaseValue(int baseValue){
        _baseValue = baseValue;
    }

    public String toStringSuppProds(){
        String str = "";
        int i;
        for(i=0;i < _keysProd.size(); i++){
            str = str + _keysProd.get(i) + "|" + _quantities.get(i) + "\n";
        }
        return str;
    }

    @Override
    @SuppressWarnings("nls")
    public String toString(){
        return super.toString() + _keySupp + "|";
    }
}