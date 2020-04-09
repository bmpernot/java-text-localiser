package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

class findIndexNumberTest {

	@Test
	void testMethod() {
		System.out.println("Testing the find index number method");
		String string = "abcdefghijklmnopqrstuvwxyz";
		String test [] = string.split("");
		MyTextLocaliser instance = new MyTextLocaliser();
		int expectedResult = 24;
		int result = instance.findIndexNumber(test, "y");
		assertEquals(expectedResult, result);
	}

}
