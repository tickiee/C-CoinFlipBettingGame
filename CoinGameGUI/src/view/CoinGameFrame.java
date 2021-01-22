package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import model.interfaces.GameEngine;
import viewmodel.GameViewModel;

/*
 * This class is the main application
 */
@SuppressWarnings("serial")
public class CoinGameFrame extends JFrame {
	
	private GameMenuBar gameMenuBar;
	private GameToolBar gameToolBar;
	private GameSummaryPanel gameSummaryPanel;
	private GameCoinPanel gameCoinPanel;
	private GameStatusBar gameStatusBar;
	private GameViewModel gameViewModel;
	
	public CoinGameFrame(GameEngine gameModel) {
		
		// Title
		super("Coin Game");
	
		// Adding the GameEngineCallback for test purposes
//		GameEngineCallback gecb = new GameEngineCallbackImpl();
//		gameModel.addGameEngineCallback(gecb);
		
		// Adding the GameEngineCallbackGUI for Assignment and GUI purposes
		GameEngineCallbackGUI gameEngineCallbackGUI = new GameEngineCallbackGUI(this);
		gameModel.addGameEngineCallback(gameEngineCallbackGUI);
		
		// Instantiate a ViewModel class
		gameViewModel = new GameViewModel(gameModel, this);
		
		// Initial Size
		super.setSize(1200, 800);
		
		// Initial Visibility
		super.setVisible(true);
		
		// Set MinimumSize
		Dimension minimumDimension = new Dimension(500, 500);
		super.setMinimumSize(minimumDimension);
		
		// Stop whole program when you exit application
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set Layout
		super.setLayout(new BorderLayout());
		
		// Game MenuBar
		gameMenuBar = new GameMenuBar(gameModel, this);
		
		// Add Game MenuBar to Frame
		super.setJMenuBar(gameMenuBar);
		
		// Game ToolBar
		gameToolBar = new GameToolBar(gameModel, this);
		
		//Add Game ToolBar to Frame
		super.add(gameToolBar, BorderLayout.NORTH);
		
		// Game SummaryPanel
		gameSummaryPanel = new GameSummaryPanel(gameModel, this);
		
		// Add Game SummaryPanel to Frame
		super.add(gameSummaryPanel, BorderLayout.WEST);
		
		// Add Game CoinPanel
		gameCoinPanel = new GameCoinPanel(gameModel, this);
		
		// Add Game CoinPanel to Frame
		super.add(gameCoinPanel, BorderLayout.CENTER); 
		
		// Game StatusBar
		gameStatusBar = new GameStatusBar(this);
		
		// Add Game StatusBar to Frame
		super.add(gameStatusBar, BorderLayout.SOUTH);
	}
	
	// Returns the Menu Bar
	public GameMenuBar getGameMenuBar() {
		return gameMenuBar;
	}

	// Returns the Summary Panel
	public GameSummaryPanel getGameSummaryPanel() {
		return gameSummaryPanel;
	}
	
	// Returns the Status Bar
	public GameStatusBar getGameStatusBar() {
		return gameStatusBar;
	}	
	
	// Returns the Tool Bar
	public GameToolBar getGameToolBar() {
		return gameToolBar;
	}
	
	// Returns the Coin Panel
	public GameCoinPanel getGameCoinPanel() {
		return gameCoinPanel;
	}
	
	// Returns the View Model
	public GameViewModel getGameViewModel() {
		return gameViewModel;
	}
}
