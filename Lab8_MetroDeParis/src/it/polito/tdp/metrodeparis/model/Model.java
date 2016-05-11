package it.polito.tdp.metrodeparis.model;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.db.MetroDAO;

public class Model {
	
	private List<Fermata> fermate;
	private List<Linea> linee;
	private List<Connessione> conn;
	private List<FermataSuLinea> ferLinee;
	private DefaultDirectedWeightedGraph<FermataSuLinea, DefaultWeightedEdge> graph = null;
	private MetroDAO dao;
	
	public Model() {
		this.dao = new MetroDAO();
		
	}
	
	public String calcolaPercorso(Fermata fPart, Fermata fArrive) {

		StringBuilder result = new StringBuilder();
		
		DijkstraShortestPath<FermataSuLinea, DefaultWeightedEdge> dijkstra = null;
		List<DefaultWeightedEdge> pathEdgeList = null;
		double weight = Double.MAX_VALUE;
		double pathTempoTotale = 0;
				
		for(FermataSuLinea fp : fPart.getFermateSuLinea()) {
			for(FermataSuLinea fa : fArrive.getFermateSuLinea()) {
				dijkstra = new DijkstraShortestPath<FermataSuLinea, DefaultWeightedEdge>(graph, fp, fa) ;
				// System.out.println(dijkstra.getPath().toString());		
				
				if (dijkstra.getPathLength() < weight) {
					pathEdgeList = dijkstra.getPathEdgeList();
					pathTempoTotale = dijkstra.getPathLength();
				}
			}
		}
							
		double temps = pathTempoTotale + (pathEdgeList.size() -1) * 30;
		
		result.append("Percorso: \n ");
		String suLinea = null;
		
		for (DefaultWeightedEdge edge : pathEdgeList) {
			
			if(suLinea== null){
				suLinea = graph.getEdgeTarget(edge).getLinea().getNome();
				result.append("\nPrendere la linea: " + suLinea + "\n[");
			}	
			
			if(graph.getEdgeTarget(edge).getLinea().getNome() != suLinea) {
				suLinea = graph.getEdgeTarget(edge).getLinea().getNome();
				result.append("]\n\nCambio su Linea: " + suLinea + "\n [");
			}
			
			result.append(graph.getEdgeTarget(edge).getNome()+", ");
		}
			
		result.setLength(result.length()-2);
		result.append("]\n\n Tempo di percorenza stimato : " + formatHour(temps));
		
		// System.out.println(temps);
		
		return result.toString();
	}
	
	public void generaGrafo() {
		
		this.fermate = dao.getListFermata();
		this.linee = dao.getListLinea();
		this.conn = dao.getListConn(fermate, linee);
		this.ferLinee = dao.getListFermataSuLinea(fermate, linee);
		this.graph = new DefaultDirectedWeightedGraph<FermataSuLinea, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			
		Graphs.addAllVertices(graph, ferLinee);
		
		for (Connessione c : conn) {

			double tempo = calcTemps(c.getStazP(),c.getStazA(),c.getLinea());

			// Cerco la fermataSuLinea corretta all'interno della lista delle
			// fermate
			FermataSuLinea fslPartenza = ferLinee.get(ferLinee.indexOf(new FermataSuLinea(c.getStazP(), c.getLinea())));
			FermataSuLinea fslArrivo = ferLinee.get(ferLinee.indexOf(new FermataSuLinea(c.getStazA(), c.getLinea())));

			fermate.get(fermate.indexOf(new Fermata(c.getStazP().getId_fermata()))).addFermataSuLinea(fslPartenza);
			
			if (fslPartenza != null && fslArrivo != null) {
				// Aggiungoun un arco pesato tra le due fermate
				Graphs.addEdge(graph, fslPartenza, fslArrivo, tempo);
			} else {
				System.err.println("Non ho trovato fslPartenza o fslArrivo. Salto alle prossime");
			}
		}
	
		 
		// Aggiungo tutte le connessioni tra tutte le fermateSuLinee della stessa Fermata
			for (Fermata fermata : fermate) {
				
				for (FermataSuLinea fslP : fermata.getFermateSuLinea()) {
					for (FermataSuLinea fslA : fermata.getFermateSuLinea()) {
						if (!fslP.equals(fslA)) {
							Graphs.addEdge(graph, fslP, fslA, fslA.getLinea().getIntervallo() * 60 / 2);
						}
					}
				}
			}	
		System.out.println("Grafo creato: " + graph.vertexSet().size() + " nodi, " + graph.edgeSet().size() + " archi");
	}
	
	public double calcTemps(Fermata p, Fermata a, Linea linea) {
		double s = LatLngTool.distance( new LatLng(p.getCoordX() , p.getCoordY()), new LatLng(a.getCoordX() , a.getCoordY()), LengthUnit. KILOMETER);
		double v = linea.getVelocita() ;
		
		double temp = (s/v)*60*60;
		
		// System.out.println(temp);
		return temp;
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
		
	public List<Fermata> getFermate() {
		return fermate;
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
