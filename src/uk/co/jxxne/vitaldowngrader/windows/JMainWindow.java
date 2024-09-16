package uk.co.jxxne.vitaldowngrader.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import uk.co.jxxne.vitaldowngrader.Main;

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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Path tempDirectory;
	
	/**
	 * Create the frame.
	 */
	public JMainWindow() {
		setTitle("fuck you jane");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 292);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
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
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 213, 338, 20);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel_2 = new JLabel("no file selected yet...");
		lblNewLabel_2.setBounds(6, 242, 338, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("select preset/bank");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectVitalFile();
				if(Main.bankOrPresetPath != null) {
					lblNewLabel_2.setText("file selected: " + Main.bankOrPresetPath.getName());
				}
			}
		});
		btnNewButton.setBounds(6, 184, 174, 29);
		contentPane.add(btnNewButton);
		
		JButton btnDowngrade = new JButton("downgrade!!11!");
		btnDowngrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				lblNewLabel_2.setText("checking filetype");
				String extension = "";
				int i = Main.bankOrPresetPath.getName().lastIndexOf('.');
				System.out.println("1");
				if (i > 0) {
				    extension = Main.bankOrPresetPath.getName().substring(i+1).toString();
				}
				
				progressBar.setValue(10);
				
				if(extension.matches("vital")) {
					System.out.println("test");
					lblNewLabel_2.setText(".vital file, no extraction needed!");
					progressBar.setValue(30);
				} else if(extension.matches("vitalbank")) {
					progressBar.setValue(20);
					lblNewLabel_2.setText(".vitalbank file, starting extraction.");
					selectExportDir();
					new File(Main.workingDirectory.getAbsoluteFile() + "/vitaldowngradertemp").mkdirs();
					File tempFolder = new File(Main.workingDirectory.getAbsoluteFile() + "/vitaldowngradertemp");
					File tempZip = new File(tempFolder.getAbsoluteFile() + "/extract.zip");
			        File destDir = new File(tempFolder.getAbsoluteFile() + "/extracted");
			        
					try {
						Files.copy(Main.bankOrPresetPath.toPath(), tempZip.toPath());
						
				        final byte[] buffer = new byte[1024];
				        final ZipInputStream zis = new ZipInputStream(new FileInputStream(tempZip));
				        ZipEntry zipEntry = zis.getNextEntry();
				        while (zipEntry != null) {
				            final File newFile = newFile(destDir, zipEntry);
				            if (zipEntry.isDirectory()) {
				                if (!newFile.isDirectory() && !newFile.mkdirs()) {
				                    throw new IOException("Failed to create directory " + newFile);
				                }
				            } else {
				                File parent = newFile.getParentFile();
				                if (!parent.isDirectory() && !parent.mkdirs()) {
				                    throw new IOException("Failed to create directory " + parent);
				                }

				                final FileOutputStream fos = new FileOutputStream(newFile);
				                int len;
				                while ((len = zis.read(buffer)) > 0) {
				                    fos.write(buffer, 0, len);
				                }
				                fos.close();
				                
				            }
				            zipEntry = zis.getNextEntry();
				        }
				        zis.closeEntry();
				        zis.close();
						progressBar.setValue(50);
				        lblNewLabel_2.setText("extracted! converting files!");
				        
				        
				        Stream<Path> path = Files.walk(Paths.get(destDir.getAbsolutePath()));
				        path = path.filter(var -> var.toString().endsWith(".vital"));
				        path.forEach((v) -> { convertVitalFile(v.toAbsolutePath().toString()); lblNewLabel_2.setText("working on file " + v.toAbsolutePath().getFileName());});      
					FileUtils.deleteDirectory(new File(tempFolder.getAbsolutePath()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			        lblNewLabel_2.setText("finished! ^w^");
					progressBar.setValue(100);
				} else {
					
				}
			}
			
		});
		btnDowngrade.setBounds(182, 184, 162, 29);
		contentPane.add(btnDowngrade);
		
	}
	
	public void convertVitalFile(String path) {
		Path workingFile = Paths.get(path);
		Charset charset = StandardCharsets.UTF_8;
		System.out.println("working on file " + path);
		try {
			String data = new String(Files.readAllBytes(workingFile), charset);
			data = data.replaceAll("\"version\":\"1.5.5", "\"version\":\"1.0.7");
			data = data.replaceAll("\"synth_version\":\"1.5.5", "\"synth_version\":\"1.0.7");
			File editedFile = new File(Main.workingDirectory.getAbsolutePath() + "/" + workingFile.getFileName());
			editedFile.createNewFile();
			FileUtils.write(editedFile, data);
			System.out.println("finished file " + path + ", now at " + editedFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectVitalFile() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("select a .vital file or a .bank file! (folders currently not supported)");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		System.out.println(System.getProperty("os.name"));
		String OS = System.getProperty("os.name").toLowerCase();
		
		if(OS.indexOf("mac") >= 0) {
			fileChooser.setCurrentDirectory(new File("/Users/" + System.getProperty("user.name") + "/Music/Vital/User/Presets"));
		} 
		
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    Main.bankOrPresetPath = new File(selectedFile.getAbsolutePath());	    
		}
	}
	
	public void selectExportDir() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("select a folder to export your converted bank to!");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedDir = fileChooser.getSelectedFile();
		    System.out.println("Selected directory: " + selectedDir.getAbsolutePath());
		    Main.workingDirectory = new File(selectedDir.getAbsolutePath());	    
		}
	}
	
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
