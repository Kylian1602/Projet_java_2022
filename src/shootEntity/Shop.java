package shootEntity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shoot.shootprojet;
import shootEntity.PNJ.Effacer_Dialog;

public class Shop {
	
	protected boolean shop_open = true;
	protected ArrayList<Item> Liste_itemShop = new ArrayList<Item>();
	protected Item item1;
	protected Item item2;
	protected Item item3;
	protected Item item4;
	protected Text msg_err = new Text();
	protected String err;
	Timer timer;
	
	public Shop() {
		msg_err.setX(500);
		msg_err.setY(200);
		msg_err.setFill(Color.RED);
		msg_err.setFont(Font.font("arial", 20));
	    shootprojet.AffichageHUD.getChildren().add(msg_err);
	}
	
	public void Shop_open() {
		item1 = new Potion();
		item1.Item_Caracteristique(2, 0, "utilisable", "7");
		item1.node = shootprojet.CreationBlock(400, 250, 70, 70, item1.img);
		Liste_itemShop.add(item1);
		
		item2 = new Power();
		item2.Item_Caracteristique(2, 0, "equipement", "6");
		item2.node = shootprojet.CreationBlock(600, 250, 70, 70, item2.img);
		Liste_itemShop.add(item2);
		
		item3 = new ObstacleItem();
		item3.Item_Caracteristique(3, 0, "utilisable", "12");
		item3.node = shootprojet.CreationBlock(800, 250, 70, 70, item3.img);
		Liste_itemShop.add(item3);
		
		item4 = new invisible();
		item4.Item_Caracteristique(3, 0, "utilisable", "14");
		item4.node = shootprojet.CreationBlock(400, 360, 70, 70, item4.img);
		Liste_itemShop.add(item4);
		
		for(Item item : Liste_itemShop) {
			item.getNode().setOnMouseClicked((e) -> {
				acheter_item(item);
			});
		}
	}
	
	//supprimer un item de la liste pendant le for cause un bug on passe par une liste intermediaire
	public void Shop_close() {
		ArrayList<Item> Liste_itemShop2 = new ArrayList<Item>();
		for(Item item : Liste_itemShop) {
			Liste_itemShop2.add(item);
		}
		for(Item item : Liste_itemShop2){
			shootprojet.AffichageDecors.getChildren().remove(item.getNode());
			Liste_itemShop.remove(item);
		}
	}
	
	public void acheter_item(Item item) {
		boolean inventaireFull = shootprojet.player.inventaire.inventaireFull();
		if(inventaireFull == true) {
			err = "inventaire plein";
		}
		else {
			if(shootprojet.player.inventaire.totalArgent >= item.prix) {
				if(item.type == "utilisable") {
					shootprojet.player.inventaire.addUtilisable(item);
					err = "";
				}
				if(item.type == "equipement") {
					shootprojet.player.inventaire.addEquipement(item);
					err = "";
				}
				shootprojet.player.inventaire.totalArgent -= item.prix;
				HUD.changeTextArgent();
				//si on veut un stock limiter dans le shop decommenter
				/*shootprojet.AffichageDecors.getChildren().remove(item.getNode());
				Liste_itemShop.remove(item);*/
			}
			else {
				err = "pas assez d'argent";
			}
		}
		afficher_msg_err(err);
	}
	
	class Effacer_Dialog extends TimerTask {
        public void run() {
        	msg_err.setText("");
        }
    }
	
	public void afficher_msg_err(String err) {
		msg_err.setText(err);
		timer = new Timer();
        timer.schedule(new Effacer_Dialog(), 3000);
	}
	
	
}
