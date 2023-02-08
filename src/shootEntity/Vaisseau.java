package shootEntity;

import javafx.scene.Node;
import shoot.shootprojet;

public class Vaisseau extends Enemie {
	
	public void MoveEnemie() {
    	if(moverigth) {
    		if(this.node.getTranslateX() <= shootprojet.hauteur - 100) {
    			this.node.setTranslateX(this.node.getTranslateX() + vitesse);
    		}
    		else {
    			moverigth = false;
    		}
    	}
    	else {
       		if(moverigth == false && this.node.getTranslateX() >= 10) {
        		this.node.setTranslateX(this.node.getTranslateX() - vitesse);
        	}
       		else {
       			moverigth = true;
       		}
    	}
	}
	
}
