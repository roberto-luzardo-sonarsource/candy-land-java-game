package com.example.candyland.gui;

import com.example.candyland.*;
import com.example.candyland.network.client.GameClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Panel containing game controls and information display.
 */
public class GameControlPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(GameControlPanel.class);
    
    private final transient CandyLandGame game;
    private final transient GameBoardPanel boardPanel;
    private final transient CandyLandGUI parentGUI;
    private JButton drawCardButton;
    private JLabel currentPlayerLabel;
    private JLabel gameStatusLabel;
    private JTextArea gameLog;
    
    /**
     * Creates a new game control panel.
     * 
     * @param game the game instance
     * @param boardPanel the board panel to update
     */
    public GameControlPanel(CandyLandGame game, GameBoardPanel boardPanel) {
        this(game, boardPanel, null);
    }
    
    /**
     * Creates a new game control panel with multiplayer support.
     * 
     * @param game the game instance
     * @param boardPanel the board panel to update
     * @param parentGUI the parent GUI (for multiplayer)
     */
    public GameControlPanel(CandyLandGame game, GameBoardPanel boardPanel, CandyLandGUI parentGUI) {
        this.game = game;
        this.boardPanel = boardPanel;
        this.parentGUI = parentGUI;
        initializeComponents();
        updateDisplay();
    }
    
    /**
     * Initializes the panel components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 600));
        setBorder(BorderFactory.createTitledBorder("Game Controls"));
        
        // Current player and status panel
        JPanel statusPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        currentPlayerLabel = new JLabel("Current Player: ");
        gameStatusLabel = new JLabel("Game Status: ");
        
        statusPanel.add(currentPlayerLabel);
        statusPanel.add(gameStatusLabel);
        
        // Draw card button
        drawCardButton = new JButton("Draw Card");
        drawCardButton.setFont(new Font("Arial", Font.BOLD, 16));
        drawCardButton.addActionListener(this::drawCard);
        statusPanel.add(drawCardButton);
        
        add(statusPanel, BorderLayout.NORTH);
        
        // Game log
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        gameLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        gameLog.append("Welcome to Candy Land!\n");
        gameLog.append("Game started with " + game.getPlayers().size() + " players.\n\n");
        
        JScrollPane scrollPane = new JScrollPane(gameLog);
        scrollPane.setPreferredSize(new Dimension(280, 400));
        add(scrollPane, BorderLayout.CENTER);
        
        // Player positions panel
        JPanel positionsPanel = createPlayerPositionsPanel();
        add(positionsPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Creates the player positions display panel.
     * 
     * @return the positions panel
     */
    private JPanel createPlayerPositionsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Player Positions"));
        
        for (Player player : game.getPlayers()) {
            JLabel playerLabel = new JLabel(player.getName() + ": Position " + player.getPosition());
            panel.add(playerLabel);
        }
        
        return panel;
    }
    
    /**
     * Handles the draw card button action.
     * 
     * @param e the action event
     */
    private void drawCard(ActionEvent e) {
        if (game.getWinner() != null) {
            return;
        }
        
        // Handle multiplayer
        if (parentGUI != null && parentGUI.isMultiplayer()) {
            try {
                GameClient client = parentGUI.getNetworkClient();
                if (client != null && client.isConnected()) {
                    client.drawCard(parentGUI.getCurrentPlayerName());
                }
            } catch (IOException ex) {
                logger.error("Error sending draw card request", ex);
                JOptionPane.showMessageDialog(this,
                    "Error communicating with server: " + ex.getMessage(),
                    "Network Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        // Local game logic
        Player currentPlayer = game.getCurrentPlayer();
        Card drawnCard = game.getDeck().drawCard();
        
        // Calculate new position
        int oldPosition = currentPlayer.getPosition();
        int newPosition = game.calculateNewPosition(currentPlayer, drawnCard);
        currentPlayer.setPosition(newPosition);
        
        // Log the move
        String moveText = currentPlayer.getName() + " drew: " + drawnCard + 
                         " (moved from " + oldPosition + " to " + newPosition + ")\n";
        addLogMessage(moveText);
        
        // Check for winner
        if (game.getBoard().isWinningSpace(newPosition)) {
            game.setWinner(currentPlayer);
            addLogMessage("\n🎉 " + currentPlayer.getName() + " WINS! 🎉\n");
            drawCardButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, 
                currentPlayer.getName() + " wins the game!", 
                "Game Over", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            game.nextPlayer();
        }
        
        // Update display
        updateDisplay();
        boardPanel.updatePlayers(game.getPlayers());
    }
    
    /**
     * Adds a message to the game log.
     * 
     * @param message the message to add
     */
    public void addLogMessage(String message) {
        gameLog.append(message);
        if (!message.endsWith("\n")) {
            gameLog.append("\n");
        }
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }
    
    /**
     * Updates the game state display.
     */
    public void updateGameState() {
        updateDisplay();
        boardPanel.updatePlayers(game.getPlayers());
    }
    
    /**
     * Updates the display with current game state.
     */
    private void updateDisplay() {
        if (game.getWinner() != null) {
            currentPlayerLabel.setText("Winner: " + game.getWinner().getName());
            gameStatusLabel.setText("Game Over!");
            drawCardButton.setEnabled(false);
        } else if (!game.getPlayers().isEmpty()) {
            currentPlayerLabel.setText("Current Player: " + game.getCurrentPlayer().getName());
            gameStatusLabel.setText("Game in Progress");
            drawCardButton.setEnabled(true);
        } else {
            currentPlayerLabel.setText("Setting up players...");
            gameStatusLabel.setText("Game Setup");
            drawCardButton.setEnabled(false);
        }
        
        // Update player positions in the south panel
        remove(((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.SOUTH));
        add(createPlayerPositionsPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
}