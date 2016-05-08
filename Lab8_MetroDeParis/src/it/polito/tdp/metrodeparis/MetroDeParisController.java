/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	private Model model;
	private List<String> fermate;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="choisPart"
    private ChoiceBox<String> choisPart; // Value injected by FXMLLoader

    @FXML // fx:id="choisArrive"
    private ChoiceBox<String> choisArrive; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void findPath(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert choisPart != null : "fx:id=\"choisPart\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert choisArrive != null : "fx:id=\"choisArrive\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
       
        model = new Model();
        fermate = model.getListNomeFermata();
        
        choisArrive.getItems().addAll(fermate);
        choisPart.getItems().addAll(fermate);
    }
    

}
