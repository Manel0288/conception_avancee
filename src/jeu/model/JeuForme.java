/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.StrategyObstacle1;
import jeu.model.StrategyObstacles;
import jeu.model.Rectangle;
import jeu.model.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barry
 */
public class JeuForme {

    private StrategyObstacles strategy;
    private ContainerForme formesJoueur;
    private ContainerForme formesObstacles;
    private int hauteur;
    private int largeur;
    public final static int LARGEUR = 800;
    public final static int HAUTEUR = 550;

    public JeuForme() {
        this(new StrategyObstacle1(), LARGEUR, HAUTEUR);
    }

    public JeuForme(StrategyObstacles strategy, int largeur, int hauteur) {

        this.strategy = strategy;
        this.largeur = largeur;
        this.hauteur = hauteur;
        formesObstacles = new ContainerForme();
        formesJoueur = new ContainerForme();
        this.strategy.setJeuModel(this);
        this.initObstacle();
    }
    
    // Ajouter les methodes de control correspondant aux differents boutons

    public StrategyObstacles getStrategy() {
        return this.strategy;

    }

    public void setStrategy(StrategyObstacles strategy) {
        this.strategy = strategy;

    }

    public ContainerForme getFormesObstacles() {
        return this.formesObstacles;
    }

    public ContainerForme getFormesJoueur() {
        return this.formesJoueur;
    }
    
    public List<Forme> getAllFormes(){
        ArrayList<Forme> allFormes = new ArrayList<>();
        allFormes.addAll(this.formesJoueur.getListForme());
        allFormes.addAll(this.formesObstacles.getListForme());
        
        return allFormes;
    }

    private void initObstacle() {
        this.strategy.createObstacles();
    }
    
    public boolean verifyIntersect(Forme forme){
        boolean intersection = false; 
        List<Forme> formes = this.getAllFormes();
        for(Forme form: formes){
            if (JeuForme.intersects(form, forme)){
                intersection = true;
                break;
            }
            if(forme instanceof Cercle && form instanceof Rectangle){
                if (JeuForme.intersects(forme, form)){
                    intersection = true;
                    break;
                }
            }
        }
        return intersection;
    }

    public Forme containsCoordinates(double x, double y){
        Forme formContainer = null; 
        List<Forme> formes = this.getAllFormes();
        for(Forme form: formes){
            if (form instanceof Rectangle){
                Rectangle rect = (Rectangle) form;
                Rectangle2D rect2D = new Rectangle2D.Double(rect.getPoint().getX(), rect.getPoint().getY(), rect.getLargeur(), rect.getHauteur());
                if(rect2D.contains(x, y)){
                    formContainer = rect;
                    break;
                }
            }
            if(form instanceof Cercle){
                Cercle cercle = (Cercle) form;
                Ellipse2D cercle2D = new Ellipse2D.Double(cercle.getCentre().getX()-cercle.getRayon(),cercle.getCentre().getY()-cercle.getRayon(),2*cercle.getRayon(),2*cercle.getRayon());
                if(cercle2D.contains(x, y)){
                    formContainer = cercle;
                    break;
                }
            }
        }
        return formContainer;
    }
    
    public static boolean intersects(Forme forme1, Forme forme2){
        
        double dist, sommeRayon;
        boolean intersect = false;
        
        if(forme1 instanceof Cercle && forme2 instanceof Cercle){
            Cercle cercle1 = (Cercle) forme1;
            Cercle cercle2 = (Cercle) forme2;
            if(!cercle1.estIdentique(cercle2)){
                dist = distance(cercle1.getCentre(), cercle2.getCentre());
                sommeRayon = cercle1.getRayon() + cercle2.getRayon();
                intersect = dist < sommeRayon;
            }
           
        }else if(forme1 instanceof Rectangle && forme2 instanceof Rectangle){
            Rectangle rect1 = (Rectangle) forme1;
            Rectangle rect2 = (Rectangle) forme2;
            if(!rect1.estIdentique(rect2)){
                Rectangle2D rect2D = new Rectangle2D.Double(rect1.getPoint().getX(), rect1.getPoint().getY(), rect1.getLargeur(), rect1.getHauteur());
                intersect = rect2D.intersects(rect2.getPoint().getX(), rect2.getPoint().getY(), rect2.getLargeur(), rect2.getHauteur());
            }
            
        }else if(forme1 instanceof Cercle && forme2 instanceof Rectangle){
            Cercle cercle = (Cercle) forme1;
            Rectangle rectangle = (Rectangle) forme2;
            Rectangle2D rect2D = new Rectangle2D.Double(rectangle.getPoint().getX(), rectangle.getPoint().getY(), rectangle.getLargeur(), rectangle.getHauteur());
            Ellipse2D cercle2D = new Ellipse2D.Double(cercle.getCentre().getX()-cercle.getRayon(),cercle.getCentre().getY()-cercle.getRayon(),2*cercle.getRayon(),2*cercle.getRayon());
            intersect = cercle2D.intersects(rect2D);   
        }
        return intersect;
    }
    
    private static double distance( Point p1, Point p2){
        return Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()));
    }
    
    public double score(){
       double scor = 0.0;
       List<Forme> formes = this.getAllFormes();
        for (Forme forme : formes) {
            scor += forme.surface();
        }
        
        scor = (scor * 100) / this.surfacePlateau();
        return JeuForme.round(scor, 2);
    }
    
    private double surfacePlateau(){
        return JeuForme.HAUTEUR * JeuForme.LARGEUR;
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
