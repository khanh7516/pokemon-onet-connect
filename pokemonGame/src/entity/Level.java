/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Acer
 */
public class Level {                //class Level đại diện cho các màn chơi
    private int rows;               // thuộc tính số hàng trong ma trận của màn chơi (int)
    private int cols;               // thuộc thính số cột trong ma trận của màn chơi (int)
    private int numberOfImgs;       // số lượng ảnh (pokemon) đưa vào ma trận (int)
    private int duplicateImgs;      // số lượng ảnh (pokemon) trùng lặp bên trong ma trận (int)
    private int playingTime;        // thời gian chơi (int) 1 giá trị đại diện cho 1s
    
    public Level(int rows, int cols, int numberOfImgs, int duplicateImgs, int playingTime) { //hàm khởi tạo đối tượng với các tham số tương ứng với các thuộc tính của class
        // gán giá trị cho các thuộc tính với các đối số tương ứng     
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
