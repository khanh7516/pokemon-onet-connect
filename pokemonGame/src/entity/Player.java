/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Acer
 */
public class Player {
    private static int autoId;
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String startTimeToString;
    private long timeTakenInSeconds;
    private String timeTaken;
    private int levelReached;
    private int score;
    private boolean hasCompletedAllLevels ;

    public Player() {
    }
    
    public Player(LocalDateTime startTime, LocalDateTime endTime, int levelReached, int score, boolean hasCompletedAllLevels) {
        this.id = ++autoId;
        this.startTime = startTime;
        this.endTime = endTime;
        
        LocalDateTime changeTime = startTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.startTimeToString =  changeTime.format(formatter);       

        
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStartTimeToString() {
        return startTimeToString;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTimeToString(String startTimeToString) {
        this.startTimeToString = startTimeToString;
    }
    
    
    public void setTimeTakenInSeconds(long timeTakenInSeconds) {
        this.timeTakenInSeconds = timeTakenInSeconds;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setLevelReached(int levelReached) {
        this.levelReached = levelReached;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHasCompletedAllLevels(boolean hasCompletedAllLevels) {
        this.hasCompletedAllLevels = hasCompletedAllLevels;
    }
    
    
    
    
    
    
    
    
    

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", timeTaken=" + timeTaken + ", levelReached=" + levelReached + ", score=" + score + ", hasCompletedAllLevels=" + hasCompletedAllLevels + '}';
    }
    
    
    
    
}
