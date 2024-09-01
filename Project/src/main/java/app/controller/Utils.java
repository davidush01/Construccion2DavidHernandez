package app.controller;

import java.sql.Date;
import java.util.Scanner;

public abstract class Utils {
	private static Scanner reader = new Scanner(System.in);
	 
	public static Scanner getReader() {
		return reader;
	}

	protected abstract Date getDate();
	
	

}
