package shootEntity;

import shootEntity.Player;
import javafx.scene.Node;
import shoot.shootprojet;

public class Power extends Item {
	protected int bonusDegat;
	
	public Power() {}

	public void setBonusDegat() {
		this.bonusDegat = (int)(2 + Math.random() * 4);
	}
	
	public void setVie(int valeur) {
		this.bonusDegat = valeur;
	}

	@Override
	public void itemEffet() {
		shootprojet.player.setDegat(shootprojet.player.getDegat() + this.bonusDegat);
	}
}
