package wzh.game.entity;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.Location;
import wzh.game.input.Menu;

public class Unit extends Entity {
	private int faction;
	private int movePoints;
	private int hp;
	private int attack;
	private int defense;
	
	private Menu premoveMenu;
	private Menu postmoveMenu;
	
	private ArrayList<Location> moveLocs;
	
	public Unit(int x, int y, Image img, Grid g, int faction) {
		super(x,y,img,g);
		this.faction = faction;
		movePoints = 3;
		moveLocs = getMoveLocations();
		for(Location loc:moveLocs) { 
			System.out.println(loc);
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
		Color color = new Color(0,0,1,.5f);
		g.setColor(color);
		for(Location loc:moveLocs) {
			Rectangle r = new Rectangle(loc.getX()*size, loc.getY()*size, size, size);
			g.fill(r);
			g.draw(r);
		}
	}
	
	public void displayPremoveMenu() {
		//TODO Display Menu
	}
	public void displayPostmoveMenu() {
		
	}
	//Returns all existent, non-obstructed locations that are either adjacent or within movePoint radius of the unit
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
//		//Adjacent, existent, non-obstructed locations
//		for(Location dif:loc.getAdjacentLocations()) {
//			if(grid.isValid(dif) && grid.isEmpty(dif) && grid.getMoveCost(dif)!=100) {
//				locs.add(dif);
//			}
//		}
//		//Within move point limit
//		for(int x=0;x<grid.getCols();x++) {
//			for(int y=0;y<grid.getRows();y++){
//				Location dif = new Location(x,y);
//				if(!locs.contains(dif) && grid.getPathCost(grid.getShortestPath(loc, dif)) <= movePoints) {
//					locs.add(dif);
//				}
//			}
//		}
		for(Location dif:loc.getAdjacentLocations()) {
			if(grid.isValid(dif) && grid.isEmpty(dif) && grid.getMoveCost(dif)!=100) {
				locs.add(dif);
			}
		}
		for(int x=0;x<grid.getCols();x++) {
			for(int y=0;y<grid.getRows();y++){
				Location moveLoc = new Location(x,y);
				if(loc.getTileDistance(moveLoc)<=movePoints && grid.isEmpty(moveLoc) && grid.getMoveCost(moveLoc)!=100)
					locs.add(moveLoc);
			}
		}
		return locs;
	}
	
	public int getHp(){
		return hp;
	}
	
	public void setHp(int hpChange){
		hp=hpChange;
	}
	
	public int getattack(){
		return attack;
	}
	
	public void setAttack(int attackChange){
		attack=attackChange;
	}
	
	public int getDefense(){
		return defense;
	}
	
	public void setDefense(int defenseChange){
		defense=defenseChange;
	}
}
