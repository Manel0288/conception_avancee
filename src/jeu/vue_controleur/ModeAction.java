/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.vue_controleur.VueJeu;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author barry
 */
public abstract class ModeAction {
    
    protected VueJeu conteneurVue;
           
    
    public ModeAction(VueJeu conteneurVue){
        this.conteneurVue = conteneurVue;
    }
    
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);

    public void paint(Graphics2D g){}
    
    //public abstract void setForme(Forme forme);
   // public abstract Forme getForme();
//    public abstract VueFormeMode getVueForme();
//    public abstract void setVueForme(VueFormeMode vueForme);
     
    
    

    
}
