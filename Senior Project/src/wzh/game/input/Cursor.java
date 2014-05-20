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
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		super.update(gc, game, delta);
		Input input = gc.getInput();
		if(focus == true) {
			if((input.isKeyPressed(Input.KEY_RIGHT) | input.isKeyPressed(Input.KEY_D)) && grid.isValid(loc.getX()+1, loc.getY())){
				loc = new Location(loc.getX()+1,loc.getY());
			}
			if((input.isKeyPressed(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_A)) && grid.isValid(loc.getX()-1, loc.getY())){
				loc = new Location(loc.getX()-1,loc.getY());
			}
			if((input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) && grid.isValid(loc.getX(), loc.getY()+1)){
				loc = new Location(loc.getX(),loc.getY()+1);
			}
			if((input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) && grid.isValid(loc.getX(), loc.getY()-1)){
				loc = new Location(loc.getX(),loc.getY()-1);
			}
			if(input.isKeyPressed(Input.KEY_SPACE)){
				if(!unitSelect &&!grid.isEmpty(loc.getX(), loc.getY()) && grid.get(loc.getX(),loc.getY()) instanceof Unit) {
					unitSelect=true;
					u = (Unit)grid.get(loc.getX(),loc.getY());
					//u.displayPremoveMenu();		
					ArrayList<Command> commands = new ArrayList<Command>();
					commands.add(new Move(u, this));
					commands.add(new Wait());
					menu = new Menu(this, commands);
					//focus = false;
				}
				else if(unitSelect){
					if(unitSelect && grid.isEmpty(loc.getX(), loc.getY())){
						u.moveTo(loc.getX(), loc.getY());
						unitSelect=false;
						menu = null;
					}
				}
			}
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
		if(unitSelect){
			menu.render(gc,game,g);
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
}
