package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		
		fis= new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowCount;
	
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		
		fis= new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		
		wb.close();
		fis.close();
		return cellCount;
	
	}
	
	public String getCellData( String sheetName, int rowNum, int colNum) throws IOException {
		
		fis= new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		
		
		DataFormatter formatter = new DataFormatter();   ////data = cell.toString();
		String data;
		
		try {
			data = formatter.formatCellValue(cell); // rerturns the formatted cell value as String regardless of the data types
		}
		catch(Exception e) {
			data = " ";
		}
		
		wb.close();
		fis.close();
		return data;
	
	}
	
	public  void setCellData( String sheetName, int rowNum, int colNum, String data) throws IOException {
		
		File xlfile=new File(path);
		if(!xlfile.exists()) {   			//if file not exists then it will create new File
		
			wb = new XSSFWorkbook();
			fos=new FileOutputStream(path);
			wb.write(fos);
		}	
			
			
		fis= new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		
		if(wb.getSheetIndex(sheetName)==-1)		//if sheet not exists then create new Sheet
			wb.createSheet(sheetName);
		ws = wb.getSheet(sheetName);
		
		
		if(ws.getRow(rowNum)==null)			// if row not exists then it will create Row
			ws.createRow(rowNum);
		row = ws.getRow(rowNum);
		
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
			
	
	}
	
	public void fillGreenColor(String sheetName, int rowNum, int colNum, String data) throws IOException {
		
		fis= new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
			
	
	}
	
	public void fillRedColor( String sheetName, int rowNum, int colNum, String data) throws IOException {
		
		fis= new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
			
	
	}
	
	
	

}
