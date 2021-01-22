package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialog.ErrorMessageDialog;
import dialog.PlaceBetDialog;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import regex.RegexChecker;
import view.CoinGameFrame;

/*
 * This class does something when the user selects Place Bet Button
 */
public class PlaceBetActionListener implements ActionListener {

	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	private Player player;
	
	private PlaceBetDialog placeBetDialog;	
	private ErrorMessageDialog errorPopup;
	
	private String playerBet;
	private BetType playerBetType;
	
	public PlaceBetActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		displayDialog();
		
		// Sets the player who clicked the button 
		player = gameFrame.getGameStatusBar().getPlayer();
		
		// if user inputs when asked to place bet is all good, enter if statement
		if(placeBet()) {
			// if bet is not 0 or BetType is not NO_BET, enter if statement
			if(!(playerBet.equals("0") || playerBetType == BetType.NO_BET)) {
				int playerBetInInt = Integer.parseInt(playerBet);
				// pushes bet and BetType to GameEngine, including the player who placed the bet
				gameModel.placeBet(player, playerBetInInt, playerBetType);
				// displays bet and BetType of the player who placed the bet
				// Adds player to a bet collection (explained in GameViewModel class)
				gameFrame.getGameViewModel().addPlayerToBetList(player);
				// updates the status bar
				gameFrame.getGameStatusBar().afterPlayerPlaceBetStatusBar();
				// updates the tool bar
				gameFrame.getGameToolBar().onlyEnableRemoveAndSpinCoinButton();
				gameFrame.repaint();
			}			
		}
	}
	
	// Displays the dialog box
	public void displayDialog() {
		placeBetDialog = new PlaceBetDialog(gameFrame).placeBet();
	}
	
	// Checks if the user input is valid
	public boolean placeBet() {
		boolean validatorChecker = true;
		errorPopup = new ErrorMessageDialog();

		playerBet = placeBetDialog.getBet();
		playerBetType = placeBetDialog.getBetType();
		
		// Reference to a custom regex class created by me and passes the string for pattern matching
		RegexChecker regex = new RegexChecker();
		
		// if user input anything that is not an integer that is 0 or more
		if(!regex.regexPlayerBet(playerBet)) {
			errorPopup.runErrorDialogBox(gameFrame, "Bet must be an integer that is at least 0");
			validatorChecker = false;	
		// if user inputs an integer but the integer is higher than how much points he currently has
		} else if(Integer.parseInt(playerBet) > player.getPoints()) {
			errorPopup.runErrorDialogBox(gameFrame, "Bet must be within your points");
			validatorChecker = false;
		}
		
		return validatorChecker;
	}
}
