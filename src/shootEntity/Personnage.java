package shootEntity;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;
import shoot.shootprojet;

public abstract class Personnage {
	protected int pointsVie;
    protected int vitesse;
    protected Node node;
    protected Node Tir;
    protected ArrayList<Node> Liste_Tir = new ArrayList<Node>();
    protected boolean cible_toucher;
    protected String couleur;
    protected int direction;
    protected int degat;

    public void setCaracteristique(int p_pointsVie, int p_vitesse) {
        this.pointsVie = p_pointsVie;
        this.vitesse = p_vitesse;
    }
    
    public void setDegat(int degat) {
    	this.degat = degat;
    }
    
    public int getDegat() {
    	return this.degat;
    }
    
    public void setTir(Node Tir) {
    	this.Tir = Tir;
    }
    
    public void setCible_Toucher(boolean cible_toucher) {
    	this.cible_toucher = cible_toucher;
    }
    
    public boolean getCible_Toucher() {
    	return this.cible_toucher;
    }
    
    public Node getTir() {
    	return this.Tir;
    }
    
    public void setBlock(Node node) {
        this.node = node;
    }
    
    public int getHP() {
    	return this.pointsVie;
    }
    
    public Node getNode() {
    	return this.node;
    }
    
    public boolean PerdreHP(int degat) {
    	boolean vivant = true;
    	this.pointsVie = this.pointsVie - degat;
    	if(this.pointsVie <= 0) {
    		vivant = false;
    	}
    	return vivant;
    }
    
    public void Shoot() {
    	this.setTir(shootprojet.CreationBlock(this.getNode().getTranslateX(), this.getNode().getTranslateY(), 20, 20, this.couleur));
    	this.Liste_Tir.add(this.getTir());
    	Deplacement_Tir();
    	this.setCible_Toucher(false);
    }
    
    private void Deplacement_Tir() {
    	for(Node Tir : Liste_Tir) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), new KeyValue(this.getTir().translateYProperty(), this.direction )));
        Tir.translateYProperty().addListener((obs, oldValue, newValue) ->
        	VerifShoot(this.getTir())
        );
        timeline.play();
    	}
    }

	protected abstract void VerifShoot(Node tir2);
    
}
