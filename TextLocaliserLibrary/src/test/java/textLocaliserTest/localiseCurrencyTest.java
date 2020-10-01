package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LocaliseCurrencyTest {

	@Test
	public void test1() {
		System.out.println("Testing the localise currency method 1");
		String inputFormat = "US";
		String outputFormat = "UK";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "£78,771.35";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the localise currency method 2");
		String inputFormat = "US";
		String outputFormat = "DE";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "90.024,40€";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the localise currency method 3");
		String inputFormat = "UK";
		String outputFormat = "US";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "\\$132,990.60";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test4() {
		System.out.println("Testing the localise currency method 4");
		String inputFormat = "UK";
		String outputFormat = "DE";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "117.645,53€";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test5() {
		System.out.println("Testing the localise currency method 5");
		String inputFormat = "DE";
		String outputFormat = "UK";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "£89,001.40";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test6() {
		System.out.println("Testing the localise currency method 6");
		String inputFormat = "DE";
		String outputFormat = "US";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "\\$115,599.52";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test7() {
		System.out.println("Testing the localise currency method 7");
		String inputFormat = "UK";
		String outputFormat = "UK";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "£102,300.46";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test8() {
		System.out.println("Testing the localise currency method 8");
		String inputFormat = "US";
		String outputFormat = "US";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "\\$102,300.46";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test9() {
		System.out.println("Testing the localise currency method 9");
		String inputFormat = "DE";
		String outputFormat = "DE";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "102.300,46€";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}

}
