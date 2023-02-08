package shootEntity;


import javafx.scene.Node;
import shoot.shootprojet;

public abstract class Item {
    protected Node node;
    protected int prix;
    protected int vitesse;
    protected String type;
    protected String img;
    
    public abstract void itemEffet();
    //public Item() {}
    public void Item_Caracteristique(int prix, int vitesse, String type, String img) {
        this.prix = prix;
        this.vitesse = vitesse;
        this.type = type;
        this.img = img;
    }
    
    public void setImage(String img) {
    	this.img = img;
    }
    
    public void setBlock(Node node) {
        this.node = node;
    }
    
    public Node getNode() {
    	return this.node;
    }
    
    public boolean MoveItem() {
    	this.node.setTranslateY(this.node.getTranslateY() + vitesse);
    	if(this.node.getBoundsInParent().intersects(shootprojet.player.node.getBoundsInParent())) {
    		if(this.type == "utilisable") {
    			boolean full = shootprojet.player.inventaire.inventaireFull();
    			if(full) {
    				return false;
    			}
    			else {
    				shootprojet.player.inventaire.addUtilisable(this);
    				shootprojet.AffichageDecors.getChildren().remove(this.getNode());
        			shootprojet.ListItem.remove(this);
        			return true;
    			}
    		}
    		if(this.type == "equipement") {
    			if(shootprojet.player.inventaire.Liste_equipement.size() >= 3) {
    				return false;
        		}
    			else {
    				shootprojet.player.inventaire.addEquipement(this);
    				shootprojet.AffichageDecors.getChildren().remove(this.getNode());
        			shootprojet.ListItem.remove(this);
        			return true;
    			}
    		}
    	}
    	return false;
    }
    
    public int Utiliser(int valeur) {
    	return valeur;
    }
    
}