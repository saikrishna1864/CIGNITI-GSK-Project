package com.starhub.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RoughCheck {

	public static void main(String[] args) throws IOException
	{
	
		
		
		Properties Locator = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\src\\test\\resources\\PropertyFiles\\Locator.properties");

		Locator.load(fis);
		
		System.out.println(Locator.getProperty("customer_name"));

		Properties config = new Properties();
		
		FileInputStream fisc = new FileInputStream("C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\src\\test\\resources\\PropertyFiles\\Config.properties");

		config.load(fisc);
		
		
		
		System.out.println(config.getProperty("browser"));
		
	}

}
