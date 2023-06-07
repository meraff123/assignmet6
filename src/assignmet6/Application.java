package assignmet6;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Application {

	// create final variables for each model type
	public static final String MODEL_3 = "model3.csv";
	public static final String MODEL_S = "modelS.csv";
	public static final String MODEL_X = "modelX.csv";
	
	public static void main(String[] args) throws IOException {

		FileService fs = new FileService();
		List<SalesData> model3SalesData = fs.loadSalesData(MODEL_3);
		List<SalesData> modelSSalesData = fs.loadSalesData(MODEL_S);
		List<SalesData> modelXSalesData = fs.loadSalesData(MODEL_X);

		generateSalesReport(model3SalesData, "Model 3");
		generateSalesReport(modelSSalesData, "Model S");
		generateSalesReport(modelXSalesData, "Model X");
	}

	private static void generateSalesReport(List<SalesData> carSalesData, String modelType) {

		System.out.println(modelType + " Yearly Sales Report");
		System.out.println("---------------------------");

		// create map containing an integer (Year) and the list of sales data
		Map<Integer, List<SalesData>> salesGroupedByYear = carSalesData.stream()
				// collect by the year of the carSalesDataList
				.collect(Collectors.groupingBy(d -> d.getDate().getYear()));
		

		// find total yearly sales with the map's entry set
		String totalYearlySales = salesGroupedByYear.entrySet().stream()
				// get the key (year) and the value (list<SalesData>)
				.map(x -> x.getKey() + " -> " + x.getValue().stream() // stream through value (list<SalesData>)
				.collect(Collectors.summingInt(SalesData::getSales))) // collect by and sum all sales integers
				.collect(Collectors.joining("\n")); // collect by a new line

		System.out.println(totalYearlySales + "\n");

		// create optional sales data object for the max and min sales
		// stream through the list of sales data to find the best month for sales
		Optional<SalesData> maxSalesData = carSalesData.stream()
				.max((SalesData o1, SalesData o2) -> o1.getSales().compareTo(o2.getSales()));
		
		// stream through the list of sales data to find the worst month for sales
		Optional<SalesData> minSalesData = carSalesData.stream()
				.min((SalesData o1, SalesData o2) -> o1.getSales().compareTo(o2.getSales()));

		System.out.println("The best month for " + modelType + " was: \n"
				+ maxSalesData.orElse(new SalesData("Jan-00", "00")).getDate());
		System.out.println("The worst month for " + modelType + " was: \n"
				+ minSalesData.orElse(new SalesData("Jan-00", "00")).getDate() + "\n");

	}
	
 }