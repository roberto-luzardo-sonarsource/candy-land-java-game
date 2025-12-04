package com.example.candyland.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dialog for setting up players at the start of the game.
 */
public class PlayerSetupDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private List<String> playerNames;
    private Map<String, ImageIcon> playerAvatars;
    private Map<String, String> playerAvatarPaths;
    private boolean setupComplete;
    private JSpinner playerCountSpinner;
    private JTextField[] nameFields;
    private JButton[] avatarButtons;
    private JPanel namePanel;
    
    /**
     * Creates a new player setup dialog.
     * 
     * @param parent the parent frame
     */
    public PlayerSetupDialog(Frame parent) {
        super(parent, "Setup Players", true);
        playerNames = new ArrayList<>();
        playerAvatars = new HashMap<>();
        playerAvatarPaths = new HashMap<>();
        setupComplete = false;
        initializeComponents();
    }
    
    /**
     * Initializes the dialog components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Welcome to Candy Land!"));
        add(titlePanel, BorderLayout.NORTH);
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Player count selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Number of Players:"), gbc);
        
        gbc.gridx = 1;
        playerCountSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 3, 1));
        playerCountSpinner.addChangeListener(e -> updateNameFields());
        mainPanel.add(playerCountSpinner, gbc);
        
        // Name fields panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        namePanel = new JPanel(new GridLayout(3, 2, 5, 5));
        namePanel.setBorder(BorderFactory.createTitledBorder("Player Names"));
        mainPanel.add(namePanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(this::startGame);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            setupComplete = false;
            setVisible(false);
        });
        
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Initialize with default player count
        updateNameFields();
    }
    
    /**
     * Updates the name input fields based on selected player count.
     */
    private void updateNameFields() {
        namePanel.removeAll();
        int playerCount = (Integer) playerCountSpinner.getValue();
        nameFields = new JTextField[playerCount];
        avatarButtons = new JButton[playerCount];
        
        namePanel.setLayout(new GridLayout(playerCount, 3, 5, 5));
        
        for (int i = 0; i < playerCount; i++) {
            final int playerIndex = i;
            
            // Player label
            namePanel.add(new JLabel("Player " + (i + 1) + ":"));
            
            // Name field
            nameFields[i] = new JTextField("Player " + (i + 1));
            namePanel.add(nameFields[i]);
            
            // Avatar button
            avatarButtons[i] = new JButton("Select Avatar");
            avatarButtons[i].addActionListener(e -> selectAvatar(playerIndex));
            namePanel.add(avatarButtons[i]);
        }
        
        revalidate();
        repaint();
    }
    
    /**
     * Opens avatar selection dialog for a player.
     * 
     * @param playerIndex the index of the player
     */
    private void selectAvatar(int playerIndex) {
        String playerName = nameFields[playerIndex].getText().trim();
        if (playerName.isEmpty()) {
            playerName = "Player " + (playerIndex + 1);
        }
        
        AvatarSelectionDialog avatarDialog = new AvatarSelectionDialog(
            (Frame) getOwner(), playerName);
        avatarDialog.setVisible(true);
        
        if (avatarDialog.isConfirmed()) {
            ImageIcon avatar = avatarDialog.getSelectedAvatar();
            String avatarPath = avatarDialog.getSelectedAvatarPath();
            
            if (avatar != null) {
                playerAvatars.put(playerName, avatar);
                playerAvatarPaths.put(playerName, avatarPath);
                avatarButtons[playerIndex].setText("✓ Avatar Set");
                avatarButtons[playerIndex].setForeground(new Color(0, 150, 0));
            } else {
                playerAvatars.remove(playerName);
                playerAvatarPaths.remove(playerName);
                avatarButtons[playerIndex].setText("Select Avatar");
                avatarButtons[playerIndex].setForeground(null);
            }
        }
    }
    
    /**
     * Handles the start game button click.
     * 
     * @param e the action event
     */
    private void startGame(ActionEvent e) {
        playerNames.clear();
        for (JTextField field : nameFields) {
            String name = field.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name for all players.", 
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            playerNames.add(name);
        }
        
        setupComplete = true;
        setVisible(false);
    }
    
    /**
     * Gets the list of player names entered by the user.
     * 
     * @return the list of player names
     */
    public List<String> getPlayerNames() {
        return new ArrayList<>(playerNames);
    }
    
    /**
     * Checks if the setup was completed successfully.
     * 
     * @return true if setup was completed
     */
    public boolean isSetupComplete() {
        return setupComplete;
    }
    
    /**
     * Gets the avatar for a specific player.
     * 
     * @param playerName the player's name
     * @return the player's avatar, or null if not set
     */
    public ImageIcon getPlayerAvatar(String playerName) {
        return playerAvatars.get(playerName);
    }
    
    /**
     * Gets the avatar path for a specific player.
     * 
     * @param playerName the player's name
     * @return the player's avatar file path, or null if not set
     */
    public String getPlayerAvatarPath(String playerName) {
        return playerAvatarPaths.get(playerName);
    }
    
    /**
     * Gets all player avatars.
     * 
     * @return map of player names to avatars
     */
    public Map<String, ImageIcon> getAllPlayerAvatars() {
        return new HashMap<>(playerAvatars);
    }
}