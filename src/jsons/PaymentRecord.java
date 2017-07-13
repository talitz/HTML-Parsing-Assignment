package jsons;

public class PaymentRecord {
	private String date;
	private String amount;
	
	public PaymentRecord() {}
	
	public PaymentRecord(String date, String amount) {
		super();
		this.date = date;
		this.amount = amount;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentRecord [date=" + date + ", amount=" + amount + "]";
	}
}
