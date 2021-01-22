package model;
import model.interfaces.Player;
import model.enumeration.BetType;
import model.interfaces.CoinPair;

public class SimplePlayer implements Player {

	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private BetType betType;
	private CoinPair result;
	
	public SimplePlayer(String playerId, String playerName, int points) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = points;
		resetBet();
	}
	
	@Override
	public String getPlayerName() {
		return playerName;
	}
	
	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public int getPoints() {
		return points;
	}
	
	@Override
	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	public String getPlayerId() {
		return playerId;
	}
	
	@Override
	public boolean setBet(int bet) {
		
		boolean boolChecker = false;
		
		if ((bet > 0) && (bet <= points)) {
			this.bet = bet;
			boolChecker = true;
		}
		
		return boolChecker;
	}
	
	@Override
	public int getBet() {
		return bet;
	}
	
	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;
	}
	
	@Override
	public BetType getBetType() {
		return betType;
	}
	
	@Override
	public void resetBet() {
		this.bet = 0;
		this.betType = BetType.NO_BET;
	}
	
	@Override 
	public CoinPair getResult() {
		return result;
	}
	
	@Override
	public void setResult(CoinPair coinPair) {
		this.result = coinPair;
	}
	
	@Override 
	public String toString() {
	    return String.format("Player: id = %s, name = %s, " + 
	    						"bet = %d, betType = %s, points = %d," + 
    							" RESULT .. Coin 1: %s, Coin 2: %s", 
    							playerId, playerName, bet, betType, points, 
    							result != null ? result.getCoin1().getFace().toString() : "Not Applicable", 
    							result != null ? result.getCoin2().getFace().toString() : "Not Applicable");
	}
}
