package com.example.candyland.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for setting up players at the start of the game.
 */
public class PlayerSetupDialog extends JDialog {
    private List<String> playerNames;
    private boolean setupComplete;
    private JSpinner playerCountSpinner;
    private JTextField[] nameFields;
    private JPanel namePanel;
    
    /**
     * Creates a new player setup dialog.
     * 
     * @param parent the parent frame
     */
    public PlayerSetupDialog(Frame parent) {
        super(parent, "Setup Players", true);
        playerNames = new ArrayList<>();
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
        
        for (int i = 0; i < playerCount; i++) {
            namePanel.add(new JLabel("Player " + (i + 1) + ":"));
            nameFields[i] = new JTextField("Player " + (i + 1));
            namePanel.add(nameFields[i]);
        }
        
        // Add empty labels to fill the grid
        while (namePanel.getComponentCount() < 6) {
            namePanel.add(new JLabel(""));
        }
        
        revalidate();
        repaint();
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
}