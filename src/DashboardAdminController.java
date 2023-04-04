/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardAdminController implements Initializable {

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
    private Button BTNmanage;
    @FXML
    private Button BTNaddflight;
    @FXML
    private Label totaluser;
    @FXML
    private Label totaltickets;
    @FXML
    private Label totalrev;
    @FXML
    private Label totalasked;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        totaluser.setText(Main.db.checkTotalUsers());
        totaltickets.setText(Main.db.checkTotalTickets());
        totalrev.setText(Main.db.checkTotalRev());
        totalasked.setText(Main.db.checkTotalQuestions());
        BTNmanage.setOnAction(e-> { 
            FXMLLoader loader = new FXMLLoader (getClass().getResource("ManageFlights.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {

            }

            Scene scene = new Scene(root);
            Stage stage = new Stage ();
            
            stage.setTitle("Airline Ticket Management");
            stage.setScene(scene);
            stage.show();
        
        } );
        
        BTNaddflight.setOnAction(e-> { 
            FXMLLoader loader = new FXMLLoader (getClass().getResource("AddFlight.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {

            }

            Scene scene = new Scene(root);
            Stage stage = new Stage ();
            
            stage.setTitle("Airline Ticket Management");
            stage.setScene(scene);
            stage.show();
        
        } );
        
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
