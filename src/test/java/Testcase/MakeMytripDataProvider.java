package Testcase;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ExcelFileRead;

public class MakeMytripDataProvider {
	
	@DataProvider
	public String[][] GetValidSearchData() throws IOException
	{
		return ExcelFileRead.ReadDataFromExcel("MakeMyTripData.xlsx", "ValidSearch");
	}

	@DataProvider
	public String[][] SameCityValidationData() throws IOException
	{
		return ExcelFileRead.ReadDataFromExcel("MakeMyTripData.xlsx", "SameCity");
	}

}
