package regex;

/*
 * This class is the pattern checker
 * If pattern is valid, return true, otherwise return false 
 */
public class RegexChecker {

	// This is the method that is called by every custom regex method that are seen below
	public boolean regexPatternCheckMethod(String userInput, String pattern) {
		boolean boolChecker = false;
		
		if(userInput.matches(pattern)) {
			boolChecker = true;
		}
		
		return boolChecker;
	}
	
	/*
	 * This regex checks for the initial points input when adding a player
	 * input can only be integers that are above 0
	 */
	public boolean regexInitialPoints(String userInput) {
		boolean boolChecker = false;
		
		if(!userInput.equals("0")) {
			String pattern = ("[0-9]+");
			
			boolChecker = regexPatternCheckMethod(userInput, pattern);
		}
		
		return boolChecker;
	}
	
	/*
	 * This regex checks for points bet by the player
	 * input can only be integers that are at least 0
	 * (PS: You can place a bet that is 0 but it will not do anything)
	 */
	public boolean regexPlayerBet(String userInput) {
		boolean boolChecker = false;
		
		String pattern = ("[0-9]+");
			
		boolChecker = regexPatternCheckMethod(userInput, pattern);
		
		return boolChecker;
	}
}
