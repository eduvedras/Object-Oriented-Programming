package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                    
import pt.tecnico.po.ui.DialogException;                                        
import pt.tecnico.po.ui.Input;                                                      
import woo.Storefront;                                                                                                   
import woo.app.exceptions.UnknownClientKeyException;  
import woo.exceptions.NoSuchClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;  
import woo.exceptions.NoSuchProductKeyException;
import woo.app.exceptions.UnavailableProductException;  
import woo.exceptions.InsufficientStockException;

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<Storefront> {

  private Input<String> _keyClient;
  private Input<Integer> _dateLim;
  private Input<String> _keyProd;
  private Input<Integer> _quantity;
  private Storefront _storefront;

  public DoRegisterSaleTransaction(Storefront receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    _storefront = receiver;
    _keyClient = _form.addStringInput(Message.requestClientKey());
    _dateLim = _form.addIntegerInput(Message.requestPaymentDeadline());
    _keyProd = _form.addStringInput(Message.requestProductKey());
    _quantity = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException,UnknownClientKeyException,UnknownProductKeyException,UnavailableProductException {
    _form.parse();
    try{
      _storefront.registerSaleTransaction(_keyClient.value(),_dateLim.value(),_keyProd.value(),_quantity.value());
    }
    catch(NoSuchClientKeyException e){
      throw new UnknownClientKeyException(_keyClient.value());
    }
    catch(NoSuchProductKeyException e){
      throw new UnknownProductKeyException(_keyProd.value());
    }
    catch(InsufficientStockException e){
      throw new UnavailableProductException(_keyProd.value(),_quantity.value(),_storefront.getProdStock(_keyProd.value()));
    }
  }

}
