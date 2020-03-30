package textLocaliserTest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTextLocaliser implements TextLocaliser {

public void loadCurrencySymbolsLocation(Map<String, String> location) {
	//		location = new HashMap<String, String>();	// might not need this line
	location.put("£", "pre");
	location.put("$", "pre");
	location.put("€", "post");
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
	int indexNumber = 0;
	String delimiterValue = null;
	
	while (delimiter == false) {
		
		boolean found = isDigit(array[indexNumber]);
		
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

public int findIndexNumber(String array [], String value) {
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
	
	String outputDateFormat = outputMap.get(outputFormat);
	String outputFormatDelimiterValue = findDelimiter(outputDateFormat);
	String outputFormatDateFields[] = inputText.split(outputFormatDelimiterValue);
	
	Map<String, String> convert = new HashMap<String, String>();
	
	for (int increment = 0; increment <= inputFormatDateFields.length; increment++) {
		convert.put(inputFormatDateFields[increment], inputTextDateFields[increment]);
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
	currencyformats.put("UK", "£");
	currencyformats.put("DE", "€");
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
	int numberOfDates = 0;
	
	String regularExpressionDate = "D[*]";
	Pattern datePattern = Pattern.compile(regularExpressionDate);
	Matcher dateMatch = datePattern.matcher(inputtext);
	
	String dateText = null;
	
	while (dateMatch.find()) {
		dateText = dateMatch.toString();
		dateText = dateText.substring(2, (dateText.length()-1));
		String localDate = localiesDate(inputFormat, outputFormat, dateText);
		localisedDateValues = localisedDateValues + " " + localDate;
		numberOfDates++;
	}
	
	localisedDateValues = numberOfDates + localisedDateValues;
	
	String localisedCurrencyValues = null;
	int numberOfCurrencies = 0;
	
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
    		currencyText = currencyText.substring(1, currencyText.length());
    	}
    	
    	else if (position.equals("post")) {
    		currencyText = currencyText.substring(0, (currencyText.length()-1));
    	}
    	
    	else {}
    	
    	double inputCurrency = Double.parseDouble(currencyText);
		String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
		localisedCurrencyValues = localisedCurrencyValues + " " + localCurrency;
    	
    }
    
    localisedCurrencyValues = numberOfCurrencies + localisedCurrencyValues;
	
    String localisedValues = localisedDateValues + " " + localisedCurrencyValues;
    
	return localisedValues;
}

}