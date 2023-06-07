package assignmet6;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalesData {
	
	private YearMonth date;
	private Integer sales;
	
	public SalesData(String date, String sales) {
		this.date = YearMonth.parse(date, DateTimeFormatter.ofPattern("MMM-yy", Locale.US));
		this.sales = Integer.parseInt(sales);
	}

	public YearMonth getDate() {
		return date;
	}
	public void setDate(YearMonth date) {
		this.date = date;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "SalesData [date=" + date + ", sales=" + sales + "]";
	}
}