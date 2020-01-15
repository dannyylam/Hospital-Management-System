/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Danny
 * A message box will pop up when you attempt 
 * to login, whether it is successful or not
 */
public class MessagePopup {
    
    public static void display(String title, String message){
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setWidth(300);
        stage.setHeight(100);
        stage.setResizable(false);
        
        Label label = new Label();
        label.setText(message);
        
        Button button = new Button();
        button.setText("Close");
        button.setOnAction(e -> stage.close());
        
        VBox layout = new VBox(5);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
        
        
    }
    
}
