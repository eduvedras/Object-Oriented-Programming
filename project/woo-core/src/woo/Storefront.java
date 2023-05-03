package woo;

import java.io.*;
import woo.exceptions.*;

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {

  /** Current filename. */
  private String _filename;

  /** The actual store. */
  private Store _store = new Store();

  /**
  * @return name of the file
  */
  public String getFilename(){
    return _filename;
  }

  /**
  * @param filename
  */
  public void setFilename(String filename){
    _filename = filename;
  }

  /**
  * @return current date
  */
  public int currentDate(){
    return _store.currentDate();
  }

  /**
  * @param days
  * @throws NoSuchDateException
  * @return days to advance
  */
  public int advanceDate(int days) throws NoSuchDateException{
    try{
      return _store.advanceDate(days);
    }
    catch(NoSuchDateException e){
      throw new NoSuchDateException(days);
    }
  }

  public int showAvailableBalance(){
    return _store.showAvailableBalance();
  }

  public int showAccountingBalance(){
    return _store.showAccountingBalance();
  }

  /**
  * @return string format of all products
  */
  public String showAllProducts(){
    return _store.showAllProducts();
  }

  public String showProductsUnderTopPrice(int price){
    return _store.showProductsUnderTopPrice(price);
  }

  public int getProdStock(String keyProd){
    return _store.getProdStock(keyProd);
  }

  public void registerProductBox(String keyProd, String keySupp,
    int price,int valCrit,String serviceType) 
    throws NoSuchSupplierKeyException,NoSuchServiceTypeException,
    NonUniqueProductKeyException{
    try{
      _store.registerProductBox(keyProd,keySupp,price,valCrit,serviceType);
    }
    catch(NoSuchSupplierKeyException e){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    catch(NoSuchServiceTypeException e){
      throw new NoSuchServiceTypeException(serviceType);
    }
    catch(NonUniqueProductKeyException e){
      throw new NonUniqueProductKeyException(keyProd);
    }
  }

  public void registerProductContainer(String keyProd, String keySupp,
    int price,int valCrit,String serviceType,String serviceLevel) 
    throws NoSuchSupplierKeyException,NoSuchServiceTypeException,
    NonUniqueProductKeyException,NoSuchServiceLevelException{
    try{
      _store.registerProductContainer(keyProd,keySupp,price,valCrit,
        serviceType,serviceLevel);
    }
    catch(NoSuchSupplierKeyException e){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    catch(NoSuchServiceTypeException e){
      throw new NoSuchServiceTypeException(serviceType);
    }
    catch(NonUniqueProductKeyException e){
      throw new NonUniqueProductKeyException(keyProd);
    }
    catch(NoSuchServiceLevelException e){
      throw new NoSuchServiceLevelException(serviceLevel);
    }
  }

  public void registerProductBook(String keyProd, String keySupp,
    int price,int valCrit,String title,String author,String ISBN) 
    throws NoSuchSupplierKeyException,NonUniqueProductKeyException{
    try{
      _store.registerProductBook(keyProd,keySupp,price,valCrit,title,author,ISBN);
    }
    catch(NoSuchSupplierKeyException e){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    catch(NonUniqueProductKeyException e){
      throw new NonUniqueProductKeyException(keyProd);
    }
  }

  public void changeProductPrice(String keyProd,int price) 
    throws NoSuchProductKeyException{
    try{
      _store.changeProductPrice(keyProd,price);
    }
    catch(NoSuchProductKeyException e){
      throw new NoSuchProductKeyException(keyProd);
    }
  }

  /**
  * @param keyClient
  * @throws NoSuchClientKeyException
  * @return string format of the client
  */
  public String showClient(String keyClient) 
    throws NoSuchClientKeyException{
    try{
      return _store.showClient(keyClient);
    }
    catch(NoSuchClientKeyException e){
      throw new NoSuchClientKeyException(keyClient);
    }
  }

  /**
  * @return string format of all clients
  */
  public String showAllClients(){
    return _store.showAllClients();
  }

  public boolean toggleProductNotifications(String keyClient,
    String keyProd) throws NoSuchClientKeyException,
    NoSuchProductKeyException{
    try{
      return _store.toggleProductNotifications(keyClient,keyProd);
    }
    catch(NoSuchClientKeyException e){
      throw new NoSuchClientKeyException(keyClient);
    }
    catch(NoSuchProductKeyException e){
      throw new NoSuchProductKeyException(keyProd);
    }
  }

  /**
  * @param keyClient,name,address
  * @throws NonUniqueClientKeyException
  */
  public void registerClient(String keyClient, String name, 
    String address) throws NonUniqueClientKeyException{
    try{
      _store.registerClient(keyClient,name,address);
    }
    catch(NonUniqueClientKeyException e){
      throw new NonUniqueClientKeyException(keyClient);
    }
  }

  public String showClientTransactions(String keyClient) 
    throws NoSuchClientKeyException{
    try{
      return _store.showClientTransactions(keyClient);
    }
    catch(NoSuchClientKeyException e){
      throw new NoSuchClientKeyException(keyClient);
    }
  }

  public String showPaymentsByClient(String keyClient) 
    throws NoSuchClientKeyException{
    try{
      return _store.showPaymentsByClient(keyClient);
    }
    catch(NoSuchClientKeyException e){
      throw new NoSuchClientKeyException(keyClient);
    }
  }

  /**
  * @return string format of all suppliers
  */
  public String showSuppliers(){
    return _store.showSuppliers();
  }

  public void registerSupplier(String keySupp,String name,String address)
    throws NonUniqueSupplierKeyException{
    try{
      _store.registerSupplier(keySupp,name,address);
    }catch(NonUniqueSupplierKeyException e){
      throw new NonUniqueSupplierKeyException(keySupp);
    }
  }

  public String showSupplierTransactions(String keySupp) 
    throws NoSuchSupplierKeyException{
    try{
      return _store.showSupplierTransactions(keySupp);
    }
    catch(NoSuchSupplierKeyException e){
      throw new NoSuchSupplierKeyException(keySupp);
    }
  }

  public String showTransaction(int keyTran) 
    throws NoSuchTransactionKeyException{
    try{
      return _store.showTransaction(keyTran);
    }
    catch(NoSuchTransactionKeyException e){
      throw new NoSuchTransactionKeyException(keyTran);
    }
  }

  public boolean toggleTransactions(String keySupp) 
    throws NoSuchSupplierKeyException{
    try{
      return _store.toggleTransactions(keySupp);
    }
    catch(NoSuchSupplierKeyException e){
      throw new NoSuchSupplierKeyException(keySupp);
    }
  }

  public void registerSaleTransaction(String keyClient,int dateLim,
    String keyProd,int quantity) throws NoSuchClientKeyException,
    NoSuchProductKeyException,InsufficientStockException{
    try{
      _store.registerSaleTransaction(keyClient,dateLim,keyProd,quantity);
    }
    catch(NoSuchProductKeyException e){
      throw new NoSuchProductKeyException(keyProd);
    }
    catch(NoSuchClientKeyException e){
      throw new NoSuchClientKeyException(keyClient);
    }
    catch(InsufficientStockException e){
      throw new InsufficientStockException(keyProd,quantity,getProdStock(keyProd));
    }
  }

  public void registerOrderTransaction(String keySupp) throws 
    NoSuchSupplierKeyException,DisabledSupplierException{
    try{
      _store.registerOrderTransaction(keySupp);
    }
    catch(NoSuchSupplierKeyException e){
      throw new NoSuchSupplierKeyException(keySupp);
    }
    catch(DisabledSupplierException e){
      throw new DisabledSupplierException(keySupp);
    }
  }

  public void putsInOrder(String keySupp,String keyProd,int quantity) 
    throws NoSuchProductKeyException,IncorrectSupplierException{
    try{
      _store.putsInOrder(keySupp,keyProd,quantity);
    }
    catch(NoSuchProductKeyException e){
      throw new NoSuchProductKeyException(keyProd);
    }
    catch(IncorrectSupplierException e){
      throw new IncorrectSupplierException(keySupp,keyProd);
    }
  }

  public void pay(int keyTran) throws NoSuchTransactionKeyException{
    try{
      _store.pay(keyTran);
    }
    catch(NoSuchTransactionKeyException e){
      throw new NoSuchTransactionKeyException(keyTran);
    }
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, 
    MissingFileAssociationException {
    try{
      ObjectOutputStream oos = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(_filename)));
      oos.writeObject(_store);
      oos.close();
    }
    catch(FileNotFoundException e){
      throw new FileNotFoundException();
    }
    catch(IllegalArgumentException e){
      throw new MissingFileAssociationException();
    }
    catch(IOException e){
      throw new IOException();
    }
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws 
    MissingFileAssociationException,FileNotFoundException,IOException{
    try{
      _filename = filename;
      save();
    }
    catch(FileNotFoundException e){
      throw new FileNotFoundException();
    }
    catch(MissingFileAssociationException e){
      throw new MissingFileAssociationException();
    }
    catch(IOException e){
      throw new IOException();
    }
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException {
    try{
      ObjectInputStream ois = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(filename)));
      _store = (Store) ois.readObject();
      ois.close();
      _filename = filename;
    }
    catch(ClassNotFoundException | IOException e){
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException | UnavailableFileException e) {
      throw new ImportFileException(textfile);
    } 
  }

}
