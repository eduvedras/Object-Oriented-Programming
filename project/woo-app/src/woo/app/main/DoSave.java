package woo.app.main;

import pt.tecnico.po.ui.Command;  
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Storefront;
import woo.app.exceptions.FileOpenFailedException;
import woo.exceptions.MissingFileAssociationException;   
import java.io.IOException;
import java.io.FileNotFoundException;
/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Storefront> {

  private Input<String> _filename;
  private Storefront _receiver;

  /** @param receiver */
  public DoSave(Storefront receiver) {
    super(Label.SAVE, receiver);
    _receiver = receiver;
    _filename = _form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try{
      if(_receiver.getFilename() == null){
        _form.parse();
        _receiver.saveAs(_filename.value());
      }
      _receiver.save();
    }
    
    catch(MissingFileAssociationException e){
      e.printStackTrace();
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
