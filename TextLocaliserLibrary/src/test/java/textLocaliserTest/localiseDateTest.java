package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LocaliseDateTest {

	@Test
	public void test1() {
		System.out.println("Testing the localise date method 1");
		String inputFormat = "US";
		String outputFormat = "UK";
		String inputText = "05/24/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "24/05/2020";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}

	@Test
	public void test2() {
		System.out.println("Testing the localise date method 2");
		String inputFormat = "US";
		String outputFormat = "DE";
		String inputText = "05/24/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2020-05-24";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the localise date method 3");
		String inputFormat = "UK";
		String outputFormat = "US";
		String inputText = "24/05/2020";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "05/24/20";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test4() {
		System.out.println("Testing the localise date method 4");
		String inputFormat = "UK";
		String outputFormat = "DE";
		String inputText = "24/05/2020";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2020-05-24";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test5() {
		System.out.println("Testing the localise date method 5");
		String inputFormat = "DE";
		String outputFormat = "UK";
		String inputText = "2020-05-24";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "24/05/2020";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test6() {
		System.out.println("Testing the localise date method 6");
		String inputFormat = "DE";
		String outputFormat = "US";
		String inputText = "2020-05-24";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "05/24/20";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test7() {
		System.out.println("Testing the localise date method 7");
		String inputFormat = "US";
		String outputFormat = "US";
		String inputText = "05/24/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "05/24/20";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test8() {
		System.out.println("Testing the localise date method 8");
		String inputFormat = "UK";
		String outputFormat = "UK";
		String inputText = "24/05/2020";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "24/05/2020";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test9() {
		System.out.println("Testing the localise date method 9");
		String inputFormat = "DE";
		String outputFormat = "DE";
		String inputText = "2020-05-24";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2020-05-24";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
}
