package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.enumeration.CoinFace;

/*
 * This class is the individual Coin Image that is in a JPanel
 */
@SuppressWarnings("serial")
public class GameSpecificCoinPanel extends JPanel{

	private Image coinImage;
	private String coinImageInString;
	
	public GameSpecificCoinPanel() {
		// When the application runs, set both coin face image to heads
		setInitialCoinFaceImage();
	}
	
	// Used for resizing
	@Override
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		
		int smallestLength = Math.min(getWidth(), getHeight());
		int startingWidth = (getWidth() - smallestLength) / 2;
		int startingHeight = (getHeight() - smallestLength) / 2;

		graphic.drawImage(coinImage, startingWidth, startingHeight, smallestLength, smallestLength, this);
	}
	
	// Used in constructor
	public void setInitialCoinFaceImage() {
		changeImage(CoinFace.HEADS);
	}
	
	// Flips the image of the CoinFace
	public void changeImage(CoinFace coinFace) {
		try {
			File file;
			
			if(coinFace == CoinFace.HEADS) {
				file = new File("img/heads.png");
				coinImage = ImageIO.read(file);
				coinImageInString = coinFace.toString();
			} else {
				file = new File("img/tails.png");
				coinImage = ImageIO.read(file);
				coinImageInString = coinFace.toString();
			}
		} catch(Exception e) {
			System.out.println("Image Error");
		}
	}
	
	// Returns a CoinFace type of a coin face
	public CoinFace getCoinFace() {
		CoinFace coinFace;
		if(coinImageInString.equals("HEADS")) {
			coinFace = CoinFace.HEADS;
		} else {
			coinFace = CoinFace.TAILS;
		}
		
		return coinFace;
	}
}
