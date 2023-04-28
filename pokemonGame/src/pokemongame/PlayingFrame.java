/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.io.File;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author Acer
 */
public class PlayingFrame extends JFrame{
    private int width = 950;
    private int height = 650;
    private HashMap<Integer, Level> levels = new HashMap();
    private Level currentLevel;
    
    public PlayingFrame(int levelIndex) {
        levels.put(1, new Level(6, 6 , 8, 2, 500));
        
        
        currentLevel = levels.get(levelIndex);
        
        
        
//        add(createMainPanel());
        add(createEndgamePanel());
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
        panel.add(new ControlPanel(currentLevel.getPlayingTime()), BorderLayout.NORTH);
        return panel;
    } 
    
    private JPanel createMatrixGraphicsPanel() {
        MatrixContainerPanel graphicsPanel = new MatrixContainerPanel(this, currentLevel.getRows(), currentLevel.getCols(), currentLevel.getNumberOfImgs(), currentLevel.getDuplicateImgs());
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBackground(Color.BLACK);
        panel.add(graphicsPanel);
        return panel;
    }
    
    
    private JPanel createEndgamePanel() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.ORANGE);
        JLabel winLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/youWin.jpg")));
//        winLabel.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(winLabel, new GridBagConstraints());
        
        JButton btnBackToMenu = new JButton("Back to menu");
        btnBackToMenu.setBackground(Color.WHITE);
        btnBackToMenu.addActionListener((e) -> {
            MenuFrame menuFrame = new MenuFrame();
            MenuFrame.clip.close();
            setVisible(false);
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel.add(btnBackToMenu, gbc);
        
        return panel;
    }
}
