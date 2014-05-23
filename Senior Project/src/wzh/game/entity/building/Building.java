package wzh.game.entity.building;

import org.newdawn.slick.Image;

import wzh.game.Grid;
import wzh.game.entity.Entity;
import wzh.game.input.Menu;

public abstract class Building extends Entity {
	protected int faction;
	
	public Building(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g);
		this.faction = faction;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int f){
		faction = f;
	}
	public abstract int getFortification();
	public Menu getMenu() {
		return null;
	} 
}
