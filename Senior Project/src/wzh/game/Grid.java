package wzh.game;

import java.util.ArrayList;

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
		rows = map.getWidth();
		cols = map.getHeight();
		entities = new Entity[rows][cols];
	}
	public Entity get(int x, int y) {
		return entities[x][y];
	}
	public boolean add(Entity e) {
		if(isEmpty(e.getLoc())) {
			entities[e.getLoc().getX()][e.getLoc().getY()] = e;
			return true;
		}
		else return false;
	}
	public void remove(int x, int y) {
		entities[x][y] = null;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public boolean isEmpty(Location loc) {
		if(entities[loc.getX()][loc.getY()] == null) return true;
		else return false;
	}
	public boolean isEmpty(int x, int y) {
		if(entities[x][y] == null) return true;
		else return false;
	}
	public boolean isValid(Location loc) {
		if(loc.getX()<cols && loc.getX()>=0 && loc.getY()<rows && loc.getY()>=0) {
			return true;
		}
		else
			return false;
	}
	public boolean isValid(int x, int y) {
		if(x<cols && x>=0 && y<rows && y>=0) return true;
		else return false;
	}
	public TiledMap getMap() {
		return map;
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		cursor.update(gc, game, delta);
		for(int x = 0; x<cols;x++) {
			for(int y = 0; y<rows;y++) {
				if(entities[x][y] != null) {
					entities[x][y].update(gc, game, delta);
				}
			}
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		map.render(0, 0, 0, 0, 20, 20);
		cursor.render(gc, game, g);
		for(int x = 0; x<cols;x++) {
			for(int y = 0; y<rows;y++) {
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
	public int getMoveCost(Location loc) {
		if(!isEmpty(loc)) return 100;
		else if(map.getTileId(loc.getX(), loc.getY(), map.getLayerIndex("Obstructions")) != 0) return 100;
		else return 1;
	}
	public int getPathCost(ArrayList<Location> path) {
		int cost = 0;
		for(Location loc:path) {
			cost += getMoveCost(loc);
		}
		return cost;
	}
	/*
	 * Find the shortest path between two locations using the the A* and Manhattan algorithms.
	 * @author Michael
	 * @param start startring location
	 * @param end ending location
	 * @return List containing the shortest path of locations from start to end.
	 */
	public ArrayList<Location> getShortestPath(Location start, Location end) {
		//TODO Algorithm to find shortest path based on movePoints from start location to end location
		ArrayList<Location> arr = new ArrayList<Location>();
		Location curLoc = start;
		while(!curLoc.equals(end)) {
			ArrayList<Location> possibles = curLoc.getAdjacentLocations();
			curLoc = possibles.get(0);
			for(Location loc:possibles) {
				if(getMoveCost(loc)!=100) {
					int curLocf = getMoveCost(curLoc) + getManhattanCost(curLoc,end);
					int f = getMoveCost(loc) + getManhattanCost(loc, end);
					if(f<curLocf)
						curLoc=loc;
				}
			}
			//System.out.println(curLoc);
			arr.add(curLoc);
		}
		return arr;
	}
	/*
	 * Approximate the remaining distance between two Locations
	 * @param start starting location
	 * @param end end location
	 * @return Approximate min. distance between the two locations
	 */
	public int getManhattanCost(Location start, Location end) {
		Location curLoc = start;
		int total = 0;
		if(start.getX()<end.getX()) {
			while(curLoc.getX()!=end.getX()) {
				curLoc = new Location(curLoc.getX()+1,curLoc.getY());
				total+=getMoveCost(curLoc);
			}
		}
		else if(start.getX()>end.getX()) {
			while(curLoc.getX()!=end.getX()) {
				curLoc = new Location(curLoc.getX()-1,curLoc.getY());
				total+=getMoveCost(curLoc);
			}
		}
		if(start.getY()<end.getY()) {
			while(curLoc.getY()!=end.getY()) {
				curLoc = new Location(curLoc.getX(),curLoc.getY()+1);
				total+=getMoveCost(curLoc);
			}
		}
		else if(start.getY()>end.getY()) {
			while(curLoc.getY()!=end.getY()) {
				curLoc = new Location(curLoc.getX(),curLoc.getY()-1);
				total+=getMoveCost(curLoc);
			}
		}
		return total;
	}
}
