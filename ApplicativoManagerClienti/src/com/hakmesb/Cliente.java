package com.hakmesb;

import java.sql.Date;

public class Cliente {
	private int id;
	private String cognomeCliente;
	private String nomeCliente;
	private int indirizzo;
	private int paeseDiNascita;
	private java.sql.Date dataDiNascita;
	private String codiceFiscale;
	private String sesso;
	
	public Cliente(int id, String cognomeCliente, String nomeCliente, int indirizzo, int paeseDiNascita,
			Date dataDiNascita, String codiceFiscale, String sesso) {
		this.id = id;
		this.cognomeCliente = cognomeCliente;
		this.nomeCliente = nomeCliente;
		this.indirizzo = indirizzo;
		this.paeseDiNascita = paeseDiNascita;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.sesso = sesso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCognomeCliente() {
		return cognomeCliente;
	}

	public void setCognomeCliente(String cognomeCliente) {
		this.cognomeCliente = cognomeCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(int indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getPaeseDiNascita() {
		return paeseDiNascita;
	}

	public void setPaeseDiNascita(int paeseDiNascita) {
		this.paeseDiNascita = paeseDiNascita;
	}

	public java.sql.Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(java.sql.Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

}
