package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class InsertStringTest {

	@Test
	public void test() {
		System.out.println("Testing the insert string method");
		String oldString = "The brown fox jumped over a lazy dog";
		String stringToBeAdded = "quick ";
		int index = 3;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "The quick brown fox jumped over a lazy dog";
		String result = instance.insertString(oldString, stringToBeAdded, index);
		assertEquals(expectedResult, result);
	}

}
