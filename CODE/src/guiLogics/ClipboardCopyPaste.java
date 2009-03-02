/*
	Copyright (C) 2009 Petri Tuononen

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package guiLogics;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.Toolkit;

import javax.swing.*;

/**
 * Copies a string to clipboard.
 * 
 * @author Petri Tuononen
 * @since 11.2.2009
 *
 */
public final class ClipboardCopyPaste implements ClipboardOwner {

	/**
	 * Empty implementation of the ClipboardOwner interface.
	 * Notifies the object that it is no longer the clipboard owner.
	 */
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}

	/**
	 * Place a String on the clipboard. Sets ownership to this class.
	 */
	public void copy(String text){
		StringSelection strSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(strSelection, this);
	}
  
	/**
	 * Returns clipboard contents in string type.
	 */
	public String paste()  {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable data = clipboard.getContents(this);
		String s;
		try {
			s = (String) (data.getTransferData(DataFlavor.stringFlavor));
		} 
		catch (Exception e) {
			s = data.toString();
		}
		return s;
	}
	
	/**
	 * Creates a popup menu and defines what happens if
	 * menu items are pressed.
	 * 
	 * @param textArea	Textarea where popup menu comes up.
	 */
	public void createPopupMenu(final JTextArea textArea) {
		JMenuItem menuItem;

		//Create the popup menu.
		JPopupMenu popup = new JPopupMenu();
		menuItem = new JMenuItem("Copy");

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClipboardCopyPaste().copy(textArea.getSelectedText());
			}
		});
		popup.add(menuItem);
		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(new ClipboardCopyPaste().paste());
			}
		});
		popup.add(menuItem);

		//Add listener to the text area so the popup menu can come up.
		MouseListener popupListener = new PopupListener(popup);
		textArea.addMouseListener(popupListener);
	}

	/**
	 * Listener for the JPopupMenu.
	 * Shows popup in the right place.
	 */
	class PopupListener extends MouseAdapter {
		JPopupMenu popup;

		PopupListener(JPopupMenu popupMenu) {
			popup = popupMenu;
		}

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

} 