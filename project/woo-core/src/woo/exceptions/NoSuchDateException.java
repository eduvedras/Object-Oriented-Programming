package woo.exceptions;

public class NoSuchDateException extends Exception{
    private static final long serialVersionUID = 202009192335L;
    
    private int _date;

    /**
    * @param date
    */
    public NoSuchDateException(int date){
        _date = date;
    }

    /**
    * @return the date
    */
    public int getDate(){
        return _date;
    }
}