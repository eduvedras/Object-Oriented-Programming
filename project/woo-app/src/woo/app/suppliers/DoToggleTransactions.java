package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                
import pt.tecnico.po.ui.DialogException;             
import pt.tecnico.po.ui.Input;                                                 
import woo.Storefront;                                                                                
import woo.app.exceptions.UnknownSupplierKeyException;  
import woo.exceptions.NoSuchSupplierKeyException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _key;

  public DoToggleTransactions(Storefront receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _storefront = receiver;
    _key = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException,UnknownSupplierKeyException {
    _form.parse();
    try{
      if(_storefront.toggleTransactions(_key.value())==true){
        _display.addLine(Message.transactionsOn(_key.value()));
        _display.display();
      }
      else{
        _display.addLine(Message.transactionsOff(_key.value()));
        _display.display();
      }
    }
    catch(NoSuchSupplierKeyException e){
      throw new UnknownSupplierKeyException(_key.value());
    }
  }

}
