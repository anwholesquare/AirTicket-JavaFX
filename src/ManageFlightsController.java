/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anan
 */
public class ManageFlightsController implements Initializable {

    @FXML
    private ScrollPane OrbitScrollPane;
    @FXML
    private VBox OrbitHolders;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrbitScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setFitToWidth(true);
        List<Node> nodeList = new ArrayList<>();
        List<FlightsModel> ol = Main.db.checkAllFlights();
        for (FlightsModel o : ol) {
            try {
                Node n1 = FXMLLoader.load(getClass().getResource("FlightsView.fxml"));
                Label fid = (Label) n1.lookup("#fid");
                fid.setText("Flight ID: " + o.id);
                Label forigin = (Label) n1.lookup("#forigin");
                forigin.setText("Origin: DAC | " + o.timeString[0]  );
                Label fdate = (Label) n1.lookup("#fdate");
                fdate.setText("starts at " + o.journeyDate);
                Button view = (Button) n1.lookup("#BTNview");
                
                view.setOnAction (e-> {
                    ButtonType ybtn = new ButtonType("Okay");
                
                    Alert a = new Alert(Alert.AlertType.NONE, "Flight Details", ybtn);
                    a.setTitle("Airstra Alert Box");
                    a.setHeaderText("DAC: " + o.timeString[0] + "\n" + 
                            "DAC: " + o.timeString[0] + "\n" +
                            "CGP: " + o.timeString[1] + "\n" +
                            "CXP: " + o.timeString[2] + "\n" +
                            "SPD: " + o.timeString[3] + "\n" +
                            "ZYL: " + o.timeString[4] + "\n" +
                            "SPD: " + o.timeString[5] + "\n" +
                            "CXB: " + o.timeString[6] + "\n" +
                            "CGP: " + o.timeString[7] + "\n" +
                            "DAC: " + o.timeString[8]);
                    a.show();
                });
                nodeList.add(n1);
            }catch (Exception e) {
                
            }
        }
        
        for (Node n: nodeList) {
             OrbitHolders.getChildren().add(n);
        }
    }    
    
}
