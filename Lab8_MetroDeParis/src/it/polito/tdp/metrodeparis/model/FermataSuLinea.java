package it.polito.tdp.metrodeparis.model;

public class FermataSuLinea extends Fermata {
	
	private Linea linea;

	public FermataSuLinea(int id_fermata, String nome, double coordX, double coordY, Linea linea) {
		super(id_fermata, nome, coordX, coordY);
		this.linea = linea;
	}

	public FermataSuLinea(Fermata fermata, Linea linea) {
		super(fermata.getId_fermata(), fermata.getNome(), fermata.getCoordX(), fermata.getCoordY());
		this.linea = linea;
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
		int result = super.hashCode();
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FermataSuLinea other = (FermataSuLinea) obj;
		if (linea == null) {
			if (other.linea != null)
				return false;
		} else if (!linea.equals(other.linea))
			return false;
		return true;
	}


	
	
	
	
}
