package textLocaliserTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class testRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TextLocaliserSuite.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Is test successful:");
		System.out.println(result.wasSuccessful());
	}

}
