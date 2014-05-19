package wzh.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.entity.Entity;

public class Grid {
	private TiledMap map;
	private int rows, cols;
	Entity[][] entities;
	
	public Grid(TiledMap map) {
		this.map = map;
		entities = new Entity[map.getWidth()][map.getHeight()];
	}
	public Entity get(int x, int y) {
		return entities[x][y];
	}
	public boolean add(Entity e) {
		if(isEmpty(e.getX(),e.getY())) {
			entities[e.getX()][e.getY()] = e;
			return true;
		}
		else return false;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public boolean isEmpty(int x, int y) {
		if(entities[x][y] == null) return true;
		else return false;
	}
	public boolean isValid(int x, int y) {
		if(x<rows && x>=0 && y<cols && y>=0) {
			return true;
		}
		else
			return false;
	}
	public TiledMap getMap() {
		return map;
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		map.render(0, 0, 0, 0, 20, 20);
		for(int x = 0; x<rows;x++) {
			for(int y = 0; y<cols;y++) {
				if(entities[x][y] != null) {
					entities[x][y].render(gc,game,g);
				}
			}
		}
	}
	
	
}
