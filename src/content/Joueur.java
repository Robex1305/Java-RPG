package content;

import javafx.scene.image.Image;

import java.util.List;

public class Joueur extends Personnage{

    public Joueur(String nom, Statistiques stats){

        super(nom, stats);
        Image pic = new Image("./img/default.png");
        this.setPicture(pic);
    }

    public Adversaire getEnnemiLePlusProche(List<Adversaire> listeAdversaire) {

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

    @Override
    public void seDeplacer(int dX, int dY) {
        super.seDeplacer(dX, dY);
        if(getIsWalking() == 1){
            Image pic = new Image("./img/default.png");
            setPicture(pic);
            setIsWalking(0);
        }else{
            Image pic = new Image("./img/sedeplace.png");
            setPicture(pic);
            setIsWalking(1);
        }
    }

    @Override
    public void attaque(Personnage adversaire) {
        super.attaque(adversaire);


        if (getIsAttacking() == 1) {
            Image pic = new Image("./img/default.png");
            setPicture(pic);
            setIsAttacking(0);
        } else {
            Image pic = new Image("./img/attaque.png");
            setPicture(pic);
            setIsAttacking(1);
        }

    }
}
