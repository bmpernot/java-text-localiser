package textLocaliser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textLocaliser.MyTextLocaliser;

public class UserInterface {

public void main (String inputCountryValue, String outputCountryValue, String inputFilePath, String outputFilePath) {
		
		String inputCountry = null;
		String outputCountry = null;
	
		if (inputCountryValue.equals("United States of America")) {
			inputCountry = "US";
		}
		else if (inputCountryValue.equals("United Kingdom")) {
			inputCountry = "UK";
		}
		
		else if (inputCountryValue.equals("Germany")) {
			inputCountry = "DE";
		}
		
		if (outputCountryValue.equals("United States of America")) {
			outputCountry = "US";
		}
		else if (outputCountryValue.equals("United Kingdom")) {
			outputCountry = "UK";
		}
		
		else if (outputCountryValue.equals("Germany")) {
			outputCountry = "DE";
		}
		
		/* need to use GUI
		
		boolean inputFileExists = false;
		boolean outputFileExists = true;
		String inputFilePath = null;
		String outputFilePath = null;
		
		Scanner userInput = new Scanner(System.in);
		
	    System.out.println("Please enter the country format the file is in: (UK, US, DE)");
	    String inputCountry = userInput.nextLine(); 
	    inputCountry = inputCountry.toUpperCase();
	    
	    System.out.println("Please enter the country format that you want the output file to be in: (UK, US, DE)");
	    String outputCountry = userInput.nextLine(); 
	    outputCountry = outputCountry.toUpperCase();
	    
	    while (inputFileExists == false) {
	    	
	    	System.out.println("Please enter the input text file's name:");
		    System.out.println("for testing use - sampleFile");
		    String inputFileName = userInput.nextLine(); 
		    
		    System.out.println("Please enter the input text file's location:");
		    System.out.println("For example; C:\\Users\\Documents\\Java\\Assessment");
		    System.out.println("for testing use - C:\\Users\\benpe\\Documents\\Java\\Assessment");
		    String inputFileLocation = userInput.nextLine(); 
		    
		    inputFilePath = inputFileLocation + "\\" + inputFileName + ".txt";
		    
		    File inputFile = new File(inputFilePath);
		    
		    if (inputFile.exists() == true) {
		    	inputFileExists = true;
		    }
		    else {
		    	System.out.println("This file doesn't exist please enter an existing file name and location");
		    }
	    }
	    
	    while (outputFileExists == true) {
	    	
	    	System.out.println("Please enter the output text file's name:");
		    String outputFileName = userInput.nextLine(); 
		    
		    System.out.println("Please enter the output text file's location:");
		    System.out.println("For example; C:\\Users\\Documents\\Java\\Assessment");
		    System.out.println("for testing use - C:\\Users\\benpe\\Documents\\Java\\Assessment");
		    String outputFileLocation = userInput.nextLine(); 
		    
		    outputFilePath = outputFileLocation + "\\" + outputFileName + ".txt";
		    
		    File outputFile = new File(outputFilePath);
		    
		    if (outputFile.exists() == false) {
		    	outputFileExists = false;
		    }
		    else {
		    	System.out.println("This file already exist please enter an non-existing file name and location");
		    }
	    }
	    
	    userInput.close();

	    // */
	    
		ArrayList<String> fileLines = new ArrayList<String>();
		
		try {
			
			File inputFile = new File(inputFilePath);

			Scanner myReader = new Scanner(inputFile);
			
			while (myReader.hasNextLine()) {
				String FileText = myReader.nextLine();
				fileLines.add(FileText);
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occur");
			e.printStackTrace();
		}
		
		String inputText = "empty";
		int fileLine = 0;
		
		while (fileLine < fileLines.size()) {
			if(inputText.equals("empty") == true) { 
				inputText = fileLines.get(fileLine) + "   ";
				fileLine++;
			}
			
			else {
				inputText = inputText + fileLines.get(fileLine) + "   ";
				fileLine++;
			}
		}
		
		TextLocaliser MyConverter = new MyTextLocaliser();
		
		String localisedValues = MyConverter.localise(inputCountry, outputCountry, inputText);
		
		String localisedValuesArray [] = localisedValues.split(" ");
		
		String numberOfDates = localisedValuesArray[0];

		String regularExpressionDate = "D\\[[^\\[]*\\]";
		Pattern datePattern = Pattern.compile(regularExpressionDate);
		Matcher dateMatch = datePattern.matcher(inputText);
		
		String dateText = null;
		int dates = 0;
		int startOfDates = 1;
		ArrayList<String> localDateArray = new ArrayList<String>();
		
		while (startOfDates <= Integer.parseInt(numberOfDates)) {
			localDateArray.add(localisedValuesArray[startOfDates]);
			startOfDates++;
		}
		
		StringBuffer stringBufferDate = new StringBuffer();
		
		while (dateMatch.find()) {
			dateText = "D[" + localDateArray.get(dates) + "]";
			dates++;
			dateMatch.appendReplacement(stringBufferDate, dateText);
		}
		
		dateMatch.appendTail(stringBufferDate);
		
		String newText = stringBufferDate.toString();
		
		int numberOfCurrency = 0;
		
		if (Integer.parseInt(numberOfDates) == 0) {
			
			numberOfCurrency = Integer.parseInt(localisedValuesArray[2]);
		}
		
		else {
			numberOfCurrency = Integer.parseInt(localisedValuesArray[Integer.parseInt(numberOfDates) + 1]);	
		}

		String regularExpressionCurrency = "C\\[[^\\[]*\\]";
		Pattern currencyPattern = Pattern.compile(regularExpressionCurrency);
		Matcher currencyMatch = currencyPattern.matcher(newText);
		
		String currencyText = null;
		
		int currenciesAdded = 0;
		int currencies = 0;
		int startOfCurrencies = 0;
		
		if (Integer.parseInt(numberOfDates) == 0) {
			startOfCurrencies = 3;
		}
		
		else {
			startOfCurrencies = Integer.parseInt(numberOfDates) + 2;	
		}
		
		ArrayList<String> localCurrencyArray = new ArrayList<String>();
		
		while (currenciesAdded < numberOfCurrency) {
			localCurrencyArray.add(localisedValuesArray[startOfCurrencies]);
			currenciesAdded++;
		}
		
		StringBuffer stringBufferCurrency = new StringBuffer();
		
		while (currencyMatch.find()) {
			currencyText = "C[" + localCurrencyArray.get(currencies) + "]";
			currencies++;
			currencyMatch.appendReplacement(stringBufferCurrency, currencyText);
		}
		
		currencyMatch.appendTail(stringBufferCurrency);
		
		String finalText = stringBufferCurrency.toString();
		
		String outputTextArray [] = finalText.split("   ");
		
		try {
		      File outputWriter = new File(outputFilePath);
		      if (outputWriter.createNewFile()) {
		    	  
		    	  BufferedWriter myWriter = new BufferedWriter(new FileWriter(outputFilePath, true));
		    	  
		    	  int outputLine = 0;
		    	  
		    	  while (outputLine < outputTextArray.length) {
		    			  myWriter.write(outputTextArray[outputLine]);
		    			  myWriter.newLine();
		    			  outputLine++;
		    	  }
		          myWriter.close();
		      } 
		      
		      else {
		        System.out.println("File already exists.");
		      }
		    }
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
}
