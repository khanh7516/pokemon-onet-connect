/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Acer
 */
public class ControlPanel extends JPanel implements ActionListener{
    private Timer timer;
    private int count;
    private static int score = 0;
    private JProgressBar progressTime;
    private static JLabel lbScore;
    

    public ControlPanel(int playingTime) {
        count = playingTime;
        
        createControlView();
        timer = new Timer(1000, this);
        timer.start();
    }
    
    private void createControlView() {
        lbScore = new JLabel();
        lbScore.setText(Integer.toString(score));
        lbScore.setFont(new Font("Arial",Font.BOLD ,26));
        lbScore.setHorizontalAlignment(JLabel.CENTER);
        lbScore.setVerticalAlignment(JLabel.CENTER);
        progressTime = new JProgressBar(0, count);
        progressTime.setValue(count);
        progressTime.setForeground(Color.GREEN);
        
        JLabel labelScore = new JLabel("Score");
        labelScore.setHorizontalAlignment(JLabel.CENTER);

        //panel container score
        JPanel panelScore = new JPanel(new GridLayout(0, 1, 0, 0));
        panelScore.add(labelScore);
        panelScore.add(lbScore);
        panelScore.setBorder(new EmptyBorder(10, 50, 10, 50));
        
        //panel container time
        JPanel panelTime = new JPanel(new GridLayout(0, 1, 0, 0));
        panelTime.add(new JLabel("Time"));
        panelTime.add(progressTime);
        panelTime.setBorder(new EmptyBorder(10, 50, 10, 20));
        
        //panle container buttons
        JPanel panelButtons = new JPanel(new GridLayout(0, 1, 2, 2));
        
        //panel container time and buttons
        JPanel panelTimeButtons = new JPanel(new BorderLayout());
        panelTimeButtons.add(panelButtons, BorderLayout.NORTH);
        panelTimeButtons.add(panelTime, BorderLayout.SOUTH);
        
        //panel container score and time
        JPanel panelScoreAndTime = new JPanel(new BorderLayout(0, 0));
        panelScoreAndTime.add(panelScore, BorderLayout.EAST);
        panelScoreAndTime.add(panelTimeButtons, BorderLayout.CENTER);

        //use panel set Layout BorderLayout to panel control in top
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 3, 5, 3));
        add(panelScoreAndTime, BorderLayout.CENTER);    
    
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        count--;
        progressTime.setValue(count);
        if(count >= 50 /5) progressTime.setForeground(Color.GREEN);
        else progressTime.setForeground(Color.red);

        // Decrement the count


        // If the count reaches zero, stop the timer
        if (count < 0) {
            timer.stop();
        }  
    }
    
    public static void upScore() {
        score += 100;
        lbScore.setText(Integer.toString(score));
    }
    
    public static void resetScore() {
        score = 0;
    }
}
