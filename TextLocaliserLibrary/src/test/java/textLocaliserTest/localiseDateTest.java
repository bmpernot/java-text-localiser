package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

class localiseDateTest {

	@Test
	void testMethod() {
		System.out.println("Testing the localise date method");
		String inputFormat = "US";
		String outputFormat = "UK";
		String inputText = "05/24/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "24/05/2020";
		String result = instance.localiesDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}

}
