package content;

import java.util.ArrayList;

public class Inventaire {
    private ArrayList<Item> contenu;

    public ArrayList<Item> getContenu() {
        return contenu;
    }

    public void setContenu(ArrayList<Item> contenu) {
        this.contenu = contenu;
    }

    public void addItem(Item item){
        this.contenu.add(item);
    }

    public void removeItem(Item item){
        this.contenu.remove(item);
    }

    public Inventaire(ArrayList<Item> contenu){
        this.contenu = contenu;
    }
}
