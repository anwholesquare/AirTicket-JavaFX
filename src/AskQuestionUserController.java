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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AskQuestionUserController implements Initializable {

    @FXML
    private Button btnMenu;
    @FXML
    private Button btnCancelTicket;
    @FXML
    private Button btnAskedQuestion;
    @FXML
    private Button btnLogout;
    @FXML
    private TextArea TAques;
    @FXML
    private Button BTNsubmit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BTNsubmit.setOnAction(e-> {
            Main.db.insertQuestion(TAques.getText());
            TAques.setText("");
            
        });
        
        
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
