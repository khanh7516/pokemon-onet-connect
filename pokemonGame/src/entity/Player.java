/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Player {                   //class Player đại diện cho người chơi or 1 lần chơi (sau khi người chơi chơi xong thắng/ thua -> class Player được khởi tạo để lưu 1 lịch sử chơi)
    private static int autoId;          
    private int id;
    private LocalDateTime startTime;    // thuộc tính lưu giá trị thời gian bắt đầu chơi (LocalDateTime)
    private LocalDateTime endTime;      // thuộc tính lưu giá trị thời gian khi chơi xong (LocalDateTime)
    private String startTimeToString;   // thời gian bắt đầu chơi được chuyển sang kiểu dữ liệu String (dễ dàng hiện thị khi cần)
    private long timeTakenInSeconds;    // khoảng thời gian chơi trong khi chơi (chơi trong bao lâu) lưu theo kiểu dữ liệu long (1 giá trị là 1s)
    private String timeTaken;           // khoảng thời gian chơi trong khi chơi được quy đổi ra phút (vd: 1p 30s, 5p 10s, ...) (String)    
    private int levelReached;           // cấp độ cuối cùng mà lần chơi đó đạt tới (int) 
    private int score;                  // điểm số mà lần chơi đó nhận được khi hoàn thành trò chơi (int)
    private boolean hasCompletedAllLevels ; // lần chơi đó có vượt qua hết toàn bộ 5 màn chơi không (boolean) (nếu vượt qua cả 5 màn chơi --> true/ nếu không thì --> false)

    public Player() {
    }
    
    public Player(LocalDateTime startTime, LocalDateTime endTime, int levelReached, int score, boolean hasCompletedAllLevels) { // khởi tạo đối tượng Player với các tham số (thời gian bắt đầu chơi, thời gian kết thúc chơi, cấp độ cuối cùng, điểm số, hoàn thành tất cả màn chơi)
        this.id = ++autoId;
        this.startTime = startTime;                 // gán giá trị của đối số cho thuộc tính của đối tượng
        this.endTime = endTime;
        
        //chuyển đổi giá trị thời gian bắt đầu (LocalDateTime) sang dạng String
        LocalDateTime changeTime = startTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.startTimeToString =  changeTime.format(formatter);       

        // lưu giá trị khoảng thời gian chơi bằng cách tìm giá trị duration (Duration) giữa startTime và endTime
        Duration duration = Duration.between(startTime, endTime);   // method beetween() của class Duration giúp tìm khoảng thời gian giữa 2 biến LocalDateTime
        timeTakenInSeconds = duration.getSeconds();                                      // khoảng thời gian được chuyển sang giây khi sử dụng thuộc tính getSecond và được gán cho thuộc tính timeTakenInSeconds   
        if (timeTakenInSeconds < 60) {                               // kiểm tra nếu khoảng thời gian (khi chuyển sang giây) < 60 không                           
            this.timeTaken = timeTakenInSeconds + "s";               // nếu có gán giá trị timeTaken thành [số giây] + s (vd: 20s, 30s, 45s, ...)                 
        } else {
            long minutes = timeTakenInSeconds / 60;                  // ngược lại đổi số giây ra phút nêu giá trị lớn hơn 60 bằng cách lấy số giây chia cho 60   
            long remainingSeconds = timeTakenInSeconds % 60;         // số giây còn lại sau khi đổi sang phút được tính qua phép chia phần dư cho 60   
            this.timeTaken = minutes + "m" + remainingSeconds + "s"; // gán giá trị timeTaken thành [số phút] + m [space] + [số giây còn lại] + s (vd: 1p 20s, 2p 10s, 5p 18s)  
        }
        
        this.levelReached = levelReached;
        this.score = score;
        this.hasCompletedAllLevels = hasCompletedAllLevels;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStartTimeToString() {
        return startTimeToString;
    }
    
    
    public long getTimeTakenInSeconds() {
        return timeTakenInSeconds;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public int getLevelReached() {
        return levelReached;
    }

    public int getScore() {
        return score;
    }

    public boolean isHasCompletedAllLevels() {
        return hasCompletedAllLevels;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTimeToString(String startTimeToString) {
        this.startTimeToString = startTimeToString;
    }
    
    
    public void setTimeTakenInSeconds(long timeTakenInSeconds) {
        this.timeTakenInSeconds = timeTakenInSeconds;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setLevelReached(int levelReached) {
        this.levelReached = levelReached;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHasCompletedAllLevels(boolean hasCompletedAllLevels) {
        this.hasCompletedAllLevels = hasCompletedAllLevels;
    }
    
    
    
    
    
    
    
    
    

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", timeTaken=" + timeTaken + ", levelReached=" + levelReached + ", score=" + score + ", hasCompletedAllLevels=" + hasCompletedAllLevels + '}';
    }
    
    
    
    
}
