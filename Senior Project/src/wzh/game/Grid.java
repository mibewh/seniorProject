package wzh.game;

import org.newdawn.slick.tiled.TiledMap;

import wzh.game.entity.Entity;

public class Grid {
	private TiledMap map;
	private int rows, cols;
	Entity[][] entities;
	
	public Grid(TiledMap map) {
		this.map = map;
	}
	public Entity get(int x, int y) {
		return entities[x][y];
	}
	public void put(int x, int y, Entity e) {
		entities[x][y] = e;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
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
	
	
}
