package woo.app.clients;

import pt.tecnico.po.ui.Command;                       
import pt.tecnico.po.ui.DialogException;                                               
import pt.tecnico.po.ui.Input;                             
import woo.Storefront;                                                                                                           
import woo.app.exceptions.UnknownClientKeyException;  
import woo.exceptions.NoSuchClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;  
import woo.exceptions.NoSuchProductKeyException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _keyClient;
  private Input<String> _keyProd;

  public DoToggleProductNotifications(Storefront storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _storefront = storefront;
    _keyClient = _form.addStringInput(Message.requestClientKey());
    _keyProd = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public void execute() throws DialogException,UnknownClientKeyException,UnknownProductKeyException {
    _form.parse();
    try{
       if(_storefront.toggleProductNotifications(_keyClient.value(),_keyProd.value())==true){
        _display.addLine(Message.notificationsOn(_keyClient.value(),_keyProd.value()));
        _display.display();
      }
      else{
        _display.addLine(Message.notificationsOff(_keyClient.value(),_keyProd.value()));
        _display.display();
      }
    }
    catch(NoSuchClientKeyException e){
      throw new UnknownClientKeyException(_keyClient.value());
    }
    catch(NoSuchProductKeyException e){
      throw new UnknownProductKeyException(_keyProd.value());
    }
  }

}
