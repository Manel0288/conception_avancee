/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.vue_controleur.VueFormeMode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import jeu.model.Cercle;

/**
 *
 * @author barry
 */
public class VueCercle extends VueFormeMode {
    
    private Cercle cercle;
    
    public VueCercle(Cercle cercle){
       this.cercle = cercle;
    }
    
    public void setCercle(Cercle c){
        this.cercle = c;
    }

    public void paint(Graphics2D g) {
        
        double cX = this.cercle.getCentre().getX();
        double cR = this.cercle.getRayon();
        double cY = this.cercle.getCentre().getY();
        g.drawOval((int) (cX-cR),(int)  (cY-cR), (int) (2*cR),(int)  (2*cR));
        
    }

    @Override
    public void fill(Graphics2D g, Color c) {
        double cX = this.cercle.getCentre().getX();
        double cR = this.cercle.getRayon();
        double cY = this.cercle.getCentre().getY();
        
        g.setColor(c);
        g.fillOval((int) (cX-cR),(int)  (cY-cR), (int) (2*cR),(int)  (2*cR));
    }  
}
