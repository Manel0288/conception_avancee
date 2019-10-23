/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import jeu.model.Forme;

/**
 *
 * @author barry
 */
public interface ListeEcouteur {
    
    public void listeStateChangedAdd(IListEcoutable liste, Forme forme);
    public void listeStateChangedRemove(IListEcoutable liste, Forme forme);

    
}
