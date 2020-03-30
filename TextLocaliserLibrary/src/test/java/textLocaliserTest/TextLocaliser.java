package textLocaliserTest;

//import static org.junit.jupiter.api.Assertions.*;		// i don't know how to test
//import org.junit.jupiter.api.Test;		// i don't know how to test

import java.util.Map;

public interface TextLocaliser {
	
	public void loadDateFormats(Map<String, String> dateformats);
	public String localiesDate(String inputFormat, String outputFormat, String inputText);
	
	public void loadCurrencyFormats(Map<String, String> currencyformats);
	public String localiseCurrency(String inputFormat, String outputFormat, double inputText);
	
	public String localise(String inputFormat, String outputFormat, String inputtext);
	
}