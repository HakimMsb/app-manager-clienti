package com.hakmesb;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

class SplashScreen {
	JWindow ssWindow;
	private static JProgressBar progressBar = new JProgressBar();
	private static int count = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
	private static Timer progressBarTimer;
	ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			progressBar.setValue(count);
			if (PROGBAR_MAX == count) {
				progressBarTimer.stop();
				FinestraApplicativo finestraApp = new FinestraApplicativo();
				ssWindow.dispose();
				finestraApp.getFrmApplicativoManagerClienti().setVisible(true);
			}
			count++;

		}
	};
	
	public SplashScreen() {
		initialize();
	}

	private void initialize() {
		ssWindow = new JWindow();
		Container container = ssWindow.getContentPane();

		JPanel panel = new JPanel();
		panel.setBorder(new javax.swing.border.EtchedBorder());
		container.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/com/hakmesb/image/ssImage.png")));
		panel.add(lblNewLabel);
		
		progressBar.setMaximum(PROGBAR_MAX);
		container.add(progressBar, BorderLayout.SOUTH);

		ssWindow.pack();
		ssWindow.setLocationRelativeTo(null);
		ssWindow.setVisible(true);

		startProgressBar();
	}
	
	 private void startProgressBar() {
	        progressBarTimer = new Timer(TIMER_PAUSE, al);
	        progressBarTimer.start();
	    }

	public JWindow getSsWindow() {
		return ssWindow;
	}
}

class SplashScreenFactory{
	public static SplashScreen createSplashScreen() {
		return new SplashScreen();
	}
}