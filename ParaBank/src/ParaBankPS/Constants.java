package ParaBankPS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Constants {
		
		public static final String key = "webdriver.chrome.driver";
		public static final String value = "D:\\Avinash\\Selenium\\Selenium Files\\chromedriver.exe";
		
		public static long Page_Load_TimeOut = 10;
		
		public static String TestData_Sheet_Path = "D:\\Avinash\\STQA\\ParaBank Regression Data.xlsx";
		
		static Workbook book;
		static Sheet sheet;
		
		public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(Constants.TestData_Sheet_Path);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				book = WorkbookFactory.create(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i+1).getCell(k).toString();
				}
			}
			
			return data;
		}
}