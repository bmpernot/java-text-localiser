package textLocaliserTest;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;
import java.io.IOException;
import java.io.FileWriter;

public class MyTextLocaliser implements TextLocaliser {
	
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
    String outputFileLocation = userInput.nextLine(); 
    
    userInput.close();
    
    String outputFile = outputFileLocation + "\\" + outputFileName + ".txt";
	
	TextLocaliser MyConverter = new MyTextLocaliser();
	
	ArrayList<String> fileLines = new ArrayList<String>();
	try {
		File inputFile = new File("C:\\Users\\benpe\\Documents\\Java\\Assessment\\sampleFile.txt");			// not going to work
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
	
	String inputText = null;
	int n = 0;
	while (n < fileLines.size()) {
		inputText = inputText + "   " + fileLines.get(n);
		n++;
	}
	
	String localisedValues = MyConverter.localise(inputCountry, outputCountry, inputText);
	
	String localisedValuesArray [] = localisedValues.split(" ");
	String localDate1 = localisedValuesArray[0];
	String localDate2 = localisedValuesArray[1];
	String localCurrency1 = localisedValuesArray[2];
	
	String regularExpressionDate = "D[*]";
	Pattern datePattern = Pattern.compile(regularExpressionDate);
	Matcher dateMatch = datePattern.matcher(inputText);
	
	String dateText = null;
	int x = 1;
	ArrayList<String> localDateArray = new ArrayList<String>();
	localDateArray.add(localDate1);
	localDateArray.add(localDate2);
	StringBuffer stringBufferDate = new StringBuffer();
	
	while (dateMatch.find()) {
		dateText = "D[" + localDateArray.get(x) + "]";
		x++;
		dateMatch.appendReplacement(stringBufferDate, dateText);
	}
	
	String regularExpressionCurrency = "C[*]";
	Pattern currencyPattern = Pattern.compile(regularExpressionCurrency);
	Matcher currencyMatch = currencyPattern.matcher(inputText);
	
	String currencyText = null;
	int y = 1;
	ArrayList<String> localCurrencyArray = new ArrayList<String>();
	localCurrencyArray.add(localCurrency1);
	StringBuffer stringBufferCurrency = new StringBuffer();
	
	while (dateMatch.find()) {
		currencyText = "C[" + localDateArray.get(y) + "]";
		y++;
		currencyMatch.appendReplacement(stringBufferCurrency, currencyText);
	}
	
	String outputTextArray [] = inputText.split("   ");
	
	try {
	      File outputWriter = new File(outputFile);
	      if (outputWriter.createNewFile()) {
	    	  FileWriter myWriter = new FileWriter(outputFile);
	    	  int z = 0;
	    	  while (z <= outputTextArray.length) {
	    			  myWriter.write(outputTextArray[z]);
	    			  myWriter.write("/n");
	    			  z++;
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

public void loadCurrencySymbolsLocation(Map<String, String> location) {
	//		location = new HashMap<String, String>();	// might not need this line
	location.put("�", "pre");
	location.put("$", "pre");
	location.put("�", "post");
}

public boolean isDigit(String character) {
	if ("0123456789".contains(character)) {
		return false;
	}
	else {
		return true;
	}
}
	
public String findDelimiter(String Text) {
	
	boolean delimiter = false;
	String array[] = Text.split("");
	int n = 0;
	String delimiterValue = null;
	
	while (delimiter == false) {
		
		boolean found = isDigit(array[n]);
		
		if(found == true) {
			delimiter = true;
			delimiterValue = array[n];
			
		}
		
		else {n++;}
	}
	return delimiterValue;
}

public int findIndexNumber(String array [], String value) {
	int indexNumberValue = 0;
	boolean foundValue = false;
	while (foundValue == false) {
		boolean test = array[indexNumberValue].contains(value);
		if (test == true) {
			foundValue = true;
		}
		else {
			indexNumberValue++;
		}
	}
	return indexNumberValue;
}

@Override
public void loadDateFormats(Map<String, String> dateformats) {
	//		dateformats = new HashMap<String, String>();	// might not need this line
	dateformats.put("US", "mm/dd/yy");
	dateformats.put("UK", "dd/mm/yyyy");
	dateformats.put("DE", "yyyy-mm-dd");
}

@Override
public String localiesDate(String inputFormat, String outputFormat, String inputText) {
	
	String inputTextDelimiterValue = findDelimiter(inputText);
	String inputTextDateFields[] = inputText.split(inputTextDelimiterValue);
	
	Map<String, String> inputMap = new HashMap<String, String>();
	loadDateFormats(inputMap);
	String inputDateFormat = inputMap.get(inputFormat);
	String inputFormatDelimiterValue = findDelimiter(inputDateFormat);
	String inputFormatDateFields[] = inputText.split(inputFormatDelimiterValue);
	
	Map<String, String> outputMap = new HashMap<String, String>();
	loadDateFormats(outputMap);
	String outputDateFormat = outputMap.get(inputFormat);
	String outputFormatDelimiterValue = findDelimiter(outputDateFormat);
	String outputFormatDateFields[] = inputText.split(outputFormatDelimiterValue);
	
	
	Map<String, String> convert = new HashMap<String, String>();
	for (int i = 0; i <= inputFormatDateFields.length; i++) {
		convert.put(inputFormatDateFields[i], inputTextDateFields[i]);
	}
	
	int inputYear = findIndexNumber(inputFormatDateFields, "y");
	int outputYear = findIndexNumber(outputFormatDateFields, "y");
	
	if (outputFormatDateFields[outputYear] == inputFormatDateFields[inputYear]) {}
	
	else if (inputFormatDateFields[inputYear].equals("yy")) {
		outputFormatDateFields[outputYear] = "20" + inputTextDateFields[inputYear];
	}
	
	else {
		outputFormatDateFields[outputYear] = inputTextDateFields[inputYear].substring(2);
	}
	
	String localisedDate = convert.get(outputFormatDateFields[0]) + outputFormatDelimiterValue + 
			convert.get(outputFormatDateFields[1]) + outputFormatDelimiterValue + 
			convert.get(outputFormatDateFields[2]);
	return localisedDate;
}

@Override
public void loadCurrencyFormats(Map<String, String> currencyformats) {
	//		currencyformats = new HashMap<String, String>(); // might not need this line
	currencyformats.put("US", "$");	
	currencyformats.put("UK", "�");
	currencyformats.put("DE", "�");
}

@Override
public String localiseCurrency(String inputFormat, String outputFormat, double inputText) {
	
	double exchangeRate = 0;
	Map<String, Double> exchangeRates = new HashMap<String, Double>();
	
	exchangeRates.put("UK-DE", 1.15);
	exchangeRates.put("UK-US", 1.30);
	
	exchangeRates.put("US-UK", 0.77);
	exchangeRates.put("US-DE", 0.88);
	
	exchangeRates.put("DE-UK", 0.87);
	exchangeRates.put("DE-US", 1.13);
	
	String key = inputFormat + "-" + outputFormat;
	exchangeRate = exchangeRates.get(key);

	double finalValue = inputText*exchangeRate;
	
	
	String localisedCurrency = null;
	
	Map<String, String> location = new HashMap<String, String>();
	loadCurrencySymbolsLocation(location);

	Map<String, String> symbolMap = new HashMap<String, String>();
	loadCurrencyFormats(symbolMap);
	
	String symbol = symbolMap.get(inputFormat);
	
	String position = location.get(symbol);
	
	if (position.equals("pre")) {
		localisedCurrency = symbol + Double.toString(finalValue);
	}
	
	else if (position.equals("post")) {
		localisedCurrency = Double.toString(finalValue) + symbol;
	}
	
	else {}
	
	return localisedCurrency;
}

@Override
public String localise(String inputFormat, String outputFormat, String inputtext) {
	
	String localisedDateValues = null;
	int numberOfDates = 1;
	
	String regularExpressionDate= "D[*]";
	Pattern datePattern = Pattern.compile(regularExpressionDate);
	Matcher dateMatch = datePattern.matcher(inputtext);
	
	String dateText = null;
	
	while (dateMatch.find()) {
		dateText = dateMatch.toString();
		dateText = dateText.substring(2, (dateText.length()-1));
		String localDate = localiesDate(inputFormat, outputFormat, dateText);
		localisedDateValues = localisedDateValues + localDate + " ";
		numberOfDates++;
	}
	
	localisedDateValues = numberOfDates + localisedDateValues;
	
	String localisedCurrencyValues = null;
	int numberOfCurrencies = 1;
	
	String regularExpressionCurrency = "C[*]";
	Pattern currencyPattern = Pattern.compile(regularExpressionCurrency);
    Matcher currencyMatch = currencyPattern.matcher(inputtext);
    
    String currencyText = null;
    
    while (currencyMatch.find()) {
    	
    	currencyText = currencyMatch.toString();
    	
    	Map<String, String> location = new HashMap<String, String>();
    	loadCurrencySymbolsLocation(location);
    	String position = location.get(inputFormat);
    	
    	if (position.equals("pre")) {
    		currencyText = currencyText.substring(1, currencyText.length());;
    		double inputCurrency = Double.parseDouble(currencyText);
    		String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
    		localisedCurrencyValues = localisedCurrencyValues + localCurrency + " ";
    	}
    	
    	else if (position.equals("post")) {
    		currencyText = currencyText.substring(0, (currencyText.length()-1));
    		double inputCurrency = Double.parseDouble(currencyText);
    		String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
    		localisedCurrencyValues = localisedCurrencyValues + localCurrency + " ";
    	}
    	
    	else {}
    }
    
    localisedCurrencyValues = numberOfCurrencies + localisedCurrencyValues;
	
    String localisedValues = localisedDateValues + localisedCurrencyValues;
    
	return localisedValues;
}

}