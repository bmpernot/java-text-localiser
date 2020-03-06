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

	String inputCountry = "US";
	String outputCountry = "UK";
	MyConverter.localise(inputCountry, outputCountry, inputText);
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

//Completed up to this point

@Override
public void loadCurrencyFormats(Map<String, String> currencyformats) {
	//		currencyformats = new HashMap<String, String>(); // might not need this line
	currencyformats.put("US", "en_US");	
	currencyformats.put("UK", "en_GB");
	currencyformats.put("DE", "de_DE");
	// en_US, en_GB and de_DE mean to change all currency symbols from input to the correct output
}

@Override
public String localiseCurrency(String inputFormat, String outputFormat, double inputText) {
	
	double exchangeRate = 0;		// find a way to allow for more currencies to be added
	
	
	if (inputFormat.equals("UK")) {
		if (outputFormat.equals("DE")) {
			exchangeRate = 1.15;
		}
		else if (outputFormat.equals("US")) {
			exchangeRate = 1.30;
		}
		else {
			exchangeRate = 1;
		}
	}
	else if (inputFormat.equals("US")) {
		if (outputFormat.equals("DE")) {
			exchangeRate = 0.88;
		}
		else if (outputFormat.equals("UK")) {
			exchangeRate = 0.77;
		}
		else {
			exchangeRate = 1;
		}
	}
	else if (inputFormat.equals("DE")) {
		if (outputFormat.equals("UK")) {
			exchangeRate = 0.87;
		}
		else if (outputFormat.equals("US")) {
			exchangeRate = 1.13;
		}
		else {
			exchangeRate = 1;
		}
	}
	else {
		exchangeRate = 1;
		}
	
	double finalValue = inputText*exchangeRate;
	String localisedCurrency = null;
	
	if (inputFormat.equals("UK")) {
		localisedCurrency = "�" + Double.toString(finalValue);
	}
	else if (inputFormat.equals("US")) {
		localisedCurrency = "$" + Double.toString(finalValue);
		}
	else if (inputFormat.equals("DE")) {
		localisedCurrency = Double.toString(finalValue) + "�";
		}
	else {
		localisedCurrency = Double.toString(finalValue);
	}
	
	return localisedCurrency;
}

@Override
public String localise(String inputFormat, String outputFormat, String inputtext) {
	
	// 	need to read through text file String to find inputDates D[xx/xx/xx] and inputCurrency C[�xx.xx]
	//	for date i might need to do it twice for brought and delivered dates, use a while loop
	
	
	
	String inputDate = "Example";
	String localDate = localiesDate(inputFormat, outputFormat, inputDate);
	
	double inputCurrency = 1000.00;
	String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
	
	
	String localisedValues = localDate + localCurrency;		// not correct need to create a new text file
	return localisedValues;
}}
