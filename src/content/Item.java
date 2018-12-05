package content;


public class Item {
    private String nom;
    private Statistiques bonus;

    public Item(String nom, int force, int endurance, int chance, int vitesse, int precision, int esquive, int degats, int armure){
        this.nom = nom;
        this.bonus = new Statistiques(force, endurance, chance, vitesse, precision, esquive, degats, armure);
    }

    public Statistiques getBonus() {
        return bonus;
    }

    public void setBonus(Statistiques bonus) {
        this.bonus = bonus;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
