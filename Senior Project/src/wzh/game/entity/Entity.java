package wzh.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.Location;
import wzh.game.input.Menu;

public abstract class Entity {
	protected Grid grid;
	protected int direction;
	protected Location loc;
	protected Image sprite;
	protected int size;
	protected boolean active;
	
	public Entity(int x, int y, Image img, Grid g){
		grid = g;
		sprite = img;
		loc = new Location(x,y);
		size = 16;
		active = true;
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
		sprite.draw((loc.getX()-grid.getUpperLeftX())*size,(loc.getY()-grid.getUpperLeftY())*size);
	}
	public Image getSprite() {
		return sprite;
	}
	public int getSize() {
		return size;
	}
	public void moveTo(int x, int y) {
		Location moveLoc = new Location(x,y);
		grid.remove(loc.getX(), loc.getY());
		if(grid.isValid(moveLoc)) {
			loc=moveLoc;
			grid.remove(x,y);
			grid.add(this);
		}
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean a) {
		active = a;
	}
	public abstract String getName();
	public abstract void hideMenus();
	public abstract Menu getMenu();
}
