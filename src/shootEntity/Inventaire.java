package shootEntity;

import java.util.ArrayList;

import shoot.shootprojet;

public class Inventaire {
	
	protected int nbItem = 3;
	protected ArrayList<Item> Liste_item = new ArrayList<Item>();
	protected ArrayList<Item> Liste_equipement = new ArrayList<Item>();
	protected int totalArgent = 0;
	
	public Inventaire() {
		for(int i = 0; i<nbItem; i++) {
			Liste_item.add(null);
		}
	}
	
	public int getArgent() {
		return this.totalArgent;
	}
	
	public boolean inventaireFull() {
		int compteur = 0;
		for(int i = 0; i<nbItem; i++) {
			if(Liste_item.get(i) != null) {
				compteur = compteur + 1;
			}
		}
		if(compteur == 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addUtilisable(Item item) {
			for(int i =0; i<Liste_item.size(); i++) {
				if(Liste_item.get(i) == null) {
					Liste_item.set(i,item);
					HUD.addItem(item.img,i);
					System.out.println(Liste_item.indexOf(item));
					return;
				}
			}
		//Liste_item.add(item);
		//HUD.addItem(item.img,Liste_item.indexOf(item));
	}
	
	public void addEquipement(Item item) {
		item.itemEffet();
		Liste_equipement.add(item);
	}
	
	public void perdreEquipement(Item item) {
		Liste_equipement.remove(item);
	}
	
	public void useConsomable(int index) {
		Item item = Liste_item.get(index);
		item.itemEffet();
		Liste_item.set(index,null);
	}

}
