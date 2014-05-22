package wzh.game.entity.building;

import org.newdawn.slick.Image;

import wzh.game.Grid;

public abstract class Village extends Building {
	public Village(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
		}
	
	public int getFortification(){
		return 1;
	}
	//TODO Check THE VILLAGE fortification of 0?
}
