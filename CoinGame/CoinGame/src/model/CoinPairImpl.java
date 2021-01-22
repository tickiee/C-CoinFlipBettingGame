package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair{
	
	private Coin coin1;
	private Coin coin2;
	
	public CoinPairImpl() {
		this.coin1 = new CoinImpl(1);
		this.coin2 = new CoinImpl(2);
	}
	
	@Override
	public Coin getCoin1() {
		return coin1;		
	}
	
	@Override
	public Coin getCoin2() {
		return coin2;		
	}
	
	@Override
	public String toString() {
		
		String coin1Side;
		String coin2Side;
		
		if (coin1.getFace() == CoinFace.HEADS) {
			coin1Side = "Heads";
		}
		else {
			coin1Side = "Tails";
		}
		
		if (coin2.getFace() == CoinFace.HEADS) {
			coin2Side = "Heads";
		}
		else {
			coin2Side = "Tails";
		}
		
		return String.format("Coin 1: %s, Coin 2: %s", coin1Side, coin2Side);
	}
	
	@Override
	public boolean equals(CoinPair coinPair) {
		
		boolean boolChecker = false;
		
		if (coinPair != null) {
			if (coin1.equals(coinPair.getCoin1()) && coin2.equals(coinPair.getCoin2())) {
				boolChecker = true;
			}
			else {
				boolChecker = false;
			}
		}
		else {
			boolChecker = false;
		}
		return boolChecker;
	}
	
	@Override
	public boolean equals(Object coinPair) {
		return coinPair instanceof CoinPair ? equals((CoinPair) coinPair) : false;
	}
	
	@Override
	public int hashCode() {
		return coin1.hashCode() * coin2.hashCode();
	}
}
