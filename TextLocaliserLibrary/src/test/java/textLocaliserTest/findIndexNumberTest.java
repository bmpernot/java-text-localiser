package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class FindIndexNumberTest {

	@Test
	public void test() {
		System.out.println("Testing the find index number method");
		String string = "abcdefghijklmnopqrstuvwxyz";
		String test [] = string.split("");
		MyTextLocaliser instance = new MyTextLocaliser();
		int expectedResult = 24;
		int result = instance.findIndexNumber(test, "y");
		assertEquals(expectedResult, result);
	}

}
