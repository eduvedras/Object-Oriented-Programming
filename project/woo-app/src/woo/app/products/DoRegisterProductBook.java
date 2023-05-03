package woo.app.products;

import pt.tecnico.po.ui.Command;   
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;
import woo.app.exceptions.UnknownSupplierKeyException;  
import woo.exceptions.NoSuchSupplierKeyException;
import woo.app.exceptions.DuplicateProductKeyException;  
import woo.exceptions.NonUniqueProductKeyException;
import woo.Storefront;
/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _keyProd;
  private Input<String> _keySupp;
  private Input<Integer> _price;
  private Input<Integer> _valCrit;
  private Input<String> _title;
  private Input<String> _author;
  private Input<String> _ISBN;

  public DoRegisterProductBook(Storefront receiver) {
    super(Label.REGISTER_BOOK, receiver);
    _storefront = receiver;
    _keyProd = _form.addStringInput(Message.requestProductKey());
    _title = _form.addStringInput(Message.requestBookTitle());
    _author = _form.addStringInput(Message.requestBookAuthor());
    _ISBN = _form.addStringInput(Message.requestISBN());
    _price = _form.addIntegerInput(Message.requestPrice());
    _valCrit = _form.addIntegerInput(Message.requestStockCriticalValue());
    _keySupp = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException, 
    UnknownSupplierKeyException, DuplicateProductKeyException {
    _form.parse();
    try{
      _storefront.registerProductBook(_keyProd.value(),_keySupp.value(),
        _price.value(),_valCrit.value(),_title.value(),_author.value(),
        _ISBN.value());
    }
    catch(NoSuchSupplierKeyException e){
      throw new UnknownSupplierKeyException(_keySupp.value());
    }
    catch(NonUniqueProductKeyException e){
      throw new DuplicateProductKeyException(_keyProd.value());
    }
  }
}
