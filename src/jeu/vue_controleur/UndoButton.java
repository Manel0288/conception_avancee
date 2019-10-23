/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import java.util.Stack;
import javax.swing.JButton;
import jeu.EcouteurModel;
import jeu.model.OperationCommand;

/**
 *
 * @author djelo bah
 */
public class UndoButton extends JButton implements EcouteurModel{
    
    public UndoButton(){
        super("undo");
    }
    @Override
    public void stateChanged(Object obj) {
        Stack<OperationCommand> stackUndo =(Stack<OperationCommand>) obj;
        if(stackUndo.isEmpty()){
          this.setEnabled(false);
        }else{
            this.setEnabled(true);
        }
    }
    
}
