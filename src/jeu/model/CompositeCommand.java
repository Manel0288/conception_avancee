/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.model;

import jeu.model.OperationCommand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barry
 */
public class CompositeCommand implements OperationCommand{
    
    private List<OperationCommand> opC;
    
    public CompositeCommand(){
        opC = new ArrayList<>();
    }
    
    public void addCommand(OperationCommand cmd){
        opC.add(cmd);
    }
    
    @Override
    public void operate() {
        for (OperationCommand operationCommand : opC) {
            operationCommand.operate();
        }    
    }

    @Override
    public void compensate() {
        for(int i = 0; i < opC.size(); i++){
            opC.get(i).compensate();
        }
        
    }
    
}
