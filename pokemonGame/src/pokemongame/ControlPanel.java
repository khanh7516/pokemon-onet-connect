/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Acer
 */
public class ControlPanel extends JPanel {

    public ControlPanel() {
//		lbScore = new JLabel("0");
//		// lbTime = new JLabel("0");
//		progressTime = new JProgressBar(0, 100);
//		progressTime.setValue(100);

        // create panel container score and time

        JPanel panelLeft = new JPanel(new GridLayout(0, 1, 5, 5));
        panelLeft.add(new JLabel("Score:"));
        panelLeft.add(new JLabel("Time:"));

//		JPanel panelCenter = new JPanel(new GridLayout(0, 1, 5, 5));
//		panelCenter.add(lbScore);
//		panelCenter.add(progressTime);

        JPanel panelScoreAndTime = new JPanel(new BorderLayout(5, 0));
        panelScoreAndTime.add(panelLeft, BorderLayout.WEST);
//		panelScoreAndTime.add(panelCenter, BorderLayout.CENTER);

        // create panel container panelScoreAndTime and button new game
        JPanel panelControl = new JPanel(new BorderLayout(10, 10));
        panelControl.setBorder(new EmptyBorder(10, 3, 5, 3));
        panelControl.add(panelScoreAndTime, BorderLayout.CENTER);
//		panelControl.add(createButton("New Game"),
//				BorderLayout.PAGE_END);

//		Icon icon = new ImageIcon(getClass().getResource(
//				"/nguyenvanquan7826/icon/pokemon.png"));

        // use panel set Layout BorderLayout to panel control in top
        setLayout(new BorderLayout());
        add(panelControl, BorderLayout.PAGE_START);
//		panel.add(new JLabel(icon), BorderLayout.CENTER);
    }
    
    
}
