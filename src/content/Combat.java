package content;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Combat {
    private Joueur joueur;
    private ArrayList<Adversaire> listeAdversaire;
    private Adversaire adversaireFocus;
    private Personnage gagnant;
    private String statutCombat;
    private Plateau plateau;

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public ArrayList<Adversaire> getListeAdversaire() {
        return listeAdversaire;
    }

    public void setListeAdversaire(ArrayList<Adversaire> listeAdversaire) {
        this.listeAdversaire = listeAdversaire;
    }

    public Adversaire getAdversaireFocus() {
        return adversaireFocus;
    }

    public void setAdversaireFocus(Adversaire adversaireFocus) {
        this.adversaireFocus = adversaireFocus;
    }

    public String getStatutCombat() {
        return statutCombat;
    }

    public void setStatutCombat(String statutCombat) {
        this.statutCombat = statutCombat;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Personnage getGagnant() {
        return gagnant;
    }

    public void setGagnant(Personnage gagnant) {
        this.gagnant = gagnant;
    }


    public enum Statut {
        EN_COURS("En cours"),
        TERMINE("Terminé");

        private String statut;

        Statut(String s) {
            this.statut = s;
        }

        public String getStatut(){
            return this.statut;
        }
    }



    ///////////////////////////////////
    // INSTANCIE UNE NOUVELLE PARTIE //
    ///////////////////////////////////
        //
    public Combat(Joueur j, ArrayList<Adversaire> listeAdversaire){
        Statut statut = Statut.EN_COURS;

        this.plateau = new Plateau(12,8,j,listeAdversaire);
        this.joueur = j;
        this.listeAdversaire = listeAdversaire;
        this.gagnant = null;
        this.statutCombat = statut.getStatut();
    }


    ////////////////////////
    // COMMENCE LA PARTIE //
    ////////////////////////
        //
    public void start() {
        joueur.setFace("o_o");
        joueur.setHp(joueur.getHpmax());

        for (Adversaire a : listeAdversaire) {
            a.setHp(a.getHpmax());
        }

        while (this.statutCombat != Statut.TERMINE.getStatut()) {
            if (joueur.isKO()) {
                System.out.println(joueur.getNom() + " est KO!");
                break;
            }

            this.adversaireFocus = joueur.getEnnemiLePlusProche(listeAdversaire);


            while (!joueur.estAssezProchePourSeBattre(adversaireFocus)) {
                joueur.setFace("o_o");
                //Si le joueur est sur une diagonale du monstre
                if ((Math.abs(joueur.getPosX() - adversaireFocus.getPosX()) == 1) && (Math.abs(joueur.getPosY() - adversaireFocus.getPosY()) == 1)) {
                    joueur.seDeplacer(adversaireFocus.getPosX() - joueur.getPosX(), 0);
                } else {
                    joueur.seDeplacerVers(adversaireFocus);
                }

                verifiePosition();
                dessinePlateau();
            }



            this.adversaireFocus.setEstAttaque(true);

            while (!adversaireFocus.isKO()) {
                sleep(500);
                System.out.println("--------------------------------");
                lanceCombat("1");
                joueur.setFace("ò.ó");


                verifiePosition();

                adversaireFocus.setFace(">.<");
                if (joueur.isKO()) {
                    joueur.setFace("x_x");
                    break;
                }
                dessinePlateau();
            }

            adversaireFocus.setFace("x_x");
            dessinePlateau();
            System.out.println(joueur.getNom() + " a vaincu " + adversaireFocus.getNom());


            listeAdversaire.remove(adversaireFocus);

            if (listeAdversaire.isEmpty()) {
                this.gagnant = joueur;
                this.statutCombat = Statut.TERMINE.getStatut();
                joueur.setFace("\\^o^/");
                System.out.println(joueur.getNom() + " a vaincu tout les monstres!");
                dessinePlateau();
            }

        }
    }

    private void verifiePosition() {
        for (Adversaire a : listeAdversaire) {
            int posXcache = a.getPosX();
            int posYcache = a.getPosY();

            a.mouvementAleatoire();

            while (a.conflitPosition(joueur) || a.conflitPosition(plateau)) {
                a.setPosX(posXcache);
                a.setPosY(posYcache);
                a.mouvementAleatoire();
            }

            /*for (Adversaire b : listeAdversaire) {
                while (a.conflitPosition(b)) {
                    a.setPosX(posXcache);
                    a.setPosY(posYcache);
                    a.mouvementAleatoire();
                }
            }*/
        }
    }


    ///////////////////////////////////////////////
    // DEMANDE DU CHOIX AU JOUEUR VIA LA CONSOLE //
    ///////////////////////////////////////////////
        //
    /*public String menuCombat(){

        System.out.println(this.joueur.getNom() + " (Vous) - " + this.joueur.getHp() + "/" + this.joueur.getHpmax() +" HP");
        System.out.println(this.adversaire.getNom() + " - " + this.adversaire.getHp() + "/" + this.adversaire.getHpmax() + " HP");

        System.out.println("");
        System.out.println("[1] Attaquer");
        System.out.println("[2] Se soigner");
        String choix = "";

        while(choix == "") {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                choix = reader.readLine();

                while(!choix.matches("[1-9]{1}")){
                    System.out.println("> Hmm, l'action demandée ne semble pas exister. Veuillez réessayer");
                    choix = System.console().readLine();
                }

            } catch (NullPointerException e) {
                choix = "";
            } catch (IOException e) {
                choix = "";
            }
        }

        return choix;
    }*/

    ////////////////////////////////
    // EXECUTE LE CHOIX DU JOUEUR //
    ////////////////////////////////
        //
    public void action(String action){
        switch (action){
            case "1": joueur.attaque(adversaireFocus);
                break;
            case "2": joueur.soigne();
                break;
        }

    }

    //////////////////////////////////////////////
    // LANCE LE COMBAT SELON LE CHOIX DU JOUEUR //
    //////////////////////////////////////////////
        //
    public void lanceCombat(String choix){ //TODO: Développer une IA pour les adversaire, afin de varier les actions
        if(joueur.getStats().getVitesse().getValue() > adversaireFocus.getStats().getVitesse().getValue()){
            action(choix);
            adversaireFocus.attaque(joueur);
        }else{
            adversaireFocus.attaque(joueur);
            action(choix);
        }
    }

    public static void sleep(long temps){
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    


    public void dessinePlateau(){

        System.out.println("--------------------------------");
        System.out.println(joueur.getNom() + ": " + joueur.getHp() + "/" + joueur.getHpmax());
        System.out.println(adversaireFocus.getNom() + ": " + adversaireFocus.getHp() + "/" + adversaireFocus.getHpmax());
        System.out.println("--------------------------------");
        this.plateau = new Plateau(12,8,joueur,listeAdversaire);
        for(int x = 0; x< 12; x++){
            for(int y = 0; y< 8; y++){
                System.out.print(plateau.getPlateau()[x][y]);
            }
            System.out.println();
        }
        System.out.println("\n\n\n");

    }



}
