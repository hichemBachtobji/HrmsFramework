package com.hrms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility extends CommonMethods {
	
	public static Workbook book;
	public static Sheet sheet;
	
	private static void openExcel(String filePath) {
		try {
			FileInputStream fileIS =new FileInputStream(filePath);
			book= new XSSFWorkbook(fileIS);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private static void loadSheet(String sheetName) {
		sheet=book.getSheet(sheetName);
	}
	
	private static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	private static int colCount(int rowIndex) {
		return sheet.getRow(rowIndex).getLastCellNum();
	}
	
	private static String cellData(int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).toString();
	}
	
	// return a 2D array of data
	
	public static Object[][] excelToArray(String filePath, String sheetName){
		openExcel(filePath);
		loadSheet(sheetName);
		
		int rows=rowCount();
		int cols=colCount(0);
		
		Object data [][]=new Object[rows -1][cols];
		
		for(int i=1;i<rows;i++) {
			// iterating cols
			for(int y=0;y<cols;y++) {
				//storing values into 2D array
				data[i-1][y]=cellData(i,y);
			}
			
		}
		return data;
	}

}
