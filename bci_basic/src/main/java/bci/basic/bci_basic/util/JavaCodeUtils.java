package bci.basic.bci_basic.util;

import java.io.PrintStream;

public class JavaCodeUtils {
	public static final PrintStream out = System.out;
	
	public static void displayCurrentThread() {
		out.println("\t#[thread name : " + Thread.currentThread().getName() + ", id : " +
				Thread.currentThread().getId() + " ]");
	}
	
	public static String getPrintlnCode(Object inst) {
		return combineStrings("System.out.println(\""
							, nullCheckForToString(inst)
							, "\"); ");
	}
	
	public static String getPrintCode(Object inst) {
		return combineStrings("System.out.print(\""
				, nullCheckForToString(inst)
				, "\"); ");		
	}
	
	private static String combineStrings(String ... values ) {		
		int totalLen = 0;
		for( String value : values ) {
			totalLen += getStringLength(value,0);
		}
		
		StringBuilder sb = new StringBuilder(totalLen);
		for( String value : values ) {
			sb.append(value);
		}
		
		return sb.toString();
	}
	
	private static int getStringLength(String val, int defaultLength) {
		if( val == null )
			return defaultLength;
		return val.length();
	}
	
	private static String nullCheckForToString(Object obj) {
		if(obj == null)
			return "null";
		return obj.toString();
	}
	
	
}
