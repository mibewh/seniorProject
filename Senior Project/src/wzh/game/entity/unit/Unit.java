package wzh.game.entity.unit;

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
import wzh.game.entity.Entity;
import wzh.game.input.Cursor;
import wzh.game.input.Menu;
import wzh.game.input.command.Attack;
import wzh.game.input.command.Cancel;
import wzh.game.input.command.Command;
import wzh.game.input.command.Fortify;
import wzh.game.input.command.Move;
import wzh.game.input.command.Wait;

public abstract class Unit extends Entity {
	protected int faction;
	protected int movePoints;
	protected int hp;
	protected double attack;
	protected int tileDefense;
	protected int fortification;
	
	protected Location lastLoc;
	
	protected Menu premoveMenu;
	protected Menu postmoveMenu;
	
	protected ArrayList<Location> moveLocs;
	private ArrayList<Location> attackLocs;
	protected boolean displayMoves;
	private boolean displayAttacks;
	
	public Unit(int x, int y, Image img, Grid g, int faction) {
		super(x,y,img,g);
		this.faction = faction;
		movePoints = 4;
		moveLocs = getMoveLocations();
		attackLocs = getAttackLocations();
		displayMoves = false;
		displayAttacks = false;
		hp = 100;
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException{
		super.update(gc, game, delta);
		if(premoveMenu!=null)
			premoveMenu.update(gc, game, delta);
		else if(postmoveMenu!=null)
			postmoveMenu.update(gc, game, delta);
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
		if(displayMoves) {
			g.setColor(new Color(0,0,1,.3f));
			for(Location loc:moveLocs) {
				g.fillRect(loc.getX()*size,loc.getY()*size,size,size);
			}
		}
		else if(displayAttacks) {
			g.setColor(new Color(1,0,0,.3f));
			for(Location loc:attackLocs) {
				g.fillRect(loc.getX()*size,loc.getY()*size,size,size);
			}
		}
		Color health = new Color(1,0,0,.8f);
		g.setColor(health);
		Rectangle healthBar = getHealthBar();
		g.fill(healthBar);
		g.draw(healthBar);
	}
	public void displayPremoveMenu(Cursor c, GameContainer gc) {
		moveLocs = getMoveLocations();
		ArrayList<Command> commands = new ArrayList<Command>();
		commands.add(new Move(this, c));
		if(getAttackLocations().size()>0)
			commands.add(new Attack(this, c));
		commands.add(new Fortify(this, c));
		commands.add(new Wait(this, c));
		commands.add(new Cancel(this,c,gc));
		setLastLoc(loc);
		premoveMenu = new Menu(c, commands, gc);
	}
	public void hideMenus() {
		premoveMenu = null;
		postmoveMenu = null;
	}
	public void displayPostmoveMenu(Cursor c, GameContainer gc) {
		moveLocs = getMoveLocations();
		ArrayList<Command> commands = new ArrayList<Command>();
		if(getAttackLocations().size()>0)
			commands.add(new Attack(this, c));
		commands.add(new Wait(this, c));
		commands.add(new Cancel(this, c,gc));
		postmoveMenu = new Menu(c,commands,gc);
		
	}
	//Returns all existent, non-obstructed locations that are either adjacent or within movePoint radius of the unit
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = grid.emptyTilesInRange(loc, movePoints);
		return Location.removeDuplicates(locs);
	}
	public ArrayList<Location> getAttackLocations() {
		ArrayList<Location> arr = grid.getAdjacentNeighbors(loc);
		for(int i = arr.size()-1;i>=0;i--) {
			Unit u = (Unit)grid.get(arr.get(i));
			if(u.getFaction()==faction)
				arr.remove(i);
		}
		return arr;
	}
	
	/*
	 * @return Stored list of move locations
	 */
	public ArrayList<Location> getMoveLocsList() {
		return moveLocs;
	}
	public void setDisplayMoves(boolean b) {
		moveLocs = getMoveLocations();
		displayMoves = b;
	}
	public void setDisplayAttacks(boolean b) {
		attackLocs = getAttackLocations();
		displayAttacks = b;
	}
	public int getHp(){
		return hp;
	}
	
	public void setHp(int hpChange){
		hp=hpChange;
	}
	public void attack(Unit other) {
		other.setHp(other.getHp() - (int)((double)getAttack() * (double)((10-other.getDefense()))/10.0));
		if(!other.checkKill()) {
			setHp(getHp() - (int)((double)other.getAttack() * (double)((10-getDefense()))/10));
			checkKill();
		}
	}
	public int getAttack(){
		return (int)(attack * (double)(hp/10+(int)Math.sqrt(hp)*2));
	}
	public double getAttackMult() {
		return attack;
	}
	public void setAttack(double attackChange){
		attack=attackChange;
	}
	
	public int getDefense(){
		//possibility for game balance
		return (1+fortification+tileDefense)*(hp/100);
	}
	
	public void setLastLoc(Location loc) {
		lastLoc = loc;
	}
	public void revertToLastLoc() {
		moveTo(lastLoc.getX(), lastLoc.getY());
		loc = lastLoc;
	}
	public boolean checkKill() {
		if(hp<=0) {
			setDisplayAttacks(false);
			grid.remove(loc.getX(), loc.getY());
			return true;
		}
		else return false;
	}
	public int getFaction() {
		return faction;
	}
	public Menu getMenu() {
		if(premoveMenu!=null){
			return premoveMenu;
		}
		else if(postmoveMenu!=null) {
			return postmoveMenu;
		}
		else return null;
	}
	public Rectangle getHealthBar() {
		return new Rectangle(loc.getX()*size+1, loc.getY()*size+size-2, hp/100*size-1, 2);
	}
	public abstract void goGray();
	public abstract void goColor();
}
