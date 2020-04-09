package textLocaliserTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
	
@Suite.SuiteClasses({
	textLocaliserTest.findDelimiterTest.class,
	textLocaliserTest.findIndexNumberTest.class,
	textLocaliserTest.insertStringTest.class,
	textLocaliserTest.localiseCurrencyTest.class,
	textLocaliserTest.localiseDateTest.class
})

public class TextLocaliserSuite {
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}
}
