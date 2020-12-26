package com.hakmesb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AppEventHandler implements ActionListener, ListSelectionListener, ItemListener {
	
	FinestraApplicativo finestraApp;
	DbManager dbmanager;
	
	AppEventHandler(DbManager dbmanager, FinestraApplicativo finestraApp){
		this.dbmanager = dbmanager;
		this.finestraApp = finestraApp;
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (!event.getValueIsAdjusting()) dbmanager.selectCliente();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton)event.getSource();
		if(button.getText().equals("Aggiungi"))
			dbmanager.aggiungiCliente();
		if(button.getText().equals("Elimina"))
			dbmanager.eliminaCliente();
		if(button.getText().equals("Aggiorna"))
			dbmanager.aggiornaCliente();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		 if (event.getStateChange() == ItemEvent.SELECTED) {
			 if(!finestraApp.getComboBoxCitta().isEnabled())
				 finestraApp.getComboBoxCitta().setEnabled(true);
			 dbmanager.updateCitta(dbmanager.getCitta(((IDDescrizione) (finestraApp.getComboBoxPaese().getSelectedItem())).getId()));
			 finestraApp.getComboBoxCitta().setSelectedItem(null);
		 }	
	}	
}
