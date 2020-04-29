package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;


public class LoadCurrencyRegularExpressionTest {

	@Test
	public void test1() {
		System.out.println("Testing the load currency regular expression method 1");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencyRegularExpression(Map);
		String key = "UK";
		String expectedResult = "\\£(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d{2})?";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the load currency regular expression method 2");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencyRegularExpression(Map);
		String key = "US";
		String expectedResult = "\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d{2})?";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the load currency regular expression method 3");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencyRegularExpression(Map);
		String key = "DE";
		String expectedResult = "(([1-9]\\d{0,2}(\\.\\d{3})*)|(([1-9]\\d*)?\\d))(,\\d{2})?\\€";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}

}
