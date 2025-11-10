package com.example.candyland.gui;

import com.example.candyland.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;

/**
 * Panel that displays the Candy Land game board with colored spaces and player positions.
 */
public class GameBoardPanel extends JPanel {
    private transient Board board;
    private transient List<Player> players;
    private final Map<com.example.candyland.Color, java.awt.Color> colorMap;
    private static final int SPACE_SIZE = 25;
    private static final int PLAYER_SIZE = 15;
    private static final int SPACES_PER_ROW = 10;
    private static final String FONT_NAME = "Arial";
    
    /**
     * Creates a new game board panel.
     * 
     * @param board the game board
     * @param players the list of players
     */
    public GameBoardPanel(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.colorMap = new EnumMap<>(com.example.candyland.Color.class);
        initializeColorMap();
        setPreferredSize(new Dimension(SPACES_PER_ROW * (SPACE_SIZE + 5) + 20, 
                                     (board.getBoardSize() / SPACES_PER_ROW + 2) * (SPACE_SIZE + 5) + 20));
        setBackground(java.awt.Color.WHITE);
    }
    
    /**
     * Initializes the color mapping from game colors to AWT colors.
     */
    private void initializeColorMap() {
        colorMap.put(com.example.candyland.Color.RED, java.awt.Color.RED);
        colorMap.put(com.example.candyland.Color.BLUE, java.awt.Color.BLUE);
        colorMap.put(com.example.candyland.Color.GREEN, java.awt.Color.GREEN);
        colorMap.put(com.example.candyland.Color.YELLOW, java.awt.Color.YELLOW);
        colorMap.put(com.example.candyland.Color.ORANGE, java.awt.Color.ORANGE);
        colorMap.put(com.example.candyland.Color.PURPLE, new java.awt.Color(128, 0, 128));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawBoard(g2d);
        drawPlayers(g2d);
        drawLegend(g2d);
    }
    
    /**
     * Draws the game board with colored spaces.
     * 
     * @param g2d the graphics context
     */
    private void drawBoard(Graphics2D g2d) {
        int x = 10;
        int y = 10;
        
        for (int i = 0; i < board.getBoardSize(); i++) {
            // Calculate position based on snake-like pattern
            int row = i / SPACES_PER_ROW;
            int col = (row % 2 == 0) ? (i % SPACES_PER_ROW) : (SPACES_PER_ROW - 1 - (i % SPACES_PER_ROW));
            
            int spaceX = x + col * (SPACE_SIZE + 5);
            int spaceY = y + row * (SPACE_SIZE + 5);
            
            // Get space color
            com.example.candyland.Color spaceColor = board.getSpaceColor(i);
            g2d.setColor(colorMap.get(spaceColor));
            g2d.fillRect(spaceX, spaceY, SPACE_SIZE, SPACE_SIZE);
            
            // Draw border
            g2d.setColor(java.awt.Color.BLACK);
            g2d.drawRect(spaceX, spaceY, SPACE_SIZE, SPACE_SIZE);
            
            // Draw space number for special locations
            if (isSpecialLocation(i)) {
                g2d.setColor(java.awt.Color.WHITE);
                g2d.setFont(new Font(FONT_NAME, Font.BOLD, 8));
                String text = String.valueOf(i);
                FontMetrics fm = g2d.getFontMetrics();
                int textX = spaceX + (SPACE_SIZE - fm.stringWidth(text)) / 2;
                int textY = spaceY + (SPACE_SIZE + fm.getAscent()) / 2;
                g2d.drawString(text, textX, textY);
            }
        }
        
        // Draw finish line
        int finishRow = (board.getBoardSize() - 1) / SPACES_PER_ROW;
        int finishCol = ((finishRow % 2 == 0) ? ((board.getBoardSize() - 1) % SPACES_PER_ROW) : 
                        (SPACES_PER_ROW - 1 - ((board.getBoardSize() - 1) % SPACES_PER_ROW)));
        int finishX = x + finishCol * (SPACE_SIZE + 5);
        int finishY = y + finishRow * (SPACE_SIZE + 5);
        
        g2d.setColor(new java.awt.Color(255, 215, 0)); // Gold color
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(finishX - 2, finishY - 2, SPACE_SIZE + 4, SPACE_SIZE + 4);
        
        // Draw "FINISH" text
        g2d.setColor(java.awt.Color.BLACK);
        g2d.setFont(new Font(FONT_NAME, Font.BOLD, 10));
        g2d.drawString("FINISH", finishX + SPACE_SIZE + 10, finishY + SPACE_SIZE / 2);
    }
    
    /**
     * Draws the players on their current positions.
     * 
     * @param g2d the graphics context
     */
    private void drawPlayers(Graphics2D g2d) {
        java.awt.Color[] playerColors = {java.awt.Color.BLACK, java.awt.Color.DARK_GRAY, java.awt.Color.GRAY};
        
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int position = Math.min(player.getPosition(), board.getBoardSize() - 1);
            
            // Calculate position
            int row = position / SPACES_PER_ROW;
            int col = (row % 2 == 0) ? (position % SPACES_PER_ROW) : (SPACES_PER_ROW - 1 - (position % SPACES_PER_ROW));
            
            int spaceX = 10 + col * (SPACE_SIZE + 5);
            int spaceY = 10 + row * (SPACE_SIZE + 5);
            
            // Offset multiple players in the same space
            int offsetX = (i % 2) * (PLAYER_SIZE / 2);
            int offsetY = (i / 2) * (PLAYER_SIZE / 2);
            
            int playerX = spaceX + (SPACE_SIZE - PLAYER_SIZE) / 2 + offsetX;
            int playerY = spaceY + (SPACE_SIZE - PLAYER_SIZE) / 2 + offsetY;
            
            // Draw player as a colored circle
            g2d.setColor(playerColors[i % playerColors.length]);
            g2d.fill(new Ellipse2D.Double(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE));
            g2d.setColor(java.awt.Color.WHITE);
            g2d.draw(new Ellipse2D.Double(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE));
        }
    }
    
    /**
     * Draws the legend showing player colors and special locations.
     * 
     * @param g2d the graphics context
     */
    private void drawLegend(Graphics2D g2d) {
        int legendX = 10;
        int legendY = getHeight() - 120;
        
        g2d.setColor(java.awt.Color.LIGHT_GRAY);
        g2d.fillRect(legendX, legendY, 200, 100);
        g2d.setColor(java.awt.Color.BLACK);
        g2d.drawRect(legendX, legendY, 200, 100);
        
        g2d.setFont(new Font(FONT_NAME, Font.BOLD, 12));
        g2d.drawString("Players:", legendX + 10, legendY + 20);
        
        java.awt.Color[] playerColors = {java.awt.Color.BLACK, java.awt.Color.DARK_GRAY, java.awt.Color.GRAY};
        for (int i = 0; i < players.size(); i++) {
            g2d.setColor(playerColors[i % playerColors.length]);
            g2d.fill(new Ellipse2D.Double(legendX + 10, legendY + 30 + (double) i * 20, PLAYER_SIZE, PLAYER_SIZE));
            g2d.setColor(java.awt.Color.BLACK);
            g2d.setFont(new Font(FONT_NAME, Font.PLAIN, 10));
            g2d.drawString(players.get(i).getName(), legendX + 35, legendY + 42 + i * 20);
        }
    }
    
    /**
     * Checks if a position is a special character location.
     * 
     * @param position the position to check
     * @return true if it's a special location
     */
    private static boolean isSpecialLocation(int position) {
        return position == 8 || position == 17 || position == 33 || position == 47 ||
               position == 75 || position == 95 || position == 104 || position == 118;
    }
    
    /**
     * Updates the player list and repaints the board.
     * 
     * @param players the updated list of players
     */
    public void updatePlayers(List<Player> players) {
        this.players = players;
        repaint();
    }
}