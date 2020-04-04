package textLocaliserTest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

public class MyTextLocaliser implements TextLocaliser {

public void loadCurrencyExchangeRate(Map<String, Double> exchangeRate) {

	exchangeRate.put("UK-DE", 1.15);
	exchangeRate.put("UK-US", 1.30);
	
	exchangeRate.put("US-UK", 0.77);
	exchangeRate.put("US-DE", 0.88);
	
	exchangeRate.put("DE-UK", 0.87);
	exchangeRate.put("DE-US", 1.13);
}
	
public void loadCurrencySymbolsLocation(Map<String, String> location) {

	location.put("UK", "pre");
	location.put("US", "pre");
	location.put("DE", "post");
}

public String findDelimiter(String Text) {

	boolean delimiter = false;
	boolean found = false;
	String array[] = Text.split("");
	int indexNumber = 0;
	String delimiterValue = null;
	
	while (delimiter == false) {
		
		if ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(array[indexNumber])) {
			found = false;
		}
		else {
			found =  true;
		}
		
		if(found == true) {
			delimiter = true;
			delimiterValue = array[indexNumber];
		}
		
		else {
			indexNumber++;
		}
	}
	
	return delimiterValue;
}

public int findIndexNumber(String array [], CharSequence value) {
	int indexNumberOfValue = 0;
	boolean foundValue = false;
	while (foundValue == false) {
		boolean test = array[indexNumberOfValue].contains(value);
		if (test == true) {
			foundValue = true;
		}
		else {
			indexNumberOfValue++;
		}
	}
	return indexNumberOfValue;
}

@Override
public void loadDateFormats(Map<String, String> dateformats) {

	dateformats.put("US", "mm/dd/yy");
	dateformats.put("UK", "dd/mm/yyyy");
	dateformats.put("DE", "yyyy-mm-dd");
}

@Override
public String localiesDate(String inputFormat, String outputFormat, String inputText) {
	
	String inputTextDelimiterValue = findDelimiter(inputText);
	
	System.out.println("following string is the delimiter"); 	// DEBUG STATEMENT
	System.out.println(inputTextDelimiterValue); 	// DEBUG STATEMENT
	
	String inputTextDateFields[] = inputText.split(inputTextDelimiterValue);
	
	System.out.println("following string is the split fields"); 	// DEBUG STATEMENT
	System.out.println(inputTextDateFields[0] + " " + inputTextDateFields[1] + " " + inputTextDateFields[2]); 	// DEBUG STATEMENT
	
	Map<String, String> inputMap = new HashMap<String, String>();
	loadDateFormats(inputMap);
	
	String inputDateFormat = inputMap.get(inputFormat);
	
	System.out.println("following string is the input format"); 	// DEBUG STATEMENT
	System.out.println(inputDateFormat); 	// DEBUG STATEMENT
	
	String inputFormatDelimiterValue = findDelimiter(inputDateFormat);
	
	System.out.println("following string is the delimiter"); 	// DEBUG STATEMENT
	System.out.println(inputFormatDelimiterValue); 	// DEBUG STATEMENT
	
	String inputFormatDateFields[] = inputDateFormat.split(inputFormatDelimiterValue);
	
	System.out.println("following string is the split fields"); 	// DEBUG STATEMENT
	System.out.println(inputFormatDateFields[0] + " " + inputFormatDateFields[1] + " " + inputFormatDateFields[2]); 	// DEBUG STATEMENT
	
	Map<String, String> outputMap = new HashMap<String, String>();
	loadDateFormats(outputMap);
	
	String outputDateFormat = outputMap.get(outputFormat);
	
	System.out.println("following string is the output format"); 	// DEBUG STATEMENT
	System.out.println(outputDateFormat); 	// DEBUG STATEMENT
	
	String outputFormatDelimiterValue = findDelimiter(outputDateFormat);
	
	System.out.println("following string is the delimiter"); 	// DEBUG STATEMENT
	System.out.println(outputFormatDelimiterValue); 	// DEBUG STATEMENT
	
	String outputFormatDateFields[] = outputDateFormat.split(outputFormatDelimiterValue);
	
	System.out.println("following string is the split fields"); 	// DEBUG STATEMENT
	System.out.println(outputFormatDateFields[0] + " " + outputFormatDateFields[1] + " " + outputFormatDateFields[2]); 	// DEBUG STATEMENT
	
	
	Map<String, String> convert = new HashMap<String, String>();
	
	for (int increment = 0; increment < inputFormatDateFields.length; increment++) {
		convert.put(inputFormatDateFields[increment], inputTextDateFields[increment]);
		
		System.out.println("following string is the input format date field"); 	// DEBUG STATEMENT
		System.out.println(inputFormatDateFields[increment]); 	// DEBUG STATEMENT
		
		System.out.println("following string is the input text date field"); 	// DEBUG STATEMENT
		System.out.println(inputTextDateFields[increment]); 	// DEBUG STATEMENT
	}
	int inputYear = findIndexNumber(inputFormatDateFields, "y");
	
	System.out.println("following interger is the input index number for years"); 	// DEBUG STATEMENT
	System.out.println(inputYear); 	// DEBUG STATEMENT
	
	int outputYear = findIndexNumber(outputFormatDateFields, "y");
	
	System.out.println("following interger is the output index number for years"); 	// DEBUG STATEMENT
	System.out.println(outputYear); 	// DEBUG STATEMENT
	
	if (outputFormatDateFields[outputYear] == inputFormatDateFields[inputYear]) {}
	
	else if (inputFormatDateFields[inputYear].equals("yy")) {
		outputFormatDateFields[outputYear] = "20" + inputTextDateFields[inputYear];
		
		System.out.println("following interger is the final output for years"); 	// DEBUG STATEMENT
		System.out.println(outputFormatDateFields[outputYear]); 	// DEBUG STATEMENT
	}
	
	else {
		outputFormatDateFields[outputYear] = inputTextDateFields[inputYear].substring(2);
		
		System.out.println("following interger is the final output for years"); 	// DEBUG STATEMENT
		System.out.println(outputFormatDateFields[outputYear]); 	// DEBUG STATEMENT
	}
	
	String localisedDate = convert.get(outputFormatDateFields[0]) + outputFormatDelimiterValue + 
			convert.get(outputFormatDateFields[1]) + outputFormatDelimiterValue + 
			outputFormatDateFields[2];
	
	System.out.println("following string is the final output"); 	// DEBUG STATEMENT
	System.out.println(localisedDate); 	// DEBUG STATEMENT
	
	return localisedDate;
}

@Override
public void loadCurrencyFormats(Map<String, String> currencyformats) {

	currencyformats.put("US", "$");	
	currencyformats.put("UK", "£");
	currencyformats.put("DE", "€");
}

@Override
public String localiseCurrency(String inputFormat, String outputFormat, double inputText) {
	
	Map<String, Double> exchangeRates = new HashMap<String, Double>();
	loadCurrencyExchangeRate(exchangeRates);
	
	Map<String, String> location = new HashMap<String, String>();
	loadCurrencySymbolsLocation(location);

	Map<String, String> symbolMap = new HashMap<String, String>();
	loadCurrencyFormats(symbolMap);
	
	double exchangeRate = 0;
	
	String key = inputFormat + "-" + outputFormat;
	
	System.out.println("following string is the key to the map"); 	// DEBUG STATEMENT
	System.out.println(key); 	// DEBUG STATEMENT
	
	exchangeRate = exchangeRates.get(key);
	
	System.out.println("following interger is the exchange rate"); 	// DEBUG STATEMENT
	System.out.println(exchangeRate); 	// DEBUG STATEMENT

	double finalValue = inputText*exchangeRate;
	
	System.out.println("following interger is the final value"); 	// DEBUG STATEMENT
	System.out.println(finalValue); 	// DEBUG STATEMENT
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	String localisedCurrency = df.format(finalValue);
	
	
	if (outputFormat.equals("DE")) {
		localisedCurrency = localisedCurrency.replace(".", ",");
		// find a way to add "." every third number
	}
	
	else if(outputFormat.equals("US") || outputFormat.equals("UK")) {
		// find a way to add "," every third number
		
	}
	
	else {}
	
	
	String symbol = symbolMap.get(inputFormat);
	
	System.out.println("following string is the symbol"); 	// DEBUG STATEMENT
	System.out.println(symbol); 	// DEBUG STATEMENT
	
	String position = location.get(inputFormat);
	
	System.out.println("following string is the position"); 	// DEBUG STATEMENT
	System.out.println(position); 	// DEBUG STATEMENT
	
	if (position.equals("pre")) {
		localisedCurrency = symbol + localisedCurrency;
		
		System.out.println("following string is the final currency"); 	// DEBUG STATEMENT
		System.out.println(localisedCurrency); 	// DEBUG STATEMENT
	}
	
	else if (position.equals("post")) {
		localisedCurrency = localisedCurrency + symbol;
		
		System.out.println("following string is the final currency"); 	// DEBUG STATEMENT
		System.out.println(localisedCurrency); 	// DEBUG STATEMENT
	}
	
	else {}
	
	return localisedCurrency;
}

@Override
public String localise(String inputFormat, String outputFormat, String inputtext) {
	
	String localisedDateValues = null;
	int numberOfDates = 0;
	
	String regularExpressionDate = "D\\[[^\\[]*\\]";
	Pattern datePattern = Pattern.compile(regularExpressionDate);
	Matcher dateMatch = datePattern.matcher(inputtext);
	
	String dateText = null;
	
	while (dateMatch.find()) {
		
		int start = dateMatch.start();
		int end = dateMatch.end();
		
		dateText = inputtext.substring(start, end);
		
		System.out.println("following dates are in the input file"); 	// DEBUG STATEMENT
		System.out.println(dateText); 	// DEBUG STATEMENT
		
		dateText = dateText.substring(2, (dateText.length()-1));
		
		System.out.println("following dates converted to the format * instead of D[*]"); 	// DEBUG STATEMENT
		System.out.println(dateText); 	// DEBUG STATEMENT
		
		String localDate = localiesDate(inputFormat, outputFormat, dateText);
		
		System.out.println("following string is the converted value"); 	// DEBUG STATEMENT
		System.out.println(localDate); 	// DEBUG STATEMENT
		
		if (numberOfDates == 0) {
			localisedDateValues = localDate;
		}
		
		else {
			localisedDateValues = localisedDateValues + " " + localDate;
		}
		
		System.out.println("following string are the localised date values"); 	// DEBUG STATEMENT
		System.out.println(localisedDateValues); 	// DEBUG STATEMENT
		
		numberOfDates++;
		
		System.out.println("following interger is the number of dates in the localisedDateValues string"); 	// DEBUG STATEMENT
		System.out.println(numberOfDates); 	// DEBUG STATEMENT
	}
	
	localisedDateValues = numberOfDates + " " + localisedDateValues;
	
	System.out.println("following string is the number of dates followed by localisedDateValues"); 	// DEBUG STATEMENT
	System.out.println(localisedDateValues); 	// DEBUG STATEMENT
	
	String localisedCurrencyValues = null;
	int numberOfCurrencies = 0;
	
	String regularExpressionCurrency = "C\\[[^\\[]*\\]";
	Pattern currencyPattern = Pattern.compile(regularExpressionCurrency);
    Matcher currencyMatch = currencyPattern.matcher(inputtext);
    
    String currencyText = null;
    
    while (currencyMatch.find()) {
    	
    	int start = currencyMatch.start();
		int end = currencyMatch.end();
		
		currencyText = inputtext.substring(start, end);
    	
    	Map<String, String> location = new HashMap<String, String>();
    	loadCurrencySymbolsLocation(location);
    	String position = location.get(inputFormat);
    	
    	if (position.equals("pre")) {
    		currencyText = currencyText.substring(3, currencyText.length()-1);
    		
    		System.out.println("following string is the currency without any symbols"); 	// DEBUG STATEMENT
    		System.out.println(currencyText); 	// DEBUG STATEMENT
    	}
    	
    	else if (position.equals("post")) {
    		currencyText = currencyText.substring(2, (currencyText.length()-2));
    		
    		System.out.println("following string is the currency without any symbols"); 	// DEBUG STATEMENT
    		System.out.println(currencyText); 	// DEBUG STATEMENT
    	}
    	
    	else {}
    	
    	if (inputFormat.equals("DE")) {
    		currencyText = currencyText.replace(".", "");
    		currencyText = currencyText.replace(",", ".");
    	}
    	else if (inputFormat.equals("US") || inputFormat.equals("UK")) {
    		currencyText = currencyText.replace(",", "");
    	}
    	else {}
    	
    	double inputCurrency = Double.parseDouble(currencyText);
    	
    	System.out.println("following double is the currency without any symbols"); 	// DEBUG STATEMENT
		System.out.println(inputCurrency); 	// DEBUG STATEMENT
		
		String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
		
		System.out.println("following string is the localised currency"); 	// DEBUG STATEMENT
		System.out.println(localCurrency); 	// DEBUG STATEMENT
		
		if (numberOfCurrencies == 0) {
			localisedCurrencyValues = localCurrency;
		}
		
		else {
			localisedCurrencyValues = localisedCurrencyValues + " " + localCurrency;
		}
		
		numberOfCurrencies++;
		
    }
    
		System.out.println("following string is all the localised currency"); 	// DEBUG STATEMENT
		System.out.println(localisedCurrencyValues); 	// DEBUG STATEMENT
    	
    
    localisedCurrencyValues = numberOfCurrencies + " " + localisedCurrencyValues;
    
    System.out.println("following string is the number of currencies followed by all the localised currency"); 	// DEBUG STATEMENT
	System.out.println(localisedCurrencyValues); 	// DEBUG STATEMENT
	
    String localisedValues = localisedDateValues + " " + localisedCurrencyValues;
    
    System.out.println("following string is all the localised values"); 	// DEBUG STATEMENT
	System.out.println(localisedValues); 	// DEBUG STATEMENT
    
	return localisedValues;
}
}