package com.hakmesb;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class LoginFrame {

	AppEventHandler eventhandler;

	private JFrame loginFrame;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JButton btnLogin;

	LoginFrame() {
		eventhandler = new AppEventHandler(this);
		createLoginFrame();
	}

	private void createLoginFrame() {
		loginFrame = new JFrame("Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setBounds(new Rectangle(400, 250));
		loginFrame.setResizable(false);
		loginFrame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		loginFrame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel lblNewLabel = new JLabel("Username:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 42, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 70, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel, -246, SpringLayout.EAST, panel);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 39, SpringLayout.SOUTH, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -246, SpringLayout.EAST, panel);
		panel.add(lblNewLabel_1);

		textFieldUsername = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldUsername, 39, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldUsername, 26, SpringLayout.EAST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, textFieldUsername, -93, SpringLayout.EAST, panel);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setEchoChar('*');
		sl_panel.putConstraint(SpringLayout.NORTH, passwordFieldPassword, -3, SpringLayout.NORTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, passwordFieldPassword, 0, SpringLayout.WEST, textFieldUsername);
		sl_panel.putConstraint(SpringLayout.EAST, passwordFieldPassword, -93, SpringLayout.EAST, panel);
		panel.add(passwordFieldPassword);

		btnLogin = new JButton("Login");
		btnLogin.setFocusPainted(false);
		sl_panel.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, textFieldUsername);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnLogin, -31, SpringLayout.SOUTH, panel);
		btnLogin.addActionListener(eventhandler);
		panel.add(btnLogin);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
		chckbxNewCheckBox.setFocusPainted(false);
		chckbxNewCheckBox.addItemListener(eventhandler);
		sl_panel.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 6, SpringLayout.SOUTH, passwordFieldPassword);
		sl_panel.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 0, SpringLayout.WEST, textFieldUsername);
		panel.add(chckbxNewCheckBox);
	}

	public JFrame getLoginFrame() {
		return loginFrame;
	}

	public JTextField getTextFieldUsername() {
		return textFieldUsername;
	}

	public JPasswordField getPasswordFieldPassword() {
		return passwordFieldPassword;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public AppEventHandler getEventHandlerInstance() {
		return eventhandler;
	}
}
