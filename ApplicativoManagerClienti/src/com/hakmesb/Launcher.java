package com.hakmesb;

import java.awt.EventQueue;

public class Launcher {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraApplicativo window = new FinestraApplicativo();
					window.getFrmApplicativoManagerClienti().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
