package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtility {

	static String projectPath = System.getProperty("user.dir");
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public DataUtility(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getColCount() {
		int colCount = 0;

		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Row count is"+ colCount);
		return colCount;
	}

	public int getRowCount() {
		int rowCount = 0;

		rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Row count is"+ rowCount);
		return rowCount;
	}

	public String getCellDataString(int rowNum, int colNum) {
		String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		return cellData;
	}

	public String getCellDataNumber(int rowNum, int colNum) {
		Cell cellData = sheet.getRow(rowNum).getCell(colNum);
		DataFormatter formatter = new DataFormatter();
        String formattedValue = formatter.formatCellValue(cellData);
        return formattedValue;
	}
    
	public static String readConfig(String propertyName){
        Properties prop = new Properties();
        InputStream input = null;
        String propertyValue = null;

        try {
            input = new FileInputStream(projectPath+"/src/main/java/config/config.properties");
            prop.load(input);
            propertyValue = prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }return propertyValue;
	}
}
