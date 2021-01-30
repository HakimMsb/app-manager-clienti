package com.hakmesb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AppEventHandler implements ActionListener, ListSelectionListener, ItemListener, KeyListener {

	FinestraApplicativo finestraApp;
	DbManager dbmanager;
	LoginFrame loginFrame;
	DbLogin dbLogin;

	AppEventHandler(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	AppEventHandler(FinestraApplicativo finestraApp, DbManager dbmanager) {
		this.finestraApp = finestraApp;
		this.dbmanager = dbmanager;
	}

	public DbLogin getLoginInstance() {
		return dbLogin;
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (!event.getValueIsAdjusting())
			dbmanager.selectCliente();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		if (actionCommand.equals("Aggiungi"))
			dbmanager.aggiungiCliente();
		if (actionCommand.equals("Elimina"))
			dbmanager.eliminaCliente();
		if (actionCommand.equals("Aggiorna"))
			dbmanager.aggiornaCliente();
		if (actionCommand.equals("Login")) {
			try {
				dbLogin = new DbLogin(loginFrame.getTextFieldUsername().getText(),
						String.valueOf(loginFrame.getPasswordFieldPassword().getPassword()));
				loginFrame.getLoginFrame().setVisible(false);
				SplashScreen.progressBarTimer.start();
			} catch (SQLException e) {
				//SplashScreen.ssWindow.setVisible(false);
				if(e.getErrorCode() == 1045) {
					JOptionPane.showMessageDialog(loginFrame.getLoginFrame(), "Wrong username or password!", "Error", 0);
				}else {
					JOptionPane.showMessageDialog(loginFrame.getLoginFrame(), e.getMessage(), "Error", 0);
				}
			}
		}
		if(actionCommand.equals("About the developer")) {
			JOptionPane.showMessageDialog(finestraApp.getFrmApplicativoManagerClienti(), "Mesbah Abdelhakim, a self-taught java developer!"
					+ "\n E-mail: hakmesb2000@gmail.com", "About the developer", 1);
		}
		if(actionCommand.equals("About the app")) {
			JOptionPane.showMessageDialog(finestraApp.getFrmApplicativoManagerClienti(), "Java application developed with Eclipse"
					+ "Window Builder \n using jdk 14.0.2, swing, jdbc and MySql database.\n Version: 1.0.0", "About the app", 1);
		}
		if(actionCommand.equals("Exit")) {
			System.exit(0);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if(event.getSource() instanceof JCheckBox) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				loginFrame.getPasswordFieldPassword().setEchoChar((char) 0);
			}else {
				loginFrame.getPasswordFieldPassword().setEchoChar('*');
			}
		}
		if(event.getSource() instanceof JComboBox) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				if (!finestraApp.getComboBoxCitta().isEnabled())
					finestraApp.getComboBoxCitta().setEnabled(true);
				dbmanager.updateCitta(
						dbmanager.getCitta(((IDDescrizione) (finestraApp.getComboBoxPaese().getSelectedItem())).getId()));
				finestraApp.getComboBoxCitta().setSelectedItem(null);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		dbmanager.updateClienti(dbmanager.getClienti(((JTextField)event.getSource()).getText()));
	}
}
