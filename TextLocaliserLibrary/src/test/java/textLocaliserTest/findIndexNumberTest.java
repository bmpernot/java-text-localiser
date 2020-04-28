package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

public class findIndexNumberTest {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}

	@Test
	void testMethod() {
		System.out.println("Testing the find index number method");
		String string = "abcdefghijklmnopqrstuvwxyz";
		String test [] = string.split("");
		MyTextLocaliser instance = new MyTextLocaliser();
		int expectedResult = 24;
		int result = instance.findIndexNumber(test, "y");
		assertEquals(expectedResult, result);
		System.out.println("find index number");
	}

}
