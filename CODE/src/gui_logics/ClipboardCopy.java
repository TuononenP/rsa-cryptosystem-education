package gui_logics;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

/**
 * Copies a string to clipboard.
 * 
 * @author Petri Tuononen
 * @since 11.2.2009
 *
 */
public final class ClipboardCopy implements ClipboardOwner {

   /**
   * Empty implementation of the ClipboardOwner interface.
   * Notifies the object that it is no longer the clipboard owner.
   */
   public void lostOwnership(Clipboard clipboard, Transferable contents) {
   }

  /**
  * Place a String on the clipboard. Sets ownership to this class.
  */
  public void copyToClipboard(String text){
    StringSelection strSelection = new StringSelection(text);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(strSelection, this);
  }

} 