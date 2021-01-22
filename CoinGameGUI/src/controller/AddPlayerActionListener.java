package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialog.AddPlayerDialog;
import dialog.ErrorMessageDialog;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import regex.RegexChecker;
import view.CoinGameFrame;

/*
 * This class does something when the user selects Add Player in the drop down menu
 */
public class AddPlayerActionListener implements ActionListener {
	
	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	private AddPlayerDialog addPlayerDialog;	
	private ErrorMessageDialog errorPopup;
	
	private String playerName;
	private String playerInitialPoints;
	
	public AddPlayerActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		displayDialog();
		
		// if user inputs when asked to add player details is all good, enter if statement
		if(addPlayer()) {
			int playerInitialPointsInInt = Integer.parseInt(playerInitialPoints);
			
			int playerSize = gameModel.getAllPlayers().size();
			String playerID = String.valueOf(playerSize + 1);
			
			// Creates the player
			Player player = new SimplePlayer(playerID, playerName, playerInitialPointsInInt);
			
			// Adds the player to the GameEngine
			gameModel.addPlayer(player);
			
			// Adds the player to the main frame
			gameFrame.getGameSummaryPanel().addPlayer(player);
			gameFrame.getGameStatusBar().selectPlayerStatusBar();
			gameFrame.getGameMenuBar().disableSeedPlayerMenuItem();
		}
	}
	
	// Displays the dialog box
	public void displayDialog() {
		addPlayerDialog = new AddPlayerDialog(gameFrame).addPlayer();
	}
	
	// Checks if the user input is valid
	public boolean addPlayer() {
		boolean validatorChecker = true;
		errorPopup = new ErrorMessageDialog();
		
		playerName = addPlayerDialog.getPlayerName();
		playerInitialPoints = addPlayerDialog.getPlayerInitialPoints();
		
		// Checks if player name is empty
		if(playerName.equals("")) {
			playerInitialPoints = "";
			errorPopup.runErrorDialogBox(gameFrame, "Player Name is empty");
			validatorChecker = false;
		// Checks if player initial points is empty
		} else if(playerInitialPoints.equals("")) {
			errorPopup.runErrorDialogBox(gameFrame, "Initial Points is empty");
			validatorChecker = false;
		} else {
			// Reference to a custom regex class created by me and passes the string for pattern matching
			RegexChecker regex = new RegexChecker();
			// If pattern does not match, error popup will appear
			if(!regex.regexInitialPoints(playerInitialPoints)) {
				errorPopup.runErrorDialogBox(gameFrame, "Initial Points must be an integer that is more than 0");
				validatorChecker = false;
			}
		}
		
		// Checks if player name is unique
		for(Player player: gameModel.getAllPlayers()) {
			if(playerName.equals(player.getPlayerName())) {
				errorPopup.runErrorDialogBox(gameFrame, "Player Name is not unique");
				validatorChecker = false;
			}
		}
		
		return validatorChecker;
	}
}
