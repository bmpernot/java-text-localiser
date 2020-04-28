package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

public class insertStringTest {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}

	@Test
	void testMethod() {
		System.out.println("Testing the insert string method");
		String oldString = "The brown fox jumped over a lazy dog";
		String stringToBeAdded = "quick ";
		int index = 3;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "The quick brown fox jumped over the lazy dog";
		String result = instance.insertString(oldString, stringToBeAdded, index);
		assertEquals(expectedResult, result);
		System.out.println("insert string");
	}

}
