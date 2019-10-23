/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.Forme;
import jeu.model.JeuForme;
import jeu.ModelEcoutable;
import jeu.model.Point;

/**
 *
 * @author barry
 */
public class Cercle extends ModelEcoutable implements Forme {
     private double rayon;
     private Point centre;
     protected boolean isReady=false;
    
     public Cercle(Point centre){
         this.rayon = -1;
         this.centre = centre;
     }
     
     public double getRayon(){
         return rayon;
     }
     
     public Point getCentre(){
         return centre;
     }
     
     public void setRayon(double rayon){
         this.rayon = rayon;
     }
     
     public void setIsReady(boolean isReady){
        this.isReady = isReady;
         
     }
     
    @Override
    public void translater(double x, double y) {
        this.centre.translater(x, y);
        fire(this);
        
    }
    
    public void setCentre(double x, double y){
      this.centre.setX(x);
      this.centre.setY(y);
    }
    
    public boolean estIdentique(Cercle c) {
	return (this.getCentre().getX() == c.getCentre().getX()) && (this.getCentre().getY() == c.getCentre().getY());
    }
    
     public boolean isInsidePanel(){
        boolean isInside = true;
        double cercleRightWidth = this.getCentre().getX() + this.rayon;
        double cercleRightHeight = this.getCentre().getY() + this.rayon;
        
        double cercleLeftWidth = this.getCentre().getX() - this.rayon;
        double cercleLeftHeight = this.getCentre().getY() - this.rayon;
        
        if(cercleRightWidth > JeuForme.LARGEUR  || cercleRightHeight > JeuForme.HAUTEUR){
            isInside = false;
        }else if(cercleLeftWidth < 0  || cercleLeftHeight < 0){
            isInside = false;
        }
        
        return isInside;
    }

    @Override
    public double surface() {
        return 3.14 * rayon*rayon;
    }
    
}
