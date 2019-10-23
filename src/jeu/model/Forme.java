/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.InterfaceModelEcoutable;

/**
 *
 * @author barry
 */
public interface Forme extends InterfaceModelEcoutable  {
    public void translater(double x, double y);
    public double surface();
}
