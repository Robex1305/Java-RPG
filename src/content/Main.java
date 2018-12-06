package content;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static ArrayList<Adversaire> listeAdversaire;

    public ArrayList<Adversaire> getListePersonnage() {
        return listeAdversaire;
    }

    public void setListePersonnage(ArrayList<Adversaire> listePersonnage) {
        this.listeAdversaire = listePersonnage;
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("interface.fxml")),753, 844);
        primaryStage.setTitle("Java-Arena");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        String titre1 = " ____  _                                            _                   _              _                                                     ";
        String titre2 = "|  _ \\(_)                                          | |                 | |            | |                         /\\                         ";
        String titre3 = "| |_) |_  ___ _ ____   _____ _ __  _   _  ___    __| | __ _ _ __  ___  | | __ _       | | __ ___   ____ _ ______ /  \\   _ __ ___ _ __   __ _ ";
        String titre4 = "|  _ <| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| | | |/ _ \\  / _` |/ _` | '_ \\/ __| | |/ _` |  _   | |/ _` \\ \\ / / _` |______/ /\\ \\ | '__/ _ \\ '_ \\ / _` |";
        String titre5 = "| |_) | |  __/ | | \\ V /  __/ | | | |_| |  __/ | (_| | (_| | | | \\__ \\ | | (_| | | |__| | (_| |\\ V / (_| |     / ____ \\| | |  __/ | | | (_| |";
        String titre6 = "|____/|_|\\___|_| |_|\\_/ \\___|_| |_|\\__,_|\\___|  \\__,_|\\__,_|_| |_|___/ |_|\\__,_|  \\____/ \\__,_| \\_/ \\__,_|    /_/    \\_\\_|  \\___|_| |_|\\__,_|";



        System.out.println(titre1);
        System.out.println(titre2);
        System.out.println(titre3);
        System.out.println(titre4);
        System.out.println(titre5);
        System.out.println(titre6);




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

        j.seDeplacer(0,0);

        Adversaire a0 = new Adversaire("Gobelin boitant", new Statistiques(10,5,6,10,7,11));
        Adversaire a1 = new Adversaire("Orc suintant", new Statistiques(7,6,7,11,8,10));
        Adversaire a2 = new Adversaire("Peepoudou", new Statistiques(2,1,25,4,0,2));

        a0.seDeplacer(5,7);
        a1.seDeplacer(6,7);
        a2.seDeplacer(2,4);

        listeAdversaire = new ArrayList<Adversaire>();

        listeAdversaire.add(a0);
        listeAdversaire.add(a1);
        listeAdversaire.add(a2);



        Combat c = new Combat(j,listeAdversaire);
        c.start();
        //launch(args);


    }


}
