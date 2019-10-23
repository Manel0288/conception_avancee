/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.vue_controleur.VueJeu;
import java.awt.Graphics2D;
import java.awt.event.*;

/**
 *
 * @author barry
 */
public class ModeVide extends ModeAction {
    
    public ModeVide(VueJeu conteneurVue){
        super(conteneurVue);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
    

    
}
