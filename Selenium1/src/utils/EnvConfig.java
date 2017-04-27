package utils;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EnvConfig {

	private static HashMap<String, String> envData = new HashMap<String, String>();
	
	public static void loadEnvironmentDetails() {
		try{
			FileInputStream inputFile;
			XSSFWorkbook workbook;
			XSSFSheet mySheet;
			workbook = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+"/envDetails.xlsx"));
			mySheet = workbook.getSheet("EnvDetails");
			String key;
			String value;
			for(int i=0;i<mySheet.getLastRowNum()+1;i++){
				try{
					key = mySheet.getRow(i).getCell(0).getStringCellValue().toLowerCase();
					value = mySheet.getRow(i).getCell(1).getStringCellValue().toLowerCase();
				}catch(Exception e){
					break;
				}
				envData.put(key.toLowerCase(), value);
			}
		}catch(Exception e){
			
		}
	}
	
	public static String getEnvDetails(String variable){
		if(envData.containsKey(variable.toLowerCase()))
			return envData.get(variable.toLowerCase());
		else 
			return "";
	}
}
