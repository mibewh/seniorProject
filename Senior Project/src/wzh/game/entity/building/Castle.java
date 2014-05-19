package wzh.game.entity.building;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;

public abstract class Castle {

	private Grid grid;
	private int x,y;
	private Image sprite;
	private int size;
	
	public Castle(){
		grid = null;
	}
	public Castle(int x, int y, Image img, Grid g) {
		grid = g;
		sprite = img;
		this.x = x;
		this.y = y;
		size = img.getHeight();
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public Grid getGrid(){
		return grid;
	}	
	
	public Image getSprite() {
		return sprite;
	}
	public int getSize() {
		return size;
	}
	
	public int getFortification(){
		return 1;
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
	}
}

