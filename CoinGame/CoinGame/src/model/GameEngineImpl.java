package model;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enumeration.BetType;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	
	private Collection<GameEngineCallback> gecbi = new ArrayList<GameEngineCallback>();
	private Map<String, Player> players = new HashMap<String, Player>();

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1,
			int delayIncrement1, int initialDelay2, int finalDelay2, int delayIncrement2)
			throws IllegalArgumentException {
		
		CoinPair coinPair = new CoinPairImpl();
		Coin coin1 = coinPair.getCoin1();
		Coin coin2 = coinPair.getCoin2();
		
		spinningExceptionCheckers(initialDelay1, finalDelay1, delayIncrement1, 
									initialDelay2, finalDelay2, delayIncrement2);
		
//		spinning(coin1, coin2, initialDelay1, finalDelay1, delayIncrement1, player);
		spinning(coin1, coin2, initialDelay1, finalDelay1, delayIncrement1, 
				initialDelay2, finalDelay2, delayIncrement2, player);
		
		int sleepDelay1 = calculateSleepDelay(initialDelay1, finalDelay1, delayIncrement1);
		int sleepDelay2 = calculateSleepDelay(initialDelay2, finalDelay2, delayIncrement2);
		sleeping(Math.max(sleepDelay1, sleepDelay2) + 100);
	
		player.setResult(coinPair);
		resultOutput(player, coinPair);
	}
	
	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1,
			int initialDelay2, int finalDelay2, int delayIncrement2) 
					throws IllegalArgumentException {
		
		CoinPair coinPair = new CoinPairImpl();
		Coin coin1 = coinPair.getCoin1();
		Coin coin2 = coinPair.getCoin2();
		
		spinningExceptionCheckers(initialDelay1, finalDelay1, delayIncrement1, 
									initialDelay2, finalDelay2, delayIncrement2);
		
//		spinning(coin1, coin2, initialDelay1, finalDelay1, delayIncrement1, null);
		spinning(coin1, coin2, initialDelay1, finalDelay1, delayIncrement1, 
				initialDelay2, finalDelay2, delayIncrement2, null);
		
		int sleepDelay1 = calculateSleepDelay(initialDelay1, finalDelay1, delayIncrement1);
		int sleepDelay2 = calculateSleepDelay(initialDelay2, finalDelay2, delayIncrement2);
		sleeping(Math.max(sleepDelay1, sleepDelay2) + 100);
		
		applyBetResults(coinPair);
		resultOutput(null, coinPair);
	}
	
	/*
	 * Description:
	 * This method is used by spinPlayer and spinSpinner methods to check if 
	 * the delays are not correct based on the specification.
	 */
	private void spinningExceptionCheckers(int initialDelay1, int finalDelay1,
			int delayIncrement1, int initialDelay2, int finalDelay2, int delayIncrement2) {
		if (initialDelay1 == 0 || finalDelay1 == 0 || delayIncrement1 == 0 ||
				initialDelay2 == 0 || finalDelay2 == 0 || delayIncrement2 == 0) {
			throw new IllegalArgumentException("Delay 0");
		}
		else if (finalDelay1 < initialDelay1 || finalDelay2 < initialDelay2) {
			throw new IllegalArgumentException("Final less than initial");
		}
		else if(delayIncrement1 > (finalDelay1 - initialDelay1) || 
				delayIncrement2 > (finalDelay2 - initialDelay2)) {
			throw new IllegalArgumentException("Delay Incrememt exceed");
		}
	}
	
	private int calculateSleepDelay(int initialDelay, int finalDelay, int delayIncrement) {
		int sleepDelay = 0;
		int delay = 0;
		
		for(delay = initialDelay; delay < finalDelay; delay = delay + delayIncrement) {
			sleepDelay = sleepDelay + delay;
		}
		
		sleepDelay = sleepDelay + delay;
		
		return sleepDelay;
	}
	
	/*
	 * Description:
	 * This method spins the coin for the player and spinner.
	 * A player will have a player value while a spinner will have a null value.
	 * This value will be passed into gameEngineCallbackLooping() method, which will
	 * have different outputs if it is a player or a spinner.
	 */
//	private void spinning(Coin coin1, Coin coin2, int initialDelay1, 
//							int finalDelay1, int delayIncrement1, Player player) {
//		for(int delay = initialDelay1; delay < finalDelay1; delay = delay + delayIncrement1) {
//			coin1.flip();
//			coin2.flip();
//			sleeping(delay);
//			gameEngineCallbackLooping(player, coin1);
//			gameEngineCallbackLooping(player, coin2);
//		}
//	}
	
	private void spinning(Coin coin1, Coin coin2, int initialDelay1, int finalDelay1, int delayIncrement1, 
							int initialDelay2, int finalDelay2, int delayIncrement2, Player player) {
		new Thread() {
			
			@Override
			public void run() {
				for(int delay1 = initialDelay1; delay1 < finalDelay1; delay1 = delay1 + delayIncrement1) {
					coin1.flip();
					sleeping(delay1);
					gameEngineCallbackLooping(player, coin1);
				}
			}
		}.start();
		
		new Thread() {
			
			@Override
			public void run() {
				for(int delay2 = initialDelay2; delay2 < finalDelay2; delay2 = delay2 + delayIncrement2) {
					coin2.flip();
					sleeping(delay2);
					gameEngineCallbackLooping(player, coin2);
				}
			}
		}.start();
	}
	
	/* 
	 * Description: 
	 * This method is used by Thread, to check if the delayTime is less than 0.
	 * If delay is less than 0, pass an exception.
	 */
	private void sleeping(int delayTime) {
		try {
			Thread.sleep(delayTime);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Description: 
	 * This method tells all gameEngineCallbacks about the coin face update of 
	 * the player or the spinner
	 * A player will have a player value while a spinner will have a null value.
	 * Therefore, if player != null, a playerCoinUpdate() method will be called, 
	 * else it would be a spinnerCoinUpdate() method
	 */
	private void gameEngineCallbackLooping(Player player, Coin coin) {
		if(player != null) {
			for(GameEngineCallback gameEngineCallback : gecbi) {
				gameEngineCallback.playerCoinUpdate(player, coin, this);
			}
		}
		else {
			for(GameEngineCallback gameEngineCallback : gecbi) {
				gameEngineCallback.spinnerCoinUpdate(coin, this);
			}
		}
	}
	
	/*
	 * Description:
	 * This method tells all gameEngineCallback about the result of the player or spinner.
	 * A player will have a player value while a spinner will have a null value.
	 * Therefore, if player != null, a playerResult() method will be called, 
	 * else it would be a spinnerResult() method
	 */
	private void resultOutput(Player player, CoinPair coinPair) {
		if(player != null) {
			for(GameEngineCallback gameEngineCallback : gecbi) {
				gameEngineCallback.playerResult(player, coinPair, this);
			}
		}
		else {
			for(GameEngineCallback gameEngineCallback : gecbi) {
				gameEngineCallback.spinnerResult(coinPair, this);
			}
		}
	}
	
	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		for(Player player : getAllPlayers()) {
			player.getBetType().applyWinLoss(player, spinnerResult);
			player.resetBet();
		}
	}
	
	@Override
	public void addPlayer(Player player) {
		
		if(players.containsKey(player.getPlayerId())) {
			players.remove(player.getPlayerId());
			
		}
		players.put(player.getPlayerId(), player);
	}
	
	@Override
	public Player getPlayer(String id) {
		
		Player player = null;
		
		if(players.containsKey(id)) {
			player = players.get(id);
		}
		
		return player;
	}
	
	@Override
	public boolean removePlayer(Player player) {
		
		boolean boolChecker = false;
		
		if(players.containsKey(player.getPlayerId())) {
			players.remove(player.getPlayerId());
			boolChecker = true;
		}
		
		return boolChecker;
	}
	
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gecbi.add(gameEngineCallback);
	}
	
	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		boolean boolChecker = false;
		
		if(gecbi.contains(gameEngineCallback)) {
			gecbi.remove(gameEngineCallback);
			boolChecker = true;
		}
			
		return boolChecker;
	}
	
	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(players.values());
	}
	
	@Override 
	public boolean placeBet(Player player, int bet, BetType betType) {
		
		boolean boolChecker = false;
		
		if(player.setBet(bet)) {
			player.setBetType(betType);
			boolChecker = true;
		}
		else {
			player.resetBet();
		}
	
		return boolChecker;
	}
}
