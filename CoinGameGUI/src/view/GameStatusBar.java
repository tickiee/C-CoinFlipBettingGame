package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enumeration.CoinFace;
import model.interfaces.Player;

/*
 * This is the Status Bar
 */
@SuppressWarnings("serial")
public class GameStatusBar extends JPanel {
	
	/*
	 * There are 3 JLabels in the Status Bar
	 * GameStatus is the current system/user prompt
	 * PlayerStatus & CoinStatus are the current state of the Player and Coin respectively
	 * More can be seen in the methods below
	 */
	private JLabel gameStatus;
	private JLabel playerStatus;
	private JLabel coinStatus;
	private Player player;
	
	private final static String playerStatusDisplay = "Player Status: ";
	private final static String coinStatusDisplay = "Coin Status: ";
	
	public GameStatusBar(CoinGameFrame gameFrame) {
		
		// Creates the status bar layout as a GridLayout
		LayoutManager statusBarLayout = new GridLayout(0, 1);
		this.setLayout(statusBarLayout);
		
		// When the game first starts, this is the status bar display
		gameStatus = new JLabel("Welcome to Coin Game!!! Please add a New Player");
		playerStatus = new JLabel(playerStatusDisplay + "Not Applicable");
		coinStatus = new JLabel(coinStatusDisplay+ "Not Applicable");
		
		// This is the border to define each JLabel
		gameStatus.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		playerStatus.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		coinStatus.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		
		// Adds each JLabel in the Status Bar
		this.add(gameStatus);
		this.add(playerStatus);
		this.add(coinStatus);
	}
	
	/*
	 * When there are no players in the game
	 *  (PS: Does not include the beginning since the beginning displays a welcome message)
	 */
	public void noPlayerStatusBar() {
		gameStatus.setText("Please add a new Player");
		playerStatus.setText(playerStatusDisplay + "Not Applicable");
		coinStatus.setText(coinStatusDisplay + "Not Applicable");
	}
	
	/*
	 * Prompting the user to select a player
	 */
	public void selectPlayerStatusBar() {
		gameStatus.setText("Please Select Player");
		playerStatus.setText(playerStatusDisplay + "Not Applicable");
		coinStatus.setText(coinStatusDisplay + "Not Applicable");
	}

	/*
	 * Prompting the user to enter a bet of the seleceted player
	 */
	public void askPlayerToAddBetStatusBar() {
		gameStatus.setText("Please Add Bet");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " is Selected");
		coinStatus.setText(coinStatusDisplay + "Idle");
	}
	
	/*
	 * Game state display that the player has placed a bet and is ready to spin
	 */
	public void afterPlayerPlaceBetStatusBar() {
		gameStatus.setText("Placed Bet");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " is ready to Spin");
		coinStatus.setText(coinStatusDisplay + "Awaiting for Spin");
	}
	
	/*
	 * Game state display that tells the user there is another player currently spinning
	 */
	public void anotherPlayerSpinningStatusBar() {
		gameStatus.setText("Please wait");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " is waiting for another player to finish spinning");
		coinStatus.setText(coinStatusDisplay + "Idle");
	}
	
	/*
	 * Game state display that tells the user the current player is currently spinning coins
	 */
	public void playerSpinningStatusBar() {
		gameStatus.setText("Please wait");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " is spinning coins");
		coinStatus.setText(coinStatusDisplay + "Spinning");
	}
	
	/*
	 * Game state display that tells the user the spinner is currently spinning
	 */
	public void spinnerSpinningStatusBar() {
		gameStatus.setText("Please wait");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " is waiting for Spinner");
		coinStatus.setText(coinStatusDisplay + "Idle");
	}
	
	/*
	 * Game state display that appears when a player has already spun coins but there are other players who
	 * have not bet and spun
	 */
	public void playerHasSpunStatusBar(CoinFace coin1Face, CoinFace coin2Face) {
		gameStatus.setText("Awaiting for other players and Spinner's turn");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " has spun coins");
		coinStatus.setText(coinStatusDisplay + "Coin 1 Spin Result - " + coin1Face.toString() + 
												" Coin 2 Spin Result - " + coin2Face.toString());
	}
	
	/*
	 * Game state display that appears when there is a player spinning coins but is not the current player selected
	 * This display prompts the user that the coins have been spun and the result has been finalized for that player
	 * User has to switch to another player to continue game 
	 */
	public void informCurrentPlayerCoinIsSpun() {
		gameStatus.setText("Coin has been spun, all buttons are disabled for " + player.getPlayerName() + " until you change player");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " idle");
		coinStatus.setText(coinStatusDisplay + "Idle");
	}
	
	/*
	 * Game state display that appears after the spinner has spun and all players have collected their win or loss bets
	 */
	public void informGameHasEnded() {
		gameStatus.setText("Game has ended");
		playerStatus.setText(playerStatusDisplay + player.getPlayerName() + " is selected");
		coinStatus.setText(coinStatusDisplay + "Idle");
	}
	
	// Returns a Player type of a player
	public Player getPlayer() {
		return player;
	}
	
	// Sets a player to the player in the class
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// Returns the GameStatus text
	public String getGameStatusText() {
		return gameStatus.getText();
	}
}
