package RestAssure2;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtil  {
	public static Object[][] getTestdata() throws Exception{
	FileInputStream f=new FileInputStream("C:\\Users\\IBMADMIN\\Desktop\\abc.xlsx");
	XSSFWorkbook xwb=new XSSFWorkbook(f);
	XSSFSheet x=xwb.getSheetAt(0);

	int rowCount=x.getLastRowNum();
	System.out.println(rowCount);
	Row row = x.getRow(1);
	int col=row.getLastCellNum();
	System.out.println(col);
	Object[][] data=new Object[rowCount+1][col];
	for(int i=0;i<=rowCount;i++){
		for(int j=0;j<col;j++){

			try {
				data[i][j] = x.getRow(i).getCell(j).getStringCellValue();
				System.out.println(data[i][j]);

			}

			catch (NullPointerException ex)

			{
				System.out.println(ex.getMessage());
			}
		}
	}
	xwb.close();
	return data;
	}

}