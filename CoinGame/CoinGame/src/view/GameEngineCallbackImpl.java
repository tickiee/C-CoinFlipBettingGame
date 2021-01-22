package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      // NOTE need to also set the console to FINE in %JRE_HOME%\lib\logging.properties
      logger.setLevel(Level.FINE);
   }

   public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
//      logger.log(Level.FINE, "Intermediate data to log .. String.format() is good here!");
      // TODO: complete this method to log intermediate results
      logger.log(Level.FINE, String.format(" %s coin %d flipped to %s", 
    		  		player.getPlayerName(), coin.getNumber(), coinFaceForGECB(coin)));
      
   }

   public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
   {

//      logger.log(Level.INFO, "Result data to log .. String.format() is good here!");
      // TODO: complete this method to log results
	   logger.log(Level.INFO, String.format(" %s, final result = Coin 1: %s, Coin 2: %s", 
			   player.getPlayerName(), coinFaceForGECB(coinPair.getCoin1()), 
			   coinFaceForGECB(coinPair.getCoin2())));
   }

   // TODO: implement rest of interface 
   @Override
   public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
	   // TODO Auto-generated method stub
	   logger.log(Level.FINE, String.format(" Spinner coin %s, flipped to %s", coin.getNumber(), 
			   coinFaceForGECB(coin)));
   }

   @Override
   public void spinnerResult(CoinPair coinPair, GameEngine engine) {
	   // TODO Auto-generated method stub
	   logger.log(Level.INFO, String.format(" Spinner, final result = Coin 1: %s, Coin 2: %s",
			   		coinFaceForGECB(coinPair.getCoin1()), coinFaceForGECB(coinPair.getCoin2())));
	   
	   StringBuilder statement = new StringBuilder();
	   for(Player players : engine.getAllPlayers()) {
		   statement.append("\n");
		   statement.append(players.toString());
	   }
	   logger.log(Level.INFO, String.format(" Final Player Results" + statement));
	   
	   
   }
   
   /*
    * Description: 
    * This method is used to return a specific string for output.
    */
   private String coinFaceForGECB(Coin coin) {
	   return coin.getFace() == CoinFace.HEADS ? "Heads" : "Tails";
   }
}
