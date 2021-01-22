package main;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.CoinGameFrame;

public class Driver {

	public static void main(String[] args) {
		GameEngine gameModel = new GameEngineImpl();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new CoinGameFrame(gameModel);
			}
		});
	}
}