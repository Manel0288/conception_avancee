/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.ModelEcoutable;

/**
 *
 * @author barry
 */
public class Point extends ModelEcoutable implements Forme{
    private double x;
    private double y;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
        
        
    }
    
    public Point(Point point){
        this(point.x,point.y);
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public void setX(double x){
         this.x = x;
    }
    
    public void setY(double y){
         this.y = y;
    }


    @Override
    public void translater(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        fire(this);
        
    }  

    @Override
    public double surface() {
        return 0.0;
    }

   
}
