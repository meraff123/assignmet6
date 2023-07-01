package com.coderscampus.tesla;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeslaReportApplication {
	public static void main(String[] args) throws IOException {
		CSVReader reader = new CSVReader();
		List<SalesData> model3SalesData = reader.loadData("model3.csv");
		List<SalesData> modelSSalesData = reader.loadData("modelS.csv");
		List<SalesData> modelXSalesData = reader.loadData("modelX.csv");
		
		showReport(model3SalesData, "Model 3");
		showReport(modelSSalesData, "Model S");
		showReport(modelXSalesData, "Model X");
		
	}

	private static void showReport(List<SalesData> carSalesData, String modelType) {
		System.out.println(modelType + " Yearly Sales Report");
		System.out.println("---------------------------");
		
		Map<Integer, List<SalesData>> groupedByYearData = carSalesData.stream()
				.collect(Collectors.groupingBy(d -> d.getDate().getYear()));
		
		String totalYearlySales = groupedByYearData.entrySet()
						.stream()
						.map(d -> d.getKey() + " -> " + d.getValue().stream()
								.collect(Collectors.summingInt(SalesData::getSales)))
						.collect(Collectors.joining("\n"));
		
		System.out.println(totalYearlySales);
		System.out.println("");
		
		Optional<SalesData> maxSalesData = carSalesData.stream()
				.max((SalesData o1, SalesData o2) -> o1.getSales().compareTo(o2.getSales()));
		Optional<SalesData> minSalesData = carSalesData.stream()
				.min((SalesData o1, SalesData o2) -> o1.getSales().compareTo(o2.getSales()));
		
		System.out.println("The best month for " + modelType + " was: " + maxSalesData.orElse(new SalesData("Jan-70", "0")).getDate());
		System.out.println("The worst month for " + modelType + " was: " + minSalesData.orElse(new SalesData("Jan-70", "0")).getDate());
		System.out.println("");
	}
}
