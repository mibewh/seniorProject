package wzh.game.entity.unit;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
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
	protected double enemAttModifier;
	protected double allyAttModifier;
	protected boolean fortified;
	protected boolean wasFortified;
	
	protected Location lastLoc;
	
	protected Image colored;
	protected Image gray;
	protected Menu premoveMenu;
	protected Menu postmoveMenu;
	
	protected ArrayList<Location> moveLocs;
	protected ArrayList<Location> attackLocs;
	protected boolean displayMoves;
	protected boolean displayAttacks;
	
	protected SpriteSheet spriteSheet;
	protected Animation standingAnimation;
	protected Animation attackAnimation;
	
	public Unit(int x, int y, Image img, Grid g, int faction) {
		super(x,y,img,g);
		this.faction = faction;
		movePoints = 4;
		moveLocs = getMoveLocations();
		attackLocs = getAttackLocations();
		displayMoves = false;
		displayAttacks = false;
		hp = 100;
		fortified = false;
		attack = 30;
		enemAttModifier = 1.0;
		allyAttModifier = 1.0;
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException{
		super.update(gc, game, delta);
		if(premoveMenu!=null)
			premoveMenu.update(gc, game, delta);
		else if(postmoveMenu!=null)
			postmoveMenu.update(gc, game, delta);
		
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		//Sprite
		if(active)
			standingAnimation.draw(getScreenX(), getScreenY());
		
		else
			super.render(gc, game, g);
		//Move and Attack locations
		if(displayMoves) {
			g.setColor(new Color(0,0,1,.3f));
			for(Location loc:moveLocs) {
				g.fillRect((loc.getX()-grid.getUpperLeftX())*size,(loc.getY()-grid.getUpperLeftY())*size,size,size);
			}
		}
		else if(displayAttacks) {
			g.setColor(new Color(1,0,0,.3f));
			for(Location loc:attackLocs) {
				g.fillRect((loc.getX()-grid.getUpperLeftX())*size,(loc.getY()-grid.getUpperLeftY())*size,size,size);
			}
		}
		//Health Bar
		if(hp<100) {
			Color health = new Color(1,0,0,.99f);
			g.setColor(health);
			Rectangle healthBar = getHealthBar();
			g.fill(healthBar);
			g.draw(healthBar);
		}
		//Fortified buff
		if(fortified) {
			SpriteSheet units = new SpriteSheet("Unitz.png",16,16);
			units.getSubImage(0, 4).draw((loc.getX()-grid.getUpperLeftX())*size,(loc.getY()-grid.getUpperLeftY())*size);
		}
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
		other.setHp((int)(other.getHp() - getAttack()*allyAttModifier*(double)((10-other.getDefense()))/10));
		this.checkKill();
		if(!other.checkKill()){
			setHp((int)(this.getHp() - other.getAttack()*enemAttModifier*(double)((10-getDefense()))/10));
		}		
	}
	public int getAttack(){
		return (int)(attack*((double)hp/100));
	}
	public void setAttack(double attackChange){
		attack=attackChange;
	}
	
	public int getDefense(){
		return (1 + getFortification() + getTileDefense());
	}
	public int getFortification() {
		if(fortified) return 1;
		else return 0;
	}
	public int getTileDefense() {
		int def=0;
		if(grid.getB(loc)!=null) def+=grid.getB(loc).getFortification();
		if(grid.getMoveCost(loc)==2) def++;
		return def;
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
		return new Rectangle((loc.getX()-grid.getUpperLeftX())*size+1, (loc.getY()-grid.getUpperLeftY())*size+size-2, (hp*size/100)-2, 2);
	}
	public boolean isFortified() {
		return fortified;
	}
	public void setFortified(boolean f) {
		fortified=f;
	}
	public boolean wasFortified() {
		return wasFortified;
	}
	public void setWasFortified(boolean f) {
		wasFortified=f;
	}
	public void goGray() {
		sprite = gray;
	}
	public void goColor() {
		sprite = colored;
	}
	public Image getColored() {
		return colored;
	}
}
