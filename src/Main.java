/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Main extends Application {
    public static Stage MainStage;
    public static Main mainClass;
    public static DB db;
    @Override
    public void start(Stage primaryStage) {
        db = new DB();
        MainStage =primaryStage;
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

    }
    
    public void setScene (String title, String fxml) {
        
        Stage primaryStage = Main.MainStage;
        primaryStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader (getClass().getResource(fxml));
        Parent root = null; 
        try {
            root = loader.load();
        } catch (IOException ex) { System.out.println(ex.toString());}
        Scene scene = new Scene(root);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
