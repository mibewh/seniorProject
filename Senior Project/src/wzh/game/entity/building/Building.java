package wzh.game.entity.building;

import org.newdawn.slick.Image;

import wzh.game.Grid;
import wzh.game.entity.Entity;
import wzh.game.input.Menu;

public abstract class Building extends Entity {
	protected int faction;
	
	public Building(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
	}
	public int getFaction() {
		return faction;
	}
	public int getFortification(){
		return 0;
	}
	public Menu getMenu() {
		//TODO Implement building menus
		return null;
	} 
}
