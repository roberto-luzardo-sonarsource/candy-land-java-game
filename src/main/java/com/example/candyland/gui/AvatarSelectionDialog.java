package com.example.candyland.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Dialog for selecting a player avatar image.
 */
public class AvatarSelectionDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final int AVATAR_SIZE = 100;
    private static final String VALIDATION_ERROR = "Validation Error";
    
    private ImageIcon selectedAvatar;
    private String selectedAvatarPath;
    private final JLabel avatarPreview;
    private boolean confirmed;
    
    public AvatarSelectionDialog(Frame parent, String playerName) {
        super(parent, "Select Avatar for " + playerName, true);
        
        confirmed = false;
        
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Info panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel infoLabel = new JLabel("<html><b>Choose Your Avatar</b><br>" +
            "Select an image file to use as your player avatar.<br>" +
            "Supported formats: PNG, JPG, GIF</html>");
        infoPanel.add(infoLabel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        
        // Center panel with avatar preview
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        
        // Avatar preview
        JPanel previewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        previewPanel.setBorder(BorderFactory.createTitledBorder("Avatar Preview"));
        avatarPreview = new JLabel();
        avatarPreview.setPreferredSize(new Dimension(AVATAR_SIZE, AVATAR_SIZE));
        avatarPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        avatarPreview.setHorizontalAlignment(SwingConstants.CENTER);
        avatarPreview.setText("No avatar");
        previewPanel.add(avatarPreview);
        centerPanel.add(previewPanel, BorderLayout.CENTER);
        
        // Browse button
        JPanel browsePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton browseButton = new JButton("Browse for Image...");
        browseButton.addActionListener(e -> browseForImage());
        browsePanel.add(browseButton);
        centerPanel.add(browsePanel, BorderLayout.SOUTH);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton skipButton = new JButton("Skip (No Avatar)");
        
        okButton.addActionListener(e -> {
            if (selectedAvatar != null) {
                confirmed = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select an avatar image or click 'Skip'",
                    VALIDATION_ERROR,
                    JOptionPane.WARNING_MESSAGE);
            }
        });
        
        skipButton.addActionListener(e -> {
            selectedAvatar = null;
            selectedAvatarPath = null;
            confirmed = true;
            dispose();
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(skipButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void browseForImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Avatar Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Image files (*.png, *.jpg, *.jpeg, *.gif)", "png", "jpg", "jpeg", "gif"));
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadAvatar(selectedFile);
        }
    }
    
    private void loadAvatar(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if (image == null) {
                JOptionPane.showMessageDialog(this,
                    "Failed to load image. Please select a valid image file.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Scale image to fit avatar size
            Image scaledImage = image.getScaledInstance(AVATAR_SIZE, AVATAR_SIZE, Image.SCALE_SMOOTH);
            selectedAvatar = new ImageIcon(scaledImage);
            selectedAvatarPath = imageFile.getAbsolutePath();
            
            // Update preview
            avatarPreview.setIcon(selectedAvatar);
            avatarPreview.setText(null);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading image: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public ImageIcon getSelectedAvatar() {
        return selectedAvatar;
    }
    
    public String getSelectedAvatarPath() {
        return selectedAvatarPath;
    }
}
