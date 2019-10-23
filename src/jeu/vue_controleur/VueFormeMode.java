/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author barry
 */
public abstract class VueFormeMode {
    
    public VueFormeMode(){};
    
    public abstract void paint(Graphics2D g);
    public abstract void fill(Graphics2D g, Color c);
}
