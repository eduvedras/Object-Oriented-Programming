package woo.app.clients;

import pt.tecnico.po.ui.Command;   
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.DialogException;
import woo.app.exceptions.UnknownClientKeyException;  
import woo.exceptions.NoSuchClientKeyException;  
import woo.Storefront;                                                                                                          

/**
 * Show client.
 */
public class DoShowClient extends Command<Storefront> {

  private Input<String> _key;
  private Storefront _storefront;

  public DoShowClient(Storefront storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _storefront = storefront;
    _key = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException,UnknownClientKeyException {
    _form.parse();
    try{
      _display.addLine(_storefront.showClient(_key.value()));
      _display.display();
    }
    catch(NoSuchClientKeyException e){
      throw new UnknownClientKeyException(_key.value());
    }
  }

}
