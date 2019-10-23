/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import java.awt.Color;
import jeu.model.Cercle;
import jeu.model.Forme;
import jeu.model.ContainerForme;
import jeu.EcouteurModel;
import jeu.ModelEcoutable;
import jeu.vue_controleur.VueCercle;
import jeu.vue_controleur.VueFormeMode;
import jeu.vue_controleur.VuePoint;
import jeu.vue_controleur.VueRectangle;
import jeu.model.Rectangle;
import jeu.model.Point;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import jeu.IListEcoutable;
import jeu.ListeEcouteur;

/**
 *
 * @author barry
 */
public class ConteneurVue extends ModelEcoutable implements EcouteurModel, ListeEcouteur{
    
    private Map<Forme, VueFormeMode> map = new HashMap<>();
    private ContainerForme containerForm;
  
    
    public ConteneurVue(ContainerForme containerForm){
        this.containerForm = containerForm;
        this.containerForm.addEcouteur(this);
        this.containerForm.addListeEcouteur(this);
        
        this.createViewForme();    

    } 
    
    @Override
    public void listeStateChangedAdd(IListEcoutable l, Forme forme) {
        if(forme instanceof Rectangle){
            map.put((Rectangle)forme, new VueRectangle((Rectangle)forme));
        } else {
            map.put((Cercle)forme, new VueCercle((Cercle)forme));
        }
        
        fire(forme);
    }
        
    @Override
    public void listeStateChangedRemove(IListEcoutable l, Forme forme) {
        map.remove(forme);
        fire(forme);
    }
       
    @Override
    public void stateChanged(Object forme) {
        Forme form = (Forme)forme;

        fire(form);
    }


    
    private void createViewForme(){
        List<Forme> formes = containerForm.getListForme();
        for(Forme forme: formes){
            if(forme instanceof Rectangle){
                map.put((Rectangle)forme, new VueRectangle((Rectangle)forme));
            } else if(forme instanceof Cercle){
                map.put((Cercle)forme, new VueCercle((Cercle)forme));
            } else {
                 map.put((Point)forme, new VuePoint((Point)forme));
            }
            
        }
    }
    
    public VueFormeMode getMapFormView(Forme form){
        return this.map.containsKey(form)? this.map.get(form) : null;
    }
    
    public void setMapFormView(Forme form,VueFormeMode viewForm){
        this.map.put(form, viewForm);
    }
    
    public void dessiner(Graphics2D g){
       List<VueFormeMode> listVuesFormes = new ArrayList<>(map.values());
       
       for(VueFormeMode vueForme: listVuesFormes){
            vueForme.paint(g);
        }
    }
    
    public void remplir(Graphics2D g, Color c){
       List<VueFormeMode> listVuesFormes = new ArrayList<>(map.values());
       
       for(VueFormeMode vueForme: listVuesFormes){
            vueForme.fill(g, c);
        }
    }
}
