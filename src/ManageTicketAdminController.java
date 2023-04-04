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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ManageTicketAdminController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnManageUsers;
    @FXML
    private Button btnManageTickets;
    @FXML
    private Button btnUserQuestion;
    @FXML
    private Button btnLogout;
    @FXML
    private ScrollPane OrbitScrollPane;
    @FXML
    private VBox OrbitHolders;

    public void loadList () {
        OrbitHolders.getChildren().clear();
        
        List<Node> nodeList = new ArrayList<>();
        List<TicketsModel> ol = Main.db.checkAllTicketsAdmin();
        for (TicketsModel o : ol) {
            try {
                Node n1 = FXMLLoader.load(getClass().getResource("TicketBuyAdminView.fxml"));
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
                
                Label LabelEmail = (Label) n1.lookup("#LabelEmail");
                LabelEmail.setText("Email: " + o.user_id);
                
                Label LabelPrice = (Label) n1.lookup("#LabelPrice");
                LabelPrice.setText("Price: " + o.price);
                
                
                        
                
                Label LabelRating = (Label) n1.lookup("#LabelRating");
                LabelRating.setText(o.rating + " stars");
                
                
                
                
                Label LabelTicket = (Label) n1.lookup("#LabelTicket");
                LabelTicket.setText("TICKET ID: " + o.ticket_id);

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
        btnDashboard.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("DashboardAdmin.fxml"));
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
        
        
        btnManageUsers.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("ManageUserAdmin.fxml"));
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
        
        
        btnManageTickets.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("ManageTicketAdmin.fxml"));
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
        
        btnUserQuestion.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("UserQuestionAdmin.fxml"));
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
