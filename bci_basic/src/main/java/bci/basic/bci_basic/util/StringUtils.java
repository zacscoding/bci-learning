package bci.basic.bci_basic.util;

public class StringUtils {
	
	public static String getPrintln(String value) {
		return "System.out.println(\"" + value + "\");"; 
	}
	
	public static String getPrintlnByObject(Object object) {
		return "System.out.println(\"" + object.toString() + "\");"; 
	}

}
