/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

/**
 *
 * @author barry
 */
public abstract class StrategyObstacles {
    
    protected JeuForme jeu;
    
    public StrategyObstacles(){
        
       
    }
    
    public void setJeuModel(JeuForme jeu){
        this.jeu = jeu;
    }
    
    protected abstract void createObstacles();
    
    public JeuForme getFormes(){
        return jeu;
    }
        
}
