/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CancelTicketsUserController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private Button btnCancelTicket;
    @FXML
    private Button btnAskedQuestion;
    @FXML
    private Button btnLogout;
    @FXML
    private ScrollPane OrbitScrollPane;
    @FXML
    private VBox OrbitHolders;

    
    
    public void loadList () {
        OrbitHolders.getChildren().clear();
        
        List<Node> nodeList = new ArrayList<>();
        List<TicketsModel> ol = Main.db.checkAllTickets();
        for (TicketsModel o : ol) {
            try {
                Node n1 = FXMLLoader.load(getClass().getResource("TicketBuy.fxml"));
                Label LabelFlight = (Label) n1.lookup("#LabelFlight");
                LabelFlight.setText("Flight No. " + o.flight_id);
                Label LabelJourneyDate = (Label) n1.lookup("#LabelJourneyDate");
                LabelJourneyDate.setText("Departing date: " + o.from_date);
                Label LabelRoute = (Label) n1.lookup("#LabelRoute");
                LabelRoute.setText(o.from_place + " | " + o.to_place);
                Label LabelDeparting = (Label) n1.lookup("#LabelDeparting");
                LabelDeparting.setText(o.departing_time);
                Label LabelBoarding = (Label) n1.lookup("#LabelBoarding");
                LabelBoarding.setText(o.boarding_time);
                
                
                Label LabelTicket = (Label) n1.lookup("#LabelTicket");
                LabelTicket.setText("TICKET ID: " + o.ticket_id);
                
                Button view = (Button) n1.lookup("#BTNrate");
                
                view.setOnAction (e-> {
                    ButtonType btn5 = new ButtonType("5");
                    ButtonType btn4 = new ButtonType("4");
                    ButtonType btn3 = new ButtonType("3");
                    ButtonType btn2 = new ButtonType("2");
                    ButtonType btn1 = new ButtonType("1");
                    Alert a = new Alert(Alert.AlertType.NONE, "You can't undo the action after booking it", btn5, btn4, btn3,btn2,btn1);
                    a.setTitle("Airstra Alert Box");
                    a.setHeaderText("Rate between 1 and 5");
                    a.showAndWait().ifPresent(response -> {
                        if (response == btn5) {
                            Main.db.rateTicket(o.ticket_id, "5");
                        }
                        if (response == btn4) {
                            Main.db.rateTicket(o.ticket_id, "4");
                        }
                        if (response == btn3) {
                            Main.db.rateTicket(o.ticket_id, "3");
                        }
                        if (response == btn2) {
                            Main.db.rateTicket(o.ticket_id, "2");
                        }
                        if (response == btn1) {
                            Main.db.rateTicket(o.ticket_id, "1");
                        }
                    });
                });
                
                
                Button cancel = (Button) n1.lookup("#BTNcancel");
                
                cancel.setOnAction (e-> {
                    ButtonType btn5 = new ButtonType("Yes");
                    ButtonType btn4 = new ButtonType("No");
                    
                    Alert a = new Alert(Alert.AlertType.NONE, "You can't undo the action after booking it", btn5, btn4);
                    a.setTitle("Airstra Alert Box");
                    a.setHeaderText("Are you confirm to delete the ticket?");
                    a.showAndWait().ifPresent(response -> {
                        if (response == btn5) {
                            Main.db.deleteTicket(o.ticket_id);
                            loadList();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrbitScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        OrbitScrollPane.setFitToWidth(true);
        loadList();
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
