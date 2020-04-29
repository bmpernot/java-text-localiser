package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LoadCurrencySymbolsLocationTest {

	@Test
	public void test1() {
		System.out.println("Testing the load currency symbol location method 1");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencySymbolsLocation(Map);
		String key = "UK";
		String expectedResult = "pre";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the load currency symbol location method 2");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencySymbolsLocation(Map);
		String key = "US";
		String expectedResult = "pre";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the load currency symbol location method 3");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadCurrencySymbolsLocation(Map);
		String key = "DE";
		String expectedResult = "post";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}

}
