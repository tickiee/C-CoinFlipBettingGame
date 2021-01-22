package view;

import javax.swing.SwingUtilities;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/*
 * This class is the GUI version of a GameEngineCallback
 * It is used to pass informaton to the main frame and its specific components
 */
public class GameEngineCallbackGUI implements GameEngineCallback {
	
	private CoinGameFrame gameFrame;
	
	public GameEngineCallbackGUI(CoinGameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		if(gameFrame.getGameStatusBar().getPlayer().equals(player)) {
			runCoinUpdate(coin);
		}
	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		runCoinUpdate(coin);
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		gameFrame.getGameViewModel().addPlayerSpunList(player);
		gameFrame.getGameViewModel().setPlayerCoinSpinning(false);
		gameFrame.getGameMenuBar().enableAddAndRemovePlayerMenuItem();
		gameFrame.getGameToolBar().onlyEnableSpinSpinnerCoinButton();
		
		// if you are the current player who spun
		if(gameFrame.getGameStatusBar().getPlayer().equals(player)) {
			if(gameFrame.getGameViewModel().checkIfAllPlayersHaveSpun()) {
				gameFrame.getGameToolBar().onlyEnableSpinSpinnerCoinButton();
//				gameFrame.getGameStatusBar().spinnerSpinningStatusBar();
				gameFrame.getGameViewModel().runSpinSpinnerButton();
			} else {
				if(gameFrame.getGameViewModel().checkIfAllBetPlayersHaveSpun()) {
					gameFrame.getGameToolBar().onlyEnableSpinSpinnerCoinButton();
				} else {
					gameFrame.getGameToolBar().disableAllButtons();
				}
				CoinFace coin1Face = gameFrame.getGameCoinPanel().getCoin1Panel().getCoinFace();
				CoinFace coin2Face = gameFrame.getGameCoinPanel().getCoin2Panel().getCoinFace();
				gameFrame.getGameStatusBar().playerHasSpunStatusBar(coin1Face, coin2Face);
//				gameFrame.getGameToolBar().disableAllButtons();
			}
		} else {
			gameFrame.getGameToolBar().disableAllButtons();
			gameFrame.getGameStatusBar().informCurrentPlayerCoinIsSpun();
		}
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		gameFrame.getGameStatusBar().informGameHasEnded();
		gameFrame.getGameToolBar().onlyEnablePlaceBetButton();
		gameFrame.getGameMenuBar().enableAddAndRemovePlayerMenuItem();
		gameFrame.getGameViewModel().setSpinnerCoinSpinning(false);
		gameFrame.getGameViewModel().getPlayerWinLoseResult();
		gameFrame.getGameViewModel().resetAllLists();
		gameFrame.getGameViewModel().removePlayersWithNoPoints();
		
	}
	
	/*
	 *  This method is created to avoid code duplication as it is used it both playerCoinUpdate() and
	 *  spinnerCoinUpdater() methods 
	 */
	private void runCoinUpdate(Coin coin) {
		SwingUtilities.invokeLater(new Runnable() {	
			
			@Override
			public void run() {
				if(coin.getNumber() == 1) {
					gameFrame.getGameCoinPanel().getCoin1Panel().changeImage(coin.getFace());
				} else {
					gameFrame.getGameCoinPanel().getCoin2Panel().changeImage(coin.getFace());
				}
				
				gameFrame.repaint();
			}
		});
	}
}
