package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinGameFrame;

/*
 * This class does something when the user removes the player's bet
 * The button can only be clicked if there is a bet placed by that player
 */
public class RemoveBetActionListener implements ActionListener {
	
	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	private Player player;
	
	public RemoveBetActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		player = gameFrame.getGameStatusBar().getPlayer();
		gameModel.placeBet(player, 0, BetType.NO_BET);
		gameFrame.repaint();
		gameFrame.getGameToolBar().onlyEnablePlaceBetButton();
		gameFrame.getGameStatusBar().askPlayerToAddBetStatusBar();
		gameFrame.getGameViewModel().removePlayerFromBetList(player);
	}
}
