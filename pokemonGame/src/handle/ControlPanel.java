/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handle;

import entity.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import view.MenuFrame;
import view.PlayedHistoryFrame;
import view.PlayingFrame;


// class ControlPanel giúp: xử lý và hiện thị các dữ liệu như ( thời gian chơi, điểm số, màn chơi hiện tại)
// class này kế thừa 1 JPanel giúp hiển thị giao diện phần control của frame chơi game
public class ControlPanel extends JPanel implements ActionListener{   
    private static Timer timer;                 // thuộc tính timer (Timer 1 class có sẵn của Java Swing) là bộ đếm để thay đổi thời gian (cụ thể là giảm dần thời gian chơi)
    private static int count;                       
    private static int copyCount;
    private static int score = 0;               // thuộc tính score hiển thị số điểm khi chơi xong giá trị mặc định là 0
    private static JProgressBar progressTime;   // progressTime là 1 thanh ngang giúp hiển thị thời gian chơi dưới dạng hình ảnh (JProgessBar 1 class có sẵn của Java Swing)
    private static JLabel lbScore;              // 1 label giúp hiển thị điểm màn chơi trên đó
    
    private PlayingFrame playingFrame;
    private static int levelIndex;              //cấp độ chơi (int)
    private LocalDateTime startTime;            //thời gian bắt đầu chơi (LocalDateTime)

    //khởi tạo đối tượng với các tham số
    public ControlPanel(PlayingFrame playingFrame, int playingTime, int levelIndex, LocalDateTime startTime) {
        // gán giá trị cho các thuộc tính tương ứng với các đối số truyển vào
        this.playingFrame = playingFrame;
        this.levelIndex = levelIndex;
        this.startTime = startTime;
        
        count = playingTime;
        copyCount = count;
        
        createControlView();
        timer = new Timer(1000, this);
        timer.start();
        
    }
    // method createControlView() giúp tạo các component và sắp xếp chúng trong panel theo kiểu BorderLayout
    private void createControlView() {
        lbScore = new JLabel();                                         // khởi tạo 1 nhãn lbScore (JLabel) chứa giá trị điểm số
        lbScore.setText(Integer.toString(score));                 // gán giá trị điểm số cho  nhãn
        lbScore.setFont(new Font("Arial",Font.BOLD ,26));   // thay đổi Font chữ, kích cỡ cho giá trị trong nhãn
        lbScore.setHorizontalAlignment(JLabel.CENTER);            // căn giữa giá trị theo chiều ngang  nhãn
        lbScore.setVerticalAlignment(JLabel.CENTER);              // căn giữa giá trị theo chiều dọc của nhãn 
        
        //khởi tạo thanh tiến độ thời gian chơi (min = 0, max = count)  (count càng lớn thời gian chơi càng nhiều)
        progressTime = new JProgressBar(0, count);
        progressTime.setValue(count);
        progressTime.setForeground(Color.GREEN);
        
        JLabel lblScoreTitle = new JLabel("Score");
        lblScoreTitle.setHorizontalAlignment(JLabel.CENTER);

        //panel container score
        JPanel panelScore = new JPanel(new GridLayout(0, 1, 0, 0));
        panelScore.add(lblScoreTitle);
        panelScore.add(lbScore);
        panelScore.setBorder(new EmptyBorder(10, 50, 10, 50));
        
        //panel container time
        JPanel panelTime = new JPanel(new GridLayout(0, 1, 0, 0));
        panelTime.add(new JLabel("Time"));
        panelTime.add(progressTime);

        
        //panle container buttons
        JPanel panelButtons = new JPanel(new GridLayout(0, 8, 2, 0));
        JLabel lblLevel = new JLabel();
        lblLevel.setText("Level " + Integer.toString(levelIndex));
        panelButtons.add(lblLevel);
        
        //tạo 1 nút Pause (giúp dừng trò chơi lại)
        JButton btnPauseGame = new JButton("Pause");
        btnPauseGame.setBackground(Color.BLACK);            // màu nền nút: đen
        btnPauseGame.setForeground(Color.WHITE);            // màu chữ nút: trắng
        btnPauseGame.addActionListener((e) -> {                // method được thực hiện sau sự kiện nhấn nút 
            timer.stop();                         // thời gian được dừng lại              
            Main.clip.stop();                     // âm nhạc dừng lại  
            JPanel glassPane = new JPanel();      // che màn chơi hiện tại lại (người chơi không thể gian lận khi thời gian đang dừng)  
            glassPane.setOpaque(true);
            playingFrame.setGlassPane(glassPane);
            glassPane.setVisible(true);
            //hiển thị hộp thoại với 2 lựa chọn (tiếp tục chơi hoặc bỏ cuộc)
            int option = JOptionPane.showOptionDialog(null, "Tiếp tục trò chơi hoặc bỏ cuộc?", "Trò chơi đang tạm dừng", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                        new String[] {"Tiếp tục", "Bỏ cuộc"}, "Tiếp tục" );
            if(option == 0 || option == JOptionPane.CLOSED_OPTION) {                    // nếu người chơi chọn tiếp tục hoặc đóng hộp thoại bằng nút close: 
                timer.start();                                                          // thời gian tiếp tục chạy
                Main.clip.loop(Clip.LOOP_CONTINUOUSLY);                            // nhạc tiếp tục chạy 
                glassPane.setVisible(false);                                       // màn chơi đang bị che được mở lại 
            }else {                                                        // nếu người chơi chọn nút bỏ cuộc (màn chơi được tính là kết thúc, người chơi được tính là thua cuộc)         
                Main.clip.loop(Clip.LOOP_CONTINUOUSLY);               // tiếp tục phát nhạc
                //khởi tạo 1 đối tượng thuộc lớp (Player) lưu các thông tin về thời gian bắt đầu, thời gian kết thúc, thời gian chơi, điểm số,... khi người chơi kết thúc màn chơi và thêm nó vào danh sách lưu các đối tượng lớp Player
                PlayedHistoryFrame.addNewPlayer(new Player(startTime, LocalDateTime.now(), levelIndex, score, false));
//                PlayedHistoryFrame.displayPlayers();
                PlayedHistoryFrame.saveGame();              // lưu lần chơi vừa xong vào file Excel
                MenuFrame menuFrame = new MenuFrame();      // hiển thị giao diện menu
                playingFrame.setVisible(false);           // ẩn giao diện chơi game
                resetScore();                               // reset điểm số (điểm số về 0)
                MatrixContainerPanel.resetLevel();          // reset màn chơi (màn chơi về 1)
            }
        });
        
        JToggleButton toggleMuteSound = new JToggleButton("Next Level");
        toggleMuteSound.setBackground(Color.BLACK);
        toggleMuteSound.setForeground(Color.WHITE);
        toggleMuteSound.addActionListener((e) -> {
              timer.stop();
              playingFrame.setVisible(false);
              playingFrame = new PlayingFrame(++MatrixContainerPanel.level, startTime);
              
        });
        panelButtons.add(btnPauseGame);
        panelButtons.add(toggleMuteSound);
        
        //panel container time and buttons
        JPanel panelTimeButtons = new JPanel(new BorderLayout());
        panelTimeButtons.add(panelButtons, BorderLayout.NORTH);
        panelTimeButtons.add(panelTime, BorderLayout.SOUTH);
        panelTimeButtons.setBorder(new EmptyBorder(10, 50, 10, 20));
        
        //panel container score and time
        JPanel panelScoreAndTime = new JPanel(new BorderLayout(0, 0));
        panelScoreAndTime.add(panelScore, BorderLayout.EAST);
        panelScoreAndTime.add(panelTimeButtons, BorderLayout.CENTER);

        //use panel set Layout BorderLayout to panel control in top
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 3, 5, 3));
        add(panelScoreAndTime, BorderLayout.CENTER);    
    
    }
    
    //method actionPerformed được triệu hồi bởi Timer sau mỗi khoảng thời gian nhất định
    @Override
    public void actionPerformed(ActionEvent e) {
        count--;          // giảm biến count 1 giá trị
        progressTime.setValue(count); //thay đổi giá trị trên thanh progressTime
        if(count >= copyCount / 5) progressTime.setForeground(Color.GREEN); //đổi màu của thanh progressTime thành màu xanh lục khi thời gian còn lại >= 20%
        else progressTime.setForeground(Color.red);                         //ngược lại thanh progressTime sẽ thành màu đỏ

        // nếu biến count đạt về 0, dừng timer (màn chơi đã kết thúc, người chơi thua cuộc)
        if (count < 0) {
            timer.stop(); //thời gian dừng lại
            //hiển thị hộp thoại báo người chơi đã thua cuộc với duy nhất 1 lựa chọn quay về menu
            int option = JOptionPane.showOptionDialog(null, "Quay về menu?", "Game over!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
            if (option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
                //khởi tạo 1 đối tượng thuộc lớp (Player) lưu các thông tin về thời gian bắt đầu, thời gian kết thúc, thời gian chơi, điểm số,... khi người chơi kết thúc màn chơi và thêm nó vào danh sách lưu các đối tượng lớp Player
                PlayedHistoryFrame.addNewPlayer(new Player(startTime, LocalDateTime.now(), levelIndex, score, false)); 
//                PlayedHistoryFrame.displayPlayers();
                PlayedHistoryFrame.saveGame();        // lưu lần chơi vừa xong vào file Excel
                MenuFrame menuFrame = new MenuFrame();// khởi tạo 1 đối tượng menuFrame (thuộc class MenuFrame) giúp hiển thị giao diện menu
                playingFrame.setVisible(false);     // ẩn gian diện chơi
                resetScore();                         // reset điểm số  (điểm số về 0)
                MatrixContainerPanel.resetLevel();    // reset level hiện tại (reset về level ban đầu level 1)  
            }
        }  
    }
    
    //method upScore() giúp tăng điểm (tăng điểm khi người chơi chọn đúng)
    public static void upScore() {
        if(count >= copyCount / 2 ) score += 200;  //nếu thời gian còn lại > 50% điểm sẽ được tăng 200
        else score += 100;                          // ngược lại điểm chỉ được tăng 100
        lbScore.setText(Integer.toString(score)); // thay đổi giá trị điểm trên lbScore --> người chơi có thể nhìn thấy
    }
    //method getScore() giúp lấy giá trị điểm số hiện tại
    public static int getScore() {
        return score;
    }
    //method resetScore() giúp reset điểm số về 0 (khi màn chơi kết thúc)
    public static void resetScore() {
        score = 0;
    }
    //method stopTime() giúp dừng thời gian
    public static void stopTime() {
        timer.stop();
    }
    //method upTime() giúp tăng thời gian (tăng thời gian khi người chơi chọn đúng)
    public static void upTime() {
        count += 3;                         //thời gian được tăng 3s
        progressTime.setValue(count);     //gán lại giá trị thời gian đã được tăng lên trước đó vào thanh progressTime
    }
}
