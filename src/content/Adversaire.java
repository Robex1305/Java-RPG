package content;

import javafx.scene.image.Image;


public class Adversaire extends Personnage{

    private boolean estAttaque = false;

    public boolean isAttaque() {
        return estAttaque;
    }

    public void setEstAttaque(boolean estAttaque) {
        this.estAttaque = estAttaque;
    }

    public Adversaire(String nom, Statistiques stats){
        super(nom, stats);
        this.setFace("*_*");
        Image pic = new Image("./img/orcdefault.png");
        this.setPicture(pic);
    }

    public int[] mouvementAleatoire() {
        if (!estAttaque) {
            int coefX = 0;
            int coefY = 0;

            double rand = Math.random();

            if (rand > 0.6) {
                coefX = -1;
            }else if (rand < 0.4) {
                coefX = 1;
            }

            rand = Math.random();

            if (rand > 0.6) {
                coefY = -1;
            }else if (rand < 0.4) {
                coefY = 1;
            }

            this.seDeplacer(coefX, coefY);
            return new int[]{coefX,coefY};
        }
        return new int[]{0,0};
    }

    @Override
    public void seDeplacer(int dX, int dY) {
        super.seDeplacer(dX, dY);
        if(getIsWalking() == 1){
            Image pic = new Image("./img/orcdefault.png");
            setPicture(pic);
            setIsWalking(0);
        }else{
            Image pic = new Image("./img/orcsedeplace.png");
            setPicture(pic);
            setIsWalking(1);
        }
    }

    @Override
    public void attaque(Personnage adversaire) {
        super.attaque(adversaire);


        if (getIsAttacking() == 1) {
            Image pic = new Image("./img/orcdefault.png");
            setPicture(pic);
            setIsAttacking(0);
        } else {
            Image pic = new Image("./img/orcattaque.png");
            setPicture(pic);
            setIsAttacking(1);
        }

    }
}
