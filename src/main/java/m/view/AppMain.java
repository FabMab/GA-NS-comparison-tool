/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Brice
 */
public class AppMain extends Application {
   
 
    @Override
    public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXML.fxml"));
         
        Scene scene = new Scene(root);
        stage.sizeToScene();
        stage.setTitle("Genetic algorithm comparison tool");
        stage.setMaxHeight(500.0);
        stage.setMaxWidth(650.0);
        stage.setMinHeight(400.0);
        stage.setMinWidth(365.0);
        stage.setScene(scene);
        stage.show();
        
    }      
        


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     launch(args);
//        Application.launch(NewFXMain.class, (java.lang.String[])null);
    }
    
}
