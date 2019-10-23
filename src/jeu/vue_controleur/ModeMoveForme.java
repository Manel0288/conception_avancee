/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.vue_controleur.VueCercle;
import jeu.vue_controleur.VueFormeMode;
import jeu.vue_controleur.VueJeu;
import jeu.vue_controleur.VueRectangle;
import jeu.model.Rectangle;
import jeu.model.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.*;
import jeu.model.Cercle;
import jeu.model.Forme;

/**
 *
 * @author barry
 */
public class ModeMoveForme extends ModeAction {
    private double xOld;
    private double yOld;
    private Point oldPoint;
    private Forme formToMove;
    private VueFormeMode viewToMove;
    private boolean isMoved;

    public ModeMoveForme(VueJeu conteneurVue) {
        super(conteneurVue);
        this.formToMove = null;
        this.viewToMove = null;
        oldPoint = null;
        this.isMoved = false;
        this.xOld = 0.0;
        this.yOld = 0.0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
       this.xOld = e.getX();
       this.yOld = e.getY();
       this.formToMove = this.conteneurVue.getModel().containsCoordinates(this.xOld, this.yOld);
       
       if(this.formToMove != null){
            if(this.formToMove instanceof Rectangle){
                Rectangle rect = (Rectangle) this.formToMove;
                this.oldPoint = new Point(rect.getPoint().getX(), rect.getPoint().getY());
                this.viewToMove = this.conteneurVue.getVueFormesJoueur().getMapFormView(rect);
            }else{
                Cercle cercle = (Cercle) this.formToMove; 
                this.oldPoint = new Point(cercle.getCentre().getX(), cercle.getCentre().getY());
                this.viewToMove = this.conteneurVue.getVueFormesJoueur().getMapFormView(cercle);
            }
            
            if(this.viewToMove != null) 
                this.viewToMove.fill((Graphics2D)this.conteneurVue.getGraphics(),Color.green);  
        }
       

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
       if(this.formToMove != null && this.viewToMove != null){
           this.isMoved = true;
           this.formToMove.translater(x-xOld, y-yOld);
           this.conteneurVue.repaint();
           xOld = x;
           yOld = y;
       }
    }   

    @Override
    public void mouseReleased(MouseEvent e) {
       this.xOld = e.getX();
       this.yOld = e.getY();
       if(isMoved){
            if(this.formToMove instanceof Rectangle){
                Rectangle rect = (Rectangle) this.formToMove;
                if(rect.isInsidePanel() && !this.conteneurVue.getModel().verifyIntersect(rect)){
                    this.viewToMove = this.conteneurVue.getVueFormesJoueur().getMapFormView(rect);
                    
                    VueRectangle vueRect = (VueRectangle) this.viewToMove;
                    vueRect.setRectangle(rect);
                    
                }else{
                    VueRectangle vueRect = (VueRectangle) this.viewToMove;
                    rect.setPoint(this.oldPoint.getX(), this.oldPoint.getY());
                    vueRect.setRectangle(rect);
                }
                isMoved = false;
            }else{
                Cercle cercle = (Cercle) this.formToMove; 
                VueCercle vueCercle = (VueCercle) this.viewToMove;
                if(cercle.isInsidePanel() && !this.conteneurVue.getModel().verifyIntersect(cercle)){
                    this.viewToMove = this.conteneurVue.getVueFormesJoueur().getMapFormView(cercle);
                    vueCercle.setCercle(cercle);
                    
                }else{
                    cercle.setCentre(this.oldPoint.getX(), this.oldPoint.getY());
                    vueCercle.setCercle(cercle);
                }
                isMoved = false;
            }
       }
       
        this.conteneurVue.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    
    public void paint(Graphics2D g){
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g.setStroke(dashed);
        if(this.formToMove instanceof Rectangle){
            Rectangle rect = (Rectangle) this.formToMove; 
            g.drawRect((int) rect.getPoint().getX(), (int) rect.getPoint().getY(), (int) rect.getLargeur(), (int) rect.getHauteur());
        }
    }
    
}
