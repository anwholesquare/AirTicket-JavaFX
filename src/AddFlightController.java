/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class AddFlightController implements Initializable {

    @FXML
    private TextField TFD2;
    @FXML
    private TextField TFD3;
    @FXML
    private TextField TFD4;
    @FXML
    private TextField TFD5;
    @FXML
    private TextField TFD6;
    @FXML
    private TextField TFD7;
    @FXML
    private TextField TFD8;
    @FXML
    private TextField TFD9;
    @FXML
    private DatePicker DPDate;
    @FXML
    private TextField TFCost;
    @FXML
    private Button BTNsearch;
    @FXML
    private TextField TFD1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        BTNsearch.setOnAction (e-> {
            String origin = "DAC";
            String timeString = TFD1.getText() + "@@" + TFD2.getText() + "@@" + TFD3.getText() + "@@" + TFD4.getText() + "@@" + TFD5.getText() + "@@" + TFD6.getText() + "@@" + TFD7.getText() + "@@" + TFD8.getText() + "@@" + TFD9.getText();
            String jDate = DPDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String pCost = TFCost.getText();
            Main.db.insertFlight(origin, timeString, jDate, pCost);
            Stage stage = (Stage) BTNsearch.getScene().getWindow();
            stage.close();
            
        });
    }    
    
}
