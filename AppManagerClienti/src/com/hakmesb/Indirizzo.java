package com.hakmesb;

public class Indirizzo {

	private int idIndirizzo;
	private String via;
	private int citta;
	private int paese;
	
	public Indirizzo(int idIndirizzo, String via, int citta, int paese) {
		this.idIndirizzo = idIndirizzo;
		this.via = via;
		this.citta = citta;
		this.paese = paese;
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getCitta() {
		return citta;
	}

	public void setCitta(int citta) {
		this.citta = citta;
	}

	public int getPaese() {
		return paese;
	}

	public void setPaese(int paese) {
		this.paese = paese;
	}
	
}
