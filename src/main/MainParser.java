package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import jsons.BillRecord;
import jsons.BillRecordsResult;
import jsons.PaymentRecord;
import jsons.PaymentRecordsResult;
import jsons.Result;
import jsons.SumResult;

public class MainParser {
	private static Document accountInfoDocument = null;
	private static Document paymentHistoryDocument = null;
	private static Result result = new Result();
	
	public static void parseAccountFile() throws IOException {
		File accountInfo = new File(Constants.AccountInfoFile);
		accountInfoDocument = Jsoup.parse(accountInfo, "UTF-8", "");
	}
	
	//Return the account number and name.
	public static void runTaskOne() {
		Element body = accountInfoDocument.body();
		result.setAccountNumber(body.getElementById("accountNumber").text());
		result.setAccountName(body.getElementById("AccountName").text());
	}
	
	//Check if the account status is active, and if so return the account balance.
	public static void runTaskTwo() {
		result.setActive(false);
		result.setBalance("-1.0"); //we set -1.0 as default
		accountInfoDocument.getAllElements().forEach(element -> {
			if(element.text().equals("ACTIVE (Payments are allowed)")) {
				result.setActive(true);
				String balance = accountInfoDocument.getElementById("AccountBalance").text();
				result.setBalance(balance);
			}
		});	
	}
	
	//Load the “Payment History” page.
	public static void runTaskThree() throws IOException {
		File paymentHistory = new File(Constants.PaymentHistoryFile);
		paymentHistoryDocument = Jsoup.parse(paymentHistory, "UTF-8", "");
	}
	
	//From the “Bill records” table return the bill amount and due date for November 2015.
	public static void runTaskFour(String year, String month) {
		Element body = paymentHistoryDocument.body();
		Element table = body.getElementById("billsTable");
		Elements rows = table.select("tr");
		
		BillRecordsResult billRecordsResult = new BillRecordsResult();
		ArrayList<BillRecord> results = new ArrayList<BillRecord>();
		result.setBillRecordsResult(billRecordsResult);
		billRecordsResult.setMonth(month);
		billRecordsResult.setYear(year);
		billRecordsResult.setResults(results);
		
		for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Elements cols = row.select("td");
		    String billDate = cols.first().text();
		    if(billDate.matches(Constants.dateRegularExpression)) { //if bill date is of date syntax
		    	String[] splitedDate = billDate.split("/");
		    	if(splitedDate[0].equals(month) && splitedDate[2].equals(year)) {
			    	String dueDate = cols.get(2).text(), billAmount = cols.get(1).text();
			    	results.add(new BillRecord(billAmount,dueDate));
		    	}
		    }
		}
	}
	
	//From the “payment records” table return the payment amount and date from the last week (assumption: today is the 1.1.2016)
	public static void runTaskFive(LocalDate today,int weeksBefore) {
		Element body = paymentHistoryDocument.body();
		Element table = body.getElementById("PaymentsTable");
		Elements rows = table.select("tr");

		PaymentRecordsResult paymentRecordsResult = new PaymentRecordsResult();
		result.setPaymentRecordsResult(paymentRecordsResult);
		ArrayList<PaymentRecord> paymentRecord = new ArrayList<PaymentRecord>();
		paymentRecordsResult.setPaymentRecord(paymentRecord);
		paymentRecordsResult.setCurrentTime(today.toString());
		paymentRecordsResult.setWeeksBefore(weeksBefore);
		    
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
		    		paymentRecord.add(new PaymentRecord(paymentDate,paymentAmount));
		    	}
		    	
		    }
		}
	}
	
	//Calculate the sum of all payments made (e.g. applied payments) in November 2015.
	public static void runTaskSix(String year, String month) {
		Element body = paymentHistoryDocument.body();
		Element table = body.getElementById("PaymentsTable");
		Elements rows = table.select("tr");
		double sumOfAllPaymentAmounts = 0;
		for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Elements cols = row.select("td");
		    
		    String billDate = cols.first().text();
		    if(billDate.matches(Constants.dateRegularExpression)) { //if bill date is of date syntax
		    	String[] splitedDate = billDate.split("/");
		    	String paymentStatus = cols.get(3).text();
		    	if(splitedDate[0].equals(month) && splitedDate[2].equals(year) && paymentStatus.equals("Applied")) {
		    		String paymentAmount = cols.get(1).text().substring(1);
		    		sumOfAllPaymentAmounts += Double.valueOf(paymentAmount);
		    	}
		    }
		}
		
		SumResult sumResult = new SumResult();
		result.setSumResult(sumResult);
		sumResult.setYear(year);
		sumResult.setMonth(month);
		sumResult.setResult(sumOfAllPaymentAmounts);
	}
	
	public static void createAndSaveResultsIntoFile(String resultsfilename, String content) throws IOException {
	    File file = new File(Constants.resultsFileName);
		
	    if (file.createNewFile()){
			System.out.println("You can view the results in results.json file as well :) Enjoy!");
	    	System.out.println("For your convinient, you can format the results with http://jsonviewer.stack.hu/");
	    } else {
	        System.out.println("Results file already exists!");
	    }
	    
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(Constants.resultsFileName);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) bw.close();
				if (fw != null) fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		parseAccountFile();
		runTaskOne();
		runTaskTwo();
		runTaskThree();
		runTaskFour("2015","11");
		runTaskFive(LocalDate.of(2016,Month.JANUARY,1),1); //assumptions given in the assignment, today is 1/1/2016, and we want all data 1 week before that date
		runTaskSix("2015","11");

		System.out.println("Results object was created!");
		System.out.println(result.toString());
		Gson gson = new Gson();
		createAndSaveResultsIntoFile(Constants.resultsFileName,gson.toJson(result));
	}
}
