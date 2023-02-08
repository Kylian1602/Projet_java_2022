package shootEntity;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shoot.shootprojet;

public abstract class PNJ extends Personnage {
	protected String dialog;
	protected Node nodeDialog;
	Text Dialog = new Text();
	Timer timer;
	
	
	class Effacer_Dialog extends TimerTask {
        public void run() {
        	Dialog.setText("");
        }
    }
	
	public void initialiser_Dialog() {
		Dialog.setX(this.getNode().getTranslateX() - dialog.length()*2);
		Dialog.setY(this.getNode().getTranslateY() - 20);
		Dialog.setFont(Font.font("Monospaced", 12));
		Dialog.setFill(Color.WHITE);
		shootprojet.AffichageDecors.getChildren().add(Dialog);
	}
	
	public void afficherDialog() {
		Dialog.setText(dialog);
		timer = new Timer();
        timer.schedule(new Effacer_Dialog(), 3000);
	}
	
	public void afficherDialog(String text) {
		Dialog.setText(text);
		timer = new Timer();
        timer.schedule(new Effacer_Dialog(), 3000);
	}
	
	
	public String getDialog() {
		return this.dialog;
	}
	
	public String setDialog() {
		return this.dialog;
	}

	@Override
	protected void VerifShoot(Node tir2) {
		
	}
	
}
