/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.Rectangle;
import jeu.model.Point;
import java.util.Random;

/**
 *
 * @author djelo bah
 */
public class StrategyObstaclesAleatoires  extends StrategyObstacles{

    @Override
    protected void createObstacles() {
        Random random = new Random();
        Forme forme=null;
        int nombreFormes = random.nextInt(6-3) + 3;
        for(int i=0; i<nombreFormes; i++){
            int x, y, typeForme;
            typeForme = random.nextInt(3-1) + 1;
            do{
               
                x = random.nextInt(JeuForme.LARGEUR);
                y = random.nextInt(JeuForme.HAUTEUR);

                switch(typeForme){
                    case 1:
                        Rectangle r;
                        do{
                            r = new Rectangle(new Point(x,y));
                            int largeur = random.nextInt(120 - 20) + 20;
                            int hauteur = random.nextInt(120 - 20) + 20;
                            
                            r.setLargeur(largeur);
                            r.setHauteur(hauteur);
                            if(!r.isInsidePanel()){
                                x = random.nextInt(JeuForme.LARGEUR);
                                y = random.nextInt(JeuForme.HAUTEUR);
                            }
                        }while(!r.isInsidePanel());
                        forme = r;
                        break;
                    case 2:
                        Cercle c;
                        do{
                            c = new Cercle(new Point(x, y));
                            int rayon = random.nextInt(120 - 20) + 20;
                            
                            c.setRayon(rayon);
                            c.setIsReady(true);
                            if(!c.isInsidePanel()){
                                x = random.nextInt(JeuForme.LARGEUR);
                                y = random.nextInt(JeuForme.HAUTEUR);
                            }
                        }while(!c.isInsidePanel());
                        forme = c;
                        break;
                    default:
                        System.out.println("Ne rien faire");
                }
            }while(forme ==null || this.jeu.verifyIntersect(forme));
            this.jeu.getFormesObstacles().addForm(forme);
        }
    }
    
}
