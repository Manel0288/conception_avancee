/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.ModelEcoutable;
import jeu.model.OperationCommand;
import jeu.vue_controleur.UndoButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author barry
 */
public class CommandHandler extends ModelEcoutable{
    private Stack<OperationCommand> stackUndo;
    private Stack<OperationCommand> stackRedo;
    private UndoButton undoBt;

    
    public CommandHandler(){
        stackUndo = new Stack<>();
        stackRedo = new Stack<>();
        undoBt = new UndoButton();
    }
    
    public void handle(OperationCommand command){
        command.operate();
        this.stackUndo.push(command);
        stackRedo.clear();
        this.fire(stackUndo);
    }
    
    public void setUndoBt(UndoButton undo){
        this.undoBt = undo;
        this.addEcouteur(undoBt);
    }
    
    public void undo(){
        OperationCommand op = this.stackUndo.pop();
        op.compensate();
        if( op != null){
           stackRedo.push(op);
        }
    }
    
    public void redo(){
        OperationCommand op = stackRedo.pop();
        op.operate();
        stackUndo.push(op);
    }
    
    public boolean isStackUndoEmpty(){
        return stackUndo.isEmpty();
    }
    
    public boolean isStackRedoEmpty(){
        return stackRedo.isEmpty();
    }
}
    
