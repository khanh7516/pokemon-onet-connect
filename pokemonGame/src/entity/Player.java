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
    private long timeTakenInSeconds;
    private String timeTaken;
    private int levelReached;
    private int score;
    private boolean hasCompletedAllLevels ;

    public Player(LocalDateTime startTime, LocalDateTime endTime, int levelReached, int score, boolean hasCompletedAllLevels) {
        this.id = ++autoId;
        this.startTime = startTime;
        this.endTime = endTime;
        
        Duration duration = Duration.between(startTime, endTime);
        timeTakenInSeconds = duration.getSeconds();
        if (timeTakenInSeconds < 60) {
            this.timeTaken = timeTakenInSeconds + "s";
        } else {
            long minutes = timeTakenInSeconds / 60;
            long remainingSeconds = timeTakenInSeconds % 60;
            this.timeTaken = minutes + "m" + remainingSeconds + "s";
        }
        
        this.levelReached = levelReached;
        this.score = score;
        this.hasCompletedAllLevels = hasCompletedAllLevels;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public long getTimeTakenInSeconds() {
        return timeTakenInSeconds;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public int getLevelReached() {
        return levelReached;
    }

    public int getScore() {
        return score;
    }

    public boolean isHasCompletedAllLevels() {
        return hasCompletedAllLevels;
    }
    
    
    
    
    
    
    

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", timeTaken=" + timeTaken + ", levelReached=" + levelReached + ", score=" + score + ", hasCompletedAllLevels=" + hasCompletedAllLevels + '}';
    }
    
    
    
    
}
