/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.model.Point;
import com.sun.prism.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author barry
 */
public class VuePoint extends VueFormeMode {
       
    private Point point;
    
    public VuePoint(Point point){
        
        this.point = point ;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawOval((int)point.getX(), (int)point.getY(), 2, 2);
       
    }

    @Override
    public void fill(Graphics2D g, Color c) {}
    
    
}
