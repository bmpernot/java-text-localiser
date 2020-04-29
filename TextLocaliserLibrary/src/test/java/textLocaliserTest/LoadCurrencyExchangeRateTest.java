package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LoadCurrencyExchangeRateTest {

	@Test
	public void test1() {
		System.out.println("Testing the load currency exchange rate method 1");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "US-UK";
		double expectedResult = 0.77;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test2() {
		System.out.println("Testing the load currency exchange rate method 2");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "US-DE";
		double expectedResult = 0.88;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test3() {
		System.out.println("Testing the load currency exchange rate method 3");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "US-US";
		double expectedResult = 1.00;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test4() {
		System.out.println("Testing the load currency exchange rate method 4");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "UK-US";
		double expectedResult = 1.30;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test5() {
		System.out.println("Testing the load currency exchange rate method 5");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "UK-DE";
		double expectedResult = 1.15;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test6() {
		System.out.println("Testing the load currency exchange rate method 6");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "UK-UK";
		double expectedResult = 1.00;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test7() {
		System.out.println("Testing the load currency exchange rate method 7");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "DE-US";
		double expectedResult = 1.13;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test8() {
		System.out.println("Testing the load currency exchange rate method 8");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "DE-UK";
		double expectedResult = 0.87;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test9() {
		System.out.println("Testing the load currency exchange rate method 9");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, Double> Map = new HashMap<String, Double>();
		instance.loadCurrencyExchangeRate(Map);
		String key = "DE-DE";
		double expectedResult = 1.00;
		double result = Map.get(key);
		assertEquals(expectedResult, result);
	}

}
