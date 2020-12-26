package com.hakmesb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class DbManager {

	final FinestraApplicativo finestraApp;
	
	public DbManager (final FinestraApplicativo finestraApp){
		this.finestraApp = finestraApp;
		connect();
	}
	
	Connection con;
	Statement st1, st2, st3, st4, st5, st6, st7, st8, st9;
	PreparedStatement stmt1, stmt2, stmt3, stmt4;
	ResultSet rsCitta, rsPaesi, rsIDIndirizzo, rsClienti, rsIndirizzo;
	IDDescrizione IDDescCitta, IDDescPaese;
	Cliente cliente;

	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/dbClienti", "root", "root");
			st1 = con.createStatement();
			st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			st3 = con.createStatement();
			st5 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			st6 = con.createStatement();
			st7 = con.createStatement();
			st8 = con.createStatement();
			st9 = con.createStatement();
			stmt1 = con.prepareStatement("insert into indirizzi(via, citta, paese)"
					+ " values(?,?,?)");
			stmt2 = con.prepareStatement("insert into clienti(cognome_cliente, nome_cliente, indirizzo"
					+ ", paese_di_nascita, data_di_nascita, codice_fiscale, sesso)"
					+ " values(?,?,?,?,?,?,?)");
						
			rsPaesi = st2.executeQuery("select * from paesi");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<IDDescrizione> getPaesi() {
		ArrayList<IDDescrizione> arraylistpaesi = new ArrayList<>();
		try {
			while(rsPaesi.next()) {
				IDDescPaese = new IDDescrizione(rsPaesi.getInt(1), rsPaesi.getString(2));
				arraylistpaesi.add(IDDescPaese);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Vector<IDDescrizione>(arraylistpaesi);
	}
	
	public Vector<IDDescrizione> getCitta(int idPaese) {
		ArrayList<IDDescrizione> arraylistcitta = new ArrayList<>();
		
		try {
			rsCitta = st3.executeQuery("select * from citta where paese = '" + idPaese + "'");
			while(rsCitta.next()) {
				IDDescCitta = new IDDescrizione(rsCitta.getInt(1), rsCitta.getString(2));
				arraylistcitta.add(IDDescCitta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Vector<IDDescrizione>(arraylistcitta);
	}
	
	public Vector<Cliente> getClienti(){
		ArrayList<Cliente> arrayListClienti = new ArrayList<>();
		try {
			st4 = con.createStatement();
			rsClienti = st4.executeQuery("select * from clienti");
			while(rsClienti.next()) {
				cliente = new Cliente(rsClienti.getInt(1), rsClienti.getString(2), rsClienti.getString(3),
						rsClienti.getInt(4), rsClienti.getInt(5), rsClienti.getDate(6),
						rsClienti.getString(7), rsClienti.getString(8));
					arrayListClienti.add(cliente);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return new Vector<Cliente>(arrayListClienti); 
	}
	
	public void selectCliente() {
		Cliente cliente = finestraApp.getListClienti().getSelectedValue();
		if(cliente == null) {
			return;
		}
		finestraApp.getTextFieldCognome().setText(cliente.getCognomeCliente());
		finestraApp.getTextFieldNome().setText(cliente.getNomeCliente());
        if(cliente.getSesso().equals("M")) {
        	finestraApp.getRdbtnMaschio().setSelected(true);
        }
        if(cliente.getSesso().equals("F")) {
        	finestraApp.getRdbtnFemmina().setSelected(true);
        }
        
        try {
        	rsIndirizzo = st5.executeQuery("select via, citta, paese from indirizzi where id_indirizzo = '"
			+ cliente.getIndirizzo() + "'");
			rsIndirizzo.next();
			finestraApp.getTextFieldIndirizzo().setText(rsIndirizzo.getString(1));
			int n = finestraApp.getComboBoxCitta().getItemCount();
			for (int i = 0; i < n; i++) {
				IDDescrizione citta = finestraApp.getComboBoxCitta().getItemAt(i);       
				if(citta.id == rsIndirizzo.getInt(2)) {
					finestraApp.getComboBoxCitta().setSelectedIndex(i);
				}
			}
		    rsIndirizzo.first();
		    n = finestraApp.getComboBoxPaese().getItemCount();
			for (int i = 0; i < n; i++) {
				IDDescrizione paese = finestraApp.getComboBoxPaese().getItemAt(i);       
				if(paese.id == rsIndirizzo.getInt(3)) {
					finestraApp.getComboBoxPaese().setSelectedIndex(i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        int n = finestraApp.getComboBoxPaeseDiNascita().getItemCount();
        for(int i = 0; i < n; i++) {
        	IDDescrizione paese = finestraApp.getComboBoxPaeseDiNascita().getItemAt(i);
        	if(paese.id == cliente.getPaeseDiNascita()) {
				finestraApp.getComboBoxPaeseDiNascita().setSelectedIndex(i);
			}
        }
        finestraApp.getDateChooserDataDiNascita().setDate(cliente.getDataDiNascita());
        finestraApp.getTextFieldCodiceFiscale().setText(cliente.getCodiceFiscale());
	}
	
	public void aggiungiCliente() {
		String cognome = finestraApp.getTextFieldCognome().getText();
		String nome = finestraApp.getTextFieldNome().getText();
		boolean isMaschio = finestraApp.getRdbtnMaschio().isSelected();
		boolean isFemmina = finestraApp.getRdbtnFemmina().isSelected();
		String indirizzo = finestraApp.getTextFieldIndirizzo().getText();
		IDDescrizione citta = (IDDescrizione)finestraApp.getComboBoxCitta().getSelectedItem();
		IDDescrizione paese = (IDDescrizione)finestraApp.getComboBoxPaese().getSelectedItem();
		IDDescrizione paeseDiNascita = (IDDescrizione)finestraApp.getComboBoxPaeseDiNascita().getSelectedItem();
		java.util.Date dataDiNascita = finestraApp.getDateChooserDataDiNascita().getDate();
		String codiceFiscale = finestraApp.getTextFieldCodiceFiscale().getText();
		
		if(cognome == null || nome == null || (isMaschio == false && isFemmina == false) || indirizzo == null || citta == null ||
				paese == null || paeseDiNascita == null || dataDiNascita == null || codiceFiscale == null) {
			return;
		}
		
		int idCitta = citta.getId();
		int idPaese = paese.getId();
		int idPaeseDiNascita = paeseDiNascita.getId();
		java.sql.Date dataDiNascitaSQL = new java.sql.Date(dataDiNascita.getTime());
		
		try {
			stmt1.setString(1,indirizzo);
			stmt1.setInt(2, idCitta);
			stmt1.setInt(3, idPaese);
			
			stmt1.executeUpdate();
			
			rsIDIndirizzo = st1.executeQuery("select id_indirizzo from indirizzi order by id_indirizzo desc limit 1");
			rsIDIndirizzo.next();
			int idIndirizzo = rsIDIndirizzo.getInt(1);
			
			stmt2.setString(1, cognome);
			stmt2.setString(2, nome);
			stmt2.setInt(3, idIndirizzo);
			stmt2.setInt(4, idPaeseDiNascita);
			stmt2.setDate(5, dataDiNascitaSQL);
			stmt2.setString(6, codiceFiscale);
			
			if(isMaschio) {
				stmt2.setString(7, "M");
			}
			if(isFemmina) {
				stmt2.setString(7, "F");
			}
			
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateClienti(getClienti());
		JOptionPane.showMessageDialog(finestraApp.getFrmApplicativoManagerClienti(), "Aggiunto");
	}
	public void eliminaCliente() {
		Cliente cliente = finestraApp.getListClienti().getSelectedValue();
		if(cliente == null) {
			return;
		}else {
			try {
				st6.executeUpdate("delete from clienti where id_cliente = '" + cliente.getId() + "'");
				st7.executeUpdate("delete from indirizzi where id_indirizzo = '" + cliente.getIndirizzo() +"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		finestraApp.getListClienti().clearSelection();
		updateClienti(getClienti());
	}
	@SuppressWarnings("unchecked")
	public void updateClienti(Vector<Cliente> v) {
		Object obj = finestraApp.getListClienti().getModel();
		DefaultListModel<Cliente> dlm = null; 
		if(obj instanceof DefaultListModel) {
			dlm = (DefaultListModel<Cliente>)obj;
			dlm.removeAllElements();
	        for(int i = 0; i < v.size(); i++) {
	            dlm.add(i, v.elementAt(i));
	        }
	        finestraApp.getListClienti().updateUI();
		}else {
			finestraApp.getListClienti().setModel(new DefaultListModel<Cliente>());
			updateClienti(v);
		}
	}
	public void updateCitta(Vector<IDDescrizione> v) {
		DefaultComboBoxModel<IDDescrizione> dcbm = (DefaultComboBoxModel<IDDescrizione>)finestraApp.getComboBoxCitta().getModel(); 	
		dcbm.removeAllElements();
	    for(int i = 0; i < v.size(); i++) {
	    	dcbm.addElement(v.elementAt(i));
	    }	
	}
	public void aggiornaCliente() {	
		Cliente cliente = finestraApp.getListClienti().getSelectedValue();
		if(cliente == null) {
			return;
		}
		String cognome = finestraApp.getTextFieldCognome().getText();
		String nome = finestraApp.getTextFieldNome().getText();
		boolean isMaschio = finestraApp.getRdbtnMaschio().isSelected();
		boolean isFemmina = finestraApp.getRdbtnFemmina().isSelected();
		String indirizzo = finestraApp.getTextFieldIndirizzo().getText();
		IDDescrizione citta = (IDDescrizione)finestraApp.getComboBoxCitta().getSelectedItem();
		IDDescrizione paese = (IDDescrizione)finestraApp.getComboBoxPaese().getSelectedItem();
		IDDescrizione paeseDiNascita = (IDDescrizione)finestraApp.getComboBoxPaeseDiNascita().getSelectedItem();
		java.util.Date dataDiNascita = finestraApp.getDateChooserDataDiNascita().getDate();
		String codiceFiscale = finestraApp.getTextFieldCodiceFiscale().getText();
		
		if(cognome == null || nome == null || (isMaschio == false && isFemmina == false) || indirizzo == null || citta == null ||
				paese == null || paeseDiNascita == null || dataDiNascita == null || codiceFiscale == null) {
			return;
		}
		
		int idCitta = citta.getId();
		int idPaese = paese.getId();
		int idPaeseDiNascita = paeseDiNascita.getId();
		java.sql.Date dataDiNascitaSQL = new java.sql.Date(dataDiNascita.getTime());
		String sesso = null;
		if(isMaschio) {
			sesso = "M";
		}
		if(isFemmina) {
			sesso = "F";
		}
		
		try {
			st8.executeUpdate("update indirizzi set via = '" + indirizzo + "', citta = '" + idCitta + "', paese = '"
					+ idPaese + "' where id_indirizzo = '" + cliente.getIndirizzo() + "'");
			
			st9.executeUpdate("update clienti set cognome_cliente = '" + cognome + "', nome_cliente = '" + nome + "',"
					+ " paese_di_nascita = '" + idPaeseDiNascita + "', data_di_nascita = '" + dataDiNascitaSQL + "',"
					+ " codice_fiscale = '" + codiceFiscale + "', sesso = '" + sesso + "' where id_cliente = '" + cliente.getId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		updateClienti(getClienti());
	}
}
