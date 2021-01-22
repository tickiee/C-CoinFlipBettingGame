package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialog.ErrorMessageDialog;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinGameFrame;

/*
 * This class does something when the user removes a player
 */
public class RemovePlayerActionListener implements ActionListener {

	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	private ErrorMessageDialog errorPopup;
	private Player removePlayer;
	
	public RemovePlayerActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		errorPopup = new ErrorMessageDialog();
		removePlayer = gameFrame.getGameStatusBar().getPlayer();
		
		// If you click remove player but never selected any player beforehand
		if(removePlayer == null) {
			errorPopup.runErrorDialogBox(gameFrame, "Please select a Player");
		/*
		 *  Checks if you are trying to remove a player that has already spun
		 *  You should not be able to delete a player that has already spun
		 */
		} else if(gameFrame.getGameViewModel().checkIfPlayerHasSpun(removePlayer)) {
			errorPopup.runErrorDialogBox(gameFrame, "You cannot delete a Player that have spun");
		} else {
			gameModel.removePlayer(removePlayer);
			gameFrame.getGameSummaryPanel().removePlayer(removePlayer);
			gameFrame.getGameStatusBar().selectPlayerStatusBar();
		}
		
		gameFrame.getGameToolBar().disableAllButtons();
		
		// Set game Status Bar to please add a new player
		if(gameModel.getAllPlayers().size() == 0) {
			gameFrame.getGameStatusBar().noPlayerStatusBar();
		// If all players currently in the list have bet and spun
		} else if(gameFrame.getGameViewModel().checkIfAllPlayersHaveSpun()) {
			gameFrame.getGameToolBar().onlyEnableSpinSpinnerCoinButton();
			gameFrame.getGameViewModel().runSpinSpinnerButton();
		// If not all players have bet and spun
		} else {
			gameFrame.getGameStatusBar().selectPlayerStatusBar();
		}
	}
}
