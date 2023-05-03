package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import woo.Storefront;
                                                                                                     
/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<Storefront> {

  private Storefront _storefront;

  public DoShowAllClients(Storefront storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
    _storefront = storefront;
  }

  @Override
  public void execute() throws DialogException {
    _display.addLine(_storefront.showAllClients());
    _display.display();
  }
}
