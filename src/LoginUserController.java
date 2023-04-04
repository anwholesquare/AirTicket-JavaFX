/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginUserController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnAdminLogin;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFpass;
    @FXML
    private TextField TFemail1;
    @FXML
    private TextField TFpass1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnAdminLogin.setOnAction(e-> { 
             FXMLLoader loader = new FXMLLoader (getClass().getResource("AdminLogin.fxml"));
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
        
        
        btnLogin.setOnAction(e-> { 
            DB db = Main.db;
             if(db.checkUser(TFemail.getText(), TFpass.getText())) {
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
        
             }else {
                 ButtonType ybtn = new ButtonType("Okay");
                
                Alert a = new Alert(Alert.AlertType.NONE, "Incorrect Password", ybtn);
                a.setTitle("Airstra Alert Box");
                a.setHeaderText("Please try with correct login credentials");
                a.showAndWait().ifPresent(response -> {
                    if (response == ybtn) {
                        TFemail.setText("");
                        TFpass.setText("");
                    }
                });
             }
        
        } );
        
        
        btnRegister.setOnAction(e-> { 
            DB db = Main.db;
            db.insertUser(TFemail1.getText(), TFpass1.getText());
            ButtonType ybtn = new ButtonType("Okay");
            Alert a = new Alert(Alert.AlertType.NONE, "User has been succesfully registered", ybtn);
            a.setTitle("Airstra Alert Box");
            a.setHeaderText("You can log into your account");
            a.showAndWait().ifPresent(response -> {
                if (response == ybtn) {
                    TFemail1.setText("");
                    TFpass1.setText("");
                }
            });   
        } );
        
        
    }    
    
}
