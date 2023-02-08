package shootEntity;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shoot.shootprojet;

public class HUD {
	
	protected static int textArgent = 0;
	protected static int textKill = 0;
	protected static Rectangle voile = new Rectangle();
	protected static Text gameOver = new Text();
	static Line PVBarre = new Line();
	Text PV = new Text();
	static Text kill = new Text();
	static Text argent = new Text();
	Text TnbPow = new Text();
	Text TnbPotion = new Text();
	Text Inventaire = new Text();
	static double VieTotal = 1250;
	protected static Node item1;
	protected static Node item2;
	protected static Node item3;
	protected static Node shop;
	
	
	public HUD() {
    Inventaire.setText("INVENTAIRE :");
    Inventaire.setX(10);
    Inventaire.setY(55);
    Inventaire.setFill(Color.WHITE);
    Inventaire.setFont(Font.font("arial", 15));
    shootprojet.AffichageHUD.getChildren().add(Inventaire);
    /*item1 = shootprojet.CreationBlock(120, 25, 50, 50, '2');
    item2 = shootprojet.CreationBlock(180, 25, 50, 50, '2');
    item3 = shootprojet.CreationBlock(240, 25, 50, 50, '2');*/
    
    /*shootprojet.CreationBlock(640, 25, 50, 50, '7');
    
    TnbPotion.setText(String.valueOf(0));
    TnbPotion.setX(690);
    TnbPotion.setY(55);
    TnbPotion.setFill(Color.WHITE);
    TnbPotion.setFont(Font.font("arial", 15));
    shootprojet.AffichageHUD.getChildren().add(TnbPotion);
   
    shootprojet.CreationBlock(735, 25, 50, 50, '6');

    TnbPow.setText(String.valueOf(0));
    TnbPow.setX(800);
    TnbPow.setY(55);
    TnbPow.setFill(Color.WHITE);
    TnbPow.setFont(Font.font("arial", 15));
    shootprojet.AffichageHUD.getChildren().add(TnbPow);*/
    
    argent.setText(String.valueOf(textArgent) + "$");
    argent.setX(880);
    argent.setY(55);
    argent.setFill(Color.WHITE);
    argent.setFont(Font.font("arial", 15));
    shootprojet.AffichageHUD.getChildren().add(argent);

    shootprojet.CreationBlock(950, 25, 50, 50, "8");
    
    kill.setText(String.valueOf(textKill));
    kill.setX(1010);
    kill.setY(55);
    kill.setFill(Color.WHITE);
    kill.setFont(Font.font("arial", 15));
    shootprojet.AffichageHUD.getChildren().add(kill);
    
    PV.setText("PV");
    PV.setX(1060);
    PV.setY(55);
    PV.setFill(Color.WHITE);
    PV.setFont(Font.font("arial", 15));
    shootprojet.AffichageHUD.getChildren().add(PV);
    
    PVBarre.setStartX(1100);
    PVBarre.setStartY(50);
    PVBarre.setEndX(1250);
    PVBarre.setEndY(50);
    PVBarre.setStrokeWidth(20);
    PVBarre.setStroke(Color.RED);
    shootprojet.AffichageHUD.getChildren().add(PVBarre);
	}
	
	public static void ChangeHP(int degat) {
		PVBarre.setEndX(VieTotal-(7.5*degat));
		VieTotal = VieTotal - (7.5*degat);
		if(shootprojet.player.getHP() <= 0) {
			PVBarre.setStroke(Color.BLACK);
		}
	}
	
	public static void removeItem(int position) {
		if(position == 1) {
			shootprojet.AffichageDecors.getChildren().remove(item1);
		}
		if(position == 2) {
			shootprojet.AffichageDecors.getChildren().remove(item2);
		}
		if(position == 3) {
			shootprojet.AffichageDecors.getChildren().remove(item3);
		}
	}
	
	public static void addItem(String img, int position) {
		if(position == 0) {
			item1 = shootprojet.CreationBlock(120, 25, 50, 50, img);
		}
		if(position == 1) {
			item2 = shootprojet.CreationBlock(180, 25, 50, 50, img);
		}
		if(position == 2) {
			item3 = shootprojet.CreationBlock(240, 25, 50, 50, img);
		}
	}
	
	public static void changeTextArgent() {
		textArgent = shootprojet.player.inventaire.getArgent();
		argent.setText(String.valueOf(textArgent) + "$");
	}
	
	public static void changeEnemieCompteur() {
		textKill = shootprojet.player.getCompteurEnemie();
		kill.setText(String.valueOf(textKill));
	}
	
	public static void GameOver() {
        voile.setX(0);
        voile.setY(0);
        voile.setWidth(1280);
        voile.setHeight(720);
        voile.setFill(Color.BLACK);
        voile.setOpacity(0.5);
        shootprojet.AffichageHUD.getChildren().add(voile);

        gameOver.setText(String.valueOf("GAME OVER"));
        gameOver.setX(490);
        gameOver.setY(360);
        gameOver.setFill(Color.WHITE);
        gameOver.setFont(Font.font("Monospaced", 60));
        shootprojet.AffichageHUD.getChildren().add(gameOver);
    }
	
	public static void Victoire() {
        voile.setX(0);
        voile.setY(0);
        voile.setWidth(1280);
        voile.setHeight(720);
        voile.setFill(Color.BLACK);
        voile.setOpacity(0.5);
        shootprojet.AffichageHUD.getChildren().add(voile);

        gameOver.setText(String.valueOf("VICTOIRE"));
        gameOver.setX(490);
        gameOver.setY(360);
        gameOver.setFill(Color.WHITE);
        gameOver.setFont(Font.font("Monospaced", 60));
        shootprojet.AffichageHUD.getChildren().add(gameOver);
    }
	
	/*public static void logoShop() {
		shop = shootprojet.CreationBlock(800, 20, 50, 50,'9');
	}*/
	
	
}
