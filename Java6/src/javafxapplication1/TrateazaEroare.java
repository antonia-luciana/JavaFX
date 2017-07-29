/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author lucia_000
 */
public class TrateazaEroare {
    
    public void eroare(String mesaj){
        Stage stage = new Stage();
                StackPane radacina = new StackPane();
                Label label = new Label(mesaj);
                label.setFont(Font.font ("Verdana", 20));
                radacina.getChildren().add(label);
                stage.setScene(new Scene(radacina));
                stage.setHeight(150);
                stage.setWidth(500);
                stage.show();
    }
}
