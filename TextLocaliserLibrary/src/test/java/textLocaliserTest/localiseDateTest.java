package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

public class localiseDateTest {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}

	@Test
	void testMethod() {
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
