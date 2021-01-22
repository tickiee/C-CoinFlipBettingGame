package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

import java.util.Random;

public class CoinImpl implements Coin {

	private int coinNumber;
	private CoinFace coinFace;	
	
	public CoinImpl(int number) {
		
		Random randomValue;
		randomValue = new Random();		
		this.coinFace = CoinFace.values()[randomValue.nextInt(2)];
		this.coinNumber = number;
	}
	
	@Override
	public int getNumber() {
		return coinNumber;
	}
	
	@Override
	public CoinFace getFace() {
		return coinFace;
	}
	
	@Override
	public void flip() {
		if (coinFace == CoinFace.HEADS) {
			coinFace = CoinFace.TAILS;
		}
		else {
			coinFace = CoinFace.HEADS;
		}
	}
	
	@Override
	public boolean equals(Coin coin) {
		
		boolean boolChecker = false;
		
		if (coin != null) {
			if (coinFace.equals(coin.getFace()) && coinNumber == coin.getNumber()) {
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
	public boolean equals(Object coin) {
		return coin instanceof Coin ? equals((Coin) coin) : false;
	}
	
	@Override
	public int hashCode() {
		return ((Integer) coinNumber).hashCode() * coinFace.hashCode();
	}
	
	@Override
	public String toString() {
		
		String coinSide;
		
		if (coinFace == CoinFace.HEADS) {
			coinSide = "Heads";
		}
		else {
			coinSide = "Tails";
		}
		return String.format("Coin %d: %s", coinNumber, coinSide);
	}
}
