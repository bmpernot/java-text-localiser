package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LocaliseDateTest {

	@Test
	public void test() {
		System.out.println("Testing the localise date method");
		String inputFormat = "US";
		String outputFormat = "UK";
		String inputText = "05/24/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "24/05/2020";
		String result = instance.localiseDate(inputFormat, outputFormat, inputText);
		assertEquals(expectedResult, result);
	}

}
