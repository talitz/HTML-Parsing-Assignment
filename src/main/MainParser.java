package main;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainParser {
	
	private static Document accountInfoDocument = null;
	private static Document paymentHistoryDocument = null;
	
	public static void parseFiles() throws IOException {
		File accountInfo = new File(Constants.AccountInfoFile);
		File paymentHistory = new File(Constants.PaymentHistoryFile);
		accountInfoDocument = Jsoup.parse(accountInfo, "UTF-8", "");
		paymentHistoryDocument = Jsoup.parse(paymentHistory, "UTF-8", "");
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
	
	public static void taskThree(String year, String month) {
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
	
	public static void taskFour(LocalDate today) {
		Element body = paymentHistoryDocument.body();
		Element table = body.getElementById("PaymentsTable");
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
	
	public static void main(String[] args) throws IOException {
		parseFiles();
		taskOne();
		taskTwo();
		taskThree("2015","11");
		taskFour(LocalDate.of(2016,Month.JANUARY,1)); //assumption given in the assignment
	}

}
