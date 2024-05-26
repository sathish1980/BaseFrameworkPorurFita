package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileRead {
	
	static String filepath =System.getProperty("user.dir")+"\\Input\\";

	List<Object> ls = new ArrayList<>();
	static String[][] excelData;
	public static String[][] ReadDataFromExcel(String filename,String Sheetname) throws IOException
	{
		File F = new File(filepath+filename);
		FileInputStream Fs = new FileInputStream(F); // it will read all type os files
		XSSFWorkbook wbk = new XSSFWorkbook(Fs);
		Sheet sheet =wbk.getSheet(Sheetname);
		int totalRows =sheet.getPhysicalNumberOfRows();
		Row row1 =sheet.getRow(1);
		int firstrowtotalColumns =row1.getLastCellNum();
		excelData = new String[totalRows][firstrowtotalColumns];
		for(int i=0;i<totalRows;i++)
		{
			Row row =sheet.getRow(i);
			int totalColumns =row.getLastCellNum();
			for(int j =0 ;j <totalColumns;j++)
			{
				Cell eachcell = row.getCell(j);
				excelData[i][j]=(String) ReadDataAsSuch(eachcell);
				//System.out.print(ReadDataAsSuch(eachcell));
				//System.out.print(" ");
			}
			//System.out.println("");
		}
		Fs.close();
		
		return excelData;
	}
	
	
	
	public static Object ReadDataAsSuch(Cell cellValue)
	{
		if(cellValue.getCellType()==CellType.STRING)
		{
			return cellValue.getStringCellValue();
		}
		else if(cellValue.getCellType()==CellType.NUMERIC)
		{
			DataFormatter data =  new DataFormatter();
			return data.formatCellValue(cellValue);
			//return cellValue.getNumericCellValue();
		}
		return null;
	}

}
