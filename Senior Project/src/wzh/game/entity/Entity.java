package wzh.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;

public abstract class Entity {
	protected Grid grid;
	protected int direction;
	protected int x, y;
	protected Image sprite;
	protected int size;
	
	public Entity(){
		grid = null;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Entity(int x, int y, Image img, Grid g){
		grid = g;
		sprite = img;
		this.x = x;
		this.y = y;
		size = img.getHeight();
	}
	
	public int getDirection(){
		return direction;
	}
	
	public Grid getGrid(){
		return grid;
	}
		
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		sprite.draw(x,y,2);
	}
	public Image getSprite() {
		return sprite;
	}
	public int getSize() {
		return size;
	}
}