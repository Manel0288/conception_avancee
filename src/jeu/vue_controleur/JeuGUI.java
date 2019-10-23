/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.vue_controleur;

import jeu.model.JeuForme;
import jeu.model.StrategyObstacles;
import jeu.model.StrategyObstaclesAleatoires;
import jeu.vue_controleur.UndoButton;
import jeu.vue_controleur.VueJeu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import jeu.model.CommandHandler;

/**
 *
 * @author barry
 */
public class JeuGUI extends JFrame {
    
    private CommandHandler handler;
    private UndoButton undo;
    private JButton redo;
    public JeuGUI(){           
        
         //creation du model de jeu
        //StrategyObstacles strategy1 = new StrategyObstacle1();
        StrategyObstacles strategyAlea = new StrategyObstaclesAleatoires();
        
        JeuForme model = new JeuForme(strategyAlea, JeuForme.LARGEUR, JeuForme.HAUTEUR);
        
        //creation de la vue-controleur du jeu
        VueJeu vueControleur = new VueJeu(model);
        
        handler = vueControleur.getHandler();
        
        ImageIcon ajout_cercle =  new ImageIcon("Images/cercleplus.png");
        JLabel image = new JLabel(ajout_cercle);
        JButton addRectangle = new JButton("Add_Rect");
        JButton addCircle = new JButton("Add_Cercle");
        JButton delete = new JButton("Delete");
        JButton move = new JButton("Move");
        undo = new UndoButton();
        redo = new JButton("Redo");
        JButton terminer = new JButton("Terminer");
        JLabel scoreAffichage = new JLabel();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        
        Container container = this.getContentPane();
        vueControleur.addMouseListener(vueControleur);
        vueControleur.addMouseMotionListener(vueControleur);
        JPanel menu = new JPanel();
        JPanel footer = new JPanel();
        footer.add(terminer);
        footer.add(scoreAffichage);
        
        handler.setUndoBt(undo);
        
        addRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vueControleur.setAction(VueJeu.Action.ADD_RECTANGLE);
                checkUndoRedo();
            }
        });
        
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vueControleur.setAction(VueJeu.Action.REDO);
               handler.redo();
               checkUndoRedo();
            }
        });
        
        addCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vueControleur.setAction(VueJeu.Action.ADD_CIRCLE);
               checkUndoRedo();
            }
        });
        
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vueControleur.setAction(VueJeu.Action.UNDO);
               handler.undo();
               checkUndoRedo();
            }
        });
        
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vueControleur.setAction(VueJeu.Action.MOVE_FORME);
               disableUndoRedo();
            }
        });
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vueControleur.setAction(VueJeu.Action.DELETE_FORME);
               checkUndoRedo();
            }
        }); 
        
        terminer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double scoreFinal = model.score();
                scoreAffichage.setText("Votre score est de : " + scoreFinal + "%");
                vueControleur.setAction(VueJeu.Action.VIDE);
                addRectangle.setEnabled(false);
                addCircle.setEnabled(false);
                move.setEnabled(false);
                delete.setEnabled(false);
                undo.setEnabled(false);
                redo.setEnabled(false);
            }
        }); 
        
        //Par defaut désactiver undo et redo
        this.disableUndoRedo();
        
         menu.add(addRectangle);
         menu.add(addCircle);
         menu.add(undo);
         menu.add(redo);
         menu.add(move);
         menu.add(delete);
         
         container.add(menu,layout.NORTH);
         container.add(vueControleur, layout.CENTER);
         container.add(footer,layout.SOUTH);
         // Ajouter un footer pour le score et certaines actions
         
         this.setTitle("Jeu de formes");
        // this.setSize(800, 700);
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         this.pack();
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    }
    
    private void checkUndoRedo(){
        if(!handler.isStackRedoEmpty()){
            redo.setEnabled(true);
        }else {
            redo.setEnabled(false);
        }
        if(!handler.isStackUndoEmpty()){
            undo.setEnabled(true);
        }else{
            undo.setEnabled(false);
        }
    }
    
    private void disableUndoRedo(){
        undo.setEnabled(false);
        redo.setEnabled(false);
    }
    
}
