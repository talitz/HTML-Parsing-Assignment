package main;
import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainParser {
	
	private static Document accountInfoDocument = null;
	private static Document paymentHistoryDocument = null;
	
	public static void parseFiles() throws IOException {
		File accountInfo = new File("AccountInfo.html");
		File paymentHistory = new File("PaymentHistory.html");
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
	public static void main(String[] args) throws IOException {
		parseFiles();
		taskOne();
		taskTwo();
	}

}
