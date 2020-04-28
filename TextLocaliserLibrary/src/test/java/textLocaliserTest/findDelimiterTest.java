package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

public class findDelimiterTest {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}

	@Test
	public void testMethod() {
		System.out.println("Testing the find delimiter method");
		String test = "dd/mm/yy";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "/";
		String result = instance.findDelimiter(test);
		assertEquals(expectedResult, result);
		System.out.println("find delimiter");
	}

}
