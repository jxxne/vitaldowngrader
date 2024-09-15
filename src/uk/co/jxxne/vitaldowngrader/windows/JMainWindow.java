package uk.co.jxxne.vitaldowngrader.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class JMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JMainWindow() {
		setTitle("fuck you jane");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("vital downgrader!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 338, 31);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		JLabel lblDowngradeYourVital = new JLabel("downgrade your vital presets meow :33");
		lblDowngradeYourVital.setHorizontalAlignment(SwingConstants.CENTER);
		lblDowngradeYourVital.setBounds(6, 36, 338, 16);
		contentPane.add(lblDowngradeYourVital);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("1.5.5 (beta)");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(18, 90, 141, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1 = new JLabel("what version of vital was the preset made in?");
		lblNewLabel_1.setBounds(16, 73, 328, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("earliest version the preset should support?");
		lblNewLabel_1_1.setBounds(16, 125, 328, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("1.0.7");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setBounds(18, 142, 141, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton = new JButton("select preset/bank");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDirectory();
			}
		});
		btnNewButton.setBounds(6, 184, 174, 29);
		contentPane.add(btnNewButton);
		
		JButton btnDowngrade = new JButton("downgrade!!11!");
		btnDowngrade.setBounds(182, 184, 162, 29);
		contentPane.add(btnDowngrade);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 213, 338, 20);
		contentPane.add(progressBar);
	}
	
	public void selectDirectory() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("select a .vital file or a .bank file! (folders currently not supported)");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		System.out.println(System.getProperty("os.name"));
		String OS = System.getProperty("os.name").toLowerCase();
		
		if(OS.indexOf("mac") >= 0) {
			fileChooser.setCurrentDirectory(new File("/Users/" + System.getProperty("user.name") + "/Music/Vital/User/Presets"));
		} 
		else if(OS.indexOf("win") >= 0) {
			fileChooser.setCurrentDirectory(new File("/Users/" + System.getProperty("user.name") + "/Documents/Vital/User/Presets"));
		}
		
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
	}
}
