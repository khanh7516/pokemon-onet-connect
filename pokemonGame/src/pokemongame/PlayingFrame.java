/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Acer
 */
public class PlayingFrame extends JFrame{
    private int width = 950;
    private int height = 650;
    private MatrixContainerPanel graphicsPanel;
    
    public PlayingFrame() {
        add(createMainPanel());
        setTitle("Pokemon Game");
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createMatrixGraphicsPanel(), BorderLayout.CENTER);
        panel.add(createControlPanel(), BorderLayout.NORTH);
        return panel;
    } 
    
    private JPanel createMatrixGraphicsPanel() {
        graphicsPanel = new MatrixContainerPanel();
        JPanel panel = new JPanel(new GridBagLayout());
        
//        JLabel backgroundLabel = new JLabel(new ImageIcon("E:/github/pokemon-onet-connect/pokemonGame/src/resources/background.jpg"));
////        panel.setBackground(Color.gray);
        panel.setBackground(Color.GREEN);
        panel.add(graphicsPanel);
        return panel;
    }
    
    
    private JPanel createControlPanel() {
        ControlPanel controlPanel = new ControlPanel();
        return controlPanel;
    }
}
