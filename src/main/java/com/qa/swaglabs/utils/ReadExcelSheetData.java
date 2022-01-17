package com.qa.swaglabs.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelSheetData {

	public static Workbook wb;
	public static Sheet sheet;
	public static String filePath = "C:\\Users\\hites\\eclipse-workspace\\hybridframework\\src\\main\\java\\com\\qa\\swaglabs\\testdata\\Customers.xlsx";

	public static Object[][] getData(String sheetName) {

		try {
			FileInputStream fis = new FileInputStream(filePath);
			 wb = WorkbookFactory.create(fis);
			sheet = wb.getSheet(sheetName);
			int rows = sheet.getLastRowNum();
			int columns = sheet.getRow(0).getLastCellNum();

			Object data[][] = new Object[rows][columns];

			for (int i = 0; i < rows; i++) {

				for (int j = 0; j < columns; j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}

			}

			return data;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
