package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.CoinGameFrame;

/*
 * This class does something when the user decides to make a select the spinner coin to spin
 * Note: If all players have bet and spun, this button is pressed automatically
 */
public class SpinSpinnerCoinActionListener implements ActionListener {

	private GameEngine gameModel;
	private CoinGameFrame gameFrame;
	
	/*
	 * PS: This is also seen in SpinCoinActionListener class
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
	
	public SpinSpinnerCoinActionListener(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameModel = gameModel;
		this.gameFrame = gameFrame;
	}
	
//	Thread is created so that there is still interaction with the game while the game is flipping coins at the back end
	@Override
	public void actionPerformed(ActionEvent event) {
		updateGameFrameWhileSpin();
		
		new Thread() {
			
			@Override
			public void run() {
				gameFrame.getGameViewModel().setSpinnerCoinSpinning(true);
				runSpinnerSpin();
			}
		}.start();
	}
	
	// Sets the corresponding components displays when the button is clicked
	public void updateGameFrameWhileSpin() {
		gameFrame.getGameToolBar().disableAllButtons();
		gameFrame.getGameMenuBar().disableAddAndRemovePlayerMenuItem();
		gameFrame.getGameStatusBar().spinnerSpinningStatusBar();
		gameFrame.repaint();
	}
	
	// Updates the GameEngine and CoinPanel (Coin images are changing)
	public void runSpinnerSpin() {
		gameModel.spinSpinner(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);
	}
}
