package it.polito.tdp.extflightdelays.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.creaGrafo(3500);
		Graph<Airport, DefaultWeightedEdge> grafo = model.getGrafo();
		
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
		
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			System.out.println(e + " " + grafo.getEdgeWeight(e));
		}

	}

}
