package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                    
import pt.tecnico.po.ui.DialogException;                                     
import pt.tecnico.po.ui.Input;                                       
import woo.Storefront;                                                                                   
import woo.app.exceptions.UnknownTransactionKeyException;  
import woo.exceptions.NoSuchTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<Storefront> {

  private Storefront _receiver;
  private Input<Integer> _keyTran;

  public DoShowTransaction(Storefront receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _receiver = receiver;
    _keyTran = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException,
    UnknownTransactionKeyException {
    _form.parse();
    try{
      _display.addLine(_receiver.showTransaction(_keyTran.value()));
      _display.display();
    }
    catch(NoSuchTransactionKeyException e){
      throw new UnknownTransactionKeyException(_keyTran.value());
    }
  }

}
