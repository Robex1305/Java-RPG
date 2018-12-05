package content;

import java.util.ArrayList;

public abstract class Personnage {
    private String nom;
    private int hp;
    private int hpmax;
    private Statistiques stats;
    private Statistiques statsDebase;
    private Equipement equipement;
    private Inventaire inventaire;

    public Statistiques getStatsDebase() {
        return statsDebase;
    }

    public void setStatsDebase(Statistiques statsDebase) {
        this.statsDebase = statsDebase;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }


    public int getHpmax() {
        return hpmax;
    }

    public void setHpmax(int hpmax) {
        this.hpmax = hpmax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Statistiques getStats() {
        return stats;
    }

    public void setStats(Statistiques stats) {
        this.stats = stats;
        this.setHpmax(this.hp = 100 + 10 * this.getStatsDebase().getEndurance().getValue());
    }

    public Personnage(String nom, Statistiques stats) {
        this.nom = nom;
        this.statsDebase = stats;
        this.hp = 100 + 10 * this.getStatsDebase().getEndurance().getValue();
        this.hpmax = hp;
        this.equipement = new Equipement(new ArrayList<Item>());
        this.rafraichirStatsEquipement();
    }


    public void attaque(Personnage adversaire){
        int degats = this.getStats().getDegats().getValue();
        boolean jetChance = this.jetChance(adversaire);
        boolean touche = this.chanceDeToucher(adversaire);

        if(!this.isKO()) {
            if (touche) {
                degats++;
                if (jetChance) {
                    degats = (int) Math.round(degats * 2);
                    System.out.print(">> Coup critique << ");
                }
                degats = (int) ((degats + this.jetForce()) * (1 - adversaire.getStats().getReductionDeDegats()));

                adversaire.setHp(adversaire.getHp() - degats);
                System.out.println("Ouch! " + this.getNom() + " inflige " + degats + " dégats à " + adversaire.getNom());
            }else {
                System.out.println(this.getNom() + " rate son attaque!");
            }
        }
    }


    ///////////////////////////////////////////////////
    // SOIGNE LE JOUEUR EN FONCTION DE SON ENDURANCE //
    ///////////////////////////////////////////////////
        //
    public void soigne(){ // TODO: Creer des sorts afin de remplacer le choix "Se soigner"
        int jet = this.jetEndurance();
        if(this.hp == this.hpmax){
            System.out.println(this.getNom() + " ne peut se soigner d'avantage");
        }else {
            int differenceHP = this.getHpmax() - this.getHp();
            this.hp = this.hp + (jet);

            if (this.hp > this.hpmax) {
                this.setHp(this.getHpmax());
                jet = differenceHP;
            }
            System.out.println(this.getNom() + " se soigne et regagne " + jet + "HP");
        }
    }

    public boolean isKO(){
        if(this.getHp() > 0){
            return false;
        }else{
            return true;
        }
    }


    ////////////////////////////////////////////////////////////////////
    // CALCULE SI L'ACTION SPECIALE REALISÉE PAR LE PERSONNAGE REUSSI //
    ////////////////////////////////////////////////////////////////////
        //
    public boolean jetChance(Personnage adversaire){
        boolean succes = false;
        double jetP1 = Math.random() * this.getStats().getChance().getValue();
        double jetP2 = Math.random() * adversaire.getStats().getChance().getValue();
        if(jetP1 > jetP2 * 3){
            succes = true;
        }

        return succes;
    }

    public int jetForce(){
        int jetP = (int) Math.round(this.getStats().getForce().getValue() * ((Math.random()) / 4));
        return jetP;
    }

    public int jetEndurance(){
        int jetP = (int) ((0.5+Math.random()) * this.getStats().getEndurance().getValue());
        return jetP;
    }

    public double jetPrecision(){
        double jetP = ((1+Math.random()) * this.getStats().getPrecision().getValue());
        return jetP;
    }

    public double jetEsquive(){
        double jetP = ((Math.random()/10) * this.getStats().getEsquive().getValue());
        return jetP;
    }


    ////////////////////////////////////////////////
    // CALCULE LES CHANCE QUE L'ATTAQUE REUSSISSE //
    ////////////////////////////////////////////////
        //
    public boolean chanceDeToucher(Personnage adversaire){
        if(this.jetPrecision() > adversaire.jetEsquive()){
            return true;
        }else{
            return false;
        }
    }

    public void rafraichirStatsEquipement(){
        Statistiques statsCalcul = this.getStatsDebase();
        for(Item item: equipement.getEquipement()){
            statsCalcul.upValue(Statistiques.FORCE, item.getBonus().getForce().getValue());
            statsCalcul.upValue(Statistiques.ENDURANCE, item.getBonus().getEndurance().getValue());
            statsCalcul.upValue(Statistiques.CHANCE, item.getBonus().getChance().getValue());
            statsCalcul.upValue(Statistiques.VITESSE, item.getBonus().getVitesse().getValue());
            statsCalcul.upValue(Statistiques.PRECISION, item.getBonus().getPrecision().getValue());
            statsCalcul.upValue(Statistiques.ESQUIVE, item.getBonus().getEsquive().getValue());

            statsCalcul.upValue(Statistiques.DEGATS, item.getBonus().getDegats().getValue());
            statsCalcul.upValue(Statistiques.ARMURE, item.getBonus().getArmure().getValue());
        }


        this.setStats(statsCalcul);

    }

    public void equiperObjet(Item i){
        this.equipement.equipeItem(i);
    }

    public void desequiperObjet(Item i){
        this.equipement.desequipeItem(i);

    }





}
