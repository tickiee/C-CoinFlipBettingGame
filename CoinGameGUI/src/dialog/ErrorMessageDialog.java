package dialog;

import javax.swing.JOptionPane;

import view.CoinGameFrame;

/*
 * This class handles error messages and displays them as a dialog box
 */
@SuppressWarnings("serial")
public class ErrorMessageDialog extends JOptionPane {

	// Displays the error dialog box with the specific error message as coded
	public void runErrorDialogBox(CoinGameFrame gameFrame, String errorMessage) {
		showMessageDialog(gameFrame, errorMessage, "ERROR", ERROR_MESSAGE);
	}
}
