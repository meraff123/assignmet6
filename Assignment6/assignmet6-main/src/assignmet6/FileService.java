package assignmet6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
	
	public List<SalesData> loadSalesData(String fileName) throws IOException {
		
		List<SalesData> salesDataList = new ArrayList<>();
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		String line = reader.readLine();
		try {
			while ((line = reader.readLine()) != null) { // while line (line being read from file) is not null then...
				String[] values = line.split(",");
				salesDataList.add(new SalesData(values[0], values[1]));
			}
		} finally {
			if (reader != null) {				
				reader.close();
			}
		}
		return salesDataList;
	}
}