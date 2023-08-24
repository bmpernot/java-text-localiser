package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

class findDelimiterTest {

	@Test
	void testMethod() {
		System.out.println("Testing the find delimiter method");
		String test = "dd/mm/yy";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "/";
		String result = instance.findDelimiter(test);
		assertEquals(expectedResult, result);
	}

}
