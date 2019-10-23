/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barry
 */
public abstract class ListeEcoutable {

    private List<ListeEcouteur> listEcouteur;

    public ListeEcoutable() {
        listEcouteur = new ArrayList<>();
    }

    public void addFormListener(ListeEcouteur listE) {
        this.listEcouteur.add(listE);
    }

    public void removeFormListener(ListeEcoutable listE) {
        if (this.listEcouteur.contains(listE)) {
            this.listEcouteur.remove(listE);

        }
    }

//    protected void fireFormeAdded(Forme f) {
//        for (ListeEcouteur listE : listEcouteur) {
//            listE.listeStateChangedAdd(this, f);
//        }
//    }
//
//    protected void fireFormeRemoved(Forme f) {
//        for (ListeEcouteur listE : listEcouteur) {
//            listE.listeStateChangedRemove(this, f);
//        }
//
//    }

}
