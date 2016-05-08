package it.polito.tdp.metrodeparis.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FermataDAO {
	
	public List<String> getListNome() {
		
		List<String> fermate = new ArrayList<String>();
		String sql = "SELECT nome "
				+ "FROM fermata";
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				fermate.add(res.getString("nome"));
			}
			
			st.close();
			conn.close();
			
			return fermate;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
