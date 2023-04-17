/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Matrix {
    private int[][] matrix;
    private int rows;
    private int cols;
 
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
    }
 
    public int getRows() {
        return rows;
    }
 
    public int getCols() {
        return cols;
    }
 
    public int getValue(Point p) {
        return matrix[p.getX()][p.getY()];
    }
 
    public void setValue(Point p, int value) {
        matrix[p.getX()][p.getY()] = value;
    }
 
    public void clearValue(Point p) {
        matrix[p.getX()][p.getY()] = 0;
    }
}
