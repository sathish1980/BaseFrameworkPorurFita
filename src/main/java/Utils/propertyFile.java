package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class propertyFile {
	
static String filepath =System.getProperty("user.dir")+"\\Config\\envProperties.properties";
	
	public static Properties ReadDataFromProperty() throws IOException
	{
		File F = new File(filepath);
		FileInputStream Fs = new FileInputStream(F); // it will read all type os files
		Properties P = new Properties();
		P.load(Fs);
		return P;
	}


}
