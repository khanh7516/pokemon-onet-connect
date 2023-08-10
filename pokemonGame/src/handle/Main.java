/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handle;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import view.MenuFrame;

public class Main {
    public static Clip clip;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //play music
//        try {
//            // Load audio file
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/audio/Kawai.wav"));
//
//            // Get audio clip
//            clip = AudioSystem.getClip();
//
//            // Open audio clip and start playing
//            clip.open(audioInputStream);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }        
        //hiển thị giao diện menu    
        MenuFrame menuFrame = new MenuFrame();
    }
}