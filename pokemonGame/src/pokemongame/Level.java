/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongame;

/**
 *
 * @author Acer
 */
public class Level {
    private int rows;
    private int cols;
    private int numberOfImgs;
    private int duplicateImgs;
    private int playingTime;
    
    public Level(int rows, int cols, int numberOfImgs, int duplicateImgs, int playingTime) {
        this.rows = rows;
        this.cols = cols;
        this.numberOfImgs = numberOfImgs;
        this.duplicateImgs = duplicateImgs;
        this.playingTime = playingTime;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getNumberOfImgs() {
        return numberOfImgs;
    }

    public int getDuplicateImgs() {
        return duplicateImgs;
    }

    public int getPlayingTime() {
        return playingTime;
    }
    
    
    
}
