package woo.exceptions;

public class NoSuchTransactionKeyException extends Exception{
    private static final long serialVersionUID = 202009192008L;
    
    private int _keyTran;

    /**
    * @param keyTran
    */
    public NoSuchTransactionKeyException(int keyTran){
        _keyTran = keyTran;
    }

    /**
    * @return the key of the transaction
    */
    public int getKeyTran(){
        return _keyTran;
    }
}