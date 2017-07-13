package jsons;

public class Result {
		private String accountNumber;
		private String accountName;
		private boolean isActive;
		private String balance;
		private BillRecordsResult billRecordsResult;
		private PaymentRecordsResult paymentRecordsResult;
		private SumResult sumResult;
		
		public Result() {}
		
		public Result(String accountNumber, String accountName, boolean isActive, String balance,
				BillRecordsResult billRecordsResult, PaymentRecordsResult paymentRecordsResult, SumResult sumResult) {
			super();
			this.accountNumber = accountNumber;
			this.accountName = accountName;
			this.isActive = isActive;
			this.balance = balance;
			this.billRecordsResult = billRecordsResult;
			this.paymentRecordsResult = paymentRecordsResult;
			this.sumResult = sumResult;
		}
		
		public String getAccountNumber() {
			return accountNumber;
		}
		
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		
		public String getAccountName() {
			return accountName;
		}
		
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		
		public boolean isActive() {
			return isActive;
		}
		
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
		
		public String getBalance() {
			return balance;
		}
		
		public void setBalance(String balance) {
			this.balance = balance;
		}
		
		public BillRecordsResult getBillRecordsResult() {
			return billRecordsResult;
		}
		
		public void setBillRecordsResult(BillRecordsResult billRecordsResult) {
			this.billRecordsResult = billRecordsResult;
		}
		
		public PaymentRecordsResult getPaymentRecordsResult() {
			return paymentRecordsResult;
		}
		
		public void setPaymentRecordsResult(PaymentRecordsResult paymentRecordsResult) {
			this.paymentRecordsResult = paymentRecordsResult;
		}
		
		public SumResult getSumResult() {
			return sumResult;
		}
		
		public void setSumResult(SumResult sumResult) {
			this.sumResult = sumResult;
		}
		
		@Override
		public String toString() {
			return "Result [accountNumber=" + accountNumber + ", accountName=" + accountName + ", isActive=" + isActive
					+ ", balance=" + balance + ", billRecordsResult=" + billRecordsResult + ", paymentRecordsResult="
					+ paymentRecordsResult + ", sumResult=" + sumResult + "]";
		}
}


