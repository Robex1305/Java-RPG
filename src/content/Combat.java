package content;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Combat {
    private Joueur joueur;
    private List<Adversaire> listeAdversaire;
    private double arenaXlength;
    private double arenaYlength;

    public List<Personnage> getListePersonnage() {
        List<Personnage> l = new ArrayList<Personnage>();
        l.add(joueur);
        l.addAll(listeAdversaire);
        return l;

    }

    private ArrayList<Personnage> listePersonnage;
    private Adversaire adversaireFocus;
    private Personnage gagnant;
    private String statutCombat;
    private int sleepTime;

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public List<Adversaire> getListeAdversaire() {
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

        public String getStatut() {
            return this.statut;
        }
    }


    ///////////////////////////////////
    // INSTANCIE UNE NOUVELLE PARTIE //
    ///////////////////////////////////
    //
    public Combat(Joueur j, ArrayList<Adversaire> listeAdversaire, double arenaXlength, double arenaYlength) {
        sleepTime = 20;
        Statut statut = Statut.EN_COURS;

        this.arenaXlength = arenaXlength;
        this.arenaYlength = arenaYlength;
        this.joueur = j;
        this.listeAdversaire = listeAdversaire;
        this.gagnant = null;
        this.statutCombat = statut.getStatut();

        for (Personnage p : getListePersonnage()) {
            p.setPosX(50 + (int) (Math.random() * arenaXlength - 100));
            p.setPosY(50 +(int) (Math.random() * arenaYlength - 100));
        }
    }

    public int getSleepTime() {
        return sleepTime;
    }

    ////////////////////////
    // COMMENCE LA PARTIE //
    ////////////////////////
    //
    public void start() {
        joueur.setHp(joueur.getHpmax());

        for (Adversaire a : listeAdversaire) {
            a.setHp(a.getHpmax());
        }
    }

    public void nextRound() {
        if (this.statutCombat != Statut.TERMINE.getStatut()) {
            if (this.adversaireFocus == null) {
                this.adversaireFocus = joueur.getEnnemiLePlusProche(listeAdversaire);
            }

            if (!joueur.estAssezProchePourSeBattre(adversaireFocus)) {
                joueur.seDeplacerVers(adversaireFocus);
            } else {
                joueur.attaque(adversaireFocus);
                this.adversaireFocus.setEstAttaque(true);
                if (!adversaireFocus.isKO()) {
                    adversaireFocus.attaque(joueur);
                } else {
                    listeAdversaire.remove(adversaireFocus);
                    this.adversaireFocus = null;
                }
            }

            getListeAdversaire().forEach(a -> {
                a.mouvementAleatoire();
            });

            if (listeAdversaire.isEmpty() ||joueur.isKO()) {
                this.statutCombat = Statut.TERMINE.getStatut();
            }
        }

    }


    private void verifiePosition() {
//        for (Adversaire a : listeAdversaire) {
//            int posXcache = a.getPosX();
//            int posYcache = a.getPosY();
//
//            a.mouvementAleatoire();
//
//            while (a.conflitPosition(joueur) || conflitPosition(a)) {
//                a.setPosX(posXcache);
//                a.setPosY(posYcache);
//                a.mouvementAleatoire();
//            }
//
//
//        }
    }

    public boolean conflitPosition(Personnage p) {
        if (p.getPosX() + 1 > arenaXlength) {
            return true;
        }
        else if (p.getPosX() - 1 < 0) {
            return true;
        }
        else if (p.getPosY() > arenaYlength) {
            return true;
        }
        else if (p.getPosY() - 1 < 0) {
            return true;
        }else{
            return false;
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
            joueur.attaque(adversaireFocus);
            adversaireFocus.attaque(joueur);
        }else{
            adversaireFocus.attaque(joueur);
            joueur.attaque(adversaireFocus);
        }
    }

    public static void sleep(long temps){
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
