package textLocaliser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

public class MyTextLocaliser implements TextLocaliser {
	
public void loadCurrencyRegularExpression(Map<String, String> RegEx) {

	RegEx.put("UK", "\\£(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d{2})?");
	RegEx.put("US", "\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d{2})?");
	RegEx.put("DE", "(([1-9]\\d{0,2}(\\.\\d{3})*)|(([1-9]\\d*)?\\d))(,\\d{2})?\\€");
}

public void loadDateRegularExpression(Map<String, String> RegEx) {

	RegEx.put("UK", "\\d{2}/\\d{2}/\\d{4}");
	RegEx.put("US", "\\d{2}/\\d{2}/\\d{2}");
	RegEx.put("DE", "\\d{4}-\\d{2}-\\d{2}");
}
	
public String insertString(String oldString, String stringToBeInserted, int index) {
    String newString = oldString.substring(0, index + 1) + stringToBeInserted + oldString.substring(index + 1); 
    return newString;
}

public void loadCurrencyExchangeRate(Map<String, Double> exchangeRate) {

	exchangeRate.put("UK-DE", 1.15);
	exchangeRate.put("UK-US", 1.30);
	exchangeRate.put("UK-UK", 1.00);
	
	exchangeRate.put("US-UK", 0.77);
	exchangeRate.put("US-DE", 0.88);
	exchangeRate.put("US-US", 1.00);
	
	exchangeRate.put("DE-UK", 0.87);
	exchangeRate.put("DE-US", 1.13);
	exchangeRate.put("DE-DE", 1.00);
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
public String localiseDate(String inputFormat, String outputFormat, String inputText) {
	
	String inputTextDelimiterValue = findDelimiter(inputText);
	
	String inputTextDateFields[] = inputText.split(inputTextDelimiterValue);
	
	Map<String, String> inputMap = new HashMap<String, String>();
	loadDateFormats(inputMap);
	
	String inputDateFormat = inputMap.get(inputFormat);
	
	String inputFormatDelimiterValue = findDelimiter(inputDateFormat);
	
	String inputFormatDateFields[] = inputDateFormat.split(inputFormatDelimiterValue);
	
	Map<String, String> outputMap = new HashMap<String, String>();
	loadDateFormats(outputMap);
	
	String outputDateFormat = outputMap.get(outputFormat);
	
	String outputFormatDelimiterValue = findDelimiter(outputDateFormat);
	
	String outputFormatDateFields[] = outputDateFormat.split(outputFormatDelimiterValue);
		
	Map<String, String> convert = new HashMap<String, String>();
	
	for (int increment = 0; increment < inputFormatDateFields.length; increment++) {
		convert.put(inputFormatDateFields[increment], inputTextDateFields[increment]);
	}
	
	int inputYear = findIndexNumber(inputFormatDateFields, "y");
	
	int outputYear = findIndexNumber(outputFormatDateFields, "y");

	if (outputFormatDateFields[outputYear].equals(inputFormatDateFields[inputYear])) {}
	
	else if (inputFormatDateFields[inputYear].equals("yy")) {
		String yearValue = convert.get(inputFormatDateFields[inputYear]);
		convert.put(outputFormatDateFields[outputYear], "20" + yearValue);
	}
	
	else {
		String yearValue = convert.get(inputFormatDateFields[inputYear]);
		convert.put(outputFormatDateFields[outputYear], yearValue.substring(2));
	}
	
	String localisedDate = convert.get(outputFormatDateFields[0]) + outputFormatDelimiterValue + 
			convert.get(outputFormatDateFields[1]) + outputFormatDelimiterValue + 
			convert.get(outputFormatDateFields[2]);
	
	return localisedDate;
}

@Override
public void loadCurrencyFormats(Map<String, String> currencyformats) {

	currencyformats.put("US", "\\$");	
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
	
	double exchangeRate = 1;
	
	String key = inputFormat + "-" + outputFormat;

	exchangeRate = exchangeRates.get(key);

	double finalValue = inputText*exchangeRate;
	
	DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	String localisedCurrency = decimalFormat.format(finalValue);
	int numberOfSpaces = 0;
	int index = localisedCurrency.length()-7;
	
	if (outputFormat.equals("DE")) {
		localisedCurrency = localisedCurrency.replace(".", ",");
		for (int increment = 1; increment <= ((localisedCurrency.length()-4 - numberOfSpaces)/3); increment++) {
			localisedCurrency = insertString(localisedCurrency, ".", index);
			numberOfSpaces++;
			index = index - 3;
		}
	}
	
	else if(outputFormat.equals("US") || outputFormat.equals("UK")) {
		for (int increment = 1; increment <= ((localisedCurrency.length()-4 - numberOfSpaces)/3); increment++) {	
			localisedCurrency = insertString(localisedCurrency, ",", index);
			numberOfSpaces++;
			index = index - 3;
		}
			
	}
	
	else {}
	
	
	String symbol = symbolMap.get(outputFormat);
	
	String position = location.get(outputFormat);
	
	if (position.equals("pre")) {
		localisedCurrency = symbol + localisedCurrency;
	}
	
	else if (position.equals("post")) {
		localisedCurrency = localisedCurrency + symbol;
	}
	
	else {}
	
	return localisedCurrency;
}

@Override
public String localise(String inputFormat, String outputFormat, String inputtext) {
	
	String localisedDateValues = null;
	int numberOfDates = 0;
	
	Map<String, String> dateRegularExpression = new HashMap<String, String>();
	loadDateRegularExpression(dateRegularExpression);
	
	String regularExpressionDate = dateRegularExpression.get(inputFormat);
	Pattern datePattern = Pattern.compile(regularExpressionDate);
	Matcher dateMatch = datePattern.matcher(inputtext);
	
	String dateText = null;
	
	while (dateMatch.find()) {
		
		int start = dateMatch.start();
		int end = dateMatch.end();
		
		dateText = inputtext.substring(start, end);
		
		String localDate = localiseDate(inputFormat, outputFormat, dateText);
		
		if (numberOfDates == 0) {
			localisedDateValues = localDate;
		}
		
		else {
			localisedDateValues = localisedDateValues + "localisedValuesDelimiter" + localDate;
		}
		
		numberOfDates++;
	}
	
	localisedDateValues = numberOfDates + "localisedValuesDelimiter" + localisedDateValues;
	
	String localisedCurrencyValues = null;
	int numberOfCurrencies = 0;
	
	Map<String, String> currencyRegularExpression = new HashMap<String, String>();
	loadCurrencyRegularExpression(currencyRegularExpression);
	
	String regularExpressionCurrency = currencyRegularExpression.get(inputFormat);
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
    		currencyText = currencyText.substring(1, currencyText.length());
    	}
    	
    	else if (position.equals("post")) {
    		currencyText = currencyText.substring(0, (currencyText.length()-1));
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
		
		String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
		
		if (numberOfCurrencies == 0) {
			localisedCurrencyValues = localCurrency;
		}
		
		else {
			localisedCurrencyValues = localisedCurrencyValues + "localisedValuesDelimiter" + localCurrency;
		}
		
		numberOfCurrencies++;
		
    }   	
    
    localisedCurrencyValues = numberOfCurrencies + "localisedValuesDelimiter" + localisedCurrencyValues;
	
    String localisedValues = localisedDateValues + "localisedValuesDelimiter" + localisedCurrencyValues;
    
	return localisedValues;
}

}