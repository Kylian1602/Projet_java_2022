package shootEntity;

import shoot.shootprojet;

public class ObstacleItem extends Item{

	public ObstacleItem() {}

	@Override
	public void itemEffet() {
		shootprojet.player.setCanBeTouchByMeteorite(false);
	}
}
