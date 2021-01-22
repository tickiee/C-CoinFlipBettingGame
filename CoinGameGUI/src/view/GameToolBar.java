package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.PlaceBetActionListener;
import controller.RemoveBetActionListener;
import controller.SpinCoinActionListener;
import controller.SpinSpinnerCoinActionListener;
import model.interfaces.GameEngine;

/*
 * This is the Tool Bar
 */
@SuppressWarnings("serial")
public class GameToolBar extends JToolBar {
	
	/*
	 * The Tool Bar consists of 4 JButtons
	 * Place Bet, Remove Bet, Spin Coin and Spin Spinner Coin
	 */
	private JButton placeBetButton;
	private JButton removeBetButton;
	private JButton spinCoinButton;
	private JButton spinSpinnerCoinButton;
	
	public GameToolBar(GameEngine gameModel, CoinGameFrame gameFrame) {
		
		// Sets the layout and colour of the Tool Bar
		LayoutManager toolBarLayout = new FlowLayout(FlowLayout.LEFT);
		this.setLayout(toolBarLayout);
		this.setBackground(Color.LIGHT_GRAY);
		
		// Instantiates the Place Bet Button
		placeBetButton = new JButton("Place Bet");
		// Sets an ActionListener to activate when a user clicks on it
		ActionListener placeBetActionListener = new PlaceBetActionListener(gameModel, gameFrame);
		placeBetButton.addActionListener(placeBetActionListener);
		placeBetButton.setEnabled(false);
		
		// Add Place Bet Button to the Tool Bar
		this.add(placeBetButton);
		
		// Instantiates the Remove Bet Button
		removeBetButton = new JButton("Remove Bet");
		// Sets an ActionListener to activate when a user clicks on it
		ActionListener removeBetActionListener = new RemoveBetActionListener(gameModel, gameFrame);
		removeBetButton.addActionListener(removeBetActionListener);
		removeBetButton.setEnabled(false);
		
		// Add Remove Bet Button to the Tool Bar
		this.add(removeBetButton);
		
		// Instantiates the Player Spin Coin Button
		spinCoinButton = new JButton("Spin Coins");
		// Sets an ActionListener to activate when a user clicks on it
		ActionListener spinCoinActionListener = new SpinCoinActionListener(gameModel, gameFrame);
		spinCoinButton.addActionListener(spinCoinActionListener);
		spinCoinButton.setEnabled(false);
		
		// Add Player Spin Coin Button to the Tool Bar
		this.add(spinCoinButton);
		
		// Instantiates the Spinner Spin Coin Button
		spinSpinnerCoinButton = new JButton("Spin Spinner Coins");
		// Sets an ActionListener to activate when it is being clicked
		ActionListener spinSpinnerCoinActionListener = new SpinSpinnerCoinActionListener(gameModel, gameFrame);
		spinSpinnerCoinButton.addActionListener(spinSpinnerCoinActionListener);
		spinSpinnerCoinButton.setEnabled(false);
		
		// Add Spinner Spin Coin Button to the Tool Bar
		this.add(spinSpinnerCoinButton);
	}
	
	// Returns a JButton type of the Spinner Spin Coin Button
	public JButton getSpinSpinnerCoinButton() {
		return spinSpinnerCoinButton;
	}
	
	/*
	 * The following methods below are used to change the state (can click or cannot click) of buttons based on
	 * the current state of the player and game 
	 */
	
	// Only Place Bet Button is enabled
	public void onlyEnablePlaceBetButton() {
		placeBetButton.setEnabled(true);
		removeBetButton.setEnabled(false);
		spinCoinButton.setEnabled(false);
		spinSpinnerCoinButton.setEnabled(false);
	}
	
	// Only Remove Bet and Player Spin Coin Buttons are enabled
	public void onlyEnableRemoveAndSpinCoinButton() {
		placeBetButton.setEnabled(false);
		removeBetButton.setEnabled(true);
		spinCoinButton.setEnabled(true);
		spinSpinnerCoinButton.setEnabled(false);
	}
	
	// All Buttons are disabled
	public void disableAllButtons() {
		placeBetButton.setEnabled(false);
		removeBetButton.setEnabled(false);
		spinCoinButton.setEnabled(false);
		spinSpinnerCoinButton.setEnabled(false);
	}
	
	// Only Spinner Spin Coin Button is enabled
	public void onlyEnableSpinSpinnerCoinButton() {
		placeBetButton.setEnabled(false);
		removeBetButton.setEnabled(false);
		spinCoinButton.setEnabled(false);
		spinSpinnerCoinButton.setEnabled(true);
	}
}
