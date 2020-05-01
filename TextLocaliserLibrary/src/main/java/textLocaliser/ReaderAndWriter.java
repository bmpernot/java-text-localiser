package textLocaliser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textLocaliser.MyTextLocaliser;

public class ReaderAndWriter {

public void readerAndWriter (String inputCountryValue, String outputCountryValue, String inputFilePath, String outputFilePath) {
		
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
				inputText = fileLines.get(fileLine) + "inputTextDelimiter";
				fileLine++;
			}
			
			else {
				inputText = inputText + fileLines.get(fileLine) + "inputTextDelimiter";
				fileLine++;
			}
		}
		
		TextLocaliser MyConverter = new MyTextLocaliser();
		
		Map<String, String> dateRegularExpression = new HashMap<String, String>();
		((MyTextLocaliser) MyConverter).loadDateRegularExpression(dateRegularExpression);
		
		Map<String, String> currencyRegularExpression = new HashMap<String, String>();
		((MyTextLocaliser) MyConverter).loadCurrencyRegularExpression(currencyRegularExpression);
		
		String localisedValues = MyConverter.localise(inputCountry, outputCountry, inputText);
		
		String localisedValuesArray [] = localisedValues.split("localisedValuesDelimiter");
		
		String numberOfDates = localisedValuesArray[0];

		String regularExpressionDate = dateRegularExpression.get(inputCountry);
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
			dateText = localDateArray.get(dates);
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

		String regularExpressionCurrency = currencyRegularExpression.get(inputCountry);
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
			currencyText = localCurrencyArray.get(currencies);
			currencies++;
			currencyMatch.appendReplacement(stringBufferCurrency, currencyText);
		}
		
		currencyMatch.appendTail(stringBufferCurrency);
		
		String finalText = stringBufferCurrency.toString();
		
		String outputTextArray [] = finalText.split("inputTextDelimiter");
		
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
