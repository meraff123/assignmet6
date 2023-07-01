package com.coderscampus.tesla;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public List<SalesData> loadData (String fileName) throws IOException {
		List<SalesData> salesData = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			// Read the header from the file and ignore it
			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				salesData.add(new SalesData(values[0], values[1]));
			}
			return salesData;
		} finally {
			if (reader != null)
				reader.close();
		}
	}
}
