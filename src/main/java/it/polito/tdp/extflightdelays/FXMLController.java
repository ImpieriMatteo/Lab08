/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	this.txtResult.clear();
    	int x; 
    	try {
    		x = Integer.parseInt(distanzaMinima.getText());
    		
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserisci un numero");
    		return;
    	}
    	
    	this.model.creaGrafo(x);
    	SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo = model.getGrafo();
    	
    	this.txtResult.appendText("Grafo creato con "+grafo.vertexSet().size()+" e "+grafo.edgeSet().size()+" archi\n\n");
    	this.txtResult.appendText("ELENCO ROTTE\n");
    	for(DefaultWeightedEdge e : grafo.edgeSet()) {
    		this.txtResult.appendText(grafo.getEdgeSource(e) + "-" + grafo.getEdgeTarget(e) + ": " +grafo.getEdgeWeight(e) + "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
