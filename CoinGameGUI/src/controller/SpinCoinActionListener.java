package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinGameFrame;

/*
 * This class does something when the user decides to make a player spin their coins
 * The spin button can only be enabled if a player has placed a bet
 */
public class SpinCoinActionListener implements ActionListener {
	
	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	private Player player;
	
	/*
	 * PS: This is also seen in SpinSpinnerCoinActionListener class
	 * The reason why is to allow customization.
	 * Player Spinning coin and Spinner Spinning coin delays can be of different timing.
	 * However, by default, both will use 100, 1000, 100 50, 500, 50
	 */
	private final static int initialDelay1 = 100;
	private final static int initialDelay2 = 50;
	private final static int finalDelay1 = 1000;
	private final static int finalDelay2 = 500;
	private final static int delayIncrement1 = 100;
	private final static int delayIncrement2 = 50;
	
	public SpinCoinActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}

	// Thread is created so that there is still interaction with the game while the game is flipping coins at the back end
	@Override
	public void actionPerformed(ActionEvent event) {
		updateGameFrameWhileSpin();

		new Thread() {
			
			@Override
			public void run() {
				gameFrame.getGameViewModel().setPlayerCoinSpinning(true);
				runPlayerSpin();
			}
			
		}.start();
	}
	
	// Sets the corresponding components displays when user clicks on this button
	public void updateGameFrameWhileSpin() {
		player = gameFrame.getGameStatusBar().getPlayer();
		gameFrame.getGameMenuBar().disableAddAndRemovePlayerMenuItem();
		gameFrame.getGameViewModel().setCurrentlySpinningPlayer(player);
		gameFrame.getGameToolBar().disableAllButtons();
		gameFrame.getGameStatusBar().playerSpinningStatusBar();
	}
	
	// Updates the GameEngine and CoinPanel (Coin images are changing)
	public void runPlayerSpin() {
		gameModel.spinPlayer(player, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
		gameFrame.getGameCoinPanel().getCoin1Panel().changeImage(player.getResult().getCoin1().getFace());
		gameFrame.getGameCoinPanel().getCoin2Panel().changeImage(player.getResult().getCoin2().getFace());
	}
}
