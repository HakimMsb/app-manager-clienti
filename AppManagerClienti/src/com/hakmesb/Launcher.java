package com.hakmesb;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreenFactory.createSplashScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
