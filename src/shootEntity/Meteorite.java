package shootEntity;

import javafx.scene.Node;
import shoot.shootprojet;

public class Meteorite extends Enemie{
	
	protected String direction;
	protected boolean canTouch = true;
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setCanTouch(boolean canTouch) {
		this.canTouch = canTouch;
	}

	public void MoveEnemie() {
		if(moverigth) {
			if(this.node.getTranslateX() < shootprojet.hauteur - 100) {
				this.node.setTranslateX(this.node.getTranslateX() + this.vitesse);
				this.node.setTranslateY(this.node.getTranslateY() + this.vitesse);
			}
			else {
				moverigth = false;
			}
		}
		else {
			if(this.node.getTranslateX() > 10) {
				this.node.setTranslateX(this.node.getTranslateX() - this.vitesse);
				this.node.setTranslateY(this.node.getTranslateY() + this.vitesse);
			}
			else {
				moverigth = true;
			}
		}
		if(this.node.getBoundsInParent().intersects(shootprojet.player.getNode().getBoundsInParent()) && canTouch == true) {
			shootprojet.AffichageDecors.getChildren().remove(this.getNode());
			shootprojet.ListEnemie.remove(this);
			shootprojet.player.PerdreHP(this.degat);
			HUD.ChangeHP(this.degat);
			this.enemie_remove = true;
			shootprojet.player.IsAlive();
		}
		if(this.node.getTranslateY() > 650) {
			shootprojet.AffichageDecors.getChildren().remove(this.getNode());
			shootprojet.ListEnemie.remove(this);
			this.enemie_remove = true;
		}
	}
	
}
