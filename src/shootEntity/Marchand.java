package shootEntity;

import shoot.shootprojet;

public class Marchand extends PNJ{
	protected boolean canOpenShop = false;
	protected boolean shopIsOpen = false;
	Shop shop = new Shop();

	public Marchand() {}
	
	public Marchand(String dialog, String couleur) {
		this.dialog = dialog;
		this.couleur = couleur;
	}
	
	public void setCanOpenShop(boolean canOpenShop) {
		this.canOpenShop = canOpenShop;
	}
	
	public void setshopIsOpen(boolean shopIsOpen) {
		this.shopIsOpen = shopIsOpen;
	}
	
	public boolean getCanOpenShop() {
		return this.canOpenShop;
	}
	
	public void onClick() {
		this.getNode().setOnMouseClicked((e) -> { 
			if(canOpenShop == false) {
				this.dialog = "Le shop n'est pas encore disponible";
				this.afficherDialog();
				return;
			}
			if(shopIsOpen == true) {
				this.dialog = "Revenez vite";
				shopIsOpen = false;
				this.afficherDialog();
				shop.Shop_close();
				return;
			}
			if(canOpenShop == true && shopIsOpen == false) {
				shop.Shop_open();
				this.dialog = "bienvenue dans mon shop";
				shopIsOpen = true;
				this.afficherDialog();
				return;
			}
	    });
	}
}
