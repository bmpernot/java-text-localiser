package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class InsertStringTest {

	@Test
	public void test1() {
		System.out.println("Testing the insert string method 1");
		String oldString = "The brown fox jumped over a lazy dog";
		String stringToBeAdded = "quick ";
		int index = 3;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "The quick brown fox jumped over a lazy dog";
		String result = instance.insertString(oldString, stringToBeAdded, index);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the insert string method 2");
		String oldString = "102300.46";
		String stringToBeAdded = ",";
		int index = 2;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "102,300.46";
		String result = instance.insertString(oldString, stringToBeAdded, index);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the insert string method 3");
		String oldString = "2504/2020";
		String stringToBeAdded = "/";
		int index = 1;
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "25/04/2020";
		String result = instance.insertString(oldString, stringToBeAdded, index);
		assertEquals(expectedResult, result);
	}

}
