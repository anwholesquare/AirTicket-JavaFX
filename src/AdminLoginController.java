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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminLoginController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         btnLogin.setOnAction(e-> { 
             DB db = Main.db;
             if(db.checkadmin(TFemail.getText(), TFpass.getText())) {
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
    }    
    
}
