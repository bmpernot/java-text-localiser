package textLocaliserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	FindDelimiterTest.class, 
	FindIndexNumberTest.class, 
	InsertStringTest.class, 
	LocaliseCurrencyTest.class,
	LocaliseDateTest.class,
	LocaliseTest.class,
	LoadCurrencyExchangeRateTest.class,
	LoadCurrencyFormatsTest.class,
	LoadCurrencySymbolsLocationTest.class,
	LoadCurrencyRegularExpressionTest.class,
	LoadDateFormatsTest.class,
	LoadDateRegularExpressionTest.class,
	ReaderAndWriterTest.class
	})
public class AllTests {

}
