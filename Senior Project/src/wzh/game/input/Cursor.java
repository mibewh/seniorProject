package wzh.game.input;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.Location;
import wzh.game.entity.Entity;
import wzh.game.entity.building.Castle;
import wzh.game.entity.unit.Unit;
import wzh.game.input.command.Cancel;
import wzh.game.input.command.Command;
import wzh.game.input.command.End;
import wzh.game.input.command.Exit;
import wzh.game.level.Level;

public class Cursor extends Entity{
	
	private boolean focus;
	private boolean unitSelect;
	private boolean menuSelect;
	private int curFaction;
	private Unit u;
	private Castle b;
	private String mode;
	private Menu optionsMenu;
	private boolean postMove;
	private int curIndex;
	private boolean buildingSelect;
	private enum Key {
		RIGHT,LEFT,UP,DOWN,SPACE,ESCAPE,NONE;
	}
	private Key lastKey;
	private boolean trackingTime=false;
	private int timeSince;
	
	public Cursor(int x, int y, Grid g, int initFaction) throws SlickException{
		super(x,y,new SpriteSheet("SpriteSheetz.png",16,16).getSubImage(7, 0),g);
		focus = false;
		mode = "Normal";
		setFaction(initFaction);
		unitSelect=false;
		menuSelect=false;
		buildingSelect=false;
		postMove=false;
		lastKey = Key.NONE;
		timeSince = 0;
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		super.update(gc, game, delta);
		Input input = gc.getInput();
		if(focus == true) {
			if(mode.equals("Attack"))
				cycleAttacks(input);
			else {
				processMoveKeys(input,gc);
				if(trackingTime) timeSince+=delta;
				if(timeSince>=500)
					checkStillHeld(input,gc);
			}
			checkSpaceBar(input, gc, game);
		}
		else if(menuSelect) {
			optionsMenu.update(gc, game, delta);
		}
		checkEscapeKey(input, gc, game);
	}

	private void checkStillHeld(Input input, GameContainer gc) {
		int upperX = grid.getUpperLeftX();
		int upperY = grid.getUpperLeftY();
		int screenWidth = gc.getWidth()/(size*2);
		int screenHeight = gc.getHeight()/(size*2);
		if((input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) && grid.isValid(loc.getX()+1, loc.getY()) && lastKey==Key.RIGHT){
			rightKey(upperX, screenWidth);
			timeSince=450;
		}
		else if((input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) && grid.isValid(loc.getX()-1, loc.getY()) && lastKey==Key.LEFT){
			leftKey(upperX);
			timeSince=450;
		}
		else if((input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) && grid.isValid(loc.getX(), loc.getY()+1) && lastKey==Key.DOWN){
			downKey(upperY, screenHeight);
			timeSince=450;
		}
		else if((input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) && grid.isValid(loc.getX(), loc.getY()-1) && lastKey==Key.UP){
			upKey(upperY);
			timeSince=450;
		}
		else {
			trackingTime = false;
			timeSince=450;
			lastKey=Key.NONE;
			timeSince = 0;
		}
	}

	private void checkEscapeKey(Input input, GameContainer gc, StateBasedGame game) {
		if(input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) {
			if(unitSelect) {
				if(mode.equals("Move") || mode.equals("Attack")) {
					cancelMove(gc);
				}
				else if(mode.equals("Normal")) {
					Menu curMenu = u.getMenu();
					curMenu.get(curMenu.size()-1).select();
				}
			}
			else if(menuSelect) {
				optionsMenu.get(optionsMenu.size()-1).select();
			}
			else if(buildingSelect) {
				b.hideMenus();
				focus=true;
				buildingSelect = false;
			}
			else if(grid.isEmpty(loc)){
				displayOptionsMenu(this, gc, game);
				menuSelect=true;
				focus=false;
			}
			lastKey = Key.ESCAPE;
		}
	}

	private void checkSpaceBar(Input input, GameContainer gc, StateBasedGame game) {
		if(input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ENTER)){
			if(!unitSelect &&!grid.isEmpty(loc.getX(), loc.getY()) && grid.get(loc.getX(),loc.getY()) instanceof Unit
					&& grid.get(loc.getX(),loc.getY()).isActive()) {
				u = (Unit)grid.get(loc.getX(),loc.getY());
				if(curFaction==u.getFaction()) {
					unitSelect=true;
					u.displayPremoveMenu(this, gc);	
					u.playSelect();
					focus = false;
				}
			}
			else if(!buildingSelect && grid.getB(loc)!=null && grid.getB(loc).getFaction()==curFaction && grid.getB(loc) instanceof Castle
					&& grid.isEmpty(loc) && !unitSelect) {
				b = (Castle)grid.getB(loc);
				if(b.isActive()) {
					b.displayPurchaseMenu(this, gc, game);
					focus = false;
					buildingSelect = true;
				}
			}
			else if(unitSelect && mode.equals("Move")){
				if(grid.isEmpty(loc.getX(), loc.getY())){
					u.moveTo(loc.getX(), loc.getY());
					u.playMove();
					u.setDisplayMoves(false);
					focus=false;
					postMove=true;
					u.displayPostmoveMenu(this, gc);
					mode = "Normal";
				}
				else if(loc.equals(u.getLoc())) {
					cancelMove(gc);
				}
			}
			else if(!unitSelect && grid.isEmpty(loc.getX(), loc.getY()))
			{
				displayOptionsMenu(this,gc,game);
				menuSelect=true;
				focus=false;
			}
			else if(unitSelect && mode.equals("Attack")) {
				u.attack((Unit)grid.get(loc));
				if(u!=null) {
					loc = u.getLoc();
					u.goGray();
					u.playAttack();
					u.setDisplayAttacks(false);
					u.setActive(false);
					if(grid.getB(loc)!=null) {
						grid.getB(loc).setFaction(u.getFaction());
					}
				}
				focus = true;
				unitSelect = false;
				mode = "Normal";
			}
			lastKey = Key.SPACE;
		}
		
	}

	private void cancelMove(GameContainer gc) {
		mode = "Normal";
		curIndex = 0;
		u.setDisplayMoves(false);
		u.setDisplayAttacks(false);
		loc=u.getLoc();
		focus=false;
		if(postMove)
			u.displayPostmoveMenu(this, gc);
		else
			u.displayPremoveMenu(this, gc);
	}

	private void processMoveKeys(Input input, GameContainer gc) {
		int upperX = grid.getUpperLeftX();
		int upperY = grid.getUpperLeftY();
		int screenWidth = gc.getWidth()/(size*2);
		int screenHeight = gc.getHeight()/(size*2);
		if((input.isKeyPressed(Input.KEY_RIGHT) || input.isKeyPressed(Input.KEY_D)) && grid.isValid(loc.getX()+1, loc.getY())){
			rightKey(upperX, screenWidth);
			trackingTime=true;
			timeSince=0;
		}
		else if((input.isKeyPressed(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_A)) && grid.isValid(loc.getX()-1, loc.getY())){
			leftKey(upperX);
			trackingTime=true;
			timeSince=0;
		}
		else if((input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) && grid.isValid(loc.getX(), loc.getY()+1)){
			downKey(upperY, screenHeight);
			trackingTime=true;
			timeSince=0;
		}
		else if((input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) && grid.isValid(loc.getX(), loc.getY()-1)){
			upKey(upperY);
			trackingTime=true;
			timeSince=0;
		}
	}

	private void upKey(int upperY) {
		Location moveLoc = new Location(loc.getX(),loc.getY()-1);
		if(upperY!=0 && moveLoc.getY()<=upperY+2) {
			grid.setUpperLeftY(upperY-1);
		}
		checkMove(moveLoc);
		lastKey = Key.UP;
	}

	private void downKey(int upperY, int screenHeight) {
		Location moveLoc =new Location(loc.getX(),loc.getY()+1);
		if(moveLoc.getY()>=grid.getUpperLeftY()+(screenHeight-3) && grid.isValid(new Location(moveLoc.getX(),moveLoc.getY()+3))) {
			//!(upperY+screenHeight>=grid.getCols()-screenHeight) &&
			grid.setUpperLeftY(upperY+1);
		}
		checkMove(moveLoc);
		lastKey = Key.DOWN;
	}

	private void leftKey(int upperX) {
		Location moveLoc = new Location(loc.getX()-1,loc.getY());
		if(upperX!=0 && moveLoc.getX()<=upperX+2) {
			grid.setUpperLeftX(upperX-1);
		}
		checkMove(moveLoc);
		lastKey = Key.LEFT;
	}

	private void rightKey(int upperX, int screenWidth) {
		Location moveLoc = new Location(loc.getX()+1,loc.getY());
		if(moveLoc.getX()>=grid.getUpperLeftX()+(screenWidth-3) && grid.isValid(new Location(moveLoc.getX()+3,moveLoc.getY()))) {
			//!(upperX>=grid.getRows()-screenWidth) && moveLoc.getX()>=grid.getUpperLeftX()+(screenWidth-3)
			grid.setUpperLeftX(upperX+1);
		}
		checkMove(moveLoc);
		lastKey = Key.RIGHT;
	}
	private void cycleAttacks(Input input) {
		if(input.isKeyPressed(Input.KEY_RIGHT) || input.isKeyPressed(Input.KEY_D)
				|| input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) {
			curIndex++;
			if(curIndex>=u.getAttackLocations().size())
				curIndex=0;
			loc = u.getAttackLocations().get(curIndex);
		}
		else if(input.isKeyPressed(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_A) 
				|| input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) {
			curIndex--;
			if(curIndex<0)
				curIndex=u.getAttackLocations().size()-1;
			loc = u.getAttackLocations().get(curIndex);
		}
	}

	private void displayOptionsMenu(Cursor cursor, GameContainer gc, StateBasedGame game) {
		ArrayList<Command> commands = new ArrayList<Command>();
		Level level = (Level)game.getCurrentState();
		commands.add(new End(level,this,gc));
		commands.add(new Exit(this,this,gc,game));
		commands.add(new Cancel(this,this,gc));
		optionsMenu = new Menu(this,commands,gc);
	}

	private void checkMove(Location moveLoc) {
		if(mode.equals("Normal")) {
			loc = moveLoc;
		} 
		else if(mode.equals("Move")) {
			for(Location possible:u.getMoveLocsList()) {
				if(possible.equals(moveLoc)) {
					loc = moveLoc;
					return;
				}
			}
			if(moveLoc.equals(u.getLoc())) loc = moveLoc;
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
	}
	public void setFocus(boolean focus){
		this.focus = focus;
	}
	public Entity getAtPos() {
		return grid.get(loc.getX(), loc.getY());
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public void setUnitSelect(boolean s) {
		unitSelect = s;
	}
	public void setMenuSelect(boolean m) {
		menuSelect = m;
	}
	public void hideMenus() {
		optionsMenu=null;
		menuSelect=false;
	}
	public void setFaction(int f) {
		curFaction = f;
		try {
			SpriteSheet ss = new SpriteSheet("SpriteSheetz.png",16,16);
			if(f==1)
				sprite=ss.getSubImage(7, 2);
			else
				sprite=ss.getSubImage(7, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public boolean isPostMove() {
		return postMove;
	}
	public void setPostMove(boolean b) {
		postMove=b;
	}

	public Menu getMenu() {
		return optionsMenu;
	}

	public void setBuildingSelect(boolean c) {
		buildingSelect = c;		
	}

	public String getName() {
		return null;
	}
	public Unit getUnit() {
		return u;
	}
}
