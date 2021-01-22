package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerActionListener;
import controller.RemovePlayerActionListener;
import controller.SeedPlayerActionListener;
import model.interfaces.GameEngine;

/*
 * This is the Menu Bar that is seen at the top of the game
 */
@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar {
	
	// A JMenuItem that allows the user to add player
	private JMenuItem addPlayer;
	// A JMenuItem that allows the user to remove player
	private JMenuItem removePlayer;
	// This is used for testing purposes (See SeedPlayerActionListener class)
	private JMenuItem seedPlayer;

	public GameMenuBar(GameEngine gameModel, CoinGameFrame gameFrame) {
		// Creates the JMenu
		JMenu playerMenu = new JMenu("Player Menu");	
		playerMenu.setMnemonic('P');
		Font playerMenuFont = new Font("Player Font", Font.BOLD, 20);
		playerMenu.setFont(playerMenuFont);
		
		// Adds the JMenu to the JMenuBar
		this.add(playerMenu);
		
		// Instantiates a Add Player JMenuItem
		addPlayer = new JMenuItem("Add Player");
		addPlayer.setMnemonic('A');
		Font addPlayerFont = new Font("Add Player Font", Font.PLAIN, 18);
		addPlayer.setFont(addPlayerFont);
		addPlayer.setActionCommand("addplayer");
		// Sets an ActionListener to activate when a user clicks on it
		ActionListener addPlayerActionListener = new AddPlayerActionListener(gameModel, gameFrame);
		addPlayer.addActionListener(addPlayerActionListener);
		
		// Adds Add Player JMenuItem to the Player Menu JMenu
		playerMenu.add(addPlayer);
		
		// Instantiates a Remove Player JMenuItem
		removePlayer = new JMenuItem("Remove Player");
		removePlayer.setMnemonic('R');
		Font removePlayerFont = new Font("Remove Player Font", Font.PLAIN, 18);
		removePlayer.setFont(removePlayerFont);
		removePlayer.setActionCommand("removeplayer");
		// Sets an ActionListener to activate when a user clicks on it
		ActionListener removePlayerActionListener = new RemovePlayerActionListener(gameModel, gameFrame);
		removePlayer.addActionListener(removePlayerActionListener);
		
		// Adds Remove Player JMenuItem to the Player Menu JMenu
		playerMenu.add(removePlayer);
		
		/*
		 * (PS: The JMenuItem below is for testing purposes (See SeedPlayerActionListener class))
		 */
		
		// Instantiates a Seed Player JMenuItem
		seedPlayer = new JMenuItem("Seed Player");
		seedPlayer.setMnemonic('S');
		Font seedPlayerFont = new Font("Seed Player Font", Font.PLAIN, 18);
		seedPlayer.setFont(seedPlayerFont);
		seedPlayer.setActionCommand("seedplayer");
		// Sets an ActionListener to activate when a user clicks on it
		ActionListener seedPlayerActionListener = new SeedPlayerActionListener(gameModel, gameFrame);
		seedPlayer.addActionListener(seedPlayerActionListener);
		
		// Adds Seed Player JMenuItem to the Player Menu JMenu
		playerMenu.add(seedPlayer);
	}
	
	// Disable all JMenu items (excludes Seed Player JMenuItem)
	public void disableAddAndRemovePlayerMenuItem() {
		addPlayer.setEnabled(false);
		removePlayer.setEnabled(false);
	}
	
	// Disable all JMenu items (excludes Seed Player JMenuItem)
	public void enableAddAndRemovePlayerMenuItem() {
		addPlayer.setEnabled(true);
		removePlayer.setEnabled(true);
	}
	
	// Disable Seed Player JMenuItem
	public void disableSeedPlayerMenuItem() {
		seedPlayer.setEnabled(false);
	}
}
