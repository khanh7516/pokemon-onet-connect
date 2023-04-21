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
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Acer
 */
public class MatrixContainerPanel extends JPanel{
    private final int ROWS = 10;
    private final int COLS = 20;
    private final int size = 45;
    
    private ArrayList<File> pokemonImgs; 
    private Pokemon[][] matrixButtons;
    private GameLogic gameLogic;
    private int[] selectedCoords = {-1, -1, -1, -1};
    
    public MatrixContainerPanel() {
        
        setLayout(new GridLayout(ROWS, COLS, 2, 2));
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension((size) * COLS, (size)* ROWS));
        createListPokemonImgs();
        createMatrixButton();
    }
    
    private void createMatrixButton() {
        matrixButtons = new Pokemon[ROWS][COLS];
        gameLogic = new GameLogic(matrixButtons);
        int index = 0;
        Collections.shuffle(pokemonImgs);
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                matrixButtons[i][j] = new Pokemon();
                if (i > 0 && i < ROWS - 1 && j > 0 && j < COLS - 1 && index < pokemonImgs.size()) {
                    matrixButtons[i][j].setValue(pokemonImgs.get(index));
                    matrixButtons[i][j].setIcon(createPokemonIcon(index));
                    matrixButtons[i][j].setBackground(Color.WHITE);
                    matrixButtons[i][j].setVisible(true);
                    matrixButtons[i][j].addActionListener((ActionEvent e) -> {
                        Pokemon clickedButton = (Pokemon) e.getSource();
                        handleButtonClick(clickedButton);
                    });
                    index++;
                }
                add(matrixButtons[i][j]);

            }
        }
    }
    
    private void handleButtonClick(Pokemon clickedButton) {
        int row = -1;
        int col = -1;
        // Tìm tọa độ của nút được chọn
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (matrixButtons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1) {
                break;
            }
        }  

            // Nếu chưa có nút nào được chọn
        if (selectedCoords[0] == -1 && selectedCoords[1] == -1) {
            selectButton(clickedButton, row, col);
        }
        // Nếu nút được chọn trùng với nút đã chọn trước đó
        else if (selectedCoords[0] == row && selectedCoords[1] == col) {
            unselectButton(clickedButton);
        }
        // Nếu nút được chọn khác với nút đã chọn trước đó
        else {
            selectAnotherButton(clickedButton, row, col);
        }
    }

    private void selectButton(Pokemon button, int row, int col) {
        selectedCoords[0] = row;
        selectedCoords[1] = col;
        button.setSelected(true);
    }

    private void unselectButton(Pokemon button) {
        selectedCoords[0] = -1;
        selectedCoords[1] = -1;
        button.setSelected(false);
    }

    private void selectAnotherButton(Pokemon button, int row, int col) {
        selectedCoords[2] = row;
        selectedCoords[3] = col;
        // Gọi method kiểm tra nút được chọn
        boolean isMatching = gameLogic.checkMatching(selectedCoords[0], selectedCoords[1], selectedCoords[2], selectedCoords[3]);
        if (isMatching) {
            matrixButtons[selectedCoords[0]][selectedCoords[1]].setVisible(false);
            matrixButtons[selectedCoords[2]][selectedCoords[3]].setVisible(false);
        }
        System.out.printf("%d,%d   %d,%d ", selectedCoords[0], selectedCoords[1], selectedCoords[2], selectedCoords[3]);
        // Đặt lại giá trị của biến tạm thời
        selectedCoords[0] = -1;
        selectedCoords[1] = -1;
        button.setSelected(false);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void createListPokemonImgs() {
        File gallery = new File("E:/github/pokemon-onet-connect/pokemonGame/src/resources/pokemon_pictures");
        File[] images = gallery.listFiles();
        pokemonImgs = new ArrayList<>();
        for (File image : images) {
            for (int j = 0; j < 6; j++) {
                pokemonImgs.add(image);
            }
        } 
    }
    
    private ImageIcon createPokemonIcon(int index) {
        ImageIcon icon = new ImageIcon(pokemonImgs.get(index).getPath());
        Image image = icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    
}
