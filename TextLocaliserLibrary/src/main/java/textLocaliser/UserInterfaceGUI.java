package textLocaliser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;

public class UserInterfaceGUI {

	private JFrame frmTextLocaliser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public UserInterfaceGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextLocaliser = new JFrame();
		frmTextLocaliser.setTitle("Text Localiser");
		frmTextLocaliser.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 15));
		frmTextLocaliser.setBounds(15, 5, 600, 400);
		frmTextLocaliser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextLocaliser.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 10, 241, 25);
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pick the input country format", "US", "UK", "DE"}));
		frmTextLocaliser.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(335, 254, 241, 25);
		comboBox_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Pick the output country format", "US", "UK", "DE"}));
		frmTextLocaliser.getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("Finish");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnNewButton.setBounds(486, 324, 90, 29);
		frmTextLocaliser.getContentPane().add(btnNewButton);
	}
}
