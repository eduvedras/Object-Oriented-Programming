package woo.app.transactions;

import pt.tecnico.po.ui.Command;                         
import pt.tecnico.po.ui.DialogException;                            
import pt.tecnico.po.ui.Input;                                            
import woo.Storefront;                                                                                       
import woo.app.exceptions.UnknownSupplierKeyException;  
import woo.exceptions.NoSuchSupplierKeyException;
import woo.app.exceptions.UnknownProductKeyException;  
import woo.exceptions.NoSuchProductKeyException;
import woo.app.exceptions.UnauthorizedSupplierException;  
import woo.exceptions.DisabledSupplierException;
import woo.app.exceptions.WrongSupplierException;  
import woo.exceptions.IncorrectSupplierException;
/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {

  private Input<String> _keySupp;
  private Input<String> _keyProd;
  private Input<Integer> _quantity;
  private Input<String> s;
  private Storefront _storefront;

  public DoRegisterOrderTransaction(Storefront receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _storefront = receiver;
  }

  @Override
  public final void execute() throws DialogException,UnauthorizedSupplierException,WrongSupplierException,UnknownSupplierKeyException,UnknownProductKeyException {
    _form.clear();
    _keySupp = _form.addStringInput(Message.requestSupplierKey());
    _keyProd = _form.addStringInput(Message.requestProductKey());
    _quantity = _form.addIntegerInput(Message.requestAmount());
    s = _form.addStringInput(Message.requestMore());
    _form.parse();
    try{
      _storefront.registerOrderTransaction(_keySupp.value());
      _storefront.putsInOrder(_keySupp.value(),_keyProd.value(),_quantity.value());
      _form.clear();
      if((s.value()).equalsIgnoreCase("s")){
        while((s.value()).equalsIgnoreCase("s")){
          _form.clear();
          _keyProd = _form.addStringInput(Message.requestProductKey());
          _quantity = _form.addIntegerInput(Message.requestAmount());
          _form.parse();
          _form.clear();
          s = _form.addStringInput(Message.requestMore());
          _form.parse();
          _storefront.putsInOrder(_keySupp.value(),_keyProd.value(),_quantity.value());
        }
      }
    }
    catch(NoSuchSupplierKeyException e){
      throw new UnknownSupplierKeyException(_keySupp.value());
    }
    catch(DisabledSupplierException e){
      throw new UnauthorizedSupplierException(_keySupp.value());
    }
    catch(NoSuchProductKeyException e){
      throw new UnknownProductKeyException(_keyProd.value());
    }
    catch(IncorrectSupplierException e){
      throw new WrongSupplierException(_keySupp.value(),_keyProd.value());
    }
  }

}
