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
import wzh.game.entity.Unit;
import wzh.game.input.command.Command;
import wzh.game.input.command.Move;
import wzh.game.input.command.Wait;

public class Cursor extends Entity{
	
	public boolean focus;
	public boolean unitSelect=false;
	private Unit u;
	private Menu menu;
	private String mode;
	
	public Cursor(int x, int y, Grid g) throws SlickException{
		super(x,y,new SpriteSheet("SpriteSheetz.png",16,16).getSubImage(6, 0),g);
		focus = false;
		mode = "Normal";
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		super.update(gc, game, delta);
		Input input = gc.getInput();
		if(focus == true) {
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
			if(input.isKeyPressed(Input.KEY_SPACE)){
				if(!unitSelect &&!grid.isEmpty(loc.getX(), loc.getY()) && grid.get(loc.getX(),loc.getY()) instanceof Unit
						&& grid.get(loc.getX(),loc.getY()).isActive()) {
					unitSelect=true;
					u = (Unit)grid.get(loc.getX(),loc.getY());
					u.displayPremoveMenu(this, gc);	
					focus = false;
				}
				else if(unitSelect && mode.equals("Move")){
					if(unitSelect && grid.isEmpty(loc.getX(), loc.getY())){
						u.moveTo(loc.getX(), loc.getY());
						unitSelect=false;
						//u.hideMenus();
						u.setDisplayMoves(false);
						u.displayPostmoveMenu(this, gc);
						mode = "Normal";
					}
				}
			}
		}
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
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
}
