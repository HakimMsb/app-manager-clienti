package com.hakmesb;

public class IDDescrizione {
	
	int id;
	String descrizione;
	
	public IDDescrizione(int id, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return descrizione;
	}

}
