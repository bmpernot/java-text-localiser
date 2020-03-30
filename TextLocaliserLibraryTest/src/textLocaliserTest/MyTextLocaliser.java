package textLocaliserTest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTextLocaliser implements TextLocaliser {

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
	int numberOfDates = 0;
	
	String regularExpressionDate = "D[*]";
	Pattern datePattern = Pattern.compile(regularExpressionDate);
	Matcher dateMatch = datePattern.matcher(inputtext);
	
	String dateText = null;
	
	while (dateMatch.find()) {
		dateText = dateMatch.toString();
		
		System.out.println("following dates are in the input file"); 	// DEBUG STATEMENT
		System.out.println(dateText); 	// DEBUG STATEMENT
		
		dateText = dateText.substring(2, (dateText.length()-1));
		
		System.out.println("following dates converted to the format * instead of D[*]"); 	// DEBUG STATEMENT
		System.out.println(dateText); 	// DEBUG STATEMENT
		
		String localDate = localiesDate(inputFormat, outputFormat, dateText);
		
		System.out.println("following string is the converted value"); 	// DEBUG STATEMENT
		System.out.println(localDate); 	// DEBUG STATEMENT
		
		localisedDateValues = localisedDateValues + " " + localDate;
		
		System.out.println("following string are the localised date values"); 	// DEBUG STATEMENT
		System.out.println(localisedDateValues); 	// DEBUG STATEMENT
		
		numberOfDates++;
		
		System.out.println("following interger is the number of dates in the localisedDateValues string"); 	// DEBUG STATEMENT
		System.out.println(numberOfDates); 	// DEBUG STATEMENT
	}
	
	localisedDateValues = numberOfDates + localisedDateValues;
	
	System.out.println("following string is the number of dates followed by localisedDateValues"); 	// DEBUG STATEMENT
	System.out.println(localisedDateValues); 	// DEBUG STATEMENT
	
	
	// break //
	
	
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
    	
    	double inputCurrency = Double.parseDouble(currencyText);
    	
    	System.out.println("following double is the currency without any symbols"); 	// DEBUG STATEMENT
		System.out.println(inputCurrency); 	// DEBUG STATEMENT
		
		String localCurrency = localiseCurrency(inputFormat, outputFormat, inputCurrency);
		
		System.out.println("following string is the localised currency"); 	// DEBUG STATEMENT
		System.out.println(localCurrency); 	// DEBUG STATEMENT
		
		localisedCurrencyValues = localisedCurrencyValues + " " + localCurrency;
		
		System.out.println("following string is all the localised currency"); 	// DEBUG STATEMENT
		System.out.println(localisedCurrencyValues); 	// DEBUG STATEMENT
    	
    }
    
    localisedCurrencyValues = numberOfCurrencies + localisedCurrencyValues;
    
    System.out.println("following string is the number of currencies followed by all the localised currency"); 	// DEBUG STATEMENT
	System.out.println(localisedCurrencyValues); 	// DEBUG STATEMENT
	
    String localisedValues = localisedDateValues + " " + localisedCurrencyValues;
    
    System.out.println("following string is all the localised values"); 	// DEBUG STATEMENT
	System.out.println(localisedValues); 	// DEBUG STATEMENT
    
	return localisedValues;
}

}