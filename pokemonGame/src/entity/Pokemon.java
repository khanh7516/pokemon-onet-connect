/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.File;
import javax.swing.JButton;

/**
 *
 * @author Acer
 */
public class Pokemon extends JButton{
    private File value;
    
    public Pokemon() {
        setVisible(false);
    }

    public File getValue() {
      return value;
    }
    public void setValue(File value) {
      this.value = value;
    }
    
}
