package wzh.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.entity.Entity;
import wzh.game.input.Cursor;

public class Grid {
	private TiledMap map;
	private int rows, cols;
	Entity[][] entities;
	private Cursor cursor;
	
	public Grid(TiledMap map) {
		this.map = map;
		map.
		rows = map.getWidth();
		cols = map.getHeight();
		entities = new Entity[rows][cols];
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
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		cursor.update(gc, game, delta);
		for(int x = 0; x<rows;x++) {
			for(int y = 0; y<cols;y++) {
				if(entities[x][y] != null) {
					entities[x][y].update(gc, game, delta);
				}
			}
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		map.render(0, 0, 0, 0, 20, 20);
		cursor.render(gc, game, g);
		for(int x = 0; x<rows;x++) {
			for(int y = 0; y<cols;y++) {
				if(entities[x][y] != null) {
					entities[x][y].render(gc,game,g);
				}
			}
		}
	}
	public Cursor getCursor() {
		return cursor;
	}
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
	public int getMoveCost(int x, int y) {
		if(map.getTileId(x, y, map.getLayerIndex("ImpedimentLevel2")) != 0) return 2;
		else if(map.getTileId(x, y, map.getLayerIndex("ImpedimentLevel3")) != 0) return 3;
		else if(map.getTileId(x, y, map.getLayerIndex("Obstructions")) != 0) return 100;
		else return 1;
	}
	
}
