package content;

import java.util.ArrayList;

public class Plateau {
    private String[][] plateau;
    private int longueur;
    private int hauteur;





    public String[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(String[][] plateau) {
        this.plateau = plateau;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int largeur) {
        this.hauteur = largeur;
    }

    public void addPersonnage(Personnage p) {

    }

    public Plateau(int longueur, int hauteur, Joueur j, ArrayList<Adversaire> adversaires) {


        String[][] plateau = new String[longueur][hauteur];
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 8; y++) {
                plateau[x][y] = "░░";//█
            }
        }

        plateau[j.getPosX()][j.getPosY()] = j.getFace();

        for (Adversaire a : adversaires) {

            plateau[a.getPosX()][a.getPosY()] = a.getFace();
        }

        this.plateau = plateau;
        this.longueur = longueur - 1;
        this.hauteur = hauteur - 1;
    }



}
