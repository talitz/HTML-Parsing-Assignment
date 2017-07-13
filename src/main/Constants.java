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
	  
	  public static final String AccountInfoFile = "AccountInfo.html";  
	  public static final String PaymentHistoryFile =  "PaymentHistory.html";
	  
	  //this regular expression accepts dd/mm/yyyy,dd-mm-yyyy or dd.mm.yyyy
	  public static final String dateRegularExpression = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
	
}