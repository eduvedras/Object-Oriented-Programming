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
import woo.app.exceptions.UnknownServiceLevelException;  
import woo.exceptions.NoSuchServiceLevelException;
import woo.Storefront;

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _keyProd;
  private Input<String> _keySupp;
  private Input<Integer> _price;
  private Input<Integer> _valCrit;
  private Input<String> _serviceType;
  private Input<String> _serviceLevel;

  public DoRegisterProductContainer(Storefront receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _storefront = receiver;
    _keyProd = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _valCrit = _form.addIntegerInput(Message.requestStockCriticalValue());
    _keySupp = _form.addStringInput(Message.requestSupplierKey());
    _serviceType = _form.addStringInput(Message.requestServiceType());
    _serviceLevel = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DialogException, 
    UnknownSupplierKeyException, DuplicateProductKeyException, 
    UnknownServiceTypeException, UnknownServiceLevelException {
    
    _form.parse();
    try{
      _storefront.registerProductContainer(_keyProd.value(),
        _keySupp.value(),_price.value(),_valCrit.value(),
        _serviceType.value(),_serviceLevel.value());
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
    catch(NoSuchServiceLevelException e){
      throw new UnknownServiceLevelException(_serviceLevel.value());
    }
  }
}
