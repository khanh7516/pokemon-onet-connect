/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongame;

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

/**
 *
 * @author Acer
 */
public class MatrixContainerPanel extends JPanel{
    private int rows;
    private int cols;
    private int size = 45;
    private int numberOfImgs;
    private int dupImgs;
    private int numberOfLevels = 2;
    
    private ArrayList<File> pokemonImgs; 
    private Pokemon[][] matrixButtons;
    private GameLogic gameLogic;
    private PlayingFrame playingFrame;
    private static int level = 1;
    private final int[] selectedCoords = {-1, -1, -1, -1};
    
    public MatrixContainerPanel(PlayingFrame playingFrame, int rows, int cols, int numberOfImgs, int duplicateImgs) {
        this.playingFrame = playingFrame;
        this.rows = rows;
        this.cols = cols;
        this.numberOfImgs = numberOfImgs;
        this.dupImgs = duplicateImgs;
        
        if(level > numberOfLevels) {
            level = 1;
        }
        
        setLayout(new GridLayout(rows, cols, 2, 2));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(size * cols, size * rows));
        createListPokemonImgs();
        createMatrixButton();
    }

    public Pokemon[][] getMatrixButtons() {
        return matrixButtons;
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
                            this.playingFrame = new PlayingFrame(level);                           
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
        System.out.println("!!!!!!!!");
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
