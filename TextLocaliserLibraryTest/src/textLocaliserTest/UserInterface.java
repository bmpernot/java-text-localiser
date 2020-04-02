package textLocaliserTest;

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
	    
	    System.out.println("Please enter the country format that you want the output file to be in: (UK, US, DE)");
	    String outputCountry = userInput.nextLine(); 
	    
	    System.out.println("Please enter the output text file's name:");
	    String outputFileName = userInput.nextLine(); 
	    
	    System.out.println("Please enter the output text file's location:");
	    System.out.println("For example; C:\\Users\\Documents\\Java\\Assessment");
	    System.out.println("for testing use - C:\\Users\\benpe\\Documents\\Java\\Assessment");
	    String outputFileLocation = userInput.nextLine(); 
	    
	    userInput.close();
	    
	    System.out.println("following string is the input country"); 	// DEBUG STATEMENT
		System.out.println(inputCountry); 	// DEBUG STATEMENT
		
		System.out.println("following string is the output country"); 	// DEBUG STATEMENT
		System.out.println(outputCountry); 	// DEBUG STATEMENT
		
		System.out.println("following string is the output file location"); 	// DEBUG STATEMENT
		System.out.println(outputFileLocation); 	// DEBUG STATEMENT
		
		System.out.println("following string is the output file name"); 	// DEBUG STATEMENT
		System.out.println(outputFileName); 	// DEBUG STATEMENT
	    
	    String outputFile = outputFileLocation + "\\" + outputFileName + ".txt";
	    
	    System.out.println("following string is the output file"); 	// DEBUG STATEMENT
		System.out.println(outputFile); 	// DEBUG STATEMENT
		
		ArrayList<String> fileLines = new ArrayList<String>();
		
		try {
			
			File inputFile = new File("C:\\Users\\benpe\\Documents\\Java\\Assessment\\sampleFile.txt");	
					// not going to work as the user can not say what file to read
			
			Scanner myReader = new Scanner(inputFile);
			
			System.out.println("following lines contain the input file contents"); 	// DEBUG STATEMENT
			
			while (myReader.hasNextLine()) {
				String FileText = myReader.nextLine();
				fileLines.add(FileText);
				System.out.println(FileText); 	// DEBUG STATEMENT
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occur");
			e.printStackTrace();
		}
		
		String inputText = null;
		int fileLine = 0;
		
		System.out.println("following string is the intput file contents as a string"); 	// DEBUG STATEMENT
		
		while (fileLine <= fileLines.size()) {
			inputText = inputText + "   " + fileLines.get(fileLine);
			fileLine++;
			System.out.println(inputText); 	// DEBUG STATEMENT
		}
		
		TextLocaliser MyConverter = new MyTextLocaliser();
		
		String localisedValues = MyConverter.localise(inputCountry, outputCountry, inputText);
		
		System.out.println("following string is the localised values"); 	// DEBUG STATEMENT
		System.out.println(localisedValues); 	// DEBUG STATEMENT
		
		String localisedValuesArray [] = localisedValues.split(" ");
		
		String numberOfDates = localisedValuesArray[0];
		
		System.out.println("following string is the number of dates"); 	// DEBUG STATEMENT
		System.out.println(numberOfDates); 	// DEBUG STATEMENT
		
		String regularExpressionDate = "D[*]";
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
		
		int numberOfCurrency = 0;
		
		if (Integer.parseInt(numberOfDates) == 0) {
			
			numberOfCurrency = Integer.parseInt(localisedValuesArray[2]);
		}
		
		else {
			numberOfCurrency = Integer.parseInt(localisedValuesArray[Integer.parseInt(numberOfDates) + 1]);	
		}
		
		String regularExpressionCurrency = "C[*]";
		Pattern currencyPattern = Pattern.compile(regularExpressionCurrency);
		Matcher currencyMatch = currencyPattern.matcher(inputText);
		
		String currencyText = null;
		
		int currencies = 1;
		int startOfCurrencies = startOfDates + 2;
		ArrayList<String> localCurrencyArray = new ArrayList<String>();
		
		while (currencies <= numberOfCurrency) {
			localCurrencyArray.add(localisedValuesArray[startOfCurrencies]);
			currencies++;
		}
		
		StringBuffer stringBufferCurrency = new StringBuffer();
		
		while (dateMatch.find()) {
			currencyText = "C[" + localDateArray.get(currencies) + "]";
			currencies++;
			currencyMatch.appendReplacement(stringBufferCurrency, currencyText);
		}
		
		System.out.println("following string is the final text before getting split"); 	// DEBUG STATEMENT
		System.out.println(inputText); 	// DEBUG STATEMENT
		
		String outputTextArray [] = inputText.split("   ");
		
		try {
		      File outputWriter = new File(outputFile);
		      if (outputWriter.createNewFile()) {
		    	  FileWriter myWriter = new FileWriter(outputFile);
		    	  int outputLine = 0;
		    	  
		    	  while (outputLine <= outputTextArray.length) {
		    			  myWriter.write(outputTextArray[outputLine]);
		    			  myWriter.write("/n");
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
