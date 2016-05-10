package it.polito.tdp.metrodeparis.model;

public class Connessione {

	private int id_conn;
	private Linea linea;
	private Fermata stazP;
	private Fermata stazA;
	
	public Connessione(int id_conn, Linea id_linea, Fermata id_StazP, Fermata id_StazA) {
		super();
		this.id_conn = id_conn;
		this.linea = id_linea;
		this.stazP = id_StazP;
		this.stazA = id_StazA;
	}


	public int getId_conn() {
		return id_conn;
	}



	public void setId_conn(int id_conn) {
		this.id_conn = id_conn;
	}



	public Linea getLinea() {
		return linea;
	}



	public void setLinea(Linea linea) {
		this.linea = linea;
	}



	public Fermata getStazP() {
		return stazP;
	}



	public void setStazP(Fermata stazP) {
		this.stazP = stazP;
	}



	public Fermata getStazA() {
		return stazA;
	}



	public void setStazA(Fermata stazA) {
		this.stazA = stazA;
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
