/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author lucia_000
 */
public class Pereche {
    public double x,y;
    
    public Pereche(double unX, double unY){
        x=unX;y=unY;
    }
    
    public Pereche(){
        x=0;y=0;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
}
