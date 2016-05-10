package it.polito.tdp.metrodeparis.model;

public class FermataSuLinea {
	
	private Fermata fermata;
	private Linea linea;
	
	public FermataSuLinea(Fermata fermata, Linea linee) {
		super();
		this.fermata = fermata;
		this.linea = linee;
	}
	
	public FermataSuLinea(Fermata fermata) {
		this.fermata = fermata;
	}
	

	public Fermata getFermata() {
		return fermata;
	}


	public void setFermata(Fermata fermata) {
		this.fermata = fermata;
	}


	public Linea getLinea() {
		return linea;
	}


	public void setLinea(Linea linea) {
		this.linea = linea;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fermata == null) ? 0 : fermata.hashCode());
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
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
		FermataSuLinea other = (FermataSuLinea) obj;
		if (fermata == null) {
			if (other.fermata != null)
				return false;
		} else if (!fermata.equals(other.fermata))
			return false;
		if (linea == null) {
			if (other.linea != null)
				return false;
		} else if (!linea.equals(other.linea))
			return false;
		return true;
	}
	
	
}
