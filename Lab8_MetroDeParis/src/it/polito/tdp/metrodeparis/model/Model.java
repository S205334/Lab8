package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.MetroDAO;

public class Model {
	
	private List<Fermata> fermate;
	private List<Linea> linee;
	private List<Connessione> conn;
	private WeightedMultigraph<Fermata, DefaultWeightedEdge> graph;
	private MetroDAO dao;
	
	public Model() {
		this.dao = new MetroDAO();
		this.fermate = dao.getListFermata();
		
		generaGrafo();
	}
	
	public String calcolaPercorso(String p, String a) {
		StringBuilder result = new StringBuilder();
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dijkstra =
				new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(graph, findFermataByNome(p), findFermataByNome(a)) ;
		
		GraphPath<Fermata, DefaultWeightedEdge> path = dijkstra.getPath() ;
		
		if (path==null)
			return null ;
		
		List<DefaultWeightedEdge> pathEdgeList = dijkstra.getPathEdgeList();
		double temps = path.getWeight() + (pathEdgeList.size() -1) * 0.3;
		
		result.append("Percorso: [ ");
		for (DefaultWeightedEdge edge : pathEdgeList)
			result.append(graph.getEdgeTarget(edge).getNome()+", ");
			
		result.setLength(result.length()-2);
		result.append(" ]\n Tempo di percorenza stimato : " + formatHour(temps));
		// System.out.println(temps);
		
		return result.toString();
	}
	
	private String formatHour(double t) {
		int hh = (int) t / (60*60);
		int mm = (int) t / 60;
		int ss = (int) t - mm*60;
		
		// System.out.println(hh);
		// System.out.println(mm);
		// System.out.println(ss);
	
		return String.format("%02d:%02d:%02d",hh,mm,ss);
	}
	
	private void generaGrafo() {
		this.linee = dao.getListLinea();
		this.conn = dao.getListConn();
		this.graph = new WeightedMultigraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		Graphs.addAllVertices(graph, this.fermate);
		
		for(Connessione c : conn) {
			Fermata stazA = findFermataById(c.getId_StazA());
			Fermata stazP = findFermataById(c.getId_StazP());
		
			Graphs.addEdge(graph, stazA, stazP, calcTemps(stazA,stazP,c.getId_linea()));
		}
		// System.out.println(graph.toString());
	}
	
	public double calcTemps(Fermata p, Fermata a, int linea) {
		double s = LatLngTool.distance( new LatLng(p.getCoordX() , p.getCoordY()), new LatLng(a.getCoordX() , a.getCoordY()), LengthUnit. KILOMETER);
		double v = findLineaById(linea).getVelocita() ;
		
		double temp = (s/v)*60*60;
		
		// System.out.println(temp);
		return temp;
	}
	
	public Fermata findFermataById(int id) {
		for(Fermata f : fermate) 
			if(f.getId_fermata() == id)
				return f;
		
		return null;
				
	}
	
	public Fermata findFermataByNome( String nome) {
		for(Fermata f : fermate) 
			if(f.getNome().equals(nome))
				return f;
		
		return null;
	}
	
	public Linea findLineaById(int id) {
		for(Linea l : linee) 
			if(l.getId_linea() == id)
				return l;
		
		return null;
	}
	public List<String> getListNomeFermata() {
		List<String> nome = new ArrayList<>();
		
		for(Fermata f : fermate)
			nome.add(f.getNome());
		
		return nome;
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
