/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handle;

import entity.Pokemon;

/**
 *
 * @author Acer
 */
public class GameLogic {
    
    private final Pokemon[][] matrixButtons;
    
    public GameLogic(Pokemon[][] matrixButtons) {
        this.matrixButtons = matrixButtons;
    }
    
    
    private boolean isEqualValue(int x0, int y0, int x1, int y1) {
        return matrixButtons[x0][y0].getValue().equals(matrixButtons[x1][y1].getValue());
    }
    
    private int[] getVerticalRange(int x, int y) {
        int top = x, bot = x;
        while (bot - 1 >= 0) {
            if (!matrixButtons[bot - 1][y].isVisible()) bot--;
            else break;
        }
        while (top + 1 < matrixButtons.length) {
            if (!matrixButtons[top + 1][y].isVisible()) top++;
            else break;
        }
        int[] range = {bot, top};
        return range;
    }
    
    private int[] getHorizontalRange(int x, int y) {
        int left = y, right = y;
        while (left - 1 >= 0) {
            if (!matrixButtons[x][left - 1].isVisible()) left--;
            else break;
        }
        while (right + 1 < matrixButtons[0].length) {
            if (!matrixButtons[x][right + 1].isVisible()) right++;
            else break;
        }
        int[] range = {left, right};
        return range;
    }
    
    private boolean isHConnect(int[] range1, int[] range2, int[] distanceRange) {
        int maxBot = (range1[0] >= range2[0]) ? range1[0] : range2[0];
        int minTop = (range1[1] < range2[1]) ? range1[1] : range2[1];
        
        int min = (distanceRange[0] < distanceRange[1]) ? distanceRange[0] : distanceRange[1];
        int max = (distanceRange[0] >= distanceRange[1]) ? distanceRange[0] : distanceRange[1];
        
        for (int i = maxBot; i <= minTop; i++){
            int invisibleBtnsCount = 0;
            for (int j = min + 1; j < max; j++ ) {
                if(!matrixButtons[i][j].isVisible()) invisibleBtnsCount++;
                else break;
            }
            if(invisibleBtnsCount == (max - min - 1)) return true;
        }
        return false;
    }
    
    private boolean isVConnect(int[] range1, int[] range2, int[] distanceRange) {
        int maxBot = (range1[0] >= range2[0]) ? range1[0] : range2[0];
        int minTop = (range1[1] < range2[1]) ? range1[1] : range2[1];
        
        int min = (distanceRange[0] < distanceRange[1]) ? distanceRange[0] : distanceRange[1];
        int max = (distanceRange[0] >= distanceRange[1]) ? distanceRange[0] : distanceRange[1];
        
        for (int i = maxBot; i <= minTop; i++){
            int invisibleBtnsCount = 0;
            for (int j = min + 1; j < max; j++ ) {
                if(!matrixButtons[j][i].isVisible()) invisibleBtnsCount++;
                else break;
            }
            if(invisibleBtnsCount == (max - min - 1)) return true;
        }
        return false;
    }

    public boolean checkMatching(int x0, int y0, int x1, int y1) {
        if(isEqualValue(x0, y0, x1, y1)) {
            int[] vRangeOfSelec1 = getVerticalRange(x0, y0);
            int[] vRangeOfSelec2 = getVerticalRange(x1, y1);
            int[] horizontalDistanceRange = {y0, y1};
            
            int[] hRangeOfSelec1 = getHorizontalRange(x0, y0);
            int[] hRangeOfSelec2 = getHorizontalRange(x1, y1);
            int[] verticalDistanceRange = {x0, x1};
            
            if(isHConnect(vRangeOfSelec1, vRangeOfSelec2, horizontalDistanceRange) 
                    || isVConnect(hRangeOfSelec1, hRangeOfSelec2, verticalDistanceRange))
                return true;
        }
        return false; 
    }
    
        
        
}
    
    
    
   
