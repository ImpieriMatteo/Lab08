package it.polito.tdp.extflightdelays.model;

import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	
	public void creaGrafo(float minMediumDistance) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		Map<Integer, Airport> idMap = dao.loadAllAirports();
		
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		/*
		List<Route> route = dao.getRoute(idMap);
		boolean flag;
		for(Route r1 : route) {
			flag = true;
			for(Route r2 : route) {
				if(!r1.equals(r2)) {
					if(r1.getPartenza().equals(r2.getArrivo()) && r1.getArrivo().equals(r2.getPartenza()) 
							&& (r1.getDistanzaMedia()+r2.getDistanzaMedia())/2>minMediumDistance) {
						r1.setDistanzaMedia((r1.getDistanzaMedia()+r2.getDistanzaMedia())/2);
						
						this.grafo.addEdge(r1.getPartenza(), r1.getArrivo());
						this.grafo.setEdgeWeight(r1.getPartenza(), r1.getArrivo(), r1.getDistanzaMedia());
						
						flag = false;
						break;
					}
				}
			}
			if(flag && r1.getDistanzaMedia()>minMediumDistance) {
				this.grafo.addEdge(r1.getPartenza(), r1.getArrivo());
				this.grafo.setEdgeWeight(r1.getPartenza(), r1.getArrivo(), r1.getDistanzaMedia());
			}
		}*/
		
		for(Route r : dao.getRoute(idMap)) {
			DefaultWeightedEdge e = this.grafo.getEdge(r.getPartenza(), r.getArrivo());
			if(e==null) {
				if(r.getDistanzaMedia()>minMediumDistance)
					Graphs.addEdgeWithVertices(grafo, r.getPartenza(), r.getArrivo(), r.getDistanzaMedia());
			}
			else {
				double pesoVecchio = this.grafo.getEdgeWeight(e);
				if((pesoVecchio+r.getDistanzaMedia())/2>minMediumDistance) {
					double pesoNuovo = (pesoVecchio + r.getDistanzaMedia())/2;
					this.grafo.setEdgeWeight(e, pesoNuovo);
				}
				else {
					this.grafo.removeEdge(e);
				}
			}
		}
	}
	
	public SimpleWeightedGraph<Airport, DefaultWeightedEdge> getGrafo() {
		return this.grafo;
	}

}
