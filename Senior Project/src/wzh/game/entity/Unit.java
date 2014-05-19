package wzh.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;

public class Unit extends Entity {
	private int faction;
	private int movePoints;
	
	public Unit(int x, int y, Image img, Grid g, int faction) {
		super(x,y,img,g);
		this.faction = faction;
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
		System.out.println("Drew");
	}
}
