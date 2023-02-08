package shootEntity;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import shoot.shootprojet;

public class MapCreation {
	
	public static void ChargeMap(){
        Rectangle fenetre = new Rectangle(shootprojet.hauteur, shootprojet.largeur);
        //pour chaque valeur de la map crée la case correspondante
        for (int i=0; i< Map.Level1.length; i++){
            String line = Map.Level1[i];
            for (int j=0; j <line.length();j++){
                switch (Character.toString(line.charAt(j))){
                    case "0":
                        break;
                    case "1":
                        Node platform = shootprojet.CreationBlock(j*60, i *60, 60, 60, Character.toString(line.charAt(j)));
                        shootprojet.BlockAvecColision.add(platform);
                        break;
                }
            }
        }
        /*player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < 1080){
            	AffichageDecors.setLayoutX(-(offset-640));
            }
        });*/
        HUD hud = new HUD();
        shootprojet.AffichageGlobal.getChildren().addAll(fenetre, shootprojet.AffichageDecors, shootprojet.AffichageHUD);
    }
}
