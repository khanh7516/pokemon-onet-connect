/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import handle.ControlPanel;
import entity.Level;
import entity.Player;
import handle.MatrixContainerPanel;

/**
 *
 * @author Acer
 */
public class PlayingFrame extends JFrame{
    private int width = 1050;
    private int height = 700;
    private int numberOfLevels = 5;
    
    private HashMap<Integer, Level> levels = new HashMap();
    private Level currentLevel;
    private int levelIndex;
    private LocalDateTime startTime;
    
    public PlayingFrame(int levelIndex, LocalDateTime startTime) {
        this.levelIndex = levelIndex;
        this.startTime = startTime;
        
        //setup levels
//        levels.put(1, new Level(6, 6 , 8, 2, 10));
//        levels.put(2, new Level(6, 6 , 8, 2, 10));
//        levels.put(2, new Level(8, 12 , 10, 6, 30));
//        levels.put(3, new Level(8, 12 , 10, 6, 500));
//        levels.put(4, new Level(8, 12 , 10, 6, 500));
//        levels.put(5, new Level(8, 12 , 10, 6, 500));
        levels.put(1, new Level(8, 12 , 10, 6, 60));
        levels.put(2, new Level(10, 16 , 14, 8, 120));
        levels.put(3, new Level(10, 20 , 24, 6, 180));
        levels.put(4, new Level(12, 20 , 30, 6, 240));
        levels.put(5, new Level(12, 23 , 35, 6, 300));
        

        if( levelIndex >= 1 && levelIndex <= numberOfLevels) {
            currentLevel = levels.get(levelIndex);
            add(createMainPanel());
        }else { 
            PlayedHistoryFrame.addNewPlayer(new Player(startTime, LocalDateTime.now(), numberOfLevels, ControlPanel.getScore(), true));
//            PlayedHistoryFrame.displayPlayers();
            PlayedHistoryFrame.saveGame();
            add(createEndgamePanel());
            ControlPanel.resetScore();
        }
        
        
        
        setTitle("Pokemon Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);        
    }
    
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createMatrixGraphicsPanel(), BorderLayout.CENTER);
        panel.add(new ControlPanel(this, currentLevel.getPlayingTime(), levelIndex, startTime), BorderLayout.NORTH);
        return panel;
    } 
    
    private JPanel createMatrixGraphicsPanel() {
        MatrixContainerPanel graphicsPanel = new MatrixContainerPanel(this, currentLevel, numberOfLevels);
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBackground(Color.BLACK);
        panel.add(graphicsPanel);
        return panel;
    }
    
    
    private JPanel createEndgamePanel() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.ORANGE);
        JLabel winLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/youWin.jpg")));
        
        panel.add(winLabel, new GridBagConstraints());
        
        JButton btnBackToMenu = new JButton("Back to menu");
        btnBackToMenu.setBackground(Color.WHITE);
        btnBackToMenu.addActionListener((e) -> {
            MenuFrame menuFrame = new MenuFrame();
            setVisible(false);
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel.add(btnBackToMenu, gbc);
        
        return panel;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
}
