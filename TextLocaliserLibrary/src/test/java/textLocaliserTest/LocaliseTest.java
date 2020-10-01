package textLocaliserTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import textLocaliser.MyTextLocaliser;

public class LocaliseTest {

	@Test
	public void test1() {
		System.out.println("Testing the localise method 1");
		String inputCountry = "US";
		String outputCountry = "UK";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is $102,300.46" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 01/24/20" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 02/05/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "24/01/2020"
				+ "localisedValuesDelimiter"
				+ "05/02/2020"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "£78,771.35";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test2() {
		System.out.println("Testing the localise method 2");
		String inputCountry = "US";
		String outputCountry = "DE";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is $102,300.46" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 01/24/20" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 02/05/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "2020-01-24"
				+ "localisedValuesDelimiter"
				+ "2020-02-05"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "90.024,40€";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test3() {
		System.out.println("Testing the localise method 3");
		String inputCountry = "US";
		String outputCountry = "US";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is $102,300.46" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 01/24/20" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 02/05/20";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "01/24/20"
				+ "localisedValuesDelimiter"
				+ "02/05/20"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "\\$102,300.46";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test4() {
		System.out.println("Testing the localise method 4");
		String inputCountry = "UK";
		String outputCountry = "US";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is £102,300.46" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 15/03/2020" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 24/04/2020";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "03/15/20"
				+ "localisedValuesDelimiter"
				+ "04/24/20"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "\\$132,990.60";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test5() {
		System.out.println("Testing the localise method 5");
		String inputCountry = "UK";
		String outputCountry = "DE";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is £102,300.46" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 15/03/2020" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 24/04/2020";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "2020-03-15"
				+ "localisedValuesDelimiter"
				+ "2020-04-24"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "117.645,53€";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test6() {
		System.out.println("Testing the localise method 6");
		String inputCountry = "UK";
		String outputCountry = "UK";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is £102,300.46" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 15/03/2020" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 24/04/2020";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "15/03/2020"
				+ "localisedValuesDelimiter"
				+ "24/04/2020"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "£102,300.46";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test7() {
		System.out.println("Testing the localise method 7");
		String inputCountry = "DE";
		String outputCountry = "UK";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is 102.300,46€" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 2020-06-06" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 2020-07-01";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "06/06/2020"
				+ "localisedValuesDelimiter"
				+ "01/07/2020"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "£89,001.40";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test8() {
		System.out.println("Testing the localise method 8");
		String inputCountry = "DE";
		String outputCountry = "US";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is 102.300,46€" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 2020-06-06" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 2020-07-01";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "06/06/20"
				+ "localisedValuesDelimiter"
				+ "07/01/20"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "\\$115,599.52";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void test9() {
		System.out.println("Testing the localise method 9");
		String inputCountry = "DE";
		String outputCountry = "DE";
		String inputText = "Order delivery details below:" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" + 
		"Price of the item ordered is 102.300,46€" + 
				"inputTextDelimiter" + 
		"inputTextDelimiter" + 
				"Order placed on 2020-06-06" + 
		"inputTextDelimiter" + 
				"inputTextDelimiter" +
		"Order is expected to reach on 2020-07-01";
		MyTextLocaliser instance = new MyTextLocaliser();
		String expectedResult = "2"
				+ "localisedValuesDelimiter"
				+ "2020-06-06"
				+ "localisedValuesDelimiter"
				+ "2020-07-01"
				+ "localisedValuesDelimiter"
				+ "1"
				+ "localisedValuesDelimiter"
				+ "102.300,46€";
		String result = instance.localise(inputCountry, outputCountry, inputText);
		assertEquals(expectedResult, result);
	}
	
}
