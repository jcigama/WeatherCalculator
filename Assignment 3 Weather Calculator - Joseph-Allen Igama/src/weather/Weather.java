/*
 * Name: Joseph-Allen Igama
 * Weather.java
 * 10/19/2019
 * Provides a simple weather calculator on the Java console.
 */

package weather;

import java.util.Scanner;

public class Weather 
{
	//	static variables to allow access and assignment from multiple methods
	static double temp;
	static double tempInF;
	static double tempInC;
	static double windSpeed;
	static double windCI;	//	differs from the method "windChillIndex" but does represent it
	static boolean yesNo;

	//	starts the weather calculator
	public static void main(String[] args)
	{
		//	Welcome screen displayed at the top
		System.out.println("Welcome to the Weather Calculator");
		System.out.println("*********************************");

		//	Initializes showMenu method
		showMenu();
	}
	
	//shows a menu to the user with three options
	public static void showMenu()
	{
		//	scanner object
		Scanner scan = new Scanner(System.in);

		//	prompts user to input an option
		System.out.println();	//	space for improved readability
		System.out.println("Choose an option");
		System.out.println();
		System.out.println("1. Enter weather values");	//	initializes getUserInputs method
		System.out.println("2. Perform calculations");	//	initializes showResults method
		System.out.println("3. Exit");	//	ends program
		System.out.println();
		System.out.print("Make a choice: ");

		//	stores user input
		int choice = scan.nextInt();

		//	input will initialize corresponding method
		if (choice == 1)
		{
			getUserInputs();
		}
		else if (choice == 2)
		{
			showResults();
		}
		else if (choice == 3)
		{
			System.out.println("");
			System.out.println("Exiting...");
			return;
		}
		else	//	number not listed in if statements will run showMenu method until a valid input is entered
		{
			showMenu();
		}
	}

	//	asks the user for the temperature and wind speed
	public static void getUserInputs()
	{
		//	scanner object
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.print("Enter the temperature: ");	//	prompts user for temperature
		temp = scan.nextDouble();	//	stores temporary temperature until user confirms if it is/isn't fahrenheit

		System.out.print("Are these degrees in Fahrenheit? true/false: ");
		yesNo = scan.nextBoolean();	//	prompts user true/false if input is/isn't a fahrenheit


		if (yesNo)	//	simplified from yesNo == true
		{
			tempInF = temp;	//	stores value into fahrenheit static variable

			//	stores value into fahrenheit static variable while also calling the farenToCelsius method
			tempInC = farenToCelsius(tempInF);	//	stores value into static variable
		}
		else if (!yesNo)	//	simplified from yesNo == false
		{
			tempInC = temp;	//	stores value into celsius static variable

			//	stores value into fahrenheit static variable while also calling the celsiusToFaren method
			tempInF = celsiusToFaren(tempInC);
		}

		System.out.print("Enter wind speed: ");	//	prompts user for wind speed
		windSpeed = scan.nextDouble();	//	stores value into wind speed static variable

		//	stores value into wind chill index static variable while also calling the windChillIndex method
		windCI = windChillIndex(tempInF, windSpeed);

		//	initializes showResults method
		showResults();
	}

	//	prints out the user's provided temperature in Celsius & Fahrenheit,
	//	the wind speed, and the wind chill index
	public static void showResults()
	{
		//	displays calculated results
		System.out.println();
		System.out.println("Results");
		System.out.println("********************************");

		//	prints celsius with the first decimal place displayed
		System.out.println("Temp in Celsius " + String.format("%.1f" , tempInC));

		//	prints fahrenheit with first decimal place displayed
		System.out.println("Temp in Fahrenheit: " + String.format("%.1f" , tempInF));

		//	prints wind speed
		System.out.println("Wind speed: " + windSpeed);

		//	prints wind chill index
		System.out.println("Wind chill index: " + windCI);
		System.out.println("********************************");

	}

	//	methods below use the "result" variable as the "double" data type to return the calculated number from where
	//	ever the method was called

	//	method that performs the arithmetic to convert fahrenheit to celsius if necessary
	public static double farenToCelsius(double faren)
	{
		double result;

		//	conversion formula
		result = ((0.5555555555555556) * (faren - 32));

		return result;
	}

	//	method that performs the arithmetic to convert celsius to fahrenheit if necessary
	public static double celsiusToFaren(double celsius)
	{
		double result;

		//	conversion formula
		result = ((1.8 * celsius) + 32);

		return result;
	}
	
	//accepts a temperature in Fahrenheit and a wind speed in MPH and returns the wind
	//chill index
	public static double windChillIndex(double temp, double windSpeed)
	{
		double result;

		//	conversion formula
		result = 35.74 + 0.6215 * temp - 35.75 * Math.pow(windSpeed, .16) + 0.4275 * temp * Math.pow(windSpeed, .16);

		return result;
	}
}