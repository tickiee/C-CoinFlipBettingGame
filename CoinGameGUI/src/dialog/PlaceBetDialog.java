package dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.enumeration.BetType;
import view.CoinGameFrame;

/*
 * This is the dialog box that popups when a user selects on the place bet button
 */
public class PlaceBetDialog {

	private CoinGameFrame gameFrame;
	
	// A text field for user to input how much they wanna place their bet
	private JTextField placeBetAmountTextField;
	
	// A combo box that contains all the BetType options (Coin 1, Coin 2, Both or No Bet)
	private JComboBox<BetType> placeBetTypeComboBox = new JComboBox<BetType>(BetType.values());
	
	public PlaceBetDialog(CoinGameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public PlaceBetDialog placeBet() {
		
		// Creates the dialog JPanel with the BorderLayout format
		LayoutManager placeBetDialogLayout = new BorderLayout(3, 3); 
		JPanel placeBetDialogPanel = new JPanel(placeBetDialogLayout);
		
		// Creates the place bet info in JPanel with the GridLayout format
		LayoutManager placeBetInfoLayout = new GridLayout(0, 1, 3, 3);
		JPanel placeBetInfoPanel = new JPanel(placeBetInfoLayout);
		
		JLabel placeBetAmountLabel = new JLabel("Enter Bet Amount: ", SwingConstants.LEFT);
		placeBetInfoPanel.add(placeBetAmountLabel);
		
		JLabel placeBetTypeLabel = new JLabel("Select a Bet Type ", SwingConstants.LEFT);
		placeBetInfoPanel.add(placeBetTypeLabel);
		
		// Adds the place bet info JPanel at the West of the Dialog Box
		placeBetDialogPanel.add(placeBetInfoPanel, BorderLayout.WEST);
		
		// Creates the user input JPanel with the GridLayout format
		LayoutManager userInputLayout = new GridLayout(0, 1, 3, 3);
		JPanel userInputPanel = new JPanel(userInputLayout);
		
		// Adds the text field in the user input JPanel
		placeBetAmountTextField = new JTextField();
		userInputPanel.add(placeBetAmountTextField);

		// Adds the combo box in the user input JPanel
		userInputPanel.add(placeBetTypeComboBox);
		
		// Adds the user input JPanel at the Center of the Dialog Box
		placeBetDialogPanel.add(userInputPanel, BorderLayout.CENTER);
		
		JOptionPane.showMessageDialog(gameFrame, placeBetDialogPanel, "Place Bet", JOptionPane.QUESTION_MESSAGE);
		
		return this;
	}
	
	// Returns a string type of the bet typed by the user
	public String getBet() {
		return placeBetAmountTextField.getText();
	}
	
	// Returns a BetType type of the BetType chosen by the user in the combo box
	public BetType getBetType() {
		return (BetType) placeBetTypeComboBox.getSelectedItem();
	}
}
