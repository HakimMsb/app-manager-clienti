package com.hakmesb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AppEventHandler implements ActionListener, ListSelectionListener, ItemListener {

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
		JButton button = (JButton) event.getSource();
		if (button.getText().equals("Aggiungi"))
			dbmanager.aggiungiCliente();
		if (button.getText().equals("Elimina"))
			dbmanager.eliminaCliente();
		if (button.getText().equals("Aggiorna"))
			dbmanager.aggiornaCliente();
		if (button.getText().equals("Login")) {

			try {
				dbLogin = new DbLogin(loginFrame.getTextFieldUsername().getText(),
						String.valueOf(loginFrame.getPasswordFieldPassword().getPassword()));
				loginFrame.getLoginFrame().setVisible(false);
				SplashScreen.progressBarTimer.start();
			} catch (SQLException e) {
				SplashScreen.ssWindow.setVisible(false);
				JOptionPane.showMessageDialog(loginFrame.getLoginFrame(), "Wrong username or password!");
			}
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
}
