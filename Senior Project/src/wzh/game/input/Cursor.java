package wzh.game.input;

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

public class Cursor extends Entity{
	
	public boolean focus;
	
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
				if(!grid.isEmpty(loc.getX(), loc.getY()) && grid.get(loc.getX(),loc.getY()) instanceof Unit) {
					Unit u = (Unit)grid.get(loc.getX(),loc.getY());
					u.displayPremoveMenu();
				}
			}
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
	}
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
}
