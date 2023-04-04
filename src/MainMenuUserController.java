/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MainMenuUserController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private Button btnCancelTicket;
    @FXML
    private Button btnAskedQuestion;
    @FXML
    private Button btnLogout;
    @FXML
    private Button BTNsearch;
    private ComboBox<String> CBRoute;
    @FXML
    private ComboBox<String> CBTravel;
    @FXML
    private DatePicker DPDate1;
    @FXML
    private ComboBox<String> CBFrom;
    @FXML
    private ComboBox<String> CBTo;
    @FXML
    private ScrollPane OrbitScrollPane;
    @FXML
    private VBox OrbitHolders;
    
    public void loadList (String JourneyDate, String seat_type, String From, String To) {
        System.out.println(JourneyDate); System.out.println(seat_type); System.out.println(From); System.out.println(To);
        OrbitHolders.getChildren().clear();
        
        List<Node> nodeList = new ArrayList<>();
        List<TicketsModel> ol = Main.db.checkAllFlightsToBuy(JourneyDate,seat_type,From,To);
        for (TicketsModel o : ol) {
            try {
                Node n1 = FXMLLoader.load(getClass().getResource("TicketView.fxml"));
                Label LabelFlight = (Label) n1.lookup("#LabelFlight");
                LabelFlight.setText("Flight No. " + o.flight_id);
                Label LabelJourneyDate = (Label) n1.lookup("#LabelJourneyDate");
                LabelJourneyDate.setText("Departing date: " + o.from_date);
                Label LabelRoute = (Label) n1.lookup("#LabelRoute");
                LabelRoute.setText(o.from_place + " to " + o.to_place);
                Label LabelDeparting = (Label) n1.lookup("#LabelDeparting");
                LabelDeparting.setText(o.departing_time);
                Label LabelBoarding = (Label) n1.lookup("#LabelBoarding");
                LabelBoarding.setText(o.boarding_time);
                
                Button view = (Button) n1.lookup("#BTNview");
                
                view.setOnAction (e-> {
                    ButtonType ybtn = new ButtonType("Yes");
                    ButtonType nbtn = new ButtonType("No");
                    Alert a = new Alert(Alert.AlertType.NONE, "You can't undo the action after booking it", ybtn, nbtn);
                    a.setTitle("Airstra Alert Box");
                    a.setHeaderText("Are you really want to book the ticket?");
                    a.showAndWait().ifPresent(response -> {
                        if (response == ybtn) {
                            Main.db.insertTicket(o.flight_id, o.from_place, o.to_place, o.seat_type, o.from_date, o.boarding_time, o.departing_time, o.price);
                            Main.mainClass.setScene("Airline Ticket Management", "CancelTicketsUser.fxml");
                        }
                    });
                });
                nodeList.add(n1);
            }catch (IOException e) {
                
            }
        }
        
        for (Node n: nodeList) {
             OrbitHolders.getChildren().add(n);
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OrbitScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setFitToWidth(true);
        CBFrom.getItems().addAll("DHAKA", "CHATTOGRAM" , "COXS BAZAR" , "SAIDPUR", "SYLHET");
        CBFrom.getSelectionModel().selectFirst();
        
        CBTo.getItems().addAll("DHAKA", "CHATTOGRAM" , "COXS BAZAR" , "SAIDPUR", "SYLHET");
        CBTo.getSelectionModel().selectFirst();
        
        
        CBTravel.getItems().addAll("WINDOW", "SIDE");
        CBTravel.getSelectionModel().selectFirst();
        
        BTNsearch.setOnAction(e-> {
            loadList(DPDate1.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), CBTravel.getValue(), CBFrom.getValue(), CBTo.getValue());
        });
        
        // TODO
        btnMenu.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("MainMenuUser.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {

            }

            Scene scene = new Scene(root);

            Main.MainStage.setTitle("Airline Ticket Management");
            Main.MainStage.setScene(scene);
            Main.MainStage.show();
        
        } );
         
         btnCancelTicket.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("CancelTicketsUser.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {

            }

            Scene scene = new Scene(root);

            Main.MainStage.setTitle("Airline Ticket Management");
            Main.MainStage.setScene(scene);
            Main.MainStage.show();
        
        } );
         
          btnAskedQuestion.setOnAction(e-> { 
            FXMLLoader loader = new FXMLLoader (getClass().getResource("AskQuestionUser.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {

            }

            Scene scene = new Scene(root);

            Main.MainStage.setTitle("Airline Ticket Management");
            Main.MainStage.setScene(scene);
            Main.MainStage.show();
        
        } );
          
          btnLogout.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("LoginUser.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {

            }

            Scene scene = new Scene(root);

            Main.MainStage.setTitle("Airline Ticket Management");
            Main.MainStage.setScene(scene);
            Main.MainStage.show();
        
        } );
         
         
    }    
    
}
