/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import jeu.InterfaceModelEcoutable;
import jeu.EcouteurModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author barry
 */
public abstract class ModelEcoutable implements InterfaceModelEcoutable{ 
    private List<EcouteurModel> listEcouteurModel;
    
    public ModelEcoutable(){
        this.listEcouteurModel =  new ArrayList<>();
    }
    @Override
    public void addEcouteur(EcouteurModel em){
        this.listEcouteurModel.add(em);  
    }
   
    @Override
    public void removeEcouteur(EcouteurModel em){
       this.listEcouteurModel.remove(em);    
    }
    
    protected void fire(Object f){
        for (EcouteurModel em : listEcouteurModel) {
            em.stateChanged(f);
        } 
    }
}
