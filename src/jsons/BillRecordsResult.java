package jsons;

import java.util.ArrayList;

public class BillRecordsResult {
	private String year;
	private String month;
	private ArrayList<BillRecord> results;
	
	public BillRecordsResult() {}
	
	public BillRecordsResult(String year, String month, ArrayList<BillRecord> results) {
		super();
		this.year = year;
		this.month = month;
		this.results = results;
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
	
	public ArrayList<BillRecord> getResults() {
		return results;
	}
	
	public void setResults(ArrayList<BillRecord> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return "BillRecordsResult [year=" + year + ", month=" + month + ", results=" + results + "]";
	}

}
