import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainParser {

	public static void main(String[] args) {
		File input = new File("AccountInfo.html");
		try {
			Document doc = Jsoup.parse(input, "UTF-8", "");
			Element body = doc.body();
			System.out.println("Account Nubmber: "+body.getElementById("accountNumber").text());
			System.out.println("Account Name: "+body.getElementById("AccountName").text());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
