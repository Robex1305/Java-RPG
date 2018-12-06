package content;

import java.io.File;
import java.util.ArrayList;

public class Joueur extends Personnage{

    public Joueur(String nom, Statistiques stats){

        super(nom, stats);
        this.setSkin(new File("src\\img\\joueur.png"));
    }

    public Adversaire getEnnemiLePlusProche(ArrayList<Adversaire> listeAdversaire) {

        Adversaire premierAdversaire = listeAdversaire.get(0);
        Adversaire lePlusProche = premierAdversaire;

        //on prend une distance de reference sur le premier adversaire de la liste;
        int distX = Math.abs(this.getPosX() - premierAdversaire.getPosX());
        int distY = Math.abs(this.getPosY() - premierAdversaire.getPosY());
        int distance = distX + distY;

        for(int i = 1; i < listeAdversaire.size(); i++){
            distX = Math.abs(this.getPosX() - listeAdversaire.get(i).getPosX());
            distY = Math.abs(this.getPosY() - listeAdversaire.get(i).getPosY());
            int distanceBis = distX + distY;
            if(distanceBis < distance){
                lePlusProche = listeAdversaire.get(i);
            }
        }

        return lePlusProche;
    }


}
