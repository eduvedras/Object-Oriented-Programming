package woo.app.suppliers;

import pt.tecnico.po.ui.Command;    
import pt.tecnico.po.ui.DialogException;
import woo.Storefront;
import pt.tecnico.po.ui.Input;
/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<Storefront> {

  private Storefront _receiver;

  public DoShowSuppliers(Storefront receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
    _receiver = receiver;
  }

  @Override
  public void execute() throws DialogException {
    String s;
    s = _receiver.showSuppliers();
    s.replaceAll("SIM",Message.yes());
    s.replaceAll("NÃO",Message.no());
    _display.addLine(s);
    _display.display();
  }
}
