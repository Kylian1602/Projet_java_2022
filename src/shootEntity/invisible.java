package shootEntity;

import shoot.shootprojet;

public class invisible extends Item{
	
	public invisible() {}

	@Override
	public void itemEffet() {
		//this.node = shootprojet.CreationBlock(this.node.getTranslateX(), this.node.getTranslateY(), this.node.getScaleX(), this.node.getScaleY(), null);
		shootprojet.player.node.setVisible(false);
	}
}
