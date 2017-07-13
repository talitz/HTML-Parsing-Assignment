package jsons;

public class SumResult {
	private String year;
	private String month;
	private double result;
	
	public SumResult() {}
	
	public SumResult(String year, String month, double result) {
		super();
		this.year = year;
		this.month = month;
		this.result = result;
	}

	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public double getResult() {
		return result;
	}
	
	public void setResult(double result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "SumResult [year=" + year + ", month=" + month + ", result=" + result + "]";
	}
}
