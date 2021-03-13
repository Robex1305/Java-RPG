package content;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private Pane mainPane;

    private static ArrayList<Adversaire> listeAdversaire;

    public ArrayList<Adversaire> getListePersonnage() {
        return listeAdversaire;
    }

    public void setListePersonnage(ArrayList<Adversaire> listePersonnage) {
        this.listeAdversaire = listePersonnage;
    }



    @FXML
    public void initialize() {
        Combat combat = initCombat();

        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                combat.start();
            }
        };

        t.start();



        Thread boucle = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(combat.getSleepTime());
                        Runnable r = new Runnable() {
                            @Override
                            public void run() {
                                combat.nextRound();
                                mainPane.getChildren().clear();
                                for (Personnage personnage : combat.getListePersonnage()) {
                                    mainPane.getChildren().add(personnage.getPicture());
                                }
                            }
                        };
                        Platform.runLater(r);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        boucle.start();




    }

    public Combat initCombat(){
        //  ---EN GENERAL--
        //  [X] TODO: Faire une TODO liste
        //  [ ] TODO: Déveloper un systeme d'inventaire avec armes, armures et autre stuff
        //  [ ] TODO: faire une interface graphique
        //  [ ] TODO:
        //  [ ] TODO:
        //  [ ] TODO:
        //  [ ] TODO:
        //  [ ] TODO:
        //  [ ] TODO:
        //  [ ] TODO:

        Item epee = new Item("Épée de Tranche-Boudin",3,0,0,0,0,0,5,0);
        Item armure = new Item("Cuirasse tachée de gras",0,0,0,-1,0,0,0,10);
        Joueur j = new Joueur("Jordan", new Statistiques(10,10,10,10,10,10));

        j.equiperObjet(epee);
        j.equiperObjet(armure);
        j.rafraichirStatsEquipement();

        Adversaire a0 = new Adversaire("Gobelin boitant", new Statistiques(10,5,6,10,7,11));
        Adversaire a1 = new Adversaire("Orc suintant", new Statistiques(7,6,7,11,8,10));
        Adversaire a2 = new Adversaire("Peepoudou", new Statistiques(2,1,25,4,0,2));

        listeAdversaire = new ArrayList<Adversaire>();

        listeAdversaire.add(a0);
        listeAdversaire.add(a1);
        listeAdversaire.add(a2);


        Combat c = new Combat(j,listeAdversaire, mainPane.getPrefWidth(), mainPane.getPrefHeight());
        return c;
    }

}
