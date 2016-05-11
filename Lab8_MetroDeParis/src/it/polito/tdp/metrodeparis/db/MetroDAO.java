package it.polito.tdp.metrodeparis.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermataSuLinea;
import it.polito.tdp.metrodeparis.model.Linea;

public class MetroDAO {

	public List<Linea> getListLinea() {
		List<Linea> linee = new ArrayList<Linea>();
		String sql = "SELECT* "
				+ "FROM linea";
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				linee.add(new Linea(Integer.parseInt(res.getString("id_linea")),
						res.getString("nome"),
						res.getDouble("velocita"),
						res.getDouble("intervallo"),
						res.getString("colore")));
			}
			
			st.close();
			conn.close();
			
			return linee;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Fermata> getListFermata() {
		List<Fermata> linee = new ArrayList<Fermata>();
		String sql = "SELECT* "
				+ "FROM fermata";
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				linee.add(new Fermata (res.getInt("id_fermata"),
						res.getString("nome"),
						res.getDouble("coordX"),
						res.getDouble("coordY")));
			}
			
			st.close();
			conn.close();
			
			return linee;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<FermataSuLinea> getListFermataSuLinea(List<Fermata> f, List<Linea> l) {
		List<FermataSuLinea> linee = new ArrayList<FermataSuLinea>();
		String sql = "SELECT DISTINCT fermata.id_fermata, linea.id_linea "
				+ "FROM fermata, linea, connessione "
				+ "WHERE (fermata.id_fermata = connessione.id_stazP OR fermata.id_fermata = connessione.id_stazA) "
				+ "AND connessione.id_linea = linea.id_linea ";
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				
				Fermata fermata = f.get(f.indexOf(new Fermata(res.getInt("id_fermata"))));
				Linea linea = l.get(l.indexOf(new Linea(res.getInt("id_linea"))));
				
				linee.add(new FermataSuLinea (fermata, linea));
				
			}
			
			st.close();
			conn.close();
			
			return linee;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Connessione> getListConn(List<Fermata> f, List<Linea> l) {
		List<Connessione> connessioni = new ArrayList<>();
		String sql = "SELECT* "
				+ "FROM connessione";
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				int idLinea = res.getInt("id_linea");
				int idStazP = res.getInt("id_stazP");
				int idStazA = res.getInt("id_stazA");
				
				Linea linea = l.get(l.indexOf(new Linea(idLinea)));
				Fermata stazP = f.get(f.indexOf(new Fermata(idStazP)));
				Fermata stazA = f.get(f.indexOf(new Fermata(idStazA)));
				
				connessioni.add(new Connessione (res.getInt("id_connessione"), linea, stazP, stazA));
			}
			
			st.close();
			conn.close();
			
			return connessioni;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
