package content;

import java.util.ArrayList;

public class Equipement {
    private ArrayList<Item> equipement;

    public ArrayList<Item> getEquipement() {
        return equipement;
    }

    public void setEquipement(ArrayList<Item> equipe) {
        this.equipement = equipe;
    }

    public void equipeItem(Item item){
        this.equipement.add(item);
    }

    public void desequipeItem(Item item){
        this.equipement.remove(item);
    }

    public Equipement(ArrayList<Item> equipement){
        this.equipement = equipement;
    }


}
