package shootEntity;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import shoot.shootprojet;

public abstract class Enemie extends Personnage{
    protected boolean moverigth = true;
    protected boolean canShoot = true;
    protected int argent;
    protected String type;
    protected boolean enemie_remove = false;

    public Enemie() {
    	this.couleur = "5";
    	this.direction = 800;
    }
    
    public boolean getEnemie_remove() {
		return this.enemie_remove;
	}
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public void setArgent(int argent) {
    	this.argent = argent;
    }
    
    public int getArgent() {
    	return this.argent;
    }
    
    
    public abstract void MoveEnemie();
    
    /*public void MoveEnemie() {
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
    }*/
    
    class MakeCanShootTrue extends TimerTask {
        public void run() {
            canShoot = true;
        }
    }
    
    public boolean ShootUp() {
    	if(canShoot == true) {
    		canShoot = false;
    		Timer timer = new Timer();
    		timer.schedule(new MakeCanShootTrue(), 5000);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    public void VerifShoot(Node Tir) {
    	if(Tir.getBoundsInParent().intersects(shootprojet.player.getNode().getBoundsInParent()) && this.cible_toucher == false){
        	shootprojet.AffichageDecors.getChildren().remove(Tir);
        	shootprojet.player.PerdreHP(this.degat);
        	HUD.ChangeHP(this.degat);
        	this.cible_toucher = true;
        	shootprojet.player.IsAlive();
        }
	}
        
}