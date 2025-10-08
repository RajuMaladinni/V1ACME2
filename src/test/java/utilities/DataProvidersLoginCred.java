package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvidersLoginCred {
	
	//DataProvider =1
	
	@DataProvider(name="LoginCredData")
	public String [][] getdata() throws IOException{
		String path=".\\testData\\loginCred.xlsx";  	//Taking xl file from testData
		
		ExcelUtility xlUtil=new ExcelUtility(path);		//creating an object for xlUtility
		
		int totalRows=xlUtil.getRowCount("LoginData");
		int totalCols=xlUtil.getCellCount("LoginData", 1);
		
		String loginData[][] = new String [totalRows][totalCols]; //created for two dimension array which can store
		
		for(int i=1; i<=totalRows; i++) {  	//1 -> read data from xl storing in two dimensional array
			
			for(int j=0; j<totalCols; j++) {	//0 --> i is row & j is col
				loginData[i-1][j]=xlUtil.getCellData("LoginData", i, j);
			}
			
		}
		
		return loginData; //returning 2-D array
		
	}

}
