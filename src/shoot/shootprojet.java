package shoot;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shootEntity.Enemie;
import shootEntity.HUD;
import shootEntity.Player;
import shootEntity.Item;
import shootEntity.Marchand;
import shootEntity.Meteorite;
import shootEntity.Potion;
import shootEntity.Power;
import shootEntity.Shop;
import shootEntity.Vaisseau;
import shootEntity.PNJ;

public class shootprojet extends Application {

	private KeyCode key;
	//liste de tout les nodes(block) qui compose la map
    public static ArrayList<Node> BlockAvecColision = new ArrayList<>();
    //liste des enemies
    public static ArrayList<Enemie> ListEnemie = new ArrayList<>();
    //affichage complet
    public static Pane AffichageGlobal = new Pane();
    //affichage des decors
    public static Pane AffichageDecors = new Pane();
    //a utiliser pour afficher la partie HUD qui est fixe
    public static Pane AffichageHUD = new Pane();
    private static Image imgBlock;
    
    //taille ecran
    public static int hauteur = 1280;
    public static int largeur = 720;
    
    //variable du joueur
    public static Player player = new Player();
    public static int vieJoueur = 20;
    public static int vitesseJoueur = 2;
    
    //
    public static boolean finJeux = false;
    
    //variable shop
    public static boolean shop_open = false;
    public static boolean shop_logo_afficher = false;
    
    //variable deplacement
    Timer timer;
    
    //variable shop
    Shop shop;
    public Marchand marchand = new Marchand();
    
    //variable item
    public static ArrayList<Item> ListItem = new ArrayList<>();
    public boolean timer_item = false;
    public boolean verif_item_supprimer;
    
    //variable enemie
    //Enemie enemie = new Enemie();
    boolean timer_enemie = false;
    //Node enemie;
    String enemie_type;
    public static boolean enemieCanSpawn = true;
    public static int tempsSpawnEnemie = 4000;
    
    
    //associe a chaque block une taille et l'insere dans AffichageDecors
    public static Node CreationBlock(double x, double y, int w, int h, String blockValue){
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        //entity.setFill(color);
        switch (blockValue) {
        case "1" :
        	imgBlock = new Image("images/block.png");
        	break;
        case "2" :
        	imgBlock = new Image("images/spaceship.png");
        	break;
        case "3" :
        	imgBlock = new Image("images/ball.png");
        	break;
        case "4" :
        	imgBlock = new Image("images/enemy1.png");
        	break;
        case "5":
        	imgBlock = new Image("images/ball2.png");
        	break;
        case "6":
        	imgBlock = new Image("images/power.png");
        	break;
        case "7":
        	imgBlock = new Image("images/potion.png");
        	break;
        case "8":
        	imgBlock = new Image("images/enemy.png");
        	break;
        case "9":
        	imgBlock = new Image("images/piece.png");
        	break;
        case "10":
        	imgBlock = new Image("images/meteorite.png");
        	break;
        case "12":
        	imgBlock = new Image("images/ObstacleItem.png");
        	break;
        case "13":
        	imgBlock = new Image("images/marchand.png");
        	break;
        case "14":
        	imgBlock = new Image("images/invisible.png");
        default : 
        	break;
        }
        entity.setFill(new ImagePattern(imgBlock));
        AffichageDecors.getChildren().add(entity);
        return entity;
    }
    
    class SpawnEnemie extends TimerTask {
        public void run() {
            timer_enemie = false;
        }
    }
    
    private void enemie_creation() {
    	int type = (int)(1 + Math.random() * 3);
    	int positionX = (int)(10 + Math.random() * 1150);
    	int positionY = 80;
    	switch(type) {
    	case 1 :
    		Vaisseau enemie = new Vaisseau();
    		enemie.setBlock(CreationBlock(positionX,positionY,50,50,"4"));
    		enemie.setCaracteristique(1,2);
    		enemie.setDegat(1);
    		enemie.setArgent(1);
    		enemie.setType("vaisseau");
    		ListEnemie.add(enemie);
    		break;
    	case 2 :
    		Vaisseau enemie2 = new Vaisseau();
    		enemie2.setBlock(CreationBlock(positionX,positionY,50,50,"8"));
    		enemie2.setCaracteristique(2,2);
    		enemie2.setDegat(1);
    		enemie2.setArgent(2);
    		enemie2.setType("vaisseau");
    		ListEnemie.add(enemie2);
    		break;
    	case 3 :
    		Meteorite enemie3 = new Meteorite();
    		if(positionX < largeur/2) {
    			enemie3.setDirection("droite");
    		}
    		else {
    			enemie3.setDirection("gauche");
    		}
    		enemie3.setType("meteorite");
    		enemie3.setBlock(CreationBlock(positionX,positionY,50,50,"10"));
    		enemie3.setCaracteristique(1,2);
    		enemie3.setDegat(1);
    		enemie3.setArgent(2);
    		if(player.getCanBeTouchByMeteorite() == false) {
    			enemie3.setCanTouch(false);
    		}
    		else {
    			enemie3.setCanTouch(true);
    		}
    		ListEnemie.add(enemie3);
    		break;
    	}
    }

    private void Enemie_Deplacement() {
    	for (Enemie enemie : ListEnemie ){
    		enemie.MoveEnemie();
    		if(enemie.getEnemie_remove() == true) {
    			return;
    		}
    		if(enemie.ShootUp() == true && enemie.getType() != "meteorite") {
    			enemie.Shoot();
    		}
    	}
    }
    
    private void Enemie() {
    	if(timer_enemie == false && ListEnemie.size() < 75) {
    		enemie_creation();
    		timer_enemie = true;
    		timer = new Timer();
    		timer.schedule(new SpawnEnemie(), tempsSpawnEnemie);
    	}
    	Enemie_Deplacement();
    }
    
    
    
    private void Item() {
        item_deplacement();
        if(timer_item == false) {
            item_creation();
            timer_item = true;
            timer = new Timer();
            //int time = (int)(10000 + Math.random() * 20000);
            int time = (int)(4000 + Math.random() * 5000);
            timer.schedule(new SpawnItem(), time);
        }
    }
    
    
    private void item_deplacement() {
        for (Item item : ListItem ){
            verif_item_supprimer = item.MoveItem();
            if(verif_item_supprimer == true) {
            	return;
            }
            if(item.getNode().getTranslateY() >= 700) {
                AffichageDecors.getChildren().remove(item.getNode());
                ListItem.remove(item);
                return;
            }
        }
    }
    
    private void item_creation() {
        int item = (int)(1 + Math.random() * 2);
        int X = (int)(10 + Math.random() * 1270);
        switch(item) {
            case 1:
                Potion potion = new Potion();
                potion.setBlock(CreationBlock(X,50,50,50,"7"));
                potion.Item_Caracteristique(2, 2, "utilisable","7");
                potion.setVie();
                ListItem.add(potion);
                break;
            case 2:
                Power power = new Power();
                power.setBlock(CreationBlock(X,50,50,50,"6"));
                power.Item_Caracteristique(2, 2, "equipement","6");
                power.setBonusDegat();
                ListItem.add(power);
                break;
        }
                
    }
    
    public void setMarchand() {
        marchand = new Marchand("Le shop n'est pas encore disponible","13");
        marchand.setBlock(CreationBlock(1100,610,50,50,"13"));
        marchand.onClick();
        marchand.initialiser_Dialog();

    }
    
    class SpawnItem extends TimerTask {
        public void run() {
            timer_item= false;
        }
    }
    
    /*public static void ouvrir_Shop() {
    	shop_open = true;
    	enemieCanSpawn = false;
		for(Enemie enemie : ListEnemie) {
			AffichageDecors.getChildren().remove(enemie.getNode());
			ListEnemie.remove(enemie);
		}
    }*/
    
    /*public static boolean verif_Shop() {
    	if(shop_logo_afficher == true) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }*/
    
    /*public void Shop() {
    	if(shop_open == false) {
    		int nb = player.getCompteurEnemie();
	    	if(nb >= 1 && shop_logo_afficher == false) {
	    		//HUD.logoShop();
	    		shop_logo_afficher = true;
	    		setPNJ();
	    	}
    	}
    }*/
    
    public void Shop() {
    	if(player.getCompteurEnemie() >= 5) {
    		marchand.afficherDialog("Le shop est disponible");
    		marchand.setCanOpenShop(true);
    	}
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	//quand on ferme la fenetre avec la crois ferme le jeux correctement (sinon il continue de tourner en etant cacher)
    	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        shootEntity.MapCreation.ChargeMap();
        player.setCaracteristique(vieJoueur, vitesseJoueur);
        player.setBlock(CreationBlock(hauteur/2, largeur /2, 40, 40, "2"));
        player.setDegat(1);
        Scene scene = new Scene(AffichageGlobal);
        //envoyer la touche presser pour determiner l'action
        scene.setOnKeyPressed(event -> player.Choix_Deplacement(key = event.getCode()));
        //reinitialiser la touche a null quand on ne la presse plus
        scene.setOnKeyReleased(event -> key = null);
        primaryStage.setTitle("Shooter");
        primaryStage.setScene(scene);
        primaryStage.show();
        //timer = new Timer();
		//timer.schedule(new SpawnEnemie(), 500);
        //permet d'excuter deplacement() en boucle et donc de simuler la physique du jeux et les deplacement
        setMarchand();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if(finJeux == false) {
	            	player.Choix_Deplacement(key);
	            	if(enemieCanSpawn == true) {
	            		Enemie();
	            	}
	            	Item();
	            	if(marchand.getCanOpenShop() == false) {
	            		Shop();
	            	}
            	}
            }
        };
        timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
