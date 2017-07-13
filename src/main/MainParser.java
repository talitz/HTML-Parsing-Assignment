package main;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainParser {
	
	private static Document accountInfoDocument = null;
	private static Document paymentHistoryDocument = null;
	
	public static void parseAccountFile() throws IOException {
		File accountInfo = new File(Constants.AccountInfoFile);
		accountInfoDocument = Jsoup.parse(accountInfo, "UTF-8", "");
	}
	
	public static void taskOne() {
		Element body = accountInfoDocument.body();
		System.out.println("Account Nubmber: "+body.getElementById("accountNumber").text());
		System.out.println("Account Name: "+body.getElementById("AccountName").text());
	}
	
	public static void taskTwo() {
		accountInfoDocument.getAllElements().forEach(element -> {
			if(element.text().equals("ACTIVE (Payments are allowed)")) {
				System.out.println("The account is active. It's balance is: "+accountInfoDocument.getElementById("AccountBalance").text());
			}
		});	
	}
	
	public static void taskThree() throws IOException {
		File paymentHistory = new File(Constants.PaymentHistoryFile);
		paymentHistoryDocument = Jsoup.parse(paymentHistory, "UTF-8", "");
	}
	
	public static void taskFour(String year, String month) {
		Element body = paymentHistoryDocument.body();
		Element table = body.getElementById("billsTable");
		Elements rows = table.select("tr");

		for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Elements cols = row.select("td");
		    String billDate = cols.first().text();
		    if(billDate.matches(Constants.dateRegularExpression)) { //if bill date is of date syntax
		    	String[] splitedDate = billDate.split("/");
		    	if(splitedDate[0].equals(month) && splitedDate[2].equals(year)) {
			    	String dueDate = cols.get(2).text(), billAmount = cols.get(1).text();
			    	System.out.println("Bill Amount: "+ billAmount + ", and Due Date: " + dueDate + ", for Bill date with Year: "+year+ " and Month: "+month);
		    	}
		    }
		}
	}
	
	public static void taskFive(LocalDate today,int weeksBefore) {
		Element body = paymentHistoryDocument.body();
		Element table = body.getElementById("PaymentsTable");
		Elements rows = table.select("tr");

		for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Elements cols = row.select("td");

		    String paymentDate = cols.first().text();
		    if(paymentDate.matches(Constants.dateRegularExpression)) { //if bill date is of date syntax
		    	String[] paymentDateSplited = paymentDate.split("/");
		    	LocalDate currentDate = LocalDate.of(Integer.valueOf(paymentDateSplited[2]), Integer.valueOf(paymentDateSplited[0]), Integer.valueOf(paymentDateSplited[1]));
		    	LocalDate weeksPrior = today.minusWeeks(weeksBefore);

		    	//is today - weeksBefore <= currentDate <= today ?
		    	if (!currentDate.isBefore(weeksPrior) && !currentDate.isAfter(today)) {
		    		String paymentAmount = cols.get(1).text();
			    	System.out.println("Payment Date: "+ paymentDate + ", and Payment Amount: " + paymentAmount + ", for current time: "+today.toString()+ " and number of weeks before: "+weeksBefore);
		    	}
		    	
		    }
		}
	}
	
	public static void taskSix() {

	}
	
	
	public static void main(String[] args) throws IOException {
		parseAccountFile();
		taskOne();
		taskTwo();
		taskThree();
		taskFour("2015","11");
		taskFive(LocalDate.of(2016,Month.JANUARY,1),1); //assumptions given in the assignment, today is 1/1/2016, and we want all data 1 week before that date
		taskSix();
	}

}
