package view;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import model.interfaces.GameEngine;

/*
 * This is the CoinPanel
 */
@SuppressWarnings("serial")
public class GameCoinPanel extends JPanel {
	
	private LayoutManager coinPanelLayout;
	private GameSpecificCoinPanel coin1Panel;
	private GameSpecificCoinPanel coin2Panel;

	public GameCoinPanel(GameEngine gameModel, CoinGameFrame gameFrame) {
		
		// Sets the JPanel layout to GridLayout
		coinPanelLayout = new GridLayout(1, 0);		
		this.setLayout(coinPanelLayout);
		
		// Creates 2 Coin panels that are JPanels so that it can show its coin face individually
		coin1Panel = new GameSpecificCoinPanel();
		this.add(coin1Panel);
		
		coin2Panel = new GameSpecificCoinPanel();
		this.add(coin2Panel);
	}
	
	// Returns the Coin 1 JPanel
	public GameSpecificCoinPanel getCoin1Panel() {
		return coin1Panel;
	}
	
	// Returns the Coin 2 JPanel
	public GameSpecificCoinPanel getCoin2Panel() {
		return coin2Panel;
	}
}
