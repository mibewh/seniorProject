package wzh.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.entity.Entity;
import wzh.game.entity.building.Building;
import wzh.game.entity.building.Castle;
import wzh.game.entity.building.Fort;
import wzh.game.entity.building.MainCastle;
import wzh.game.entity.building.Village;
import wzh.game.entity.unit.Archer;
import wzh.game.entity.unit.Horseman;
import wzh.game.entity.unit.Spearman;
import wzh.game.entity.unit.Swordsman;
import wzh.game.input.Cursor;

public class Grid {
	private TiledMap map;
	private int rows, cols;
	Entity[][] entities;
	Building[][] buildings;
	private Cursor cursor;
	private Location upperLeft;
	
	public Grid(TiledMap map) throws SlickException {
		this.map = map;
		upperLeft = new Location(0,0);
		rows = map.getWidth();
		cols = map.getHeight();
		entities = new Entity[rows][cols];
		buildings = new Building[rows][cols];
		loadEntities();
	}
	public void loadEntities() throws SlickException{
		int layer = map.getLayerIndex("Objects");
		SpriteSheet tiles = new SpriteSheet("SpriteSheetz.png",16,16);
		for(int x=0;x<cols;x++) {
			for(int y=0;y<rows;y++) {
				int ID = map.getTileId(x, y, layer);
				Entity toAdd = null;
				Building build = null;
				switch(ID) {
				case 227://Red Sword
					toAdd = new Swordsman(x,y,this,2);
					break;
				case 228://Blue Sword
					toAdd = new Swordsman(x,y,this,1);
					break;
				case 242://Red Spear
					toAdd = new Spearman(x,y,this,2);
					break;
				case 243://Blue Spear
					toAdd = new Spearman(x,y,this,1);
					break;
				case 257://Red Archer
					toAdd = new Archer(x,y,this,2);
					break;
				case 258://Blue Archer
					toAdd = new Archer(x,y,this,1);
					break;
				case 272://Red Horse
					toAdd = new Horseman(x,y,this,2);
					break;
				case 273://Blue Horse
					toAdd = new Horseman(x,y,this,1);
					break;
				case 16:
					build = new Village(x,y,tiles.getSubImage(0, 1),this,0);
					break;
				case 31:
					build = new Castle(x,y,tiles.getSubImage(0,2),this,0);
					break;
				case 32:
					build = new Castle(x,y,tiles.getSubImage(1,2),this,2);
					break;
				case 33:
					build = new Castle(x,y,tiles.getSubImage(2,2),this,1);
					break;
				case 46:
					build = new Fort(x,y,tiles.getSubImage(0,3),this,0);
					break;
				case 61:
					build = new MainCastle(x,y,tiles.getSubImage(0,4),this,2);
					break;
				case 62:
					build = new MainCastle(x,y,tiles.getSubImage(1,4),this,1);
					break;
				default:
					build = null;
					toAdd=null;
					break;
				}
				if(build!=null)
					buildings[x][y] = build;
				if(toAdd!=null)
					entities[x][y] = toAdd;
			}
		}
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		System.out.println(upperLeft);
		cursor.update(gc, game, delta);
		for(int x = 0; x<cols;x++) {
			for(int y = 0; y<rows;y++) {
				if(entities[x][y] != null) {
					entities[x][y].update(gc, game, delta);
				}
			}
		}
		for(int x = 0; x<cols;x++) {
			for(int y = 0; y<rows;y++) {
				if(buildings[x][y] != null) {
					buildings[x][y].update(gc, game, delta);
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
				if(buildings[x][y] != null) {
					buildings[x][y].render(gc,game,g);
				}
			}
		}
		for(int x = 0; x<cols;x++) {
			for(int y = 0; y<rows;y++) {
				if(entities[x][y] != null) {
					entities[x][y].render(gc,game,g);
				}
			}
		}
		cursor.render(gc, game, g);
	}
	public Entity get(int x, int y) {
		return entities[x][y];
	}
	public Entity get(Location loc) {
		return entities[loc.getX()][loc.getY()];
	}
	public Building getB(Location loc) {
		return buildings[loc.getX()][loc.getY()];
	}
	public ArrayList<Entity> getAllEntities() {
		ArrayList<Entity> arr = new ArrayList<Entity>();
		for(int x=0;x<cols;x++) {
			for(int y=0;y<rows;y++) {
				if(!isEmpty(x,y)) //Remove this line if there are complications
					arr.add(entities[x][y]);
			}
		}
		return arr;
	}
	public ArrayList<Building> getAllBuildings() {
		ArrayList<Building> arr = new ArrayList<Building>();
		for(int x=0;x<cols;x++) {
			for(int y=0;y<rows;y++) {
				if(buildings[x][y]!=null) arr.add(buildings[x][y]);
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
	public int getUpperLeftX() {
		return upperLeft.getX();
	}
	public void setUpperLeftX(int x) {
		upperLeft = new Location(x,upperLeft.getY());
	}
	public int getUpperLeftY() {
		return upperLeft.getY();
	}
	public void setUpperLeftY(int y) {
		upperLeft = new Location(upperLeft.getX(),y);
	}
	public boolean isEmpty(Location loc) {
		if(entities[loc.getX()][loc.getY()] == null) return true;
		else return false;
	}
	public boolean isEmpty(int x, int y) {
		if(entities[x][y] == null || entities[x][y] instanceof Building) return true;
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
	public Cursor getCursor() {
		return cursor;
	}
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
	public int getMoveCost(Location loc) {
		if(!isEmpty(loc)) return 100;
		else if(map.getTileId(loc.getX(), loc.getY(), map.getLayerIndex("Obstructions")) != 0) return 100;
		else if(map.getTileId(loc.getX(), loc.getY(), map.getLayerIndex("Difficult")) != 0) return 2;
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
					neighbors.addAll(emptyTilesInRange(loc,n-getMoveCost(loc))); //Recursive call to branch out and get more neighbors
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
