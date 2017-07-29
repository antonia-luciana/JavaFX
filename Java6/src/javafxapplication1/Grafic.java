/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author lucia_000
 */
class Grafic extends Pane {
   // private String culoare="GRAY";
    public ArrayList l = new ArrayList();
    public static List <Pereche> valori = new ArrayList<>();
    /*private Integer nr=0;
    public double xprim;
    public double yprim*/
    
    public Grafic(
            // Function<Double, Double> f,
            //
            
            String functieString,
            double xMin, double xMax, double xInc,
            Coordonate axe,String culoare,double grosime
    ) {
         try{net.objecthunter.exp4j.Expression expresie = new ExpressionBuilder(functieString).variables("x").build();

        Path path = new Path();
        path.setStroke(Color.valueOf(culoare));
        path.setStrokeWidth(grosime);

        path.setClip(
                new Rectangle(
                        0, 0,
                        axe.getPrefWidth(),
                        axe.getPrefHeight()
                )
        );

        double x = xMin;
        expresie.setVariable("x", x);
        double y = expresie.evaluate();

        
        path.getElements().add(
                new MoveTo(
                        mapX(x, axe), mapY(y, axe)
                )
        );
       
        
        x += xInc;
        
        
        while (x < xMax) {
            //   y = f.apply(x);

            expresie.setVariable("x", x);
            y = expresie.evaluate();

            valori.add( new Pereche( mapX(x, axe), mapY(y, axe) ));
            path.getElements().add(
                    new LineTo(
                            mapX(x, axe), mapY(y, axe)
                    )
            );
           
            x += xInc;
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axe.getPrefWidth(), axe.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(path);  }
       catch(Exception e){
          System.out.println("Eroare la functia pe care ai dat-o !!");
          TrateazaEroare tr=new TrateazaEroare();
          tr.eroare("Nu ati introdus o functie corecta!");
          
        }
    }


    private double mapX(double x, Coordonate axe) {
        double tx = axe.getPrefWidth() / 2;
        double sx = axe.getPrefWidth() / (axe.getXAxis().getUpperBound() - axe.getXAxis().getLowerBound());
     
         return x * sx + tx;
    }

    private double mapY(double y, Coordonate axe) {
        double ty = axe.getPrefHeight() / 2;
        double sy = axe.getPrefHeight() / (axe.getYAxis().getUpperBound() - axe.getYAxis().getLowerBound());
        
        return -y * sy + ty;
    }
    
    public Grafic(
            Coordonate axe
    ) {
        getChildren().setAll(axe);
    }
    


}