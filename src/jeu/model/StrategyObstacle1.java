/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.StrategyObstacles;
import jeu.model.Rectangle;
import jeu.model.Point;

/**
 *
 * @author barry
 */
/*
*Cette strategie genere un rectangle, un cercle et un point
*/
public class StrategyObstacle1 extends StrategyObstacles{

    
    @Override
    protected void createObstacles() {
        Rectangle r1 = new Rectangle(new Point(80, 90));
        r1.setLargeur(100);
        r1.setHauteur(50);
        
        Rectangle r2 = new Rectangle(new Point(510, 460));
        r2.setLargeur(150);
        r2.setHauteur(70);
        
        Point cercleCentre1 = new Point(300,400);
        Cercle c1 = new Cercle(cercleCentre1);
        c1.setRayon(40);
        c1.setIsReady(true);
        
        Point cercleCentre2 = new Point(550,260);
        Cercle c2 = new Cercle(cercleCentre2);
        c2.setRayon(60);
        c2.setIsReady(true);
        this.jeu.getFormesObstacles().addForm(r1);
        this.jeu.getFormesObstacles().addForm(c1);
        this.jeu.getFormesObstacles().addForm(r2);
        this.jeu.getFormesObstacles().addForm(c2);  
    }
    
}
