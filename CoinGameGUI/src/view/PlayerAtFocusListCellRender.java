package view;
import model.interfaces.Player;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * This class is the CellRenderer of the SummaryPanel
 */
public class PlayerAtFocusListCellRender implements ListCellRenderer<Player> {
	
	private static Border notFocusedBorder = new EmptyBorder(10, 1, 1, 1);
	private static TitledBorder focusedBorder = new TitledBorder(LineBorder.createBlackLineBorder(), "Player");
	private DefaultListCellRenderer[] defaultListCellRendererArray = {
			new DefaultListCellRenderer(), new DefaultListCellRenderer(), new DefaultListCellRenderer(),
			new DefaultListCellRenderer(), new DefaultListCellRenderer()
	};
	
	private String playerLastResult = "";
	private String result = "";
	private Player playerHoldingResult = null;
	
	@Override
	public JPanel getListCellRendererComponent(JList<? extends Player> simplePlayerList, 
													Player simplePlayer, int playerIndex, boolean getSelected, 
													boolean getCellHasFocus) {
		
		LayoutManager specificPlayerLayout = new GridLayout(5, 0);
		JPanel specificPlayerInfo = new JPanel(specificPlayerLayout);
		
		Font font = new Font("Summary Panel Font", Font.PLAIN, 12);
		
		JLabel playerNameLabel = (JLabel) defaultListCellRendererArray[0].getListCellRendererComponent(simplePlayerList, 
				"Player Name: " + simplePlayer.getPlayerName(), playerIndex, getSelected, getCellHasFocus);
		playerNameLabel.setFont(font);
		specificPlayerInfo.add(playerNameLabel);
		
		JLabel playerPointLabel = (JLabel) defaultListCellRendererArray[1].getListCellRendererComponent(simplePlayerList,
				"Player Points: " + simplePlayer.getPoints(), playerIndex, getSelected, getCellHasFocus);
		playerPointLabel.setFont(font);
		specificPlayerInfo.add(playerPointLabel);
		
		JLabel playerBetPointsLabel = (JLabel) defaultListCellRendererArray[2].getListCellRendererComponent(simplePlayerList, 
				"Player Bet Points: " + simplePlayer.getBet(), playerIndex, getSelected, getCellHasFocus);
		playerBetPointsLabel.setFont(font);
		specificPlayerInfo.add(playerBetPointsLabel);
		
		JLabel playerBetTypeLabel = (JLabel) defaultListCellRendererArray[3].getListCellRendererComponent(simplePlayerList,
				"Player Bet Type: " + simplePlayer.getBetType().toString(), playerIndex, getSelected, getCellHasFocus);
		playerBetTypeLabel.setFont(font);
		specificPlayerInfo.add(playerBetTypeLabel);
		
		if(playerHoldingResult == null) {
			playerLastResult = "Not Applicable";
		} else {
			if(simplePlayer.equals(playerHoldingResult)) {
				playerLastResult = result;
			}
		}
		
		JLabel playerWinLoseLabel = (JLabel) defaultListCellRendererArray[4].getListCellRendererComponent(simplePlayerList,
				"Player Last Result: " + playerLastResult, playerIndex, getSelected, 
				getCellHasFocus);
		playerWinLoseLabel.setFont(font);
		specificPlayerInfo.add(playerWinLoseLabel);
		specificPlayerInfo.setBorder(getSelected && getCellHasFocus ? focusedBorder : notFocusedBorder);
		
		return specificPlayerInfo;
	}
	
	public void setLastResult(Player player, String result) {
		this.result = result;
		this.playerHoldingResult = player;
	}
}
