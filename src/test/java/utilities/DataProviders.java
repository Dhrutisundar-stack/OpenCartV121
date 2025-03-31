package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
//DataProvider 1 
	@DataProvider(name="LoginData")
	public String [][]getData ()throws IOException
	{
		String path=".\\testData\\OpenCart_TestData.xlsx";//taking excel file from test data
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for ExcelUtility
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];//created for 2d array which can store 
		for (int i=1;i<=totalrows;i++)//1 //read the data from excel and store in 2d array
		{
			for (int j=0;j<totalcols;j++) //0  i is row j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			}
		}
		 return logindata;//returning 2d array
		
	}
	//DataProvider 2 
	// if you'hv more xl-data and you want to use them through data provider then add under DataProvider 2  and follow the above same process
	//DataProvider 3 
	// if you'hv more xl-data and you want to use them through data provider then add under DataProvider 3  and follow the above same process
	//DataProvider 4 
	// if you'hv more xl-data and you want to use them through data provider then add under DataProvider 4 and follow the above same process
}
