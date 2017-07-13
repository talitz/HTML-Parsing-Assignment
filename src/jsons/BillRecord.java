package jsons;

public class BillRecord {
	private String amount;
	private String due;
	
	public BillRecord() {}
	
	public BillRecord(String amount, String due) {
		super();
		this.amount = amount;
		this.due = due;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getDue() {
		return due;
	}
	
	public void setDue(String due) {
		this.due = due;
	}

	@Override
	public String toString() {
		return "BillRecord [amount=" + amount + ", due=" + due + "]";
	}

}
