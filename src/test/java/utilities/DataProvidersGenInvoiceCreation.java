package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvidersGenInvoiceCreation {
	
	@DataProvider(name="GenInvCreationData")
	public String [][] getdata() throws IOException{
		String path=".\\testData\\GenInv.xlsx";  	//Taking xl file from testData
		
		ExcelUtility xlUtil=new ExcelUtility(path);		//creating an object for xlUtility
		
		int totalRows=xlUtil.getRowCount("Sheet1");
		int totalCols=xlUtil.getCellCount("Sheet1", 1);
		
		String genInvCreationData[][] = new String [totalRows][totalCols]; //created for two dimension array which can store
		
		for(int i=1; i<=totalRows; i++) {  	//1 -> read data from xl storing in two dimensional array
			
			for(int j=0; j<totalCols; j++) {	//0 --> i is row & j is col
				genInvCreationData[i-1][j]=xlUtil.getCellData("Sheet1", i, j);
			}
			
		}
		
		return genInvCreationData; //returning 2-D array
		
	}

}
