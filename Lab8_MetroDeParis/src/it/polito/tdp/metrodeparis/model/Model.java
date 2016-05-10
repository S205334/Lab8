package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.MetroDAO;

public class Model {
	
	private List<Fermata> fermate;
	private List<Linea> linee;
	private List<Connessione> conn;
	private List<FermataSuLinea> ferLinee;
	private DirectedWeightedMultigraph<FermataSuLinea, DefaultWeightedEdge> graph;
	private MetroDAO dao;
	
	public Model() {
		this.dao = new MetroDAO();
		this.ferLinee = new ArrayList<>();
		this.fermate = dao.getListFermata();
		
		generaGrafo();
	}
	
	public DijkstraShortestPath<FermataSuLinea, DefaultWeightedEdge> findPath(String p, String a) {
		DijkstraShortestPath<FermataSuLinea, DefaultWeightedEdge> dijkstra;
		
		for(FermataSuLinea fp : findFermataByNome(p)) {
			for(FermataSuLinea fa : findFermataByNome(a)) {
				dijkstra = new DijkstraShortestPath<FermataSuLinea, DefaultWeightedEdge>(graph, fp, fa) ;
							
				if (dijkstra.getPath() !=null)
					return dijkstra;
			}
		}
		
		return null;
	}
	
	public String calcolaPercorso(String p, String a) {
		
		StringBuilder result = new StringBuilder();
		DijkstraShortestPath<FermataSuLinea, DefaultWeightedEdge> dijkstra = findPath(p,a);		
		GraphPath<FermataSuLinea, DefaultWeightedEdge> path = dijkstra.getPath() ;
		
		if (path==null)
			return null;
		
		List<DefaultWeightedEdge> pathEdgeList = dijkstra.getPathEdgeList();
		double temps = path.getWeight() + (pathEdgeList.size() -1) * 0.3;
		
		result.append("Percorso: \n [ ");
		String suLinea = null;
		
		for (DefaultWeightedEdge edge : pathEdgeList) {
			
			if(graph.getEdgeTarget(edge).getLinea().getNome() != suLinea) {
				suLinea = graph.getEdgeTarget(edge).getLinea().getNome();
				result.append("/nCambio su Linea: " + suLinea + "/n");
			}
			
			result.append(graph.getEdgeTarget(edge).getFermata().getNome()+", ");
		}
			
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
		this.conn = dao.getListConn(fermate, linee);
		
		this.graph = new DirectedWeightedMultigraph<FermataSuLinea, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		for(Connessione c : conn)
			ferLinee.add(new FermataSuLinea(c.getStazP(), c.getLinea()));
		
		Graphs.addAllVertices(graph, ferLinee);

		for(Connessione c : conn) {
			FermataSuLinea stazA = ferLinee.get(ferLinee.indexOf(new FermataSuLinea(c.getStazA(), c.getLinea())));
			FermataSuLinea stazP = ferLinee.get(ferLinee.indexOf(new FermataSuLinea(c.getStazP(), c.getLinea())));
		
			Graphs.addEdge(graph, stazP, stazA, calcTemps(c.getStazP(),c.getStazA(),c.getLinea()));
		}
		// System.out.println(graph.toString());
	}
	
	public double calcTemps(Fermata p, Fermata a, Linea linea) {
		double s = LatLngTool.distance( new LatLng(p.getCoordX() , p.getCoordY()), new LatLng(a.getCoordX() , a.getCoordY()), LengthUnit. KILOMETER);
		double v = linea.getVelocita() ;
		
		double temp = (s/v)*60*60;
		
		// System.out.println(temp);
		return temp;
	}
	

	
	public List<FermataSuLinea> findFermataByNome( String nome) {
		List<FermataSuLinea> fs = new ArrayList<>();
		
		for(FermataSuLinea f : ferLinee) 
			if(f.getFermata().getNome().equals(nome))
				fs.add(f);
		
		return fs;
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
