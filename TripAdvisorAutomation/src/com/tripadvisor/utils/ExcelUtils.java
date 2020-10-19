package com.tripadvisor.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static HashMap<String, ArrayList<String>> readExcelData(
			String testName) throws IOException {
		HashMap<String, ArrayList<String>> data = new HashMap<>();

		FileInputStream readFile = new FileInputStream(
				System.getProperty("user.dir")
						+ "/resources/testdata/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(readFile);
		XSSFSheet sheet = workbook.getSheet(testName);
		Iterator<Row> rowIterator = sheet.iterator();

		ArrayList<String> rowData = new ArrayList<>();
		rowIterator = sheet.iterator();
		int rowNum = 1;
		Row row;
		if (rowIterator.hasNext())
			row = rowIterator.next();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			Iterator<Cell> cellIterator = row.iterator();
			if(row.getCell(0)==null){
				break;
			}
			rowData = new ArrayList<>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				rowData.add(cell.getStringCellValue());
			}
			data.put("" + (rowNum), rowData);
			rowNum++;
		}
		
		workbook.close();
		readFile.close();

		return data;
	}

	public static void writeExcel(String[] data, String sheetName, String heading){
		//create workbook
		//create sheet name is sheetName
		//write "heading" to first row first cell
		//write array values to the first column, starting from the second cell
		//autoresize
		//write to an excel file using fileoutputstream etc, name the excel file "Output1.xlsx"
		
	}
	
	public static void writeExcel(String[][] data, String sheetName, String headings[]){
		//create workbook
		//create sheet name is sheetName
		//write all "headings" to first row
		//write array values to the respective columns, starting from the second row
		//autoresize
		//write to an excel file using fileoutputstream etc, name the excel file "Output2.xlsx"
		
	}
	
}
