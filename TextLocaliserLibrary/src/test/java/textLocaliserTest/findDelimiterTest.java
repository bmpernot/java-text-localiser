package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class FindDelimiterTest {

	@Test
	public void test1() {
		System.out.println("Testing the find delimiter method 1");
		String test = "dd/mm/yy";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "/";
		String result = instance.findDelimiter(test);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the find delimiter method 2");
		String test = "yyyy-mm-dd";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "-";
		String result = instance.findDelimiter(test);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the find delimiter method 3");
		String test = "mm/dd/yy";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "/";
		String result = instance.findDelimiter(test);
		assertEquals(expectedResult, result);
	}
}
