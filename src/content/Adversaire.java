package content;

import java.io.File;

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
        this.setSkin(new File("src\\img\\joueur.png"));
        this.setFace("*_*");
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

}
