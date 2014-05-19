package wzh.game.entity.building;

import org.newdawn.slick.Image;

import wzh.game.Grid;

/**
 * A Castle is a building that when selected by the cursor opens a window for purchasing different units 
 * also provides a defensive bonus(fortification) of 1
 * @author Harrison
 */
public abstract class Castle extends Building {
	
	public Castle(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
	}
	
	public int getFortification(){
		return 1;
	}
}

