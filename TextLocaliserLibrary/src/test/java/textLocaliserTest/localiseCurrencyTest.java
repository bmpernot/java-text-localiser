package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

public class localiseCurrencyTest {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}

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
		System.out.println("localise currency");
	}

}
