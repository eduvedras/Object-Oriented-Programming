package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                    
import pt.tecnico.po.ui.DialogException;       
import pt.tecnico.po.ui.Input;                                     
import woo.Storefront;                                                                                               
import woo.app.exceptions.UnknownSupplierKeyException;  
import woo.exceptions.NoSuchSupplierKeyException;

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<Storefront> {

  private Storefront _receiver;
  private Input<String> _keySupp;

  public DoShowSupplierTransactions(Storefront receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _receiver = receiver;
    _keySupp = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException,UnknownSupplierKeyException {
    _form.parse();
    try{
      _display.addLine(_receiver.showSupplierTransactions(_keySupp.value()));
      _display.display();
    }
    catch(NoSuchSupplierKeyException e){
      throw new UnknownSupplierKeyException(_keySupp.value());
    }
  }

}
