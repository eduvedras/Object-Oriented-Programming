package woo.app.products;

import pt.tecnico.po.ui.Command;   
import pt.tecnico.po.ui.DialogException;
import woo.Storefront;
import pt.tecnico.po.ui.Input;
/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<Storefront> {
  private Storefront _receiver;

  public DoShowAllProducts(Storefront receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
    _receiver = receiver;
  }

  @Override
  public final void execute() throws DialogException {
    _display.addLine(_receiver.showAllProducts());
    _display.display();
  }

}
