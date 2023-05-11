/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handle;

import entity.Level;
import entity.Pokemon;
import view.PlayingFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//class MatrixContainerPanel giúp hiện thị và xử lý ma trận của màn chơi
public class MatrixContainerPanel extends JPanel{
    private int rows;                               // số hàng của ma trận (int)
    private int cols;                               // số cột của ma trận (int)
    private final int SIZE = 45;                    // độ dài cạnh của nút (nút vuông) trong ma trận được gán giá trị cố định là 45  (int) 
    private int numberOfImgs;                       // số lượng ảnh (pokemon) đưa vào ma trận                (int)  
    private int dupImgs;                            // số lượng ảnh (pokemon) trùng lặp bên trong ma trận    (int)
    private int numberOfLevels;                     // số lượng màn chơi của game
    
    private ArrayList<File> pokemonImgs;            // danh sánh lưu các file ảnh của các Pokemon
    private Pokemon[][] matrixButtons;              // mảng 2 chiều với các phần tử là các đối tượng thuộc lớp Pokemon (các button)
    private GameLogic gameLogic;                    // biến gameLogic kiểu dữ liệu là class gameLogic
    private PlayingFrame playingFrame;              // frame màn chơi
    public static int level = 1;                    // màn chơi thứ bao nhiêu (1 -> 5) (int)
    
    //mảng selectedCoords chứa 4 phần tử lưu tọa độ x,y của 2 button được chọn
    //được khởi tạo ban đầu với giá trị mặc định của tất cả các phần tử là -1. Tức là, ban đầu, không có phần tử nào được chọn.
    private final int[] selectedCoords = {-1, -1, -1, -1};     
    
    //Hàm khởi tạo của class 
    public MatrixContainerPanel(PlayingFrame playingFrame, Level currentLevel, int numberOfLevels) {
        this.playingFrame = playingFrame;
        this.rows = currentLevel.getRows();
        this.cols = currentLevel.getCols();
        this.numberOfImgs = currentLevel.getNumberOfImgs();
        this.dupImgs = currentLevel.getDuplicateImgs();
        
        this.numberOfLevels = numberOfLevels;
        if(level > numberOfLevels) resetLevel();    //nếu màn chơi hiện tại mà lớn hơn số lượng màn chơi, màn chơi hiện tại sẽ được reset
        
        setLayout(new GridLayout(rows, cols, 2, 2));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SIZE * cols, SIZE * rows));
        
        createListPokemonImgs();
        createMatrixButton();
    }
    //lấy mảng các nút pokemon trong ma trận
    public Pokemon[][] getMatrixButtons() {
        return matrixButtons;
    }
    //method resetLevel() reset level của game về 1 (màn chơi đầu tiên)
    public static void resetLevel() {
        level = 1;
    }
    
    private void createMatrixButton() {
        matrixButtons = new Pokemon[rows][cols];
        gameLogic = new GameLogic(matrixButtons);
        
        int index = 0;
        Collections.shuffle(pokemonImgs);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrixButtons[i][j] = new Pokemon();
                if (i > 0 && i < rows - 1 && j > 0 && j < cols - 1 && index < pokemonImgs.size()) {
                    matrixButtons[i][j].setValue(pokemonImgs.get(index));
                    matrixButtons[i][j].setIcon(createPokemonIcon(index, pokemonImgs));
                    matrixButtons[i][j].setBackground(Color.WHITE);
                    matrixButtons[i][j].setVisible(true);
                    matrixButtons[i][j].addActionListener((ActionEvent e) -> {
                        Pokemon clickedButton = (Pokemon) e.getSource();
                        handleButtonClick(clickedButton);
                        if(allButtonsAreHidden()) {
                            ControlPanel.stopTime();
                            this.playingFrame.setVisible(false);
                            this.playingFrame = new PlayingFrame(++level, playingFrame.getStartTime());  
                        }
                    });
                    index++;
                }
                add(matrixButtons[i][j]);

            }
        }
    }
    
    private void handleButtonClick(Pokemon clickedButton) {
        int x = -1;
        int y = -1;
        // Tìm tọa độ của nút được chọn
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrixButtons[i][j] == clickedButton) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (-1 != x && y != -1) {
                break;
            }
        }  

            // Nếu chưa có nút nào được chọn
        if (selectedCoords[0] == -1 && selectedCoords[1] == -1) {
            selectButton(clickedButton, x, y);
            
        }
        // Nếu nút được chọn trùng với nút đã chọn trước đó
        else if (selectedCoords[0] == x && selectedCoords[1] == y) {
            unselectButton(clickedButton);
        }
        // Nếu nút được chọn khác với nút đã chọn trước đó
        else {
            selectAnotherButton(clickedButton, x, y);
        }

//        System.out.println(checkMatrixForMatch());
//        while (!checkMatrixForMatch()) {            
//            shufflePokemons();
//        }
    }

    private void selectButton(Pokemon button, int x, int y) {
        selectedCoords[0] = x;
        selectedCoords[1] = y;
        button.setSelected(true);
    }

    private void unselectButton(Pokemon button) {
        selectedCoords[0] = -1;
        selectedCoords[1] = -1;
        button.setSelected(false);

    }

    private void selectAnotherButton(Pokemon button, int x, int y) {
        selectedCoords[2] = x;
        selectedCoords[3] = y;
        // Gọi method kiểm tra nút được chọn
        boolean isMatching = gameLogic.checkMatching(selectedCoords[0], selectedCoords[1], selectedCoords[2], selectedCoords[3]);
        if (isMatching) {
            matrixButtons[selectedCoords[0]][selectedCoords[1]].setVisible(false);
            matrixButtons[selectedCoords[2]][selectedCoords[3]].setVisible(false);
            ControlPanel.upScore();
            ControlPanel.upTime();
        }
        System.out.printf("%d,%d   %d,%d ", selectedCoords[0], selectedCoords[1], selectedCoords[2], selectedCoords[3]);
        // Đặt lại giá trị của biến tạm thời
        selectedCoords[0] = -1;
        selectedCoords[1] = -1;
        button.setSelected(false);

        System.out.println(checkMatrixForMatch());
        System.out.println("        ");
        while (!checkMatrixForMatch()) {            
            shufflePokemons();
        }

    }
    
    private boolean allButtonsAreHidden() {
        boolean allButtonsAreHidden = true;
        for (int i = 0; i < matrixButtons.length; i++) {
            for (int j = 0; j < matrixButtons[0].length; j++) {
                if (matrixButtons[i][j].isVisible()) {
                    allButtonsAreHidden = false;
                    break;
                }
            }
            if (!allButtonsAreHidden) {
                break;
            }
        }
        return allButtonsAreHidden;
    }
    
    private boolean checkMatrixForMatch() {
        if (allButtonsAreHidden()) return true;
        for (int i = 1; i < matrixButtons.length - 1; i++) {
            for (int j = 1; j < matrixButtons[0].length - 1; j++) {
                if (!matrixButtons[i][j].isVisible()) {
                    continue; // skip buttons that are not visible
                }
                for (int k = i; k < matrixButtons.length - 1; k++) {
                    for (int l = 1; l < matrixButtons[0].length - 1; l++) {
                        if (!matrixButtons[k][l].isVisible()) {
                            continue; // skip buttons that are not visible
                        }
                        if (k == i && l <= j) {
                            continue; // skip pairs that have already been checked
                        }
                        if (gameLogic.checkMatching(i, j, k, l)) {
                            return true; // found a matching pair
                        }
                    }
                }
            }
        }
        return false; // no matching pairs found
    }

    private void shufflePokemons() {
        ArrayList<File> visiblePokemonImgs = new ArrayList<>();
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++){
                if(matrixButtons[i][j].isVisible()) {
                    visiblePokemonImgs.add(matrixButtons[i][j].getValue());
                }
            }
        }
        
        Collections.shuffle(visiblePokemonImgs);
        int indexShuffleList = 0;
        
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++){
                if(matrixButtons[i][j].isVisible()) {
                    matrixButtons[i][j].setValue(visiblePokemonImgs.get(indexShuffleList));
                    matrixButtons[i][j].setIcon(createPokemonIcon(indexShuffleList, visiblePokemonImgs));
                    indexShuffleList++;
                }
            }
        }
        
    }
  
    private void createListPokemonImgs() {
        File gallery = new File("src/resources/pokemon_pictures");
        File[] images = gallery.listFiles();
        pokemonImgs = new ArrayList<>();
        for (int i = 0; i < numberOfImgs; i++) {
            for (int j = 0; j < dupImgs; j++) {
                pokemonImgs.add(images[i]);
            }
        } 
    }
    
    private ImageIcon createPokemonIcon(int index, ArrayList<File> pokemonIconsList) {
        ImageIcon icon = new ImageIcon(pokemonIconsList.get(index).getPath());
        Image image = icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
    
}
