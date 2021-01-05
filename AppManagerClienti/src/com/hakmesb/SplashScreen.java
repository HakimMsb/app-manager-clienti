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

}

class SplashScreenFactory {
	public static SplashScreen createSplashScreen() {
		return new SplashScreen();
	}
}