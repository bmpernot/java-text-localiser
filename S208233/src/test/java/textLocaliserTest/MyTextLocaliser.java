package textLocaliserTest;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MyTextLocaliser implements TextLocaliser {
	
public static void main (String [] args) {
	TextLocaliser MyConverter = new MyTextLocaliser();
	
	ArrayList<String> fileLines = new ArrayList<String>();
	try {
		File inputFile = new File("sampleFile.txt");
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
	while (n < fileLines.size())
		inputText = inputText + " " + fileLines.get(n);
		n++;

	String inputCountry = "US";		// for example text file
	String outputCountry = "UK";	// for example text file
	MyConverter.localise(inputCountry, outputCountry, inputText);
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

	//Completed up to this point

@Override
public String localise(String inputFormat, String outputFormat, String inputtext) {
	
	
	
	int leftInputDate1 = inputtext.indexOf("D[");
	int rightInputDate1 = inputtext.indexOf("]");
	String inputDate1 = inputtext.substring(leftInputDate1+1, rightInputDate1);
	String localInputDate1 = localiesDate(inputFormat, outputFormat, inputDate1);
	
	int leftInputDate2 = inputtext.indexOf("D[");	// not going to work
	int rightInputDate2 = inputtext.indexOf("]");
	String inputDate2 = inputtext.substring(leftInputDate2+1, rightInputDate2);
	String localInputDate2 = localiesDate(inputFormat, outputFormat, inputDate2);
	
	int leftInputCurrency = inputtext.indexOf("C[");
	int rightInputCurrency = inputtext.indexOf("]");
	String inputCurrencyString = null;
	
	//all good below
	
	Map<String, String> location = new HashMap<String, String>();
	loadCurrencySymbolsLocation(location);
	String position = location.get(inputFormat);
	
	if (position.equals("pre")) {
		inputCurrencyString = inputtext.substring(leftInputCurrency+2, rightInputCurrency);;
	}
	
	else if (position.equals("post")) {
		inputCurrencyString = inputtext.substring(leftInputCurrency+1, rightInputCurrency-1);;
	}
	
	else {}
	
	double inputCurrency = Double.parseDouble(inputCurrencyString);
	String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
	
	String localisedValues = localInputDate1 + " " + localInputDate2 + " " + localCurrency;
	
	return localisedValues;
}}
