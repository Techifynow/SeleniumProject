package utils;

import java.io.*;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.*;

public class DataProvider {
	FileInputStream inputFile;
	XSSFWorkbook workbook;
	XSSFSheet mySheet;
	
	private HashMap<String, String> data = new HashMap<String, String>();
	
	// to load test data
	public void loadTestData(String testCaseName, String iteration) throws FileNotFoundException, IOException{
		workbook = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+"/testData/testData.xlsx"));
		mySheet = workbook.getSheet("testData");
		int tcRowNum = getRowNumber(testCaseName, mySheet);
		int itrRowNum = getIterationRowNum(mySheet, testCaseName, tcRowNum, iteration);
		
		String key = null;
		String value = null;
		
		for(int i=2;i<1000;i++){
			try{
				key = mySheet.getRow(tcRowNum).getCell(i).getStringCellValue();
				value = mySheet.getRow(itrRowNum).getCell(i).getStringCellValue();
			}catch(Exception e){
				break;
			}
			data.put(key.toLowerCase(), value);
		}
	}
	
	//to get the row number
	public int getRowNumber(String testCaseName, XSSFSheet sheet){
		int row = 0;
		int lastRowNum = sheet.getLastRowNum();
		String tcNameExcel = null;
		try{
			for(int i=0;i<lastRowNum;i++){
				try{
					XSSFCell cell = sheet.getRow(i).getCell(0);
					switch(cell.getCellTypeEnum()){
					case STRING:
						tcNameExcel = cell.getStringCellValue();
						break;
					case NUMERIC:
						cell.getNumericCellValue();
						break;
					case BOOLEAN:
						cell.getBooleanCellValue();
						break;
					case BLANK:
						break;
					}
					
					if(!tcNameExcel.isEmpty() && tcNameExcel.equalsIgnoreCase(testCaseName)){
						row = i;
					}
				}catch(Exception e){
					
				}
			}
			
		}catch(Exception e){
			tcNameExcel = "";
		}
		return row;
	}
	
	//to get the data
	public String getData(String fieldName){
		String value = "";
		if(data.containsKey(fieldName.toLowerCase())){
			value = data.get(fieldName.toLowerCase());
		}
		return value;
	}
	
	//To get iteration row number
	public int getIterationRowNum(XSSFSheet sheet, String testCaseName, int tcRowNum, String iteration){
		int itrRow = 0;
		String itrName = null;
		
		for(int i=tcRowNum; i<=sheet.getLastRowNum();i++){
			try{
				itrName = sheet.getRow(i).getCell(1).getStringCellValue();
			}catch(Exception e){
				itrName = "";
			}
			
			if(itrName.equalsIgnoreCase(iteration)){
				itrRow = i;
				break;
			}
		}
		return itrRow;
	}
	
}
