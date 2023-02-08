package shootEntity;

import shootEntity.Player;

import javafx.scene.Node;
import shoot.shootprojet;

public class Potion extends Item{
	protected int pointDeVie;
	
	public Potion() {}
	
	public void setVie() {
		this.pointDeVie = (int)(2 + Math.random() * 4);
	}
	
	public void setVie(int valeur) {
		this.pointDeVie = valeur;
	}

	@Override
	public void itemEffet() {
		shootprojet.player.PerdreHP(-this.pointDeVie);
		HUD.ChangeHP(-this.pointDeVie);
		shootprojet.player.IsAlive();
	}
}
