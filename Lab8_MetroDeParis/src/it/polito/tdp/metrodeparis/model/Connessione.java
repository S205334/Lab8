package it.polito.tdp.metrodeparis.model;

public class Connessione {

	private int id_conn;
	private int id_linea;
	private int id_StazP;
	private int id_StazA;
	
	public Connessione(int id_conn, int id_linea, int id_StazP, int id_StazA) {
		super();
		this.id_conn = id_conn;
		this.id_linea = id_linea;
		this.id_StazP = id_StazP;
		this.id_StazA = id_StazA;
	}
	
	
	public int getId_conn() {
		return id_conn;
	}


	public void setId_conn(int id_conn) {
		this.id_conn = id_conn;
	}


	public int getId_linea() {
		return id_linea;
	}


	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}


	public int getId_StazP() {
		return id_StazP;
	}


	public void setId_StazP(int id_StazP) {
		this.id_StazP = id_StazP;
	}


	public int getId_StazA() {
		return id_StazA;
	}


	public void setId_StazA(int id_StazA) {
		this.id_StazA = id_StazA;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_conn;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (id_conn != other.id_conn)
			return false;
		return true;
	}
	
	
	
}
