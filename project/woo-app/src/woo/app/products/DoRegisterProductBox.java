package woo.app.products;

import pt.tecnico.po.ui.Command;    
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;
import woo.app.exceptions.UnknownSupplierKeyException;  
import woo.exceptions.NoSuchSupplierKeyException;
import woo.app.exceptions.DuplicateProductKeyException;  
import woo.exceptions.NonUniqueProductKeyException;
import woo.app.exceptions.UnknownServiceTypeException;  
import woo.exceptions.NoSuchServiceTypeException;
import woo.Storefront;

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _keyProd;
  private Input<String> _keySupp;
  private Input<Integer> _price;
  private Input<Integer> _valCrit;
  private Input<String> _serviceType;

  public DoRegisterProductBox(Storefront receiver) {
    super(Label.REGISTER_BOX, receiver);
    _storefront = receiver;
    _keyProd = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _valCrit = _form.addIntegerInput(Message.requestStockCriticalValue());
    _keySupp = _form.addStringInput(Message.requestSupplierKey());
    _serviceType = _form.addStringInput(Message.requestServiceType());
  }

  @Override
  public final void execute() throws DialogException, 
    UnknownSupplierKeyException, DuplicateProductKeyException, 
    UnknownServiceTypeException {
    _form.parse();
    try{
      _storefront.registerProductBox(_keyProd.value(),_keySupp.value(),
        _price.value(),_valCrit.value(),_serviceType.value());
    }
    catch(NoSuchSupplierKeyException e){
      throw new UnknownSupplierKeyException(_keySupp.value());
    }
    catch(NonUniqueProductKeyException e){
      throw new DuplicateProductKeyException(_keyProd.value());
    }
    catch(NoSuchServiceTypeException e){
      throw new UnknownServiceTypeException(_serviceType.value());
    }
  }
}
