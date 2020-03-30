package textLocaliserTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main (String [] args) {
		
		Scanner userInput = new Scanner(System.in);
		
	    System.out.println("Please enter the country format the file is in: (UK, US, DE)");
	    String inputCountry = userInput.nextLine(); 
	    
	    System.out.println("Please enter the country format that you want the output file to be in: (UK, US, DE)");
	    String outputCountry = userInput.nextLine(); 
	    
	    System.out.println("Please enter the output text file's name:");
	    String outputFileName = userInput.nextLine(); 
	    
	    System.out.println("Please enter the output text file's location:");
	    System.out.println("For example; C:\\Users\\Documents\\Java\\Assessment");
	    String outputFileLocation = userInput.nextLine(); 
	    
	    userInput.close();
	    
	    String outputFile = outputFileLocation + "\\" + outputFileName + ".txt";
		
		TextLocaliser MyConverter = new MyTextLocaliser();
		
		ArrayList<String> fileLines = new ArrayList<String>();
		try {
			File inputFile = new File("C:\\Users\\benpe\\Documents\\Java\\Assessment\\sampleFile.txt");	
					// not going to work as the user can not say what file to read
			Scanner myReader = new Scanner(inputFile);
			
			while (myReader.hasNextLine()) {
				String FileText = myReader.nextLine();
				fileLines.add(FileText);
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occur");
			e.printStackTrace();
		}
		
		String inputText = null;
		int n = 0;
		while (n < fileLines.size()) {
			inputText = inputText + "   " + fileLines.get(n);
			n++;
		}
		
		String localisedValues = MyConverter.localise(inputCountry, outputCountry, inputText);
		
		String localisedValuesArray [] = localisedValues.split(" ");
		
		String numberOfDates = localisedValuesArray[0];
		
		String regularExpressionDate = "D[*]";
		Pattern datePattern = Pattern.compile(regularExpressionDate);
		Matcher dateMatch = datePattern.matcher(inputText);
		
		String dateText = null;
		int x = 0;
		int w = 1;
		ArrayList<String> localDateArray = new ArrayList<String>();
		
		while (w <= Integer.parseInt(numberOfDates)) {
			localDateArray.add(localisedValuesArray[w]);
			w++;
		}
		
		StringBuffer stringBufferDate = new StringBuffer();
		
		while (dateMatch.find()) {
			dateText = "D[" + localDateArray.get(x) + "]";
			x++;
			dateMatch.appendReplacement(stringBufferDate, dateText);
		}
		
		int numberOfCurrency = Integer.parseInt(localisedValuesArray[Integer.parseInt(numberOfDates) + 1]);
		
		String regularExpressionCurrency = "C[*]";
		Pattern currencyPattern = Pattern.compile(regularExpressionCurrency);
		Matcher currencyMatch = currencyPattern.matcher(inputText);
		
		String currencyText = null;
		
		int y = 1;
		int v = w + 2;
		ArrayList<String> localCurrencyArray = new ArrayList<String>();
		
		while (y <= numberOfCurrency) {
			localCurrencyArray.add(localisedValuesArray[v]);
			y++;
		}
		
		StringBuffer stringBufferCurrency = new StringBuffer();
		
		while (dateMatch.find()) {
			currencyText = "C[" + localDateArray.get(y) + "]";
			y++;
			currencyMatch.appendReplacement(stringBufferCurrency, currencyText);
		}
		
		String outputTextArray [] = inputText.split("   ");
		
		try {
		      File outputWriter = new File(outputFile);
		      if (outputWriter.createNewFile()) {
		    	  FileWriter myWriter = new FileWriter(outputFile);
		    	  int z = 0;
		    	  while (z <= outputTextArray.length) {
		    			  myWriter.write(outputTextArray[z]);
		    			  myWriter.write("/n");
		    			  z++;
		    	  }
		          myWriter.close();
		      } 
		      
		      else {
		        System.out.println("File already exists.");
		      }
		    }
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
}
