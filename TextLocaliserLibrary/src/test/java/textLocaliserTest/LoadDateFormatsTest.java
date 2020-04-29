package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LoadDateFormatsTest {

	@Test
	public void test1() {
		System.out.println("Testing the load date formats method 1");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadDateFormats(Map);
		String key = "UK";
		String expectedResult = "dd/mm/yyyy";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the load date formats method 2");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadDateFormats(Map);
		String key = "US";
		String expectedResult = "mm/dd/yy";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the load date formats method 3");
		MyTextLocaliser instance = new MyTextLocaliser();
		Map<String, String> Map = new HashMap<String, String>();
		instance.loadDateFormats(Map);
		String key = "DE";
		String expectedResult = "yyyy-mm-dd";
		String result = Map.get(key);
		assertEquals(expectedResult, result);
	}

}
