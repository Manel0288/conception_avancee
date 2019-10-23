/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import java.util.Stack;

/**
 *
 * @author barry
 */
public class RemoveCommand  implements OperationCommand{
    
    private Forme forme;
    private ContainerForme conteneurForme;
    
    public RemoveCommand(ContainerForme conteneur, Forme forme){
        this.forme = forme;
        this.conteneurForme = conteneur;
    }

    @Override
    public void operate() {
        int index = this.conteneurForme.getFormeIndex(this.forme);
        this.conteneurForme.removeForm(index);     
    }

    @Override
    public void compensate() {
        this.conteneurForme.addForm(this.forme);      
    }
    
}
