package textLocaliserTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import textLocaliser.MyTextLocaliser;

class insertStringTest {

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
	}

}
