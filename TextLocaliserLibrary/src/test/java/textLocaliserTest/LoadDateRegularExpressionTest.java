package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LoadDateRegularExpressionTest {

	@Test
	public void test1() {
		System.out.println("Testing the load date regular expression method 1");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadDateRegularExpression(Map);
		String key = "UK";
		String expectedResult = "\\d{2}/\\d{2}/\\d{4}";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test2() {
		System.out.println("Testing the load date regular expression method 2");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadDateRegularExpression(Map);
		String key = "US";
		String expectedResult = "\\d{2}/\\d{2}/\\d{2}";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the load date regular expression method 3");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadDateRegularExpression(Map);
		String key = "DE";
		String expectedResult = "\\d{4}-\\d{2}-\\d{2}";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
}
