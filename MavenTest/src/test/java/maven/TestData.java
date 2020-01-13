/* Extract Test Data from Excel */
package maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	
	static String url;
	static String userId;
	static String passWord;
	static String stDate;
	static String enDate;
	static String abName;
	static String abReason;

	public void data () throws IOException{		
		
		File file = new File ("C:\\Users\\VigneshwarUthaman\\git\\maven\\MavenTest\\TestData.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		XSSFWorkbook Book = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = Book.getSheet("Sheet1");

		XSSFCell cell = sheet.getRow(1).getCell(2);

	    url = cell.getStringCellValue();

		XSSFCell cell1 = sheet.getRow(2).getCell(2);

		userId = cell1.getStringCellValue();

		XSSFCell cell2 = sheet.getRow(3).getCell(2);

		passWord = cell2.getStringCellValue();

		XSSFCell cell3 = sheet.getRow(4).getCell(2);

		stDate = cell3.getStringCellValue();

		XSSFCell cell4 = sheet.getRow(5).getCell(2);

		enDate = cell4.getStringCellValue();

		XSSFCell cell5 = sheet.getRow(6).getCell(2);

		abName = cell5.getStringCellValue();

		try {
			
			XSSFCell cell6 = sheet.getRow(7).getCell(2);

			abReason = cell6.getStringCellValue();
			
		}
		
		catch (Exception e){
			abReason = "";
		}
		
		Book.close();
		
//		System.out.println("Test Data Imported");

	}
}