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

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserQuestionAdminController implements Initializable {

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
        List<QuestionModel> ol = Main.db.checkAllQuestions();
        for (QuestionModel o : ol) {
            try {
                Node n1 = FXMLLoader.load(getClass().getResource("questionView.fxml"));
                Label LabelQuestion = (Label) n1.lookup("#LabelQuestion");
                LabelQuestion.setText(o.question);
                Label LabelCD = (Label) n1.lookup("#LabelCD");
                LabelCD.setText("created date: " + o.created_date);
                
                Button send = (Button) n1.lookup("#btnSend");
                send.setOnAction (e-> {
                            String recipient =URLEncoder.encode(o.email, StandardCharsets.UTF_8);
                            String subject = URLEncoder.encode("Reply: " + o.question, StandardCharsets.UTF_8);
                            String body = URLEncoder.encode("Write your message here", StandardCharsets.UTF_8);
                            String uriStr = String.format("mailto:%s?subject=%s&body=%s",
                                                          recipient, subject, body);

                            
                    try {
                        Desktop.getDesktop().mail(new URI(uriStr));
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(UserQuestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(UserQuestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
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
        loadList ();
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
