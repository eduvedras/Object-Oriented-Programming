package woo.app.clients;

import pt.tecnico.po.ui.Command;                         
import pt.tecnico.po.ui.DialogException;                
import pt.tecnico.po.ui.Input;                           
import woo.Storefront;                                                             
import woo.app.exceptions.UnknownClientKeyException;  
import woo.exceptions.NoSuchClientKeyException;
/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<Storefront> {

  private Storefront _storefront;
  private Input<String> _keyClient;

  public DoShowClientTransactions(Storefront storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    _storefront = storefront;
    _keyClient = _form.addStringInput(Message.requestClientKey());

  }

  @Override
  public void execute() throws DialogException,UnknownClientKeyException {
    _form.parse();
    try{
      _display.addLine(_storefront.showClientTransactions(_keyClient.value()));
      _display.display();
    }
    catch(NoSuchClientKeyException e){
      throw new UnknownClientKeyException(_keyClient.value());
    }
  }

}
