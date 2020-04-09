package textLocaliserTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textLocaliserTest.MyTextLocaliser; // will need to change when put back into real application

public class UserInterface {

public static void main (String [] args) {
		
		Scanner userInput = new Scanner(System.in);
		
	    System.out.println("Please enter the country format the file is in: (UK, US, DE)");
	    String inputCountry = userInput.nextLine(); 
	    inputCountry = inputCountry.toUpperCase();
	    
	    System.out.println("Please enter the country format that you want the output file to be in: (UK, US, DE)");
	    String outputCountry = userInput.nextLine(); 
	    outputCountry = outputCountry.toUpperCase();
	    
	    System.out.println("Please enter the output text file's name:");
	    String outputFileName = userInput.nextLine(); 
	    
	    System.out.println("Please enter the output text file's location:");
	    System.out.println("For example; C:\\Users\\Documents\\Java\\Assessment");
	    System.out.println("for testing use - C:\\Users\\benpe\\Documents\\Java\\Assessment");
	    String outputFileLocation = userInput.nextLine(); 
	    
	    System.out.println("Please enter the input text file's name:");
	    System.out.println("for testing use - sampleFile");
	    String inputFileName = userInput.nextLine(); 
	    
	    System.out.println("Please enter the input text file's location:");
	    System.out.println("For example; C:\\Users\\Documents\\Java\\Assessment");
	    System.out.println("for testing use - C:\\Users\\benpe\\Documents\\Java\\Assessment");
	    String inputFileLocation = userInput.nextLine(); 
	    
	    userInput.close();
	    
	    String outputFilePath = outputFileLocation + "\\" + outputFileName + ".txt";
		
		String inputFilePath = inputFileLocation + "\\" + inputFileName + ".txt";

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
