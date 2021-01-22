package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinGameFrame;

/*
 * This class is used for testing purposes.
 * It adds players to the game, allowing for fast and quicker testing.
 * Use it at your own risk! :) 
 * (PS: I did not remove this class/JMenuItem in case you (the marker) would like to use it.
 * 	If there are any errors, I am sorry.)
 * 
 * (PS: If you add a player before Seeding Player, you cannot seed player. This is a limitation that
 * 	shows theres room for improvement in my code.)
 */
public class SeedPlayerActionListener implements ActionListener {
	
	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	
	public SeedPlayerActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		run();
		gameFrame.getGameMenuBar().disableSeedPlayerMenuItem();
	}
	
	public void run() {
		seedAddPlayer("A", 1000);
		seedAddPlayer("B", 100);
		seedAddPlayer("C", 10);
		seedAddPlayer("D", 1);
		seedAddPlayer("E", 300);
		seedAddPlayer("F", 500);
		seedAddPlayer("G", 70);
	}
	
	public void seedAddPlayer(String playerName, int initialPoints) {
		int playerSize = gameModel.getAllPlayers().size();
		String playerID = String.valueOf(playerSize + 1);
		Player player = new SimplePlayer(playerID, playerName, initialPoints);
		gameModel.addPlayer(player);
		gameFrame.getGameSummaryPanel().addPlayer(player);
		gameFrame.getGameStatusBar().selectPlayerStatusBar();
	}
}
