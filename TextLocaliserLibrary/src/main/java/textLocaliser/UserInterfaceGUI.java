package textLocaliser;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.Font;
import javax.swing.SwingConstants;

import textLocaliser.UserInterface;


public class UserInterfaceGUI {

	private JFrame frmTextLocaliser;
	private JTextField inputFileText;
	private JTextField outputFileDirectoryText;
	private JTextField outputFileName;

	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterfaceGUI window = new UserInterfaceGUI();
					window.frmTextLocaliser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserInterfaceGUI() {
		initialise();
	}

	private void initialise() {
		frmTextLocaliser = new JFrame();
		frmTextLocaliser.setTitle("Text Localiser");
		frmTextLocaliser.setBounds(100, 100, 500, 350);
		frmTextLocaliser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextLocaliser.getContentPane().setLayout(null);
		
		String[] boxOptions = {"", "United States of America", "United Kingdom", "Germany"};
		
		JComboBox<String> inputCountry = new JComboBox<>(boxOptions);
		inputCountry.setBounds(255, 25, 180, 21);
		frmTextLocaliser.getContentPane().add(inputCountry);
		
		JComboBox<String> outputCountry = new JComboBox<>(boxOptions);
		outputCountry.setBounds(255, 66, 180, 21);
		frmTextLocaliser.getContentPane().add(outputCountry);
		
		JLabel inputCountryLabel = new JLabel("Please select an input country");
		inputCountryLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		inputCountryLabel.setBounds(35, 29, 210, 21);
		frmTextLocaliser.getContentPane().add(inputCountryLabel);
		
		JLabel outputCountryLabel = new JLabel("Please select an output country");
		outputCountryLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		outputCountryLabel.setBounds(35, 68, 221, 21);
		frmTextLocaliser.getContentPane().add(outputCountryLabel);
		
		JButton browseInputFile = new JButton("Browse");
		browseInputFile.setVerticalAlignment(SwingConstants.TOP);
		browseInputFile.setFont(new Font("Calibri", Font.PLAIN, 14));
		browseInputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.setFileFilter(filter);

				int returnValue = fileChooser.showOpenDialog(null);
				String inputFilePath = null;

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					inputFilePath = selectedFile.getAbsolutePath();
				}
				inputFileText.setText(inputFilePath);			
			}
		});
		
		browseInputFile.setBounds(391, 126, 85, 21);
		frmTextLocaliser.getContentPane().add(browseInputFile);
		
		JButton browseOutputFileLocation = new JButton("Browse");
		browseOutputFileLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Choose a directory to save your file: ");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				String outputFileDirectory = null;
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isDirectory()) {
						outputFileDirectory = jfc.getSelectedFile().toString();
					}
					outputFileDirectoryText.setText(outputFileDirectory);
				}
			}
		});
		browseOutputFileLocation.setVerticalAlignment(SwingConstants.TOP);
		browseOutputFileLocation.setFont(new Font("Calibri", Font.PLAIN, 14));
		browseOutputFileLocation.setBounds(391, 187, 85, 21);
		frmTextLocaliser.getContentPane().add(browseOutputFileLocation);
		
		inputFileText = new JTextField();
		inputFileText.setBounds(10, 128, 368, 19);
		frmTextLocaliser.getContentPane().add(inputFileText);
		inputFileText.setColumns(10);
		inputFileText.setEnabled(false);
		
		outputFileDirectoryText = new JTextField();
		outputFileDirectoryText.setColumns(10);
		outputFileDirectoryText.setBounds(10, 189, 368, 19);
		frmTextLocaliser.getContentPane().add(outputFileDirectoryText);
		outputFileDirectoryText.setEnabled(false);
		
		JLabel inputFileLabel = new JLabel("Please select an input file");
		inputFileLabel.setVerticalAlignment(SwingConstants.TOP);
		inputFileLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		inputFileLabel.setBounds(10, 108, 170, 21);
		frmTextLocaliser.getContentPane().add(inputFileLabel);
		
		JLabel outputFileLocationLabel = new JLabel("Please select a directory for the output file");
		outputFileLocationLabel.setVerticalAlignment(SwingConstants.TOP);
		outputFileLocationLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		outputFileLocationLabel.setBounds(10, 170, 286, 20);
		frmTextLocaliser.getContentPane().add(outputFileLocationLabel);

		outputFileName = new JTextField();
		outputFileName.setBounds(10, 250, 368, 19);
		frmTextLocaliser.getContentPane().add(outputFileName);
		outputFileName.setColumns(10);
		
		JLabel outputFileNameLabel = new JLabel("Please name the output file");
		outputFileNameLabel.setVerticalAlignment(SwingConstants.TOP);
		outputFileNameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		outputFileNameLabel.setBounds(10, 230, 286, 20);
		frmTextLocaliser.getContentPane().add(outputFileNameLabel);
		
		JButton finish = new JButton("Finish");
		finish.setVerticalAlignment(SwingConstants.TOP);
		finish.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		finish.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e) {
				
				boolean inputFileExists = false;
				boolean outputFileExists = true;
				
				
				File inputFile = new File(inputFileText.getText());
				
			    if (inputFile.exists() == true) {
			    	inputFileExists = true;
			    }
			    
			    File outputFile = new File(outputFileDirectoryText.getText() + "\\" + outputFileName.getText() + ".txt");
			    
			    if (outputFile.exists() == false) {
			    	outputFileExists = false;
			    }
				
				if (inputCountry.getSelectedItem().equals("")){
					JOptionPane.showMessageDialog(null, "Please fill out the input country correctly before continuing");
				}
				else if (outputCountry.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out the output country correctly before continuing");
				}
				else if (inputFileExists == false) {
					JOptionPane.showMessageDialog(null, "This file does not exist");
				}
				else if (outputFileExists == true) {
					JOptionPane.showMessageDialog(null, "This file already exists");
				}
				else if (("//\\?%*:|<>.\"").equals(outputFileDirectoryText.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter a valid output file name");
				}
				else if (inputCountry.getSelectedItem().equals(outputCountry.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "Input country and output country are equal");
				}
				else {
					String inputCountryValue = inputCountry.getSelectedItem().toString();
					String outputCountryValue = outputCountry.getSelectedItem().toString();
					String inputFileValue = inputFileText.getText();
					String outputFileValue = outputFileDirectoryText.getText() + "\\" + outputFileName.getText() + ".txt";
					
					UserInterface asdf = new UserInterface();
					asdf.main(inputCountryValue, outputCountryValue, inputFileValue, outputFileValue);
					
					System.exit(0);
				}
			}
		});
		
		finish.setBounds(391, 282, 85, 21);
		frmTextLocaliser.getContentPane().add(finish);
		
	}
}
