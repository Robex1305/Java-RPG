package content;


public class Statistiques {

    //////////////////////////
    // VALEURS STATISTIQUES //
    //////////////////////////

    private Statistique force;
    private Statistique endurance;
    private Statistique chance;
    private Statistique precision;
    private Statistique esquive;
    private Statistique vitesse;


    private Statistique armure;
    private Statistique degats;

    private double reductionDeDegats;


    ////////////////////////////
    // INTITULÉS STATISTIQUES //
    ////////////////////////////

    public static final String FORCE = "Force";
    public static final String ENDURANCE = "Endurance";
    public static final String CHANCE = "Chance";
    public static final String VITESSE = "Vitesse";
    public static final String PRECISION = "Précision";
    public static final String ESQUIVE = "Esquive";

    public static final String ARMURE = "Armure";
    public static final String DEGATS = "Degat";



    public Statistique getArmure() {
        return armure;
    }

    public void setArmure(Statistique armure) {
        this.armure = armure;
    }

    public Statistique getDegats() {
        return degats;
    }

    public void setDegats(Statistique degats) {
        this.degats = degats;
    }

    public Statistique getForce() {
        return force;
    }

    public void setForce(Statistique force) {
        this.force = force;
    }

    public Statistique getEndurance() {
        return endurance;
    }

    public void setEndurance(Statistique endurance) {
        this.endurance = endurance;
    }

    public Statistique getChance() {
        return chance;
    }

    public void setChance(Statistique chance) {
        this.chance = chance;
    }

    public Statistique getPrecision() {
        return precision;
    }

    public void setPrecision(Statistique precision) {
        this.precision = precision;
    }

    public Statistique getEsquive() {
        return esquive;
    }

    public void setEsquive(Statistique esquive) {
        this.esquive = esquive;
    }

    public Statistique getVitesse() {
        return vitesse;
    }

    public void setVitesse(Statistique vitesse) {
        this.vitesse = vitesse;
    }

    public double getReductionDeDegats() {
        return reductionDeDegats;
    }

    public void setReductionDeDegats(double reductionDeDegats) {
        this.reductionDeDegats = reductionDeDegats;
    }


    public Statistiques(int force, int endurance, int chance, int precision, int esquive, int vitesse){
        this.force = new Statistique(FORCE,force);
        this.endurance = new Statistique(ENDURANCE,endurance);
        this.chance = new Statistique(CHANCE,chance);
        this.vitesse = new Statistique(VITESSE,vitesse);
        this.precision = new Statistique(PRECISION,precision);
        this.esquive = new Statistique(ESQUIVE,esquive);

        this.degats = new Statistique(DEGATS,1);
        this.armure = new Statistique(ARMURE,0);

        calculReductionDeDegats();
    }

    public Statistiques(int force, int endurance, int chance, int precision, int esquive, int vitesse, int degats, int armure){
        this.force = new Statistique(FORCE,force);
        this.endurance = new Statistique(ENDURANCE,endurance);
        this.chance = new Statistique(CHANCE,chance);
        this.vitesse = new Statistique(VITESSE,vitesse);
        this.precision = new Statistique(PRECISION,precision);
        this.esquive = new Statistique(ESQUIVE,esquive);

        this.degats = new Statistique(DEGATS,degats);
        this.armure = new Statistique(ARMURE,armure);

        calculReductionDeDegats();
    }



    public void upValue(String nom, int value){
        switch (nom){
            case FORCE: this.force.add(value); break;
            case ENDURANCE: this.endurance.add(value); break;
            case CHANCE: this.chance.add(value); break;
            case VITESSE: this.vitesse.add(value); break;
            case PRECISION: this.precision.add(value); break;
            case ESQUIVE: this.esquive.add(value); break;
            case ARMURE: this.armure.add(value); break;
            case DEGATS: this.degats.add(value); break;
        }
    }
    ///////////////////////////////////////////////
    // REDUIT LES DEGATS SUBIT SELON L'ENDURANCE //
    ///////////////////////////////////////////////
        //
    public void calculReductionDeDegats(){ //TODO: Ajouter une statistique Armure
         this.reductionDeDegats = 0.005 * (double) armure.getValue();
    }



}
