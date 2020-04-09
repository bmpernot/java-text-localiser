package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

class localiseCurrencyTest {

	@Test
	void testMethod() {
		System.out.println("Testing the localise currency method");
		String inputFormat = "US";
		String outputFormat = "UK";
		Double inputText = 102300.46;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "£78,771.35";
		String result = instance.localiseCurrency(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}

}
