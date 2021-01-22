package controller;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.enumeration.BetType;
import model.enumeration.CoinFace;
import model.interfaces.Player;
import view.CoinGameFrame;

/*
 * This class does something when the user selects a player from the SummaryPanel
 */
public class PlayerListListener implements ListSelectionListener {
	
	private CoinGameFrame gameFrame;
	
	// The list of players
	private JList<Player> playerList;
	
	// The specific player, which will be used to track the current player being highlighted
	private Player player;
	
	public PlayerListListener(CoinGameFrame gameFrame, JList<Player> playerList) {
		this.gameFrame = gameFrame;
		this.playerList = playerList;
		this.player = null;
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		try {
			player = playerList.getSelectedValue();
			
			// if spinner is spinning
			if(gameFrame.getGameViewModel().getSpinnerCoinSpinning()) {
				spinnerSpinning();
			// if player is spinning
			} else if(gameFrame.getGameViewModel().getPlayerCoinSpinning()) {
				// if the player that is you is spinning
				if(gameFrame.getGameViewModel().getCurrentlySpinningPlayer().equals(player)) {
					playerCurrentlySpinning();
				// if the player that is not you is spinning
				} else {
					anotherPlayerCurrentlySpinning();
				}
			} else {
				// if player has no valid bet
				if(player.getBet() == 0 || player.getBetType() == BetType.NO_BET) {
					pleasePlaceBet();
				// if player has bet but not spun
				} else if(player.getBet() > 0 && player.getBetType() != BetType.NO_BET &&
						!gameFrame.getGameViewModel().checkIfPlayerHasSpun(player) &&
						!gameFrame.getGameViewModel().getPlayerCoinSpinning()) {
					placedBet();
				
				// if player has bet and spun
				} else if(player.getBet() > 0 && player.getBetType() != BetType.NO_BET &&
							gameFrame.getGameViewModel().checkIfPlayerHasSpun(player)) {
					playerHasSpun();
				} 
			}

			gameFrame.repaint();		
		} catch(Exception exception) {
			
		}
	}
	
	// Returns a player type of a player
	public Player getPlayerFromList() {
		return player;
	}
	
	/*
	 *  Below are methods that are excuted based on the current state of the game,
	 *  which is based on valueChanged() method 
	 */
	public void spinnerSpinning() {
		gameFrame.getGameStatusBar().setPlayer(player);
		gameFrame.getGameToolBar().disableAllButtons();
		gameFrame.getGameStatusBar().spinnerSpinningStatusBar();
		gameFrame.getGameMenuBar().disableAddAndRemovePlayerMenuItem();
	}
	
	public void pleasePlaceBet() {
		gameFrame.getGameStatusBar().setPlayer(player);
		gameFrame.getGameToolBar().onlyEnablePlaceBetButton();
		gameFrame.getGameStatusBar().askPlayerToAddBetStatusBar();
		gameFrame.getGameMenuBar().enableAddAndRemovePlayerMenuItem();
	}
	
	public void placedBet() {
		gameFrame.getGameStatusBar().setPlayer(player);
		gameFrame.getGameStatusBar().afterPlayerPlaceBetStatusBar();
		gameFrame.getGameToolBar().onlyEnableRemoveAndSpinCoinButton();
		gameFrame.getGameMenuBar().enableAddAndRemovePlayerMenuItem();
	}
	
	public void playerHasSpun() {
		gameFrame.getGameStatusBar().setPlayer(player);
		CoinFace coin1Face = player.getResult().getCoin1().getFace();
		CoinFace coin2Face = player.getResult().getCoin2().getFace();
		gameFrame.getGameStatusBar().playerHasSpunStatusBar(coin1Face, coin2Face);
		gameFrame.getGameCoinPanel().getCoin1Panel().changeImage(coin1Face);
		gameFrame.getGameCoinPanel().getCoin2Panel().changeImage(coin2Face);
		gameFrame.getGameMenuBar().enableAddAndRemovePlayerMenuItem();
		
		// if all players that have bet, have spun, enter if, otherwise, enter else
		if(gameFrame.getGameViewModel().checkIfAllBetPlayersHaveSpun()) {
			gameFrame.getGameToolBar().onlyEnableSpinSpinnerCoinButton();
		} else {
			gameFrame.getGameToolBar().disableAllButtons();
		}
	}
	
	public void anotherPlayerCurrentlySpinning() {
		gameFrame.getGameStatusBar().setPlayer(player);
		gameFrame.getGameToolBar().disableAllButtons();
		gameFrame.getGameStatusBar().anotherPlayerSpinningStatusBar();
		gameFrame.getGameMenuBar().disableAddAndRemovePlayerMenuItem();
	}
	
	public void playerCurrentlySpinning() {
		gameFrame.getGameStatusBar().setPlayer(player);
		gameFrame.getGameToolBar().disableAllButtons();
		gameFrame.getGameStatusBar().playerSpinningStatusBar();
		gameFrame.getGameMenuBar().disableAddAndRemovePlayerMenuItem();
	}
}
