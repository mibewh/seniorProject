package wzh.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.Location;

public abstract class Entity {
	protected Grid grid;
	protected int direction;
	protected Location loc;
	protected Image sprite;
	protected int size;
	
	public Entity(int x, int y, Image img, Grid g){
		grid = g;
		sprite = img;
		loc = new Location(x,y);
		size = img.getHeight();
	}
	public Location getLoc() {
		return loc;
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
		sprite.draw(loc.getX()*size,loc.getY()*size);
	}
	public Image getSprite() {
		return sprite;
	}
	public int getSize() {
		return size;
	}
	public void moveTo(int x, int y) {
		Location moveLoc = new Location(x,y);
		if(grid.isValid(moveLoc)) {
			loc=moveLoc;
			grid.remove(x,y);
			grid.add(this);
		}
	}
}
