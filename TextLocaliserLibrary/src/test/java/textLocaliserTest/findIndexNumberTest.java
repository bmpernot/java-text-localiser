package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class FindIndexNumberTest {

	@Test
	public void test1() {
		System.out.println("Testing the find index number method 1");
		String string = "abcdefghijklmnopqrstuvwxyz";
		String test [] = string.split("");
		MyTextLocaliser instance = new MyTextLocaliser();
		int expectedResult = 24;
		int result = instance.findIndexNumber(test, "y");
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the find index number method 2");
		String string = "Hello World";
		String test [] = string.split("");
		MyTextLocaliser instance = new MyTextLocaliser();
		int expectedResult = 6;
		int result = instance.findIndexNumber(test, "W");
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the find index number method 3");
		String string = "dd/mm/yy";
		String test [] = string.split("/");
		MyTextLocaliser instance = new MyTextLocaliser();
		int expectedResult = 1;
		int result = instance.findIndexNumber(test, "m");
		assertEquals(expectedResult, result);
	}
}
