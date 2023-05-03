package woo.app.lookups;

import pt.tecnico.po.ui.Command;                                        
import pt.tecnico.po.ui.DialogException;                                          
import pt.tecnico.po.ui.Input;                                             
import woo.Storefront;                                                                                   
import woo.app.exceptions.UnknownClientKeyException;  
import woo.exceptions.NoSuchClientKeyException;

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<Storefront> {

  private Storefront _receiver;
  private Input<String> _keyClient;

  public DoLookupPaymentsByClient(Storefront storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    _receiver = storefront;
    _keyClient = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException,UnknownClientKeyException {
    _form.parse();
    try{
      _display.addLine(_receiver.showPaymentsByClient(_keyClient.value()));
      _display.display();
    }
    catch(NoSuchClientKeyException e){
      throw new UnknownClientKeyException(_keyClient.value());
    }
  }

}
