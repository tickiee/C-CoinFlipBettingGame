package dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.CoinGameFrame;

/*
 * This is the dialog box that popups when a user selects on add player
 */
public class AddPlayerDialog {

	private CoinGameFrame gameFrame;

	// The text field for player name
	private JTextField playerNameTextField;
	
	// The text field for initial point
	private JTextField playerInitialPointTextField;
	
	public AddPlayerDialog(CoinGameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public AddPlayerDialog addPlayer() {
		
		// Creates the dialog JPanel with the BorderLayout format
		LayoutManager addPlayerDialogLayout = new BorderLayout(3, 3); 
		JPanel addPlayerDialogPanel = new JPanel(addPlayerDialogLayout);
		
		// Creates the player info JPanel with the GridLayout format
		LayoutManager playerInfoLayout = new GridLayout(0, 1, 3, 3);
		JPanel playerInfoPanel = new JPanel(playerInfoLayout);
		
		// Adds the 2 JLabels in the player info JPanel
		JLabel playerNameLabel = new JLabel("Player Name: ", SwingConstants.LEFT);
		playerInfoPanel.add(playerNameLabel);
		
		JLabel initalPointLabel = new JLabel("Initial Points: ", SwingConstants.LEFT);
		playerInfoPanel.add(initalPointLabel);
		
		// Adds the player info JPanel to the West of the dialog JPanel
		addPlayerDialogPanel.add(playerInfoPanel, BorderLayout.WEST);
		
		// Creates the user input JPanel with the GridLayout format
		LayoutManager userInputLayout = new GridLayout(0, 1, 3, 3);
		JPanel userInputPanel = new JPanel(userInputLayout);
		
		// Creates a TextField for the player name and adds it to the user input JPanel
		playerNameTextField = new JTextField();
		userInputPanel.add(playerNameTextField);
		
		// Creates a TextField for the initial point and adds it to the user input JPanel
		playerInitialPointTextField = new JTextField();
		userInputPanel.add(playerInitialPointTextField);
		
		// Adds the user input JPanel to the Center of the dialog JPanel
		addPlayerDialogPanel.add(userInputPanel, BorderLayout.CENTER);
				
		// Display
		JOptionPane.showMessageDialog(gameFrame, addPlayerDialogPanel, "Add New Player", JOptionPane.QUESTION_MESSAGE);
		
		return this;
	}
	
	// Returns the player name input by the user
	public String getPlayerName() {
		return playerNameTextField.getText();
	}
	
	// Returns the player initial points input by the user
	public String getPlayerInitialPoints() {
		return playerInitialPointTextField.getText();
	}
	
}
