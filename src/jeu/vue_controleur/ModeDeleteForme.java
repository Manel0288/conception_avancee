/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import java.awt.Color;
import jeu.model.RemoveCommand;
import jeu.vue_controleur.VueFormeMode;
import jeu.vue_controleur.VueJeu;
import jeu.model.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.List;
import jeu.model.Cercle;
import jeu.model.ContainerForme;
import jeu.model.Forme;

/**
 *
 * @author barry
 */
public class ModeDeleteForme extends ModeAction {
    private double x;
    private double y;
    private Forme formToDelete;
    private VueFormeMode viewToDelete;

    public ModeDeleteForme(VueJeu conteneurVue) {
        super(conteneurVue);
        this.formToDelete = null;
        this.viewToDelete = null;
        this.x = 0.0;
        this.y = 0.0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
      if(this.formToDelete != null && this.viewToDelete != null){
          List<Forme> formesJoueurs = this.conteneurVue.getModel().getFormesJoueur().getListForme();
          ContainerForme conteneurF = this.conteneurVue.getModel().getFormesJoueur();
          
          this.conteneurVue.getHandler().handle(new RemoveCommand(conteneurF, this.formToDelete));
      }

    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
       if(this.formToDelete != null && this.viewToDelete != null)
           this.conteneurVue.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        this.formToDelete = this.conteneurVue.getModel().containsCoordinates(x, y) ;
        
        if(this.formToDelete != null){
            if(this.formToDelete instanceof Rectangle){
                Rectangle rect = (Rectangle) this.formToDelete; 
                this.viewToDelete = this.conteneurVue.getVueFormesJoueur().getMapFormView(rect);
            }else{
                Cercle cercle = (Cercle) this.formToDelete; 
                this.viewToDelete = this.conteneurVue.getVueFormesJoueur().getMapFormView(cercle);
            }
            
            if(this.viewToDelete != null)
                this.viewToDelete.fill((Graphics2D)this.conteneurVue.getGraphics(),Color.red); 
        }else{
            this.conteneurVue.repaint();
        }
        
    }

}
