/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Acer
 */
public class Player {
    private static int autoId;
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String timeTaken;
    private int levelReached;
    private int score;
    private boolean hasCompletedAllLevels ;

    public Player(LocalDateTime startTime, LocalDateTime endTime, double timeTaken, int levelReached, int score, boolean hasCompletedAllLevels) {
        this.id = ++autoId;
        this.startTime = startTime;
        this.endTime = endTime;
        Duration duration = Duration.between(startTime, endTime);
        double timeTakenInSeconds = duration.getSeconds();
        if (timeTakenInSeconds < 60) {
            this.timeTaken = timeTakenInSeconds + "s";
        } else {
            double minutes = timeTakenInSeconds / 60;
            double remainingSeconds = timeTakenInSeconds % 60;
            this.timeTaken = minutes + "m" + remainingSeconds + "s";
        }
        this.levelReached = levelReached;
        this.score = score;
        this.hasCompletedAllLevels = hasCompletedAllLevels;
    }
    
    
    
}
