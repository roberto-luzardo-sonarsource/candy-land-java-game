package com.example.candyland.gui;

import com.example.candyland.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main GUI window for the Candy Land game.
 */
public class CandyLandGUI extends JFrame {
    private transient CandyLandGame game;
    private transient GameBoardPanel boardPanel;
    private transient GameControlPanel controlPanel;
    
    /**
     * Creates and displays the Candy Land GUI.
     */
    public CandyLandGUI() {
        initializeGame();
        if (game != null) {
            initializeGUI();
            setVisible(true);
        } else {
            System.exit(0);
        }
    }
    
    /**
     * Initializes the game with player setup.
     */
    private void initializeGame() {
        PlayerSetupDialog setupDialog = new PlayerSetupDialog(this);
        setupDialog.setVisible(true);
        
        if (setupDialog.isSetupComplete()) {
            List<String> playerNames = setupDialog.getPlayerNames();
            game = new CandyLandGame();
            
            // Add players to the game
            for (String name : playerNames) {
                game.addPlayer(name);
            }
        }
    }
    
    /**
     * Initializes the GUI components.
     */
    private void initializeGUI() {
        setTitle("Candy Land - The Board Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create board panel
        boardPanel = new GameBoardPanel(game.getBoard(), game.getPlayers());
        JScrollPane boardScrollPane = new JScrollPane(boardPanel);
        boardScrollPane.setPreferredSize(new Dimension(800, 600));
        add(boardScrollPane, BorderLayout.CENTER);
        
        // Create control panel
        controlPanel = new GameControlPanel(game, boardPanel);
        add(controlPanel, BorderLayout.EAST);
        
        // Create menu bar
        createMenuBar();
        
        // Set window properties
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 700));
    }
    
    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Game menu
        JMenu gameMenu = new JMenu("Game");
        
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> startNewGame());
        gameMenu.add(newGameItem);
        
        gameMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        gameMenu.add(exitItem);
        
        // Help menu
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem rulesItem = new JMenuItem("Game Rules");
        rulesItem.addActionListener(e -> showGameRules());
        helpMenu.add(rulesItem);
        
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);
        
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    /**
     * Starts a new game.
     */
    private void startNewGame() {
        int option = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to start a new game?", 
            "New Game", 
            JOptionPane.YES_NO_OPTION);
            
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            new CandyLandGUI();
        }
    }
    
    /**
     * Shows the game rules dialog.
     */
    private void showGameRules() {
        String rules = """
            Candy Land Game Rules:
            
            1. Each player takes turns drawing cards
            2. Move to the next space of the color shown on your card
            3. Double color cards move to the second occurrence of that color
            4. Special character cards move directly to that character's location
            5. Players never move backward with character cards
            6. The first player to reach or pass the finish line wins!
            
            Special Characters:
            - Plumpy (Position 8)
            - Mr. Mint (Position 17)
            - Jolly (Position 33)
            - Lord Licorice (Position 47)
            - Gramma Nutt (Position 75)
            - Princess Lolly (Position 95)
            - Queen Frostine (Position 104)
            - King Kandy (Position 118)
            """;
            
        JOptionPane.showMessageDialog(this, rules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Shows the about dialog.
     */
    private void showAbout() {
        String about = """
            Candy Land - Java Implementation
            
            A classic board game for 1-3 players.
            
            Developed using Java Swing for educational purposes.
            
            Version 1.0
            """;
            
        JOptionPane.showMessageDialog(this, about, "About Candy Land", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Main method to start the GUI application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Start the application
        SwingUtilities.invokeLater(CandyLandGUI::new);
    }
}