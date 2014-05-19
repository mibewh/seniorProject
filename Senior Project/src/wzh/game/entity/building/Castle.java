package wzh.game.entity.building;

import org.newdawn.slick.Image;

import wzh.game.Grid;

public abstract class Castle extends Building {
	
	public Castle(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
	}
	
	public int getFortification(){
		return 1;
	}
}

