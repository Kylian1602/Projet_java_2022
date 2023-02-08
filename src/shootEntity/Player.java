package shootEntity;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import shoot.shootprojet;

public class Player extends Personnage {
	
	public Timer timer;
	protected boolean canShoot = true;
	protected boolean delayClick = false;
	protected Inventaire inventaire = new Inventaire();
	protected int CompteurEnemie = 0;
	protected int nbVictoire = 20;
	protected boolean canBeTouchByMeteorite = true;
	
	public int getCompteurEnemie() {
		return this.CompteurEnemie;
	}
	
	public boolean getCanBeTouchByMeteorite() {
		return this.canBeTouchByMeteorite;
	}
	
	public void setCanBeTouchByMeteorite(boolean canBeTouchByMeteorite) {
		this.canBeTouchByMeteorite = canBeTouchByMeteorite;
	}
	
	public Player() {
		this.couleur = "3";
    	this.direction = -20;
	}
	
	class MakeCanShootTrue extends TimerTask {
        public void run() {
            canShoot = true;
            timer.cancel();
        }
    }
	
	class MakeDelay extends TimerTask {
        public void run() {
            delayClick = false;
            timer.cancel();
        }
    }

	public void Choix_Deplacement(KeyCode key){
        if (key == KeyCode.Z && this.getNode().getTranslateY() >= 300 && !isCollision("haute")){
        	Deplacement_Personnage(this.vitesse,"haut");
        }
        if (key == KeyCode.Q && this.getNode().getTranslateX() >= 10 && !isCollision("gauche")){
        	Deplacement_Personnage(this.vitesse,"gauche");
        	
        }
        if (key == KeyCode.D && this.getNode().getTranslateX() <= shootprojet.hauteur - 50 && !isCollision("droite")){
        	Deplacement_Personnage(this.vitesse,"droite");
        }
        if(key == KeyCode.S && this.getNode().getTranslateY() <= shootprojet.largeur - 100 && !isCollision("basse")) {
        	Deplacement_Personnage(this.vitesse,"bas");
        }
        if(key == KeyCode.A && this.canShoot == true) {
        		this.canShoot = false;
        		Shoot();
        		timer = new Timer();
        		timer.schedule(new MakeCanShootTrue(), 500);
        		
        }
        if(key == KeyCode.E && inventaire.Liste_item.get(0) != null && delayClick == false) {
        	delayClick = true;
        	inventaire.useConsomable(0);
        	HUD.removeItem(1);
        	timer = new Timer();
        	timer.schedule(new MakeDelay(), 1000);
        }
        if(key == KeyCode.R && inventaire.Liste_item.get(1) != null && delayClick == false) {
        	delayClick = true;
        	inventaire.useConsomable(1);
        	HUD.removeItem(2);
        	timer = new Timer();
        	timer.schedule(new MakeDelay(), 1000);
        }
        if(key == KeyCode.T && inventaire.Liste_item.get(2) != null && delayClick == false) {
        	delayClick = true;
        	inventaire.useConsomable(2);
        	HUD.removeItem(3);
        	timer = new Timer();
        	timer.schedule(new MakeDelay(), 1000);
        }
        /*if(key == KeyCode.F && shootprojet.shop_open == false) {
        	boolean verif = shootprojet.verif_Shop();
        	if(verif == true) {
        		shootprojet.ouvrir_Shop();
        		Shop shop = new Shop();
        		shop.Shop_open();
        	}
        }*/
     }
	
	private void Deplacement_Personnage(int Vitesse, String type){
    	for (int i=0; i < Math.abs(Vitesse);i++){
            if(type == "droite") {
            	this.getNode().setTranslateX(this.getNode().getTranslateX() + this.vitesse);
            }
            if(type == "gauche") {
            	this.getNode().setTranslateX(this.getNode().getTranslateX() - this.vitesse);
            }
            if(type == "haut") {
            	this.getNode().setTranslateY(this.getNode().getTranslateY() - this.vitesse);
            }
            if(type == "bas") {
            	this.getNode().setTranslateY(this.getNode().getTranslateY() + this.vitesse);
            }
    	}
    }
	
	public void Victoire() {
		if(this.CompteurEnemie >= nbVictoire) {
			shootprojet.finJeux = true;
			HUD.Victoire();
		}
	}
	
	public void VerifShoot(Node Tir) {
		for(Node tir : Liste_Tir) {
    		for (Enemie enemie : shootprojet.ListEnemie){
                if(tir.getBoundsInParent().intersects(enemie.getNode().getBoundsInParent()) && shootprojet.player.cible_toucher == false){
                	shootprojet.AffichageDecors.getChildren().remove(tir);
                	enemie.PerdreHP(this.degat);
                	shootprojet.player.cible_toucher = true;
                	if(enemie.getHP() <= 0) {
                		shootprojet.AffichageDecors.getChildren().remove(enemie.getNode());
                		shootprojet.ListEnemie.remove(enemie);
                		Liste_Tir.remove(tir);
                		shootprojet.player.inventaire.totalArgent += enemie.argent;
                		this.CompteurEnemie += 1;
                		Victoire();
                		HUD.changeTextArgent();
                		HUD.changeEnemieCompteur();
                		return;
                	}
                }
                if(tir.getTranslateY() > 1300) {
                	shootprojet.AffichageDecors.getChildren().remove(tir);
                	Liste_Tir.remove(tir);
                }
    		} 
		}
	}
	
	public void IsAlive() {
		if(this.pointsVie <= 0) {
    		shootprojet.finJeux = true;
    		HUD.GameOver();
    		shootprojet.AffichageDecors.getChildren().remove(shootprojet.player.getNode());
    	}
	}
	
	public boolean isCollision(String direction) {
		for (Node node : shootprojet.BlockAvecColision) {
			switch (direction) {
				case "gauche": //etre a droite du bloc
					if (node.getTranslateX() < shootprojet.player.node.getTranslateX() && shootprojet.player.node.getTranslateX() < node.getTranslateX() + 60 && node.getTranslateY() -40 < shootprojet.player.node.getTranslateY() && node.getTranslateY() > shootprojet.player.node.getTranslateY() -50)						
						return true;
				break;
				case "droite": //etre a gauche du bloc
					if ( node.getTranslateX() > shootprojet.player.node.getTranslateX() && shootprojet.player.node.getTranslateX() + 50 > node.getTranslateX() && node.getTranslateY() -40 < shootprojet.player.node.getTranslateY() && node.getTranslateY() > shootprojet.player.node.getTranslateY() -50) {	
						return true;
					}
				break;
				case "basse": //etre en haut du bloc
					if (shootprojet.player.node.getTranslateY() < node.getTranslateY() && shootprojet.player.node.getTranslateY() > node.getTranslateY() - 60 && node.getTranslateX() +40 > shootprojet.player.node.getTranslateX() && node.getTranslateX() < shootprojet.player.node.getTranslateX() +40)						
						return true;
				break;
				case "haute": //etre en bas du bloc
					if (shootprojet.player.node.getTranslateY() > node.getTranslateY() && shootprojet.player.node.getTranslateY() -60 < node.getTranslateY()  && node.getTranslateX() +40 > shootprojet.player.node.getTranslateX() && node.getTranslateX() < shootprojet.player.node.getTranslateX() +40)						
						return true;
				break;
			}
		}
		return false;
	}
	
}