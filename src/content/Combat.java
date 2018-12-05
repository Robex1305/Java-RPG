package content;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Combat {
    private Adversaire adversaire;
    private Joueur joueur;
    private Personnage gagnant;
    private String statutCombat;

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
    };



    ///////////////////////////////////
    // INSTANCIE UNE NOUVELLE PARTIE //
    ///////////////////////////////////
        //
    public Combat(Joueur j, Adversaire a){
        Statut statut = Statut.EN_COURS;

        this.adversaire = a;
        this.joueur = j;
        this.gagnant = null;
        this.statutCombat = statut.getStatut();
    }


    ////////////////////////
    // COMMENCE LA PARTIE //
    ////////////////////////
        //
    public Personnage start(){

        joueur.setHp(joueur.getHpmax());
        adversaire.setHp(adversaire.getHpmax());

        System.out.println("Que le combat commence!");
        System.out.println("");
        System.out.println("> Choisissez une action");
        while(this.statutCombat != Statut.TERMINE.getStatut()){

            if(adversaire.isKO()){
                this.gagnant = joueur;
                this.statutCombat = Statut.TERMINE.getStatut();
                break;
            }else if(joueur.isKO()){
                this.gagnant = adversaire;
                this.statutCombat = Statut.TERMINE.getStatut();
                break;
            }

            String choix = menuCombat();
            lanceCombat(choix);
        }

        System.out.println("Le combat est terminé! Le gagnant est... " + this.gagnant.getNom() + "!");
        return this.gagnant;

    }


    ///////////////////////////////////////////////
    // DEMANDE DU CHOIX AU JOUEUR VIA LA CONSOLE //
    ///////////////////////////////////////////////
        //
    public String menuCombat(){

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
    }

    ////////////////////////////////
    // EXECUTE LE CHOIX DU JOUEUR //
    ////////////////////////////////
        //
    public void action(String action){
        switch (action){
            case "1": joueur.attaque(adversaire);
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
        if(joueur.getStats().getVitesse().getValue() > adversaire.getStats().getVitesse().getValue()){
            action(choix);
            adversaire.attaque(joueur);
        }else{
            adversaire.attaque(joueur);
            action(choix);
        }
    }




}
