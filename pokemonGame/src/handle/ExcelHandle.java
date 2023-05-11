/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handle;

import entity.Player;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Acer
 */
public class ExcelHandle {
    private static final int COLUMN_INDEX_ID = 0;
    private static final int COLUMN_INDEX_START_TIME = 1;
    private static final int COLUMN_INDEX_END_TIME = 2;
    private static final int COLUMN_INDEX_START_TIME_TO_STRING = 3;
    private static final int COLUMN_INDEX_TIME_TAKEN_IN_SECOND = 4;
    private static final int COLUMN_INDEX_TIME_TAKEN = 5;
    private static final int COLUMN_INDEX_SCORE = 6;
    private static final int COLUMN_INDEX_LEVEL_REACHED = 7;
    private static final int COLUMN_INDEX_HAS_COMPLETED_ALL_LEVELS = 8;
    
    public static ArrayList<Player> readExcel(String excelFilePath) throws IOException {
        ArrayList<Player> list = new ArrayList<>();
        
        File file = new File(excelFilePath);
        InputStream inputStream = Files.newInputStream(file.toPath());
        
        
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("data");
        
        for(Row nextRow  : sheet){
            if(nextRow.getRowNum() == 0) continue;
            Player player = new Player();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();   
                setUser(player, cell);
            }
            list.add(player);
        }
        return list;
    }


    private static void setUser(Player player, Cell cell) {
        int columnIndex = cell.getColumnIndex();
        Object cellValue = getCellValue(cell);
        switch(columnIndex) {
            case COLUMN_INDEX_ID -> player.setId(((Double) cellValue).intValue());
            case COLUMN_INDEX_START_TIME -> player.setStartTime((LocalDateTime) getLocalDateTimeFromDouble((Double) cellValue));
            case COLUMN_INDEX_END_TIME -> player.setEndTime((LocalDateTime) getLocalDateTimeFromDouble((Double) cellValue));
            case COLUMN_INDEX_START_TIME_TO_STRING -> player.setStartTimeToString((String) cellValue);
            case COLUMN_INDEX_TIME_TAKEN_IN_SECOND -> player.setTimeTakenInSeconds(((Double) cellValue).longValue());
            case COLUMN_INDEX_TIME_TAKEN -> player.setTimeTaken((String)cellValue);
            case COLUMN_INDEX_SCORE -> player.setScore(((Double) cellValue).intValue());
            case COLUMN_INDEX_LEVEL_REACHED -> player.setLevelReached(((Double) cellValue).intValue());
            case COLUMN_INDEX_HAS_COMPLETED_ALL_LEVELS -> player.setHasCompletedAllLevels((boolean) cellValue);
        }
        
    }
    
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        
        Object cellValue = null;
        switch(cellType) {
            case NUMERIC -> {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = (LocalDateTime) cell.getLocalDateTimeCellValue();
                    } else {
                        cellValue = cell.getNumericCellValue();
                    }}
            case STRING -> cellValue = cell.getStringCellValue();
            case BOOLEAN -> cellValue = cell.getBooleanCellValue();
            default -> cellValue = null;
        }
        
        return cellValue;
    }
    
    private static LocalDateTime getLocalDateTimeFromDouble(Double numericValue) {
        Instant instant = Instant.ofEpochMilli(numericValue.longValue());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
    
    
    
//    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
//        Workbook workbook = null;
//        if(excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook(inputStream);
//        }else if(excelFilePath.endsWith("xls")){
//            workbook = new HSSFWorkbook(inputStream);
//        }else {
//            System.out.println("File không đúng định dạng");
//        }
//        return workbook;
//    }
    
//    private static Workbook getWorkbook( String excelFilePath) throws IOException {
//        Workbook workbook = null;
//        if(excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook();
//        }else if(excelFilePath.endsWith("xls")){
//            workbook = new HSSFWorkbook();
//        }else {
//            System.out.println("File không đúng định dạng");
//        }
//        return workbook;
//    }


    public static void writeExcel (String excelFilePath, ArrayList<Player> players) throws IOException{
        File file = new File(excelFilePath);
        InputStream inputStream = Files.newInputStream(file.toPath());
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("data");
        
        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex);
        
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue("ID");
        
        cell = row.createCell(COLUMN_INDEX_START_TIME);
        cell.setCellValue("Thời gian bắt đầu");
        
        cell = row.createCell(COLUMN_INDEX_END_TIME);
        cell.setCellValue("Thời gian kết thúc");
        
        cell = row.createCell(COLUMN_INDEX_START_TIME_TO_STRING);
        cell.setCellValue("Thời gian bắt đầu (string)");
        
        cell = row.createCell(COLUMN_INDEX_TIME_TAKEN_IN_SECOND);
        cell.setCellValue("Thời gian chơi(s)");
        
        cell = row.createCell(COLUMN_INDEX_TIME_TAKEN);
        cell.setCellValue("Thời gian chơi");
        
        cell = row.createCell(COLUMN_INDEX_SCORE);
        cell.setCellValue("Điểm số");
        
        cell = row.createCell(COLUMN_INDEX_LEVEL_REACHED);
        cell.setCellValue("Màn chơi cao nhất");
        
        cell = row.createCell(COLUMN_INDEX_HAS_COMPLETED_ALL_LEVELS);
        cell.setCellValue("Chiến thắng trò chơi");
        
        rowIndex++;
        for(Player player : players) {
            row = sheet.createRow(rowIndex);
            writeBook(player, row, sheet);
            rowIndex++;
        }

        OutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        
        
    }

    public static void writeBook(Player player, Row row, Sheet sheet) {
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(row.getRowNum());
        
        cell = row.createCell(COLUMN_INDEX_START_TIME);
        cell.setCellValue(player.getStartTime());
        
        cell = row.createCell(COLUMN_INDEX_END_TIME);
        cell.setCellValue(player.getEndTime());
        
        cell = row.createCell(COLUMN_INDEX_START_TIME_TO_STRING);
        cell.setCellValue(player.getStartTimeToString());
        
        cell = row.createCell(COLUMN_INDEX_TIME_TAKEN_IN_SECOND);
        cell.setCellValue(player.getTimeTakenInSeconds()); 
        
        cell = row.createCell(COLUMN_INDEX_TIME_TAKEN);
        cell.setCellValue(player.getTimeTaken());
        
        cell = row.createCell(COLUMN_INDEX_SCORE);
        cell.setCellValue(player.getScore());
        
        cell = row.createCell(COLUMN_INDEX_LEVEL_REACHED);
        cell.setCellValue(player.getLevelReached());
        
        cell = row.createCell(COLUMN_INDEX_HAS_COMPLETED_ALL_LEVELS);
        cell.setCellValue(player.isHasCompletedAllLevels());
        
    }
}
