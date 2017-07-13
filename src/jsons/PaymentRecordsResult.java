package jsons;

import java.util.ArrayList;

public class PaymentRecordsResult {
	private String currentTime;
	private int weeksBefore;
	private ArrayList<PaymentRecord> paymentRecord;
	
	public PaymentRecordsResult() {}
	
	public PaymentRecordsResult(String currentTime, int weeksBefore, ArrayList<PaymentRecord> paymentRecord) {
		super();
		this.currentTime = currentTime;
		this.weeksBefore = weeksBefore;
		this.paymentRecord = paymentRecord;
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
	public int getWeeksBefore() {
		return weeksBefore;
	}
	
	public void setWeeksBefore(int weeksBefore) {
		this.weeksBefore = weeksBefore;
	}
	
	public ArrayList<PaymentRecord> getPaymentRecord() {
		return paymentRecord;
	}
	
	public void setPaymentRecord(ArrayList<PaymentRecord> paymentRecord) {
		this.paymentRecord = paymentRecord;
	}
	
	@Override
	public String toString() {
		return "PaymentRecordsResult [currentTime=" + currentTime + ", weeksBefore=" + weeksBefore + ", paymentRecord="
				+ paymentRecord + "]";
	}
}
