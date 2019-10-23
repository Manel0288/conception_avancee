/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.vue_controleur.VueJeu;
import jeu.model.Rectangle;
import jeu.model.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.*;
import java.util.List;
import jeu.model.AddCommand;
import jeu.model.ContainerForme;

/**
 *
 * @author barry
 */
public class ModeAddRectangle extends ModeAction {
    
      private double x;
      private double y;
      private double hauteur;
      private double largeur;
      
    public ModeAddRectangle(VueJeu conteneurVue){
        super(conteneurVue); 
        this.x = 0.0;
        this.y = 0.0;
        this.hauteur = 0.0;
        this.largeur = 0.0;

    }
    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
              
        this.hauteur = e.getY() - this.y;
        this.largeur = e.getX() - this.x;
        this.conteneurVue.repaint();   
    }

 

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle rect = new Rectangle(new Point(this.x, this.y));
        rect.setHauteur(hauteur);
        rect.setLargeur(largeur);
        if(!rect.isInsidePanel() || this.conteneurVue.getModel().verifyIntersect(rect)){
            this.x = -1;
            this.y = -1;
            this.hauteur = -1;
            this.largeur = -1;
            rect = null;
            this.conteneurVue.repaint();   
           
        }
        
        if(this.largeur != -1 && this.hauteur != -1){
            ContainerForme conteneurF = this.conteneurVue.getModel().getFormesJoueur();
            this.conteneurVue.getHandler().handle(new AddCommand(conteneurF, rect));
        }
         this.conteneurVue.repaint();
        
    }
    
    public void paint(Graphics2D g)
    {   
        g.drawRect((int) this.x, (int) this.y, (int) this.largeur, (int) this.hauteur);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}  
}
