package wzh.game.entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.Location;
import wzh.game.input.Menu;

public class Unit extends Entity {
	private int faction;
	private int movePoints;
	private int hp;
	
	private Menu premoveMenu;
	private Menu postmoveMenu;
	
	public Unit(int x, int y, Image img, Grid g, int faction) {
		super(x,y,img,g);
		this.faction = faction;
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
	}
	
	public void displayPremoveMenu() {
		//TODO Display Menu
	}
	public void displayPostmoveMenu() {
		
	}
	//Returns all existent, non-obstructed locations that are either adjacent or within movePoint radius of the unit
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		//Adjacent, existent, non-obstructed locations
		for(Location dif:loc.getAdjacentLocations()) {
			if(grid.isValid(dif) && grid.isEmpty(dif) && grid.getMoveCost(dif)!=100)
				locs.add(dif);
		}
		//Within move point limit
		for(int x=0;x<grid.getCols();x++) {
			for(int y=0;y<grid.getRows();y++){
				Location dif = new Location(x,y);
				if(!locs.contains(dif) && grid.getPathCost(grid.getShortestPath(loc, dif)) <= movePoints)
					locs.add(dif);
			}
		}
		return locs;
	}
	
	public int getHp(){
		return hp;
	}

	
	public void setHp(int hpChanged){
		hp=hpChanged;
	}
}
