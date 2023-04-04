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
public class ManageUserAdminController implements Initializable {

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
        List<UserModel> ol = Main.db.checkAllUsers();
        for (UserModel o : ol) {
            try {
                Node n1 = FXMLLoader.load(getClass().getResource("UserView.fxml"));
                Label LabelUser = (Label) n1.lookup("#user");
                LabelUser.setText(o.id + ". " + o.email);
                
                Button view = (Button) n1.lookup("#btnDelete");
                
                view.setOnAction (e-> {
                    ButtonType ybtn = new ButtonType("Yes");
                    ButtonType nbtn = new ButtonType("No");
                    Alert a = new Alert(Alert.AlertType.NONE, "You can't undo the action after clicking yes", ybtn, nbtn);
                    a.setTitle("Airstra Alert Box");
                    a.setHeaderText("Are you really want to delete the account?");
                    a.showAndWait().ifPresent(response -> {
                        if (response == ybtn) {
                         Main.db.deleteUser(o.id);
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
    /**
     * Initializes the controller class.
     */
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
