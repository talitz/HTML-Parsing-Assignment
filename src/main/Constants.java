package main;

public final class Constants  {

	  /**
	   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
	   and so on. Thus, the caller should be prevented from constructing objects of 
	   this class, by declaring this private constructor. 
	  */
	  private Constants() {
	    //this prevents even the native class from 
	    //calling this ctor as well :
	    throw new AssertionError();
	  }
	  
	  public static final String AccountInfoFile 		 = "AccountInfo.html";  
	  public static final String PaymentHistoryFile 	 = "PaymentHistory.html";
	  public static final String resultsFileName 		 = "results.json";
	  
	  //formats accepted mm/dd/yyyy or mm-dd-yyyy or mm.dd.yyyy format
	  public static final String dateRegularExpression	 = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
}