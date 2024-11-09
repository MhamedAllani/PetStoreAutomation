package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    public FileInputStream file;
    public FileOutputStream fos;
    public XSSFWorkbook wb;
    public XSSFSheet ws;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtils(String path){
        this.path = path;
    }
    public int getRowCount(String xlsheet) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        ws = wb.getSheet(xlsheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        file.close();
        return rowCount;
    }

    public int getCellCount(String xlsheet, int rowNum) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        file.close();
        return cellCount;
    }

    public String getCellData(String xlsheet, int rowNum, int colNum) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);

        String data;

        try {
            // data=cell.toString();
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);// Returns the formatted value of cell as a string
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        file.close();
        return data;
    }

    public void setCellData(String xlsheet, int rowNum, int colNum, String Data) throws IOException {
        File xfile = new File(path);
        if(!xfile.exists()){    // If file not exist then create new file
            wb= new XSSFWorkbook();
            fos= new FileOutputStream(path);
            wb.write(fos);
        }
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);

        if(wb.getSheetIndex(xlsheet)==-1)  // If Sheet not exist then create new sheet
            wb.createSheet(xlsheet);

        ws = wb.getSheet(xlsheet);

        if(ws.getRow(rowNum)==null)  //If row not exist then create new row
            ws.createRow(rowNum);
        row = ws.getRow(rowNum);

        cell = row.createCell(colNum);
        cell.setCellValue(Data);
        fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        file.close();
        fos.close();
    }

    public void fillGreenColor(String xlsheet, int rowNum, int colNum) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        file.close();
        fos.close();
    }

    public void fillRedColor(String xlsheet, int rownum, int column) throws IOException {
        file = new FileInputStream(path);
        wb = new XSSFWorkbook(file);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(column);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        file.close();
        fos.close();
    }

}
