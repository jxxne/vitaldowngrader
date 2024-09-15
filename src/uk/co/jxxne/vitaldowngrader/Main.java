package uk.co.jxxne.vitaldowngrader;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import uk.co.jxxne.vitaldowngrader.windows.JMainWindow;

public class Main {
	
	public static File bankOrPresetPath;
	public static File workingDirectory;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMainWindow frame = new JMainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

}
