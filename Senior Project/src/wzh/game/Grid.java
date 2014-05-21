package wzh.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.entity.Entity;
import wzh.game.entity.unit.Archer;
import wzh.game.entity.unit.Horseman;
import wzh.game.entity.unit.Spearman;
import wzh.game.entity.unit.Swordsman;
import wzh.game.input.Cursor;

public class Grid {
	private TiledMap map;
	private int rows, cols;
	Entity[][] entities;
	private Cursor cursor;
	
	public Grid(TiledMap map) throws SlickException {
		this.map = map;
		rows = map.getWidth();
		cols = map.getHeight();
		entities = new Entity[rows][cols];
		loadEntities();
	}
	public void loadEntities() throws SlickException{
		int layer = map.getLayerIndex("Objects");
		SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
		for(int x=0;x<cols;x++) {
			for(int y=0;y<rows;y++) {
				int ID = map.getTileId(x, y, layer);
				Entity toAdd;
				switch(ID) {
				case 227://Red Sword
					toAdd = new Swordsman(x,y,ss.getSubImage(1, 0),this,2);
					break;
				case 228://Blue Sword
					toAdd = new Swordsman(x,y,ss.getSubImage(2, 0),this,1);
					break;
				case 242://Red Spear
					toAdd = new Spearman(x,y,ss.getSubImage(1, 1),this,2);
					break;
				case 243://Blue Spear
					toAdd = new Spearman(x,y,ss.getSubImage(2, 1),this,1);
					break;
				case 257://Red Archer
					toAdd = new Archer(x,y,ss.getSubImage(1, 2),this,2);
					break;
				case 258://Blue Archer
					toAdd = new Archer(x,y,ss.getSubImage(2, 2),this,1);
					break;
				case 272://Red Horse
					toAdd = new Horseman(x,y,ss.getSubImage(1, 3),this,2);
					break;
				case 273://Blue Horse
					toAdd = new Archer(x,y,ss.getSubImage(2, 3),this,1);
					break;
				default:
					toAdd=null;
				}
				entities[x][y] = toAdd;
			}
		}
	}
	public Entity get(int x, int y) {
		return entities[x][y];
	}
	public Entity get(Location loc) {
		return entities[loc.getX()][loc.getY()];
	}
	public ArrayList<Entity> getAllEntities() {
		ArrayList<Entity> arr = new ArrayList<Entity>();
		for(int x=0;x<cols;x++) {
			for(int y=0;y<rows;y++) {
				arr.add(entities[x][y]);
			}
		}
		return arr;
	}
	public ArrayList<Location> getFilledLocations() {
		ArrayList<Entity> ent = getAllEntities();
		ArrayList<Location> arr = new ArrayList<Location>();
		for(Entity e:ent) {
			if(e!=null) arr.add(e.getLoc());
		}
		return arr;
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
		for(int l=0;l<map.getLayerCount()-1;l++) {
			map.render(0,0,0,0,20,20,l,false);
		}//map.render(0, 0, 0, 0, 20, 20);
		for(int x = 0; x<cols;x++) {
			for(int y = 0; y<rows;y++) {
				if(entities[x][y] != null) {
					entities[x][y].render(gc,game,g);
				}
			}
		}
		cursor.render(gc, game, g);
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
	public boolean isObstructed(Location loc) {
		if(map.getTileId(loc.getX(), loc.getY(), map.getLayerIndex("Obstructions")) != 0 || !isEmpty(loc)) return true;
		else return false;
	}
	public int getPathCost(ArrayList<Location> path) {
		int cost = 0;
		for(Location loc:path) {
			cost += getMoveCost(loc);
		}
		return cost;
	}
	public ArrayList<Location> getAdjacentNeighbors(Location loc) {
		//System.out.println(loc);
		ArrayList<Location> adj = loc.getAdjacentLocations();
		ArrayList<Entity> ents = getAllEntities();
		ArrayList<Location> occupied = new ArrayList<Location>();
		ArrayList<Location> arr = new ArrayList<Location>();
		for(Entity e:ents){
			if(e!=null) occupied.add(e.getLoc());
		}
		for(Location add:adj) {
			if(add.isIn(occupied)) {
				arr.add(add);
			}
		}
		return arr;
	}
	/*
	 * Recursive method giving unobstructed neighbors within a range to a location
	 * @Author Michael
	 */
	public ArrayList<Location> emptyTilesInRange(Location init, int n) {
		ArrayList<Location> neighbors = new ArrayList<Location>();
		if(n>0) {
			for(Location loc:init.getAdjacentLocations()) {
				if(isValid(loc) && !isObstructed(loc)) {
					neighbors.add(loc);
					neighbors.addAll(emptyTilesInRange(loc,n-1)); //Recursive call to branch out and get more neighbors
				}
			}
			return neighbors;
		}
		else {
			//neighbors.add(init); //BRING THIS BACK IF THERE ARE ISSUES
			return neighbors;
		}
	}
}
