/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.ModelEcoutable;
import jeu.model.Point;

/**
 *
 * @author barry
 */
public class Rectangle extends ModelEcoutable implements Forme, Cloneable {
    
    private Point point;
    private double hauteur ;
    private double largeur ;

    public Rectangle(Point point ) {
        this.point= point;
        this.hauteur = -1;
        this.largeur = -1;
    }
  
    public double getLargeur(){
        return largeur;
    }
  
    public double getHauteur(){
        return hauteur;
    }
  
    public void setLargeur(double largeur){
        this.largeur = largeur;
    }
  
  
    public void setHauteur(double hauteur){
        this.hauteur = hauteur;
    }

    public Point getPoint(){
        return this.point;
    }
  
    public void setPoint(double x, double y){
        this.point.setX(x);
        this.point.setY(y);
    }
  
    @Override
    public double surface() {
        return this.hauteur * this.largeur;
    }

    @Override
    public void translater(double dx, double dy) {
        this.point.setX(point.getX()+dx);
        this.point.setY(point.getY()+dy);
        fire(this);
    }
  
    public boolean isInsidePanel(){
        boolean isInside = true;
        
        double rectWidth = this.getPoint().getX() + this.largeur;
        double rectHeight = this.getPoint().getY() + this.hauteur;
        
        
        if(rectWidth > JeuForme.LARGEUR || rectHeight > JeuForme.HAUTEUR){
            isInside = false;
        }
        return isInside;
    }
    
    public boolean estIdentique(Rectangle rect) {
	return (this.getPoint().getX() == rect.getPoint().getX()) && (this.getPoint().getY() == rect.getPoint().getY());
    }
}
