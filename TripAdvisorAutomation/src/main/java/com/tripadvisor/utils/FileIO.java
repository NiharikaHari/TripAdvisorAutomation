package com.tripadvisor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tripadvisor.base.BaseUI;

public class FileIO {

	private static FileInputStream read_file;
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksheet;
	private static Row row;
	private static FileOutputStream write_file;
	private static File file;
	private static Properties prop;
	

	/************** Get properties file ****************/
	public static Properties initProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir")
								+ "/src/test/resources/objectrepository/config.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	/************** Get Test Data based on Test Name ****************/
	public static HashMap<String, ArrayList<String>> readExcelData(
			String testName) throws IOException {
		HashMap<String, ArrayList<String>> data = new HashMap<>();

		read_file = new FileInputStream(System.getProperty("user.dir")
				+ prop.getProperty("testData_path"));
		workbook = new XSSFWorkbook(read_file);
		worksheet = workbook.getSheet(testName);
		Iterator<Row> rowIterator = worksheet.iterator();

		ArrayList<String> rowData = new ArrayList<>();
		rowIterator = worksheet.iterator();
		int rowNum = 1;
		if (rowIterator.hasNext())
			row = rowIterator.next();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			Iterator<Cell> cellIterator = row.iterator();
			if (row.getCell(0) == null) {
				break;
			}
			rowData = new ArrayList<>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				CellType type = cell.getCellTypeEnum();
				if(type.equals(CellType.STRING))
					rowData.add(cell.getStringCellValue());
				else if(type.equals(CellType.NUMERIC))
					rowData.add(""+(int)cell.getNumericCellValue());
			}
			data.put("" + (rowNum), rowData);
			rowNum++;
		}

		workbook.close();
		read_file.close();

		return data;
	}

	/************** Write to excel sheet ****************/
	public static void writeExcel(String[] data, String sheetName,
			String heading) {

		String[][] newData = new String[data.length][1];
		String[] headings = new String[1];

		headings[0] = heading;
		for (int i = 0; i < data.length; ++i) {
			newData[i][0] = data[i];
		}

		writeExcel(newData, sheetName, headings);
	}

	public static void writeExcel(String[][] data, String sheetName,
			String headings[]) {
		String filePath = System.getProperty("user.dir") + "/TestOutput/Output-"+BaseUI.timestamp+".xlsx";
		file = new File(filePath);

		boolean fileExists = false;

		try {
			if (file.isFile()) {
				read_file = new FileInputStream(file);
				workbook = new XSSFWorkbook(read_file);
				fileExists = true;
			} else {
				workbook = new XSSFWorkbook();
			}
			int rowStart = 0;
			if (workbook.getSheet(sheetName) == null)
				worksheet = workbook.createSheet(sheetName);
			else{
				worksheet = workbook.getSheet(sheetName);
				rowStart = worksheet.getLastRowNum()+2;
			}
			row = worksheet.createRow(rowStart);
			for (int i = 0; i < data[0].length; ++i) {
				row.createCell(i).setCellValue(headings[i]);
			}
			for (int i = 0; i < data.length; ++i) {
				row = worksheet.createRow(i +rowStart+ 1);
				for (int j = 0; j < data[0].length; ++j) {
					row.createCell(j).setCellValue(data[i][j]);
				}
			}
			for (int i = 0; i < data[0].length; ++i) {
				worksheet.autoSizeColumn(i);
			}
			write_file = new FileOutputStream(filePath);
			workbook.write(write_file);
			write_file.close();
			workbook.close();
			if (fileExists)
				read_file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
