/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import entity.Player;
import handle.ExcelHandle;
import java.awt.Color;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class PlayedHistoryFrame extends javax.swing.JFrame {

    private static ArrayList<Player> playerRecords = new ArrayList<>();

    
    public PlayedHistoryFrame() {
        initComponents();
        
        loadGameHistory();
        setTitle("Lịch sử chơi");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        btnBackToMenu.setBackground(Color.BLACK);
        btnBackToMenu.setForeground(Color.WHITE);
        
        int index = 0;
        DefaultTableModel model = (DefaultTableModel) tablePlayers.getModel();
        for(Player player : playerRecords) {
            Object[] rowData = {++index, player.getTimeTaken(), player.getScore(), player.getLevelReached(), player.isHasCompletedAllLevels()}; 
            model.addRow(rowData);
        }
        btnSortByStartTimeActionPerformed(null);
    }
    
    public static void addNewPlayer(Player player) {
        playerRecords.add(player);
    }
    
    public static void displayPlayers() {
        for (Player player : playerRecords) System.out.println(player);
    }
    
    public static void saveGame() {
        try {
            ExcelHandle.writeExcel("src/excelFiles/game_history.xlsx", PlayedHistoryFrame.playerRecords);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void loadGameHistory() {
        try {
            playerRecords = ExcelHandle.readExcel("src/excelFiles/game_history.xlsx");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablePlayers = new javax.swing.JTable();
        btnBackToMenu = new javax.swing.JButton();
        btnTopPlayer = new javax.swing.JButton();
        btnSortByStartTime = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablePlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Thời gian bắt đầu", "Khoảng thời gian chơi", "Điểm số", "Màn chơi cao nhất", "Chiến thắng trò chơi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablePlayers);

        btnBackToMenu.setText("Back");
        btnBackToMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMenuActionPerformed(evt);
            }
        });

        btnTopPlayer.setText("Thành tích tốt nhất");
        btnTopPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTopPlayerActionPerformed(evt);
            }
        });

        btnSortByStartTime.setText("Gần đây nhất");
        btnSortByStartTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByStartTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnSortByStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTopPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                        .addComponent(btnBackToMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBackToMenu)
                    .addComponent(btnSortByStartTime)
                    .addComponent(btnTopPlayer))
                .addGap(7, 7, 7)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackToMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMenuActionPerformed
        // TODO add your handling code here:
        MenuFrame menuFrame = new MenuFrame();
        setVisible(false);
    }//GEN-LAST:event_btnBackToMenuActionPerformed

    private void btnTopPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTopPlayerActionPerformed
        // TODO add your handling code here:
        lblMessage.setText("Những lần chơi có thành tích tốt nhất!");
        List<Player> sortedPlayers = new ArrayList<>();
        sortedPlayers.addAll(playerRecords);
        DefaultTableModel model = (DefaultTableModel) tablePlayers.getModel();

        // Sắp xếp danh sách Player theo yêu cầu
        sortedPlayers.sort(Comparator.comparing(Player::isHasCompletedAllLevels).reversed()
                .thenComparing(Comparator.comparingInt(Player::getLevelReached).reversed())
                .thenComparing(Comparator.comparingInt(Player::getScore).reversed())
                .thenComparing(Comparator.comparingLong(Player::getTimeTakenInSeconds))
                .thenComparing(Comparator.comparing(Player::getStartTime)));

        // Cập nhật dữ liệu đã sắp xếp vào bảng
        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player p = sortedPlayers.get(i);
            model.setValueAt(i+1, i, 0);
            model.setValueAt(p.getStartTimeToString(), i, 1);
            model.setValueAt(p.getTimeTaken(), i, 2);
            model.setValueAt(p.getScore(), i, 3);
            model.setValueAt(p.getLevelReached(), i, 4);
            model.setValueAt(p.isHasCompletedAllLevels(), i, 5);
        }
    }//GEN-LAST:event_btnTopPlayerActionPerformed

    private void btnSortByStartTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByStartTimeActionPerformed
        // TODO add your handling code here:
        lblMessage.setText("Những lần chơi gần đây");
        List<Player> sortedPlayers = new ArrayList<>();
        sortedPlayers.addAll(playerRecords);
        DefaultTableModel model = (DefaultTableModel) tablePlayers.getModel();

        // Sắp xếp danh sách Player theo lần chơi mới nhất
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        sortedPlayers.sort(Comparator.comparing(Player::getStartTimeToString));
        
        // Cập nhật dữ liệu đã sắp xếp vào bảng
        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player p = sortedPlayers.get(i);
            model.setValueAt(i+1, i, 0);
            model.setValueAt(p.getStartTimeToString(), i, 1);
            model.setValueAt(p.getTimeTaken(), i, 2);
            model.setValueAt(p.getScore(), i, 3);
            model.setValueAt(p.getLevelReached(), i, 4);
            model.setValueAt(p.isHasCompletedAllLevels(), i, 5);
        }
    }//GEN-LAST:event_btnSortByStartTimeActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PlayedHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PlayedHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PlayedHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PlayedHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PlayedHistoryFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToMenu;
    private javax.swing.JButton btnSortByStartTime;
    private javax.swing.JButton btnTopPlayer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JTable tablePlayers;
    // End of variables declaration//GEN-END:variables
}
