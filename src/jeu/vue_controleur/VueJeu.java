package jeu.vue_controleur;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import jeu.model.CommandHandler;
import jeu.EcouteurModel;
import jeu.EcouteurModel;
import jeu.model.Forme;
import jeu.IListEcoutable;
import jeu.model.JeuForme;
import jeu.model.JeuForme;
import jeu.ListeEcouteur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author barry
 */
public class VueJeu extends JPanel implements EcouteurModel, MouseMotionListener, MouseListener{
    private ConteneurVue vueFormesJoueur;
    private ConteneurVue vueFormesObstacles;
    public enum Action { ADD_RECTANGLE, ADD_CIRCLE, UNDO, REDO, DELETE_FORME, MOVE_FORME, VIDE }
    private JeuForme model;
    private ModeAddRectangle actionAddRectangle;
    private ModeAddCercle actionAddCircle;
    private ModeMoveForme actionMoveForme;
    private ModeDeleteForme actionDeleleForme;
    private ModeAction modeActuel;
    private ModeVide actionVide;
    
    protected CommandHandler handler;
    
    @Override
    public Dimension getPreferredSize()       
    {
        return new Dimension(JeuForme.LARGEUR, JeuForme.HAUTEUR);
    }
        
    @Override
    public Dimension getMaximumSize()    
    {
        return new Dimension(JeuForme.LARGEUR, JeuForme.HAUTEUR);
    }
    
    public VueJeu(JeuForme model){
        this.model = model;
        this.vueFormesJoueur = new ConteneurVue(this.model.getFormesJoueur());
        this.vueFormesObstacles = new ConteneurVue(this.model.getFormesObstacles());
        
        this.vueFormesJoueur.addEcouteur(this);
        this.vueFormesObstacles.addEcouteur(this);
        
        actionAddRectangle = new ModeAddRectangle(this);
        actionAddCircle = new ModeAddCercle(this);
        actionMoveForme = new ModeMoveForme(this);
        actionDeleleForme = new ModeDeleteForme(this);
        actionVide = new ModeVide(this);
        modeActuel = actionVide;
        
        
        this.handler = new CommandHandler();
    } 
    
    public CommandHandler getHandler(){
        return this.handler;
    }
    
    @Override
    public void stateChanged(Object forme) {
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.modeActuel.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.modeActuel.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        this.modeActuel.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       this.modeActuel.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public ConteneurVue getVueFormesObstacles(){
        return this.vueFormesObstacles;
    }
    
    public ConteneurVue getVueFormesJoueur(){
        return this.vueFormesJoueur;
    }
    
    public void setAction(Action action){
        switch(action){
            case ADD_RECTANGLE:
                modeActuel = actionAddRectangle;
                break;
            case ADD_CIRCLE:
                modeActuel = actionAddCircle;
                break;
            case MOVE_FORME:
                modeActuel = actionMoveForme;
                break;
            case DELETE_FORME:
                modeActuel = actionDeleleForme;
                break;
            default:
                modeActuel = actionVide;
                break;      
        }
    }
    
    public ModeAction getMode(){
        return this.modeActuel;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(1,1,JeuForme.LARGEUR-2,JeuForme.HAUTEUR-2);
        Graphics2D newG = (Graphics2D) g;
        this.vueFormesObstacles.dessiner(newG);
        this.vueFormesObstacles.remplir(newG, Color.black);
        this.vueFormesJoueur.dessiner(newG);
        this.vueFormesJoueur.remplir(newG, Color.blue);
        this.modeActuel.paint(newG);
        
    }
    
    public JeuForme getModel(){
        return this.model;
    }
}
