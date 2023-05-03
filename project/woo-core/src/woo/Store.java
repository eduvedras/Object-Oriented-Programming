package woo;

import java.util.*;
import java.io.*;
import woo.exceptions.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  private int _date=0;
  private int _available=0;
  private int _ordersValue=0;
  private int _salesValue=0;
  private int _payedSalesValue=0;
  private int _accounting=0;
  private int _numberTran = 0;
  private Map<String,Product> _products = 
    new TreeMap<String,Product>(String.CASE_INSENSITIVE_ORDER);
  private Map<String,Client> _clients = 
    new TreeMap<String,Client>(String.CASE_INSENSITIVE_ORDER);
  private Map<String,Supplier> _suppliers = 
    new TreeMap<String,Supplier>(String.CASE_INSENSITIVE_ORDER);
  private Map<Integer,Sale> _sales = 
    new TreeMap<Integer,Sale>();
  private Map<Integer,Order> _orders = 
    new TreeMap<Integer,Order>();
  
  public Store(){
    //do nothing
  }
  
  /**
  * @return current date
  */
  public int currentDate(){
    return _date;
  }

  /**
  * @param days
  * @throws NoSuchDateException
  * @return date
  */
  public int advanceDate(int days) throws NoSuchDateException{
    if(days>0){
    _date = _date + days;
    return _date;
    }
    else{
      throw new NoSuchDateException(days);
    }
  }

  public int showAvailableBalance(){
    _available= _payedSalesValue - _ordersValue;
    return _available;
  }

  public int showAccountingBalance(){
    int value;
    for(Map.Entry<Integer,Sale> s: _sales.entrySet()){
      value = taxes(s.getValue().getKeyClient(), s.getValue().getKeyTran(), s.getValue().getKeyProd());
      _salesValue += value;
    }
    _accounting = _salesValue - _ordersValue;
    return _accounting;
  }

  /**
  * @return string format of all products
  */
  public String showAllProducts(){
    String str = "";
    for(Map.Entry<String,Product> p: _products.entrySet()){
      str = str + p.getValue().toString() + "\n";
    }
    
    return str;
  }

  public String showProductsUnderTopPrice(int price){
    String str = "";
    for(Map.Entry<String,Product> p: _products.entrySet()){
      if(p.getValue().getPrice() < price){
        str = str + p.getValue().toString() + "\n";
      }
    }
    
    return str;
  }

  public void registerProductBox(String keyProd, String keySupp,
    int price,int valCrit,String serviceType) 
    throws NoSuchSupplierKeyException,NoSuchServiceTypeException,
    NonUniqueProductKeyException{
    if(_suppliers.containsKey(keySupp)==false){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    if(_products.containsKey(keyProd)==true){
      throw new NonUniqueProductKeyException(keyProd);
    }
    Product p = new Box(keyProd,keySupp,price,valCrit,serviceType);
    Box b = (Box) p;
    if(b.getErrorServiceType()==true){
      throw new NoSuchServiceTypeException(serviceType);
    }
    _products.put(keyProd,p);
    for(Map.Entry<String,Client> c: _clients.entrySet()){
      Observer o = (Observer) c.getValue();
      p.registerObserver(o);
    }
  }

  public void registerProductContainer(String keyProd, String keySupp,
    int price,int valCrit,String serviceType,String serviceLevel) 
    throws NoSuchSupplierKeyException,NoSuchServiceTypeException,
    NonUniqueProductKeyException,NoSuchServiceLevelException{
    if(_suppliers.containsKey(keySupp)==false){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    if(_products.containsKey(keyProd)==true){
      throw new NonUniqueProductKeyException(keyProd);
    }
    Product p = new Container(keyProd,keySupp,price,valCrit,serviceType,serviceLevel);
    Container c = (Container) p;
    if(c.getErrorServiceType()==true){
      throw new NoSuchServiceTypeException(serviceType);
    }
    if(c.getErrorServiceLevel()==true){
      throw new NoSuchServiceLevelException(serviceLevel);
    }
    _products.put(keyProd,p);
    for(Map.Entry<String,Client> client: _clients.entrySet()){
      Observer o = (Observer) client.getValue();
      p.registerObserver(o);
    }
  }

  public void registerProductBook(String keyProd, String keySupp,
    int price,int valCrit,String title,String author,String ISBN) 
    throws NoSuchSupplierKeyException,NonUniqueProductKeyException{
    if(_suppliers.containsKey(keySupp)==false){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    if(_products.containsKey(keyProd)==true){
      throw new NonUniqueProductKeyException(keyProd);
    }
    Product p = new Book(keyProd,keySupp,price,valCrit,title,author,ISBN);
    _products.put(keyProd,p);
    for(Map.Entry<String,Client> c: _clients.entrySet()){
      Observer o = (Observer) c.getValue();
      p.registerObserver(o);
    }
  }

  public void changeProductPrice(String keyProd,int price) 
    throws NoSuchProductKeyException{
    if(_products.containsKey(keyProd)==false){
      throw new NoSuchProductKeyException(keyProd);
    }
    Product p = _products.get(keyProd);
    if(price >=0){
      if(price < p.getPrice()){
        p.notifyObservers(keyProd,"BARGAIN");
      }
      p.setPrice(price);
    }
  }

  public int getProdStock(String keyProd){
    int stock=0;
    stock = _products.get(keyProd).getStock();
    return stock;
  }

  /**
  * @param keyClient
  * @throws NoSuchClientKeyException
  * @return string format of the client
  */
  public String showClient(String keyClient) 
    throws NoSuchClientKeyException{
    if(_clients.containsKey(keyClient)==false){
      throw new NoSuchClientKeyException(keyClient);
    }
    String str = "";
    int i;
    Client c = _clients.get(keyClient);
    ArrayList<String> _keysProd;
    _keysProd= c.getKeysProd();
    str = str + c.toString() + "\n";
    for(i=0;i<_keysProd.size();i++){
      str = str + c.display(i) + "|" 
          + _products.get(_keysProd.get(i)).getPrice() + "\n";
    }
    c.removeNotifications();
    return str;
  }

  /**
  * @return string format of all clients
  */
  public String showAllClients(){
    String str = "";
    for(Map.Entry<String,Client> c: _clients.entrySet()){
      str = str + c.getValue().toString() + "\n";
    }
    
    return str;
  }

  public String showPaymentsByClient(String keyClient) 
    throws NoSuchClientKeyException{
    if(_clients.containsKey(keyClient)==false){
      throw new NoSuchClientKeyException(keyClient);
    }
    String str="";
    for(Map.Entry<Integer,Sale> s: _sales.entrySet()){
      if(s.getValue().getKeyClient().equalsIgnoreCase(keyClient)){
        if(s.getValue().getDate() != -1){
          try{
            str = str + showTransaction(s.getKey());
          }
          catch(NoSuchTransactionKeyException e){
            e.printStackTrace();
          }
        }
      }
    }
    return str;
  }

  public boolean toggleProductNotifications(String keyClient,
    String keyProd) throws NoSuchClientKeyException,
    NoSuchProductKeyException{
    if(_clients.containsKey(keyClient)==false){
      throw new NoSuchClientKeyException(keyClient);
    }
    if(_products.containsKey(keyProd)==false){
      throw new NoSuchProductKeyException(keyProd);
    }
    ArrayList<Observer> observers;
    Product p = _products.get(keyProd);
    Observer o = (Observer) _clients.get(keyClient);
    observers = p.getObservers();
    if(observers.contains(o)==true){
      p.removeObserver(o);
      return false;
    }
    else{
      p.registerObserver(o);
      return true;
    }
  }

  public String getP(int keyTran,String keyProd){
    int dateLim;
    int N;
    Sale s = _sales.get(keyTran);
    String str = "";

    dateLim = s.getDateLim();
    N = _products.get(keyProd).getN();
    if((dateLim - _date) >= N){
      str = str + "P1";
    }
    if(0 <= (dateLim - _date) && (dateLim - _date) < N){
      str = str + "P2";
    }
    if(0 < (_date - dateLim) && (_date - dateLim) <= N){
      str = str + "P3";
    }
    if(_date - dateLim > N){
      str = str + "P4";
    }
    return str;
  }

  public int taxes(String keyClient,int keyTran,String keyProd){
    String status;
    String p;
    int price;
    int quantity;
    int base;
    int dateLim;
    Sale s = _sales.get(keyTran);
    double res = 0;

    status = _clients.get(keyClient).getStatus();
    price = _products.get(keyProd).getPrice();
    quantity = s.getQuantity();
    base = price * quantity;
    dateLim = s.getDateLim();
    p=getP(keyTran,keyProd);

    if(status.equals("NORMAL")){
      if(p.equals("P1")){
        res = base * 0.9;
      }
      if(p.equals("P2")){
        res = base;
      }
      if(p.equals("P3")){
        res = base + (_date - dateLim) * base * 0.05;
      }
      if(p.equals("P4")){
        res = base + (_date - dateLim) * base * 0.1;
      }
    }
    if(status.equals("SELECTION")){
      if(p.equals("P1")){
        res = base * 0.9;
      }
      if(p.equals("P2")){
        if(dateLim - _date >= 2){
          res = base * 0.95;
        }
        else{
          res = base;
        }
      }
      if(p.equals("P3")){
        if(_date - dateLim <=1){
          res = base;
        }
        else{
          res = base + (_date - dateLim) * base * 0.02;
        }
      }
      if(p.equals("P4")){
        res = base + (_date - dateLim) * base * 0.05;
      }
    }
    if(status.equals("ELITE")){
      if(p.equals("P1")){
        res = base * 0.9;
      }
      if(p.equals("P2")){
        res = base * 0.9;
      }
      if(p.equals("P3")){
        res = base * 0.95;
      }
      if(p.equals("P4")){
        res = base;
      }
    }
    int r = (int) res;
    return r;
  }

  public String showClientTransactions(String keyClient) 
    throws NoSuchClientKeyException{
    String str="";
    int payment;
    if(_clients.containsKey(keyClient)==false){
      throw new NoSuchClientKeyException(keyClient);
    }
    for(Map.Entry<Integer,Sale> s: _sales.entrySet()){
      if(s.getValue().getKeyClient().equalsIgnoreCase(keyClient)){
        try{
          str = str + showTransaction(s.getKey());
        }
        catch(NoSuchTransactionKeyException e){
          e.printStackTrace();
        }
      }
    }
    return str;
  }

  /**
  * @param keyClient,name,address
  * @throws NonUniqueClientException
  */
  public void registerClient(String keyClient, String name, 
    String address) throws NonUniqueClientKeyException{
    if(_clients.containsKey(keyClient)==true){
      throw new NonUniqueClientKeyException(keyClient);
    }
    Client c = new Client(keyClient,name,address);
    _clients.put(keyClient,c);
    for(Map.Entry<String,Product> p: _products.entrySet()){
      Observer o = (Observer) _clients.get(keyClient);
      p.getValue().registerObserver(o);
    }
  }

  /**
  * @return string format of all suppliers
  */
  public String showSuppliers(){
    String str = "";
    for(Map.Entry<String,Supplier> s: _suppliers.entrySet()){
      str = str + s.getValue().toString() + "\n";
    }
    
    return str;
  }

  public void registerSupplier(String keySupp,String name,
    String address)throws NonUniqueSupplierKeyException{
    if(_suppliers.containsKey(keySupp)==true){
      throw new NonUniqueSupplierKeyException(keySupp);
    }
    Supplier s = new Supplier(keySupp,name,address);
    _suppliers.put(keySupp,s);
  }

  public boolean toggleTransactions(String keySupp) 
    throws NoSuchSupplierKeyException{
    if(_suppliers.containsKey(keySupp)==false){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    if(_suppliers.get(keySupp).getActivity() == true){
      _suppliers.get(keySupp).setActivity();
      return false;
    }
    else{
      _suppliers.get(keySupp).setActivity();
      return true;
    }
  }

  public String showSupplierTransactions(String keySupp) 
    throws NoSuchSupplierKeyException{
    String str="";

    if(_suppliers.containsKey(keySupp)==false){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    for(Map.Entry<Integer,Order> o: _orders.entrySet()){
      if(o.getValue().getKeySupp().equalsIgnoreCase(keySupp)){
        try{
          str = str + showTransaction(o.getKey());
        }
        catch(NoSuchTransactionKeyException e){
          e.printStackTrace();
        }
      }
    }
    return str;
  }

  public String showTransaction(int keyTran) 
    throws NoSuchTransactionKeyException{
    if(_sales.containsKey(keyTran)==false && _orders.containsKey(keyTran)==false){
      throw new NoSuchTransactionKeyException(keyTran);
    }
    String str="";
    if(_orders.containsKey(keyTran)==true){
      Order o = _orders.get(keyTran);
      int base;
      str = str + o.toString();
      base = o.getBaseValue();
      str = str + base + "|" + o.getDate() + "\n";
      str = str + o.toStringSuppProds();
    }
    else{
      Sale s = _sales.get(keyTran);
      int payment;
      str = str + s.toString();
      str = str + (_products.get(s.getKeyProd()).getPrice())*
        s.getQuantity() + "|";
      if(s.getActualValue() == -1){
      payment = taxes(s.getKeyClient(),keyTran,s.getKeyProd());
      str = str + payment;
      }
      else{
        str = str + s.getActualValue();
      }
      str = str + "|" + s.getDateLim();
      if(s.getDate() != -1){
        str = str + "|" + s.getDate();
      }
      str = str + "\n";
    }
    return str;
  }

  public void registerSaleTransaction(String keyClient,int dateLim,
    String keyProd,int quantity) throws NoSuchClientKeyException,
    NoSuchProductKeyException,InsufficientStockException{
    if(_products.containsKey(keyProd)==false){
      throw new NoSuchProductKeyException(keyProd);
    }
    int stock;
    stock = getProdStock(keyProd);
    if(_products.get(keyProd).getStock() < quantity){
      throw new InsufficientStockException(keyProd,quantity,stock);
    }
    if(_clients.containsKey(keyClient)==false){
      throw new NoSuchClientKeyException(keyClient);
    }
    
    Sale s = new Sale(keyClient,dateLim,keyProd,quantity);
    s.setKeyTran(_numberTran);
    _sales.put(_numberTran,s);
    _numberTran++;
    _products.get(keyProd).setStock(_products.get(keyProd).getStock()
       - quantity);
    int price;
    price = _products.get(keyProd).getPrice() * quantity;
    _clients.get(keyClient).setPurchases((_clients.get(keyClient).getPurchases() + price));
  }

  public void registerOrderTransaction(String keySupp) 
    throws NoSuchSupplierKeyException,DisabledSupplierException{
    if(_suppliers.containsKey(keySupp)==false){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    if(_suppliers.get(keySupp).getActivity() == false){
      throw new DisabledSupplierException(keySupp);
    }
    Order o = new Order(keySupp);
    o.setKeyTran(_numberTran);
    _orders.put(_numberTran,o);
    _numberTran++;
    o.setDate(_date);
  }

  public void putsInOrder(String keySupp,String keyProd,int quantity)
    throws NoSuchProductKeyException,IncorrectSupplierException{
    if(_products.containsKey(keyProd)==false){
      _orders.remove(_numberTran - 1);
      _numberTran--;
      throw new NoSuchProductKeyException(keyProd);
    }
    if(_products.get(keyProd).getKeySupp().equalsIgnoreCase(keySupp)){
      int keyTran;
      keyTran = _numberTran - 1;
      Order o = _orders.get(keyTran);
      o.putsInOrder(keyProd,quantity);
      Product p = _products.get(keyProd);
      if(p.getStock()==0){
        p.notifyObservers(keyProd,"NEW");
      }
      p.setStock(p.getStock() + quantity);
      int price;
      price = p.getPrice()*quantity;
      _ordersValue += price;
      o.setBaseValue(o.getBaseValue() + 
        (_products.get(keyProd).getPrice()*quantity));
    }
    else{
      _orders.remove(_numberTran - 1);
      _numberTran--;
      throw new IncorrectSupplierException(keySupp,keyProd);
    }
  }

  public void updatePoints(int keyTran,int payment){
    int dateLim;
    Sale s = _sales.get(keyTran);
    double points;
    String keyClient;
    Client c;
    keyClient = s.getKeyClient();
    c = _clients.get(keyClient);
    dateLim = s.getDateLim();
    if(dateLim - _date >= 0){
      points = payment * 10;
      int p = (int) points;
      c.setPoints((c.getPoints() + p));
      c.verifyStatus();
    }
    if(c.getStatus().equals("ELITE") && (_date - dateLim) > 15){
      points = c.getPoints() * 0.25;
      int p = (int) points;
      c.setPoints(p);
      c.verifyStatus();
    }
    if(c.getStatus().equals("SELECTION") && (_date - dateLim) > 2){
      points = c.getPoints() * 0.1;
      int p = (int) points;
      c.setPoints(p);
      c.verifyStatus();
    }
  }

  public void pay(int keyTran) throws NoSuchTransactionKeyException{
    int payment;
    if(_orders.containsKey(keyTran)==false){
      if(_sales.containsKey(keyTran)==false){
        throw new NoSuchTransactionKeyException(keyTran);
      }
      if(_sales.get(keyTran).getDate() == -1){
        Sale s = _sales.get(keyTran);
        Client c = _clients.get(s.getKeyClient());
        payment = taxes(s.getKeyClient(),keyTran,s.getKeyProd());
        
        _payedSalesValue += payment;
        _sales.get(keyTran).setDate(_date);
        c.setPaid((c.getPaid() + payment));
        s.setActualValue(payment);
        updatePoints(keyTran,payment);
      }
    }
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   * @throws UnavailableFileException
   */
  void importFile(String txtfile) throws IOException, BadEntryException,UnavailableFileException{
    try{
      BufferedReader reader = new BufferedReader(new FileReader(txtfile));
      String line;
      while((line = reader.readLine()) != null){
        String[] fields = line.split("\\|");
        Pattern patBox = Pattern.compile("^(BOX)");
        Pattern patBook = Pattern.compile("^(BOOK)");
        Pattern patContainer = Pattern.compile("^(CONTAINER)");
        Pattern patClient = Pattern.compile("^(CLIENT)");
        Pattern patSupplier = Pattern.compile("^(SUPPLIER)");
        if(patBox.matcher(fields[0]).matches()){
          int price = Integer.parseInt(fields[4]);
          int valCrit = Integer.parseInt(fields[5]);
          int stock = Integer.parseInt(fields[6]);
          Product box = new Box(fields[1],fields[3],price,valCrit,fields[2],stock);
          _products.put(fields[1],box);
          for(Map.Entry<String,Client> client: _clients.entrySet()){
            Observer o = (Observer) client.getValue();
            box.registerObserver(o);
          }
        }
        else if(patBook.matcher(fields[0]).matches()){
          //register book
          int price = Integer.parseInt(fields[6]);
          int valCrit = Integer.parseInt(fields[7]);
          int stock = Integer.parseInt(fields[8]);
          Product book = new Book(fields[1],fields[5],price,valCrit,fields[2],fields[3],fields[4],stock);
          _products.put(fields[1],book);
          for(Map.Entry<String,Client> client: _clients.entrySet()){
            Observer o = (Observer) client.getValue();
            book.registerObserver(o);
          }
        }
        else if(patContainer.matcher(fields[0]).matches()){
          //register container
          int price = Integer.parseInt(fields[5]);
          int valCrit = Integer.parseInt(fields[6]);
          int stock = Integer.parseInt(fields[7]);
          Product container = new Container(fields[1],fields[4],price,valCrit,fields[2],fields[3],stock);
          _products.put(fields[1],container);
          for(Map.Entry<String,Client> client: _clients.entrySet()){
            Observer o = (Observer) client.getValue();
            container.registerObserver(o);
          }
        }
        else if(patClient.matcher(fields[0]).matches()){
          //register client
          Client client = new Client(fields[1],fields[2],fields[3]);
          _clients.put(fields[1],client);
          for(Map.Entry<String,Product> p: _products.entrySet()){
            Observer o = (Observer) client;
            p.getValue().registerObserver(o);
          }
        }
        else if(patSupplier.matcher(fields[0]).matches()){
          //register supplier
          Supplier supplier = new Supplier(fields[1],fields[2],fields[3]);
          _suppliers.put(fields[1],supplier);
        }
      }
      reader.close();
    }
    catch(IllegalArgumentException e){
      throw new BadEntryException(txtfile);
    }
    catch(FileNotFoundException e){
      throw new UnavailableFileException(txtfile);
    }
    catch(IOException e){
      throw new IOException();
    }
  }

}
