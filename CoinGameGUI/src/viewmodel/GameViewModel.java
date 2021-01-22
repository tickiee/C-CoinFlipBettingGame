package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinGameFrame;

public class GameViewModel {
	
	private GameEngine gameModel;
	private CoinGameFrame gameFrame;

	/*
	 *  This list keeps track of all players who have bet.
	 *  It does not track whether players have spun 
	 */
	private List<Player> playerBetList = new ArrayList<Player>();
	
	/*
	 *  This list keeps track of all players who have spun.
	 *  Since players can only spin when they have bet, it indirectly keeps track of players who have bet
	 */
	private List<Player> playerSpunList = new ArrayList<Player>();
	
	/*
	 * This list keeps track of the before bet points of players who have spun
	 * This is used to determine whether they won or loss the round.
	 */
	private List<Integer> storePlayerForPoints = new ArrayList<Integer>();
	
	// These booleans are to keep track if a player or spinner coin is spinning
	private boolean playerCoinSpinning = false;
	private boolean spinnerCoinSpinning = false;
	
	// This is to hold the player who is currently spinning
	private Player currentlySpinningPlayer;
	
	public GameViewModel(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}
	
	// Adds the player who placed the bet to this collection
	public void addPlayerToBetList(Player player) {
		playerBetList.add(player);
	}
	
	// Removes player who placed the bet from the collection
	public void removePlayerFromBetList(Player player) {
		playerBetList.remove(player);
	}

	// Adds the player who spun their coins to this collection
	public void addPlayerSpunList(Player player) {
		playerSpunList.add(player);
		storePlayerForPoints.add(player.getPoints());
	}
	
	// Get the win or lose of all players
	public void getPlayerWinLoseResult() {
		int index = 0;
		
		for(Player playerSpun: playerSpunList) {
			for(Player allPlayers: gameModel.getAllPlayers()) {
				if(playerSpun.getPlayerName().equals(allPlayers.getPlayerName())) {
					if(storePlayerForPoints.get(index) > allPlayers.getPoints()) {
						gameFrame.getGameSummaryPanel().getRenderer().setLastResult(allPlayers, "Loss");
					} else {
						gameFrame.getGameSummaryPanel().getRenderer().setLastResult(allPlayers, "Won");
					}
					
					index = index + 1;
				}
			}
		}
		
		gameFrame.repaint();
	}
	
	/*
	 *  Returns a boolean that checks if all players who have bet have also all spun
	 *  If all players who have bet have spun, return true, otherwise return false
	 */
	public boolean checkIfAllBetPlayersHaveSpun() {
		boolean boolChecker = false;
		
		if(playerBetList.size() == playerSpunList.size()) {
			boolChecker = true;
		}
		
		return boolChecker;
	}
	
	/*
	 * Returns a boolean that checks if every player in the game have spun
	 * If all players have spun, return true, otherwise return false
	 */
	public boolean checkIfAllPlayersHaveSpun() {
		boolean boolChecker = false;
		
		if(gameModel.getAllPlayers().size() == playerSpunList.size()) {
			boolChecker = true;
		}
		
		return boolChecker;
	}
	
	/*
	 * Returns a boolean that checks if a specific player has spun
	 * If the speciifc player has spun, return true, otherwise return false
	 */
	public boolean checkIfPlayerHasSpun(Player player) {
		boolean boolChecker = false;
		
		if(playerSpunList.size() > 0) {
			for(Player playerInList: playerSpunList) {
				if(player.getPlayerName().equals(playerInList.getPlayerName())) {
					boolChecker = true;
				}
			}
		}
		
		return boolChecker;
	}

	// Returns an integer that is the size of the players who have spun
	public int getPlayerSpunListSize() {
		return playerSpunList.size();
	}
	
	// Returns a boolean that checks if there is any player currently spinning their coins
	public boolean getPlayerCoinSpinning() {
		return playerCoinSpinning;
	}
	
	// Sets if the player is spinning their coin
	public void setPlayerCoinSpinning(boolean playerCoinSpinning) {
		this.playerCoinSpinning = playerCoinSpinning;
	}
	
	// Returns a boolean that checks if the spinner is currently spinning his coins
	public boolean getSpinnerCoinSpinning() {
		return spinnerCoinSpinning;
	}
	
	// Sets the spinner spinning their coin
	public void setSpinnerCoinSpinning(boolean spinnerCoinSpinning) {
		this.spinnerCoinSpinning = spinnerCoinSpinning;
	}
	
	// Returns a player type of the player who is currently spinning their coins
	public Player getCurrentlySpinningPlayer() {
		return currentlySpinningPlayer;
	}

	// Sets the player to the ViewModel to track who is currently spinning
	public void setCurrentlySpinningPlayer(Player currentlySpinningPlayer) {
		this.currentlySpinningPlayer = currentlySpinningPlayer;
	}
	
	/*
	 *  When the round has ended, all lists have to be cleared
	 *  Otherwise, the ViewModel will not remove these players and will assume that they have either bet or spun
	 */
	public void resetAllLists() {
		playerBetList.clear();
		playerSpunList.clear();
		storePlayerForPoints.clear();
	}
	
	// Clicks the spinner button when called
	public void runSpinSpinnerButton() {
		gameFrame.getGameToolBar().getSpinSpinnerCoinButton().doClick();
	}
	
	// If a player has no points after the round, player is removed from the game 
	public void removePlayersWithNoPoints() {
		for(Player player: gameModel.getAllPlayers()) {
			if(player.getPoints() <= 0) {
				gameModel.removePlayer(player);
				gameFrame.getGameSummaryPanel().removePlayer(player);
			}
		}
		
		gameFrame.repaint();
		
		// If there are no players left in the game, view will ask user to add a new player
		if(gameModel.getAllPlayers().size() == 0) {
			gameFrame.getGameToolBar().disableAllButtons();
			gameFrame.getGameStatusBar().noPlayerStatusBar();
		}
	}
}
