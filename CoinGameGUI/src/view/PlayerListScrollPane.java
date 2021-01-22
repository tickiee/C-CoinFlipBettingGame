package view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import model.interfaces.Player;

/*
 * This JScrollPane is used by the SummaryPanel
 */
@SuppressWarnings("serial")
public class PlayerListScrollPane extends JScrollPane {
	
	private GameSummaryPanel gameSummaryPanel;

	public PlayerListScrollPane(GameSummaryPanel gameSummaryPanel, JList<Player> playerList) {
		this.gameSummaryPanel = gameSummaryPanel;
		
		// Sets the view of the list of players in the Summary Panel
		this.setViewportView(playerList);
		
		Border playerListBorder = BorderFactory.createLoweredBevelBorder();
		this.setBorder(playerListBorder);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	// Resizes the scroll pane based on the size of the game summary panel
	@Override
	public Dimension getPreferredSize() {
		Dimension preferredDimension = super.getPreferredSize();
		preferredDimension.width = gameSummaryPanel.getWidth() - 30;
		preferredDimension.height = gameSummaryPanel.getHeight() - 30;
		
		return preferredDimension;
	}
}
