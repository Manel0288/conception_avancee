/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.Forme;
import jeu.model.ContainerForme;
import jeu.model.OperationCommand;
import java.util.Stack;

/**
 *
 * @author barry
 */
public class AddCommand implements OperationCommand{
    private Forme forme;
    private ContainerForme conteneurForme;
    
    public AddCommand(ContainerForme conteneur, Forme forme){
        this.forme = forme;
        this.conteneurForme = conteneur;
    }

    @Override
    public void operate() {
        this.conteneurForme.addForm(this.forme);   
    }

    @Override
    public void compensate() {
        this.conteneurForme.removeForm(this.conteneurForme.getFormeIndex(this.forme));     
    }
       
}
