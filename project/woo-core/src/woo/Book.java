package woo;

public class Book extends Product{
    private String _title;
    private String _author;
    private String _ISBN;

    /**
    * @param keyProd,keySupp,price,valCrit,title,author,ISBN
    */
    public Book(String keyProd, String keySupp, int price, int valCrit, String title,String author,String ISBN){
        super(keyProd, keySupp, price, valCrit);

        super.setN(3);
        _title = title;
        _author = author;
        _ISBN = ISBN;
    }

    /**
    * @param keyProd,keySupp,price,valCrit,title,author,ISBN,stock
    */
    public Book(String keyProd, String keySupp, int price, int valCrit, String title,String author,String ISBN,int stock){
        this(keyProd, keySupp, price, valCrit, title, author,ISBN);
        super.setStock(stock);
    }

    /**
    * @return the title of the book
    */
    public String getTitle(){
        return _title;
    }

    /**
    * @return the author of the book
    */
    public String getAuthor(){
        return _author;
    }

    /**
    * @return the ISBN of the book
    */
    public String getISBN(){
        return _ISBN;
    }

    /**
    * @param title
    */
    public void setTitle(String title){
        _title = title;
    }

    /**
    * @param author
    */
    public void setAuthor(String author){
        _author = author;
    }

    /**
    * @param ISBN
    */
    public void setISBN(String ISBN){
        _ISBN = ISBN;
    }

    @Override
    @SuppressWarnings("nls")
    /**
    * @return the string format of the book
    */
    public String toString(){
        String str = String.format("BOOK");
        String str1 = String.format("%s|%s|%s",_title,_author,_ISBN);
        return str + super.toString() + str1;
    }
}