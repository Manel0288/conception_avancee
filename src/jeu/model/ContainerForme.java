/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.EcouteurModel;
import jeu.ModelEcoutable;
import jeu.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import jeu.IListEcoutable;
import jeu.ListeEcouteur;

/**
 *
 * @author barry
 */
public class ContainerForme extends ModelEcoutable implements EcouteurModel, IListEcoutable{
    private List<ListeEcouteur> listEcouteur;
    private List<Forme> listForme; 
    
    
    public ContainerForme(){
        listEcouteur =  new ArrayList<>();
        this.listForme = new ArrayList<>();
    }
    
    public void addForm(Forme forme){
        this.listForme.add(forme);  
        forme.addEcouteur(this);
        this.fireFormeAdded(forme);
    }
    
    public void removeForm(int index){
        if(this.listForme.contains(this.listForme.get(index))){
           Forme forme = this.listForme.remove(index);
           forme.removeEcouteur(this);
           this.fireFormeRemoved(forme);
        }
    }
    
    public int getFormeIndex(Forme f){
        return this.listForme.indexOf(f);
    }
    public void editForm(int index,Forme forme){
       
        this.listForme.set(index, forme);
        this.fire(forme);
    }
    
    public List<Forme> getListForme(){
        return this.listForme;
    }
    
    @Override
    public void stateChanged(Object obj) {
        Forme forme = (Forme)obj;
         this.listForme.set(this.listForme.indexOf(forme), forme);
         if(forme instanceof Point){
             Point point = (Point) forme;
         }
         this.fire(forme);

    }

    @Override
    public void addListeEcouteur(ListeEcouteur listE) {
        this.listEcouteur.add(listE);

    }

    @Override
    public void removeListeEcouteur(ListeEcouteur listE) {
        if(this.listEcouteur.contains(listE)){
            this.listEcouteur.remove(listE);
        }
    }
    
    protected void fireFormeAdded(Forme f) {
        for (ListeEcouteur listE : listEcouteur) {
            listE.listeStateChangedAdd(this, f);
        }
    }

    protected void fireFormeRemoved(Forme f) {
        for (ListeEcouteur listE : listEcouteur) {
            listE.listeStateChangedRemove(this, f);
        }

    }

       
    
}
    
