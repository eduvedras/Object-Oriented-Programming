package woo.app.products;

import pt.tecnico.po.ui.Command;  
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;
import woo.Storefront;
import woo.app.exceptions.UnknownProductKeyException;  
import woo.exceptions.NoSuchProductKeyException;

/**
 * Change product price.
 */
public class DoChangePrice extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _keyProd;
  private Input<Integer> _price;
  
  public DoChangePrice(Storefront receiver) {
    super(Label.CHANGE_PRICE, receiver);
    _storefront = receiver;
    _keyProd = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException, UnknownProductKeyException{
    _form.parse();
    try{
      _storefront.changeProductPrice(_keyProd.value(),_price.value());
    }
    catch(NoSuchProductKeyException e){
      throw new UnknownProductKeyException(_keyProd.value());
    }
  }
}
