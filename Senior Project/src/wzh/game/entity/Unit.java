package wzh.game.entity;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.Location;
import wzh.game.input.Cursor;
import wzh.game.input.Menu;
import wzh.game.input.command.Command;
import wzh.game.input.command.Move;
import wzh.game.input.command.Wait;

public class Unit extends Entity {
	private int faction;
	private int movePoints;
	private int hp;
	private int attack;
	private int defense;
	
	private Menu premoveMenu;
	private Menu postmoveMenu;
	
	private ArrayList<Location> moveLocs;
	private boolean displayMoves;
	
	public Unit(int x, int y, Image img, Grid g, int faction) {
		super(x,y,img,g);
		this.faction = faction;
		movePoints = 3;
		moveLocs = getMoveLocations();
		displayMoves = false;
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
		g.setColor(new Color(0,0,1,.3f));
		if(displayMoves) {
			for(Location loc:moveLocs) {
				g.drawRect(loc.getX()*size,loc.getY()*size,size,size);
			}
		}
	}
	public void displayPremoveMenu(Cursor c, GameContainer gc) {
		ArrayList<Command> commands = new ArrayList<Command>();
		commands.add(new Move(this, c));
		commands.add(new Wait());
		premoveMenu = new Menu(c, commands, gc);
	}
	public void hideMenus() {
		premoveMenu = null;
		postmoveMenu = null;
	}
	public void displayPostmoveMenu(Cursor c, GameContainer gc) {
		
	}
	//Returns all existent, non-obstructed locations that are either adjacent or within movePoint radius of the unit
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int x=0;x<grid.getCols();x++) {
			for(int y=0;y<grid.getRows();y++){
				Location moveLoc = new Location(x,y);
				if(loc.getTileDistance(moveLoc)<=movePoints && grid.isEmpty(moveLoc) && grid.getMoveCost(moveLoc)!=100)
					locs.add(moveLoc);
			}
		}
		return locs;
	}
	public void setDisplayMoves(boolean b) {
		displayMoves = b;
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
