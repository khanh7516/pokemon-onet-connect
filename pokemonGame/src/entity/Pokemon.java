/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.File;
import javax.swing.JButton;

public class Pokemon extends JButton{ //class Pokemon đại diện cho mỗi một nút có hình pokemon trong 1 ma của 1 màn chơi (kế thừa JButton)
    
    private File value;               // thuộc tính value lưu giá trị của mỗi nút ( kiểu dữ liệu File)
                                     // thuộc tính value giúp so sánh giá trị của các nút ( 2 nút giống nhau or 2 nút khác nhau)    
                                    // thuộc tính value giúp set icon cho các nút thuận tiện hơn  
   
    public Pokemon() {              // khởi tạo 1 đối tượng (tạo ra 1 nút pokemon)
        
        setVisible(false);     // sau khi khởi tạo --> gán giá trị visible của nút thành false (các nút trong ma trận sẽ không hiển thị)
    }                               // thuộc tính visible là thuộc tính được kế thừa từ class JButton    

    public File getValue() {
      return value;
    }
    public void setValue(File value) {
      this.value = value;
    }
    
}
