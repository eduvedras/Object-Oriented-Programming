package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;                                                                                  
import pt.tecnico.po.ui.Input;              
import woo.Storefront;                                                                                                                    

/**
 * Show current date.
 */
public class DoDisplayDate extends Command<Storefront> {

  private Storefront _receiver;

  public DoDisplayDate(Storefront receiver) {
    super(Label.SHOW_DATE, receiver);
    _receiver = receiver;
  }

  @Override
  public final void execute() throws DialogException {
    _display.addLine(Message.currentDate(_receiver.currentDate()));
    _display.display();
  }
}
