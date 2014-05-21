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
import wzh.game.entity.unit.Unit;
import wzh.game.input.command.Cancel;
import wzh.game.input.command.Command;
import wzh.game.input.command.End;
import wzh.game.level.Level;

public class Cursor extends Entity{
	
	private boolean focus;
	private boolean unitSelect;
	private boolean menuSelect;
	private int curFaction;
	private Unit u;
	private String mode;
	private Menu optionsMenu;
	
	public Cursor(int x, int y, Grid g, int initFaction) throws SlickException{
		super(x,y,new SpriteSheet("SpriteSheetz.png",16,16).getSubImage(7, 0),g);
		focus = false;
		mode = "Normal";
		setFaction(initFaction);
		unitSelect=false;
		menuSelect=false;
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		super.update(gc, game, delta);
		Input input = gc.getInput();
		if(focus == true) {
			processMoveKeys(input);
			checkSpaceBar(input, gc, game);
		}
		else if(menuSelect) {
			optionsMenu.update(gc, game, delta);
		}
		checkEscapeKey(input, gc, game);
	}

	private void checkEscapeKey(Input input, GameContainer gc, StateBasedGame game) {
		if(input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) {
			if(unitSelect) {
				if(mode.equals("Move")) {
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
			else if(grid.isEmpty(loc)){
				displayOptionsMenu(this, gc, game);
				menuSelect=true;
				focus=false;
			}
		}
	}

	private void checkSpaceBar(Input input, GameContainer gc, StateBasedGame game) {
		if(input.isKeyPressed(Input.KEY_SPACE)){
			if(!unitSelect &&!grid.isEmpty(loc.getX(), loc.getY()) && grid.get(loc.getX(),loc.getY()) instanceof Unit
					&& grid.get(loc.getX(),loc.getY()).isActive()) {
				u = (Unit)grid.get(loc.getX(),loc.getY());
				if(curFaction==u.getFaction()) {
					unitSelect=true;
					u.displayPremoveMenu(this, gc);	
					focus = false;
				}
			}
			else if(unitSelect && mode.equals("Move")){
				if(grid.isEmpty(loc.getX(), loc.getY())){
					u.moveTo(loc.getX(), loc.getY());
					unitSelect=false;
					//u.hideMenus();
					u.setDisplayMoves(false);
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
		}
		
	}

	private void cancelMove(GameContainer gc) {
		mode = "Normal";
		u.setDisplayMoves(false);
		loc=u.getLoc();
		focus=false;
		u.displayPremoveMenu(this, gc);
	}

	private void processMoveKeys(Input input) {
		if((input.isKeyPressed(Input.KEY_RIGHT) | input.isKeyPressed(Input.KEY_D)) && grid.isValid(loc.getX()+1, loc.getY())){
			Location moveLoc = new Location(loc.getX()+1,loc.getY());
			checkMove(moveLoc);
		}
		if((input.isKeyPressed(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_A)) && grid.isValid(loc.getX()-1, loc.getY())){
			Location moveLoc = new Location(loc.getX()-1,loc.getY());
			checkMove(moveLoc);
		}
		if((input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) && grid.isValid(loc.getX(), loc.getY()+1)){
			Location moveLoc =new Location(loc.getX(),loc.getY()+1);
			checkMove(moveLoc);
		}
		if((input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) && grid.isValid(loc.getX(), loc.getY()-1)){
			Location moveLoc = new Location(loc.getX(),loc.getY()-1);
			checkMove(moveLoc);
		}
	}

	private void displayOptionsMenu(Cursor cursor, GameContainer gc, StateBasedGame game) {
		ArrayList<Command> commands = new ArrayList<Command>();
		Level level = (Level)game.getCurrentState();
		commands.add(new End(level,this));
		commands.add(new Cancel(this,this));
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
		if(menuSelect) {
			optionsMenu.render(gc, game, g);
		}
	}
	public void setFocus(boolean focus){
		this.focus = focus;
	}
	public Entity getAtPos() {
		return grid.get(loc.getX(), loc.getY());
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
}
