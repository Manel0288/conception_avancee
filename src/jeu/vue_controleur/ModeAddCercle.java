/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.vue_controleur.VueJeu;
import jeu.model.Point;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import jeu.model.AddCommand;
import jeu.model.Cercle;
import jeu.model.ContainerForme;

/**
 *
 * @author barry
 */
public class ModeAddCercle extends ModeAction {
     
      private double x;
      private double y;
      private double rayon;
      
    public ModeAddCercle(VueJeu conteneurVue) {
        super(conteneurVue);
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
       this.rayon = Math.sqrt((e.getY() - this.y ) * (e.getY() - this.y) + (e.getX() - this.x) * (e.getX() - this.x));
       this.conteneurVue.repaint();

        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Cercle cercle = new Cercle(new Point(this.x, this.y));
        cercle.setRayon(this.rayon);
        
        if(!cercle.isInsidePanel() || this.conteneurVue.getModel().verifyIntersect(cercle)){
            this.x = -1;
            this.y = -1;
            this.rayon = -1;
            cercle = null;
            this.conteneurVue.repaint();

        }
        
        if(this.rayon != -1 && this.x != -1){
            ContainerForme conteneurF = this.conteneurVue.getModel().getFormesJoueur();
            this.conteneurVue.getHandler().handle(new AddCommand(conteneurF, cercle));
        }
         this.conteneurVue.repaint();
    }
      
    
    
    public void paint(Graphics2D g){   
      g.drawOval((int) (this.x- this.rayon),(int)  (this.y- this.rayon), (int) (2*this.rayon),(int)  (2*this.rayon));
      
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
  
}
