package com.hakmesb;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.SwingConstants;

class SplashScreen {

	LoginFrame loginFrame;
	DbLogin dbLogin;
	FinestraApplicativo finestraApp;
	SplashScreen splashscreen = this;

	static JWindow ssWindow;
	private static JProgressBar progressBar = new JProgressBar();
	private static int count = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
	static Timer progressBarTimer;
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			progressBar.setValue(count);
			if (PROGBAR_MAX == count * 2) {
				progressBarTimer.stop();
				loginFrame = new LoginFrame();
				ssWindow.setVisible(false);
				loginFrame.getLoginFrame().setVisible(true);
			}
			if (count == ((PROGBAR_MAX / 2) + 1))
				SplashScreen.ssWindow.setVisible(true);
			if (PROGBAR_MAX == count) {
				progressBarTimer.stop();
				dbLogin = loginFrame.getEventHandlerInstance().getLoginInstance();
				loginFrame.getLoginFrame().dispose();
				finestraApp = new FinestraApplicativo(dbLogin);
				finestraApp.getFrmApplicativoManagerClienti().setVisible(true);

				ssWindow.dispose();

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
		URL imageURL = SplashScreen.class.getResource("/src/com/hakmesb/image/ssImage.png");
		if(imageURL == null)
			imageURL = SplashScreen.class.getResource("/com/hakmesb/image/ssImage.png");
		ImageIcon image = new ImageIcon(imageURL);
		lblNewLabel.setIcon(image);
		panel.add(lblNewLabel);

		progressBar.setMaximum(PROGBAR_MAX);
		container.add(progressBar, BorderLayout.SOUTH);
		
		JLabel appName = new JLabel(getVersionAndNameFromManifest());
		appName.setHorizontalAlignment(SwingConstants.CENTER);
		ssWindow.getContentPane().add(appName, BorderLayout.NORTH);
		
		ssWindow.pack();
		ssWindow.setLocationRelativeTo(null);
		ssWindow.setVisible(true);

		startProgressBar();
	}

	private void startProgressBar() {
		progressBarTimer = new Timer(TIMER_PAUSE, al);
		progressBarTimer.start();
	}
	
	public String getVersionAndNameFromManifest() {
	    InputStream manifestStream = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
	    if (manifestStream != null) {
	        Manifest manifest = null;
			try {
				manifest = new Manifest(manifestStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        Attributes attributes = manifest.getMainAttributes();
	        return attributes.getValue("versionName");
	    }
	    return "";
	}
}

class SplashScreenFactory {
	public static SplashScreen createSplashScreen() {
		return new SplashScreen();
	}
}