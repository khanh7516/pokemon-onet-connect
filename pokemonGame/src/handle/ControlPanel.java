/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handle;

import entity.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import view.MenuFrame;
import view.PlayedHistoryFrame;
import view.PlayingFrame;

/**
 *
 * @author Acer
 */
public class ControlPanel extends JPanel implements ActionListener{
    private static Timer timer;
    private static int count;
    private static int copyCount;
    private static int score = 0;
    private static JProgressBar progressTime;
    private static JLabel lbScore;
    
    private PlayingFrame playingFrame;
    private int levelIndex;
    private LocalDateTime startTime;

    public ControlPanel(PlayingFrame playingFrame, int playingTime, int levelIndex, LocalDateTime startTime) {
        this.playingFrame = playingFrame;
        this.levelIndex = levelIndex;
        this.startTime = startTime;
        
        count = playingTime;
        copyCount = count;
        
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
        
        JLabel lblScoreTitle = new JLabel("Score");
        lblScoreTitle.setHorizontalAlignment(JLabel.CENTER);

        //panel container score
        JPanel panelScore = new JPanel(new GridLayout(0, 1, 0, 0));
        panelScore.add(lblScoreTitle);
        panelScore.add(lbScore);
        panelScore.setBorder(new EmptyBorder(10, 50, 10, 50));
        
        //panel container time
        JPanel panelTime = new JPanel(new GridLayout(0, 1, 0, 0));
        panelTime.add(new JLabel("Time"));
        panelTime.add(progressTime);

        
        //panle container buttons
        JPanel panelButtons = new JPanel(new GridLayout(0, 8, 2, 0));
        JLabel lblLevel = new JLabel();
        lblLevel.setText("Level " + Integer.toString(levelIndex));
        panelButtons.add(lblLevel);
        
        JButton btnPauseGame = new JButton("Pause");
        btnPauseGame.setBackground(Color.BLACK);
        btnPauseGame.setForeground(Color.WHITE);
        btnPauseGame.addActionListener((e) -> {
            timer.stop();
            Main.clip.stop();
            JPanel glassPane = new JPanel();
            glassPane.setOpaque(true);
            playingFrame.setGlassPane(glassPane);
            glassPane.setVisible(true);
            
            int option = JOptionPane.showOptionDialog(null, "Tiếp tục trò chơi hoặc bỏ cuộc?", "Trò chơi đang tạm dừng", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                        new String[] {"Tiếp tục", "Bỏ cuộc"}, "Tiếp tục" );
            if(option == 0 || option == JOptionPane.CLOSED_OPTION) {
                timer.start();
                Main.clip.loop(Clip.LOOP_CONTINUOUSLY);
                glassPane.setVisible(false);        
            }else {
                Main.clip.loop(Clip.LOOP_CONTINUOUSLY);
                PlayedHistoryFrame.addNewPlayer(new Player(startTime, LocalDateTime.now(), levelIndex, score, false));
//                PlayedHistoryFrame.displayPlayers();
                PlayedHistoryFrame.saveGame();
                MenuFrame menuFrame = new MenuFrame();
                playingFrame.setVisible(false);     
                resetScore();
                MatrixContainerPanel.resetLevel();
            }
        });
        
        JToggleButton toggleMuteSound = new JToggleButton("Next Level");
        toggleMuteSound.setBackground(Color.BLACK);
        toggleMuteSound.setForeground(Color.WHITE);
        toggleMuteSound.addActionListener((e) -> {
              timer.stop();
              playingFrame.setVisible(false);
              playingFrame = new PlayingFrame(++MatrixContainerPanel.level, startTime);
              
        });
        panelButtons.add(btnPauseGame);
        panelButtons.add(toggleMuteSound);
        
        //panel container time and buttons
        JPanel panelTimeButtons = new JPanel(new BorderLayout());
        panelTimeButtons.add(panelButtons, BorderLayout.NORTH);
        panelTimeButtons.add(panelTime, BorderLayout.SOUTH);
        panelTimeButtons.setBorder(new EmptyBorder(10, 50, 10, 20));
        
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
        count--;          // Decrement the count
        progressTime.setValue(count);
        if(count >= copyCount / 5) progressTime.setForeground(Color.GREEN);
        else progressTime.setForeground(Color.red);

        // If the count reaches zero, stop the timer
        if (count < 0) {
            timer.stop();
            int option = JOptionPane.showOptionDialog(null, "Quay về menu?", "Game over!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
            if (option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
                PlayedHistoryFrame.addNewPlayer(new Player(startTime, LocalDateTime.now(), levelIndex, score, false));
//                PlayedHistoryFrame.displayPlayers();\
                PlayedHistoryFrame.saveGame();
                MenuFrame menuFrame = new MenuFrame();
                playingFrame.setVisible(false);
                resetScore();
                MatrixContainerPanel.resetLevel();
            }
        }  
    }
    
    public static void upScore() {
        if(count >= copyCount / 2 ) score += 200;
        else score += 100;
        lbScore.setText(Integer.toString(score));
    }

    public static int getScore() {
        return score;
    }
    
    public static void resetScore() {
        score = 0;
    }
    
    public static void stopTime() {
        timer.stop();
    }
    
    public static void upTime() {
        count += 5;
        progressTime.setValue(count);
    }
}
