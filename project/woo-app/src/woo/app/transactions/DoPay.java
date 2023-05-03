package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                        
import pt.tecnico.po.ui.DialogException;                                      
import pt.tecnico.po.ui.Input;                                 
import woo.Storefront;                                                                             
import woo.app.exceptions.UnknownTransactionKeyException;  
import woo.exceptions.NoSuchTransactionKeyException;

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<Storefront> {

  private Storefront _storefront;
  private Input<Integer> _keySale;
  
  public DoPay(Storefront storefront) {
    super(Label.PAY, storefront);
    _storefront = storefront;
    _keySale = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException,
    UnknownTransactionKeyException {
    _form.parse();
    try{
      _storefront.pay(_keySale.value());
    }
    catch(NoSuchTransactionKeyException e){
      throw new UnknownTransactionKeyException(_keySale.value());
    }
  }

}
