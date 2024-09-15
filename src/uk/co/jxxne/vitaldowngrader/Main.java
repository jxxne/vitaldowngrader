package uk.co.jxxne.vitaldowngrader;

import java.awt.EventQueue;

import uk.co.jxxne.vitaldowngrader.windows.JMainWindow;

public class Main {

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
