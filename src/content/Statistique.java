package content;

public class Statistique {
    private String nom;
    private int value;

    public Statistique(String nom, int value){
        this.nom = nom;
        this.value = value;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int value){
        this.value += value;
    }


}
