package it.polito.tdp.metrodeparis.model;

import java.util.List;

import it.polito.tdp.metrodeparis.db.FermataDAO;
import it.polito.tdp.metrodeparis.db.LineaDAO;

public class Model {
	
	public List<String> getListNomeFermata() {
		FermataDAO dao = new FermataDAO();
		return dao.getListNome();
	}
	
	public List<String> getListNomeLinea() {
		LineaDAO dao = new LineaDAO();
		return dao.getListNome();
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
