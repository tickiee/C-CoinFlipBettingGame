package model.enumeration;

import model.interfaces.CoinPair;
import model.interfaces.Player;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */

public enum BetType
{
	
      COIN1 {
         @Override
         public void applyWinLoss(Player player, CoinPair spinnerResult) {
        	 
        	 int points = player.getPoints();
        	 int playerBet = player.getBet();
        	 boolean spinResult = player.getResult().getCoin1().equals(spinnerResult.getCoin1());
        	 points = calculatePoints(points, playerBet, spinResult, false);
        	 player.setPoints(points);
         }
      },
      COIN2 {
    	  @Override
    	  public void applyWinLoss(Player player, CoinPair spinnerResult) {
    		  
    		  int points = player.getPoints();
    		  int playerBet = player.getBet();
    		  boolean spinResult = player.getResult().getCoin2().equals(spinnerResult.getCoin2());
    		  points = calculatePoints(points, playerBet, spinResult, false);
    		  player.setPoints(points);
    	  }
    	  
      },
      BOTH {
    	  @Override
    	  public void applyWinLoss(Player player, CoinPair spinnerResult) {

    		  int points = player.getPoints();
    		  int playerBet = player.getBet();
    		  boolean spinResult = player.getResult().getCoin1().equals(spinnerResult.getCoin1()) &&
    				  player.getResult().getCoin2().equals(spinnerResult.getCoin2());
    		  points = calculatePoints(points, playerBet, spinResult, true);
    		  player.setPoints(points);
    	  }
    	  
      },
      NO_BET {
    	  @Override
    	  public void applyWinLoss(Player player, CoinPair spinnerResult) {
    		  
    	  }
    	  
      };
      
      // TODO finish this class with other enum constants
   
      /**
       * This method is to be overridden for each bet type<br>
       * see assignment specification for betting odds and how win/loss is applied
       * 
       * @param player - the player to apply the win/loss points balance adjustment
       * @param spinnerResult - the CoinPair result of the spinner to compare to
       */
      public abstract void applyWinLoss(Player player, CoinPair spinnerResult);
      
      /*
       * Description: This method calculates the final points of the player.
       * The points will be added or subtracted based on winning or losing
       */
      private static int calculatePoints(int points, int playerBet, 
    		  								boolean spinResult, boolean bothBet) {
    	  if(spinResult) {
    		  points = points + playerBet;
    		  if(bothBet) {
    			  points = points + playerBet;
    		  }
    	  }
    	  else {
    		  points = points - playerBet;
    	  }
    	  
    	  return points;
      }
      
}