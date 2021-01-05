package com.hakmesb;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class FinestraApplicativo {

	DbManager dbmanager;
	AppEventHandler eventhandler;
	Vector<Cliente> vectorClienti;

	private JFrame frmApplicativoManagerClienti;
	private JTextField textFieldCognome;
	private JTextField textFieldNome;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldCodiceFiscale;
	private final ButtonGroup buttonGroupSesso = new ButtonGroup();
	private JTextField textFieldCercaCliente;
	private JComboBox<IDDescrizione> comboBoxPaese;
	private JComboBox<IDDescrizione> comboBoxPaeseDiNascita;
	private JComboBox<IDDescrizione> comboBoxCitta;
	private JRadioButton rdbtnMaschio;
	private JRadioButton rdbtnFemmina;
	private JDateChooser dateChooserDataDiNascita;
	private JList<Cliente> listClienti;
	private JButton btnAggiungi;
	private JButton btnAggiorna;
	private JButton btnElimina;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JToolBar toolBar;
	private JLabel lblDateAndTime;

	public FinestraApplicativo(DbLogin dbLogin) {
		dbmanager = new DbManager(this, dbLogin);
		eventhandler = new AppEventHandler(this, dbmanager);
		initialize();
	}

	private void initialize() {
		frmApplicativoManagerClienti = new JFrame();
		frmApplicativoManagerClienti.setTitle("Applicativo manager clienti");
		frmApplicativoManagerClienti.setBounds(100, 100, 800, 600);
		frmApplicativoManagerClienti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApplicativoManagerClienti.setResizable(false);
		frmApplicativoManagerClienti.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Informazioni cliente");
		lblNewLabel.setBounds(7, 11, 767, 41);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel = new JPanel();
		panel.setBounds(7, 63, 767, 357);
		panel.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(100, 23, 164, 20);
		textFieldCognome.setColumns(10);
		panel.add(textFieldCognome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(370, 23, 164, 20);
		textFieldNome.setColumns(10);
		panel.add(textFieldNome);

		Vector<IDDescrizione> listaPaesi = dbmanager.getPaesi();
		comboBoxPaese = new JComboBox<IDDescrizione>(listaPaesi);
		comboBoxPaese.setBounds(432, 139, 125, 22);
		comboBoxPaese.setSelectedItem(null);
		comboBoxPaese.addItemListener(eventhandler);
		panel.add(comboBoxPaese);

		comboBoxPaeseDiNascita = new JComboBox<IDDescrizione>(listaPaesi);
		comboBoxPaeseDiNascita.setBounds(150, 208, 125, 22);
		comboBoxPaeseDiNascita.setSelectedItem(null);
		panel.add(comboBoxPaeseDiNascita);

		comboBoxCitta = new JComboBox<IDDescrizione>();
		comboBoxCitta.setBounds(252, 139, 125, 22);
		comboBoxCitta.setEnabled(false);
		comboBoxCitta.setModel(new DefaultComboBoxModel<IDDescrizione>());
		panel.add(comboBoxCitta);

		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(72, 140, 125, 20);
		textFieldIndirizzo.setColumns(10);
		panel.add(textFieldIndirizzo);

		rdbtnMaschio = new JRadioButton("Maschio");
		rdbtnMaschio.setFocusPainted(false);
		buttonGroupSesso.add(rdbtnMaschio);
		rdbtnMaschio.setBounds(72, 83, 203, 23);
		panel.add(rdbtnMaschio);

		rdbtnFemmina = new JRadioButton("Femmina");
		rdbtnFemmina.setFocusPainted(false);
		buttonGroupSesso.add(rdbtnFemmina);
		rdbtnFemmina.setBounds(283, 83, 280, 23);
		panel.add(rdbtnFemmina);

		dateChooserDataDiNascita = new JDateChooser();
		dateChooserDataDiNascita.setBounds(150, 271, 106, 49);
		panel.add(dateChooserDataDiNascita);

		textFieldCodiceFiscale = new JTextField();
		textFieldCodiceFiscale.setBounds(432, 284, 86, 20);
		textFieldCodiceFiscale.setColumns(10);
		panel.add(textFieldCodiceFiscale);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(567, 11, 187, 335);
		panel_2.setBorder(new TitledBorder(null, "Cerca cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		textFieldCercaCliente = new JTextField();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textFieldCercaCliente, 10, SpringLayout.NORTH, panel_2);
		textFieldCercaCliente.setColumns(10);
		panel_2.add(textFieldCercaCliente);

		vectorClienti = dbmanager.getClienti();

		listClienti = new JList<>(vectorClienti);
		listClienti.setCellRenderer(new DefaultListCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(listClienti, value, index, isSelected,
						cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Cliente) {
					((JLabel) renderer)
							.setText(((Cliente) value).getCognomeCliente() + " " + ((Cliente) value).getNomeCliente());
				}
				return renderer;
			}
		});
		listClienti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listClienti.addListSelectionListener(eventhandler);
		listClienti.setBounds(10, 61, 167, 225);

		JScrollPane scrollPane = new JScrollPane();
		sl_panel_2.putConstraint(SpringLayout.WEST, textFieldCercaCliente, 0, SpringLayout.WEST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.EAST, textFieldCercaCliente, 0, SpringLayout.EAST, scrollPane);
		sl_panel_2.putConstraint(SpringLayout.WEST, scrollPane, 4, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, scrollPane, -6, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.NORTH, scrollPane, 46, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, panel_2);
		scrollPane.setViewportView(listClienti);
		listClienti.setLayoutOrientation(JList.VERTICAL);
		panel_2.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setBounds(13, 26, 77, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(283, 26, 77, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Sesso");
		lblNewLabel_3.setBounds(13, 88, 45, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Indirizzo");
		lblNewLabel_4.setBounds(13, 143, 77, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Paese di nascita");
		lblNewLabel_5.setBounds(13, 212, 98, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Codice fiscale");
		lblNewLabel_6.setBounds(309, 287, 98, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Citta");
		lblNewLabel_7.setBounds(207, 143, 35, 14);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Paese");
		lblNewLabel_8.setBounds(387, 143, 45, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Data di nascita");
		lblNewLabel_9.setBounds(13, 287, 98, 14);
		panel.add(lblNewLabel_9);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(7, 431, 767, 80);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmApplicativoManagerClienti.getContentPane().setLayout(null);
		frmApplicativoManagerClienti.getContentPane().add(lblNewLabel);
		frmApplicativoManagerClienti.getContentPane().add(panel);
		frmApplicativoManagerClienti.getContentPane().add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setFocusPainted(false);
		btnAggiungi.addActionListener(eventhandler);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnAggiungi, 104, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnAggiungi, -12, SpringLayout.SOUTH, panel_1);
		panel_1.add(btnAggiungi);

		btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.setFocusPainted(false);
		btnAggiorna.addActionListener(eventhandler);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnAggiungi, 0, SpringLayout.NORTH, btnAggiorna);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnAggiungi, -106, SpringLayout.WEST, btnAggiorna);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnAggiorna, 430, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnAggiorna, 62, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnAggiorna, 320, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnAggiorna, 7, SpringLayout.NORTH, panel_1);
		panel_1.add(btnAggiorna);

		btnElimina = new JButton("Elimina");
		btnElimina.setFocusPainted(false);
		btnElimina.addActionListener(eventhandler);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnElimina, 0, SpringLayout.NORTH, btnAggiungi);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnElimina, 106, SpringLayout.EAST, btnAggiorna);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnElimina, 0, SpringLayout.SOUTH, btnAggiungi);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnElimina, -115, SpringLayout.EAST, panel_1);
		panel_1.add(btnElimina);
		
		toolBar = new JToolBar();
		toolBar.setBounds(0, 522, 794, 16);
		frmApplicativoManagerClienti.getContentPane().add(toolBar);
		
		lblDateAndTime = new JLabel("");
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				lblDateAndTime.setText(ZonedDateTime.now().format( DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z")));
			}
		};
		new Timer(1000, al).start();
		toolBar.add(lblDateAndTime);
		
		menuBar = new JMenuBar();
		frmApplicativoManagerClienti.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("About the developer");
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem_1);

	}

	public JFrame getFrmApplicativoManagerClienti() {
		return frmApplicativoManagerClienti;
	}

	public JTextField getTextFieldCognome() {
		return textFieldCognome;
	}

	public JTextField getTextFieldNome() {
		return textFieldNome;
	}

	public JTextField getTextFieldIndirizzo() {
		return textFieldIndirizzo;
	}

	public JTextField getTextFieldCodiceFiscale() {
		return textFieldCodiceFiscale;
	}

	public ButtonGroup getButtonGroupSesso() {
		return buttonGroupSesso;
	}

	public JTextField getTextFieldCercaCliente() {
		return textFieldCercaCliente;
	}

	public JComboBox<IDDescrizione> getComboBoxPaese() {
		return comboBoxPaese;
	}

	public JComboBox<IDDescrizione> getComboBoxPaeseDiNascita() {
		return comboBoxPaeseDiNascita;
	}

	public JComboBox<IDDescrizione> getComboBoxCitta() {
		return comboBoxCitta;
	}

	public JRadioButton getRdbtnMaschio() {
		return rdbtnMaschio;
	}

	public JRadioButton getRdbtnFemmina() {
		return rdbtnFemmina;
	}

	public JDateChooser getDateChooserDataDiNascita() {
		return dateChooserDataDiNascita;
	}

	public JList<Cliente> getListClienti() {
		return listClienti;
	}

	public JButton getBtnAggiungi() {
		return btnAggiungi;
	}

	public JButton getBtnAggiorna() {
		return btnAggiorna;
	}

	public JButton getBtnElimina() {
		return btnElimina;
	}
}
