package wzh.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;

public class Entity {
	private Grid grid;
	private int direction;
	private int x, y;
	private Image sprite;
	
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
		
	}
	public Image getSprite() {
		return sprite;
	}
}