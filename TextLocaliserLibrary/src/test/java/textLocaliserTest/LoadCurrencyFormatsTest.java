package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LoadCurrencyFormatsTest {

	@Test
	public void test1() {
		System.out.println("Testing the load currency formats method 1");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencyFormats(Map);
		String key = "UK";
		String expectedResult = "£";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the load currency formats method 2");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencyFormats(Map);
		String key = "US";
		String expectedResult = "$";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the load currency formats method 3");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencyFormats(Map);
		String key = "DE";
		String expectedResult = "€";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
}
