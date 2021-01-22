package view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

import controller.PlayerListListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/*
 * This is the SummaryPanel
 */
@SuppressWarnings("serial")
public class GameSummaryPanel extends JPanel {
	
	private CoinGameFrame gameFrame;
	private DefaultListModel<Player> playerListSummary = new DefaultListModel<Player>();
	private JList<Player> playerList;
	private PlayerAtFocusListCellRender renderer;
	
	public GameSummaryPanel(GameEngine gameModel, CoinGameFrame gameFrame) {
		this.gameFrame = gameFrame;
		
		// Creates the border and sets the border of the Summary Panel
		Border border = BorderFactory.createTitledBorder("Game Summary");
		this.setBorder(border);
		
		// Instantiates the JList based on the List of players
		playerList = new JList<Player>(playerListSummary);

		// Instantiates the CellRenderer
		renderer = new PlayerAtFocusListCellRender();
		
		// Sets the cell renderer of the JList
		playerList.setCellRenderer(renderer);
		playerList.setVisibleRowCount(5);
		
		// Sets an ActionListener to activate when a user clicks on it
		ListSelectionListener listListener = new PlayerListListener(gameFrame, playerList);
		playerList.addListSelectionListener(listListener);
		
		// Creates a JScrollPane
		JScrollPane playerListScrollPane = new PlayerListScrollPane(this, playerList);
		
		/*
		 *  Sets the border of the JScrollPane and when the scroll bar will appear based
		 *  on the horizontal and vertical dimensions of the Summary Panel
		 */ 
		Border playerListBorder = BorderFactory.createLoweredBevelBorder();
		playerListScrollPane.setBorder(playerListBorder);
		playerListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		playerListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		// Adds the scroll pane to the SummaryPanel 
		this.add(playerListScrollPane);
	}
	
	// Resizes the SummaryPanel based on the main frame size
	@Override
	public Dimension getPreferredSize() {
		Dimension preferredDimension = super.getPreferredSize();
		preferredDimension.width = gameFrame.getWidth() / 4;
		
		return preferredDimension;
	}
	
	// Adds player to the list
	public void addPlayer(Player player) {
		playerListSummary.addElement(player);
	}
	
	// Removes player from the list
	public void removePlayer(Player player) {
		playerListSummary.removeElement(player);
	}
	
	// Returns a CellRenderer type of the renderer
	public PlayerAtFocusListCellRender getRenderer() {
		return renderer;
	}
	
}
