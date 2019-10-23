/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.model.Rectangle;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 *
 * @author barry
 */
public class VueRectangle extends VueFormeMode {
   
    private Rectangle rectangle;
    
    public VueRectangle(Rectangle rectangle){
         this.rectangle = rectangle;
    }

    @Override
    public void paint(Graphics2D g) {
        if (rectangle.getPoint() != null && rectangle.getLargeur() > 0 && rectangle.getHauteur() > 0) {
            g.drawRect((int) rectangle.getPoint().getX(), (int) rectangle.getPoint().getY(), (int) rectangle.getLargeur(), (int) rectangle.getHauteur());
            
        }
      
    }
    
    public void setRectangle(Rectangle r){
        this.rectangle = r;
    }

    @Override
    public void fill(Graphics2D g, Color c) {
        g.setColor(c);
        g.fillRect((int) this.rectangle.getPoint().getX(), (int) this.rectangle.getPoint().getY(), (int) this.rectangle.getLargeur(), (int) this.rectangle.getHauteur());
    }
      
}
