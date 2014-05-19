package wzh.game.entity.building;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.entity.Entity;

public abstract class Building extends Entity {
	
	public Building(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
	}
	
	public int getFortification(){
		return 0;
	}
}
