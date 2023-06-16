package DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataprovide {
	DataFormatter format = new DataFormatter();
	@Test(dataProvider="DriveTest")
	public void TestCaseData(String Greeting,String Message,String number)
	{
		System.out.println(Greeting+Message+number);
		
	}
	
	
	
	@DataProvider(name="DriveTest")
	public Object[][] getdata() throws IOException
	{
		//Object[][] data= {{"HelloONE","MsgONE","1"},{"HelloTwo","MsgTwo","2"},{"HelloThree","MsgThree","3"}};
		
		FileInputStream fis = new FileInputStream("https://d.docs.live.net/3fcbd4880d79ba52/Desktop/DataProvider.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
 		XSSFSheet sheet =wb.getSheetAt(0);
		int RowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int ColumnCount = row.getLastCellNum();
		Object[][] data= new Object[RowCount-1][ColumnCount];
		
		for(int i=0;i<RowCount-1;i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<ColumnCount;j++)
			{
				
				XSSFCell cell=row.getCell(j);
				data[i][j]=format.formatCellValue(cell);
			}
		}
		return data;
		
		
		//return data;
	}

}
