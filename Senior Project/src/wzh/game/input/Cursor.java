package wzh.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
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
			if((input.isKeyPressed(Input.KEY_RIGHT) | input.isKeyPressed(Input.KEY_D)) && grid.isValid(x+1, y)){
				x++;
			}
			if((input.isKeyPressed(Input.KEY_LEFT) || input.isKeyPressed(Input.KEY_A)) && grid.isValid(x-1, y)){
				x--;
			}
			if((input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) && grid.isValid(x, y+1)){
				y++;
			}
			if((input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) && grid.isValid(x, y-1)){
				y--;
			}
			if(input.isKeyPressed(Input.KEY_SPACE)){
				if(!grid.isEmpty(x, y) && grid.get(x,y) instanceof Unit) {
					Unit u = (Unit)grid.get(x,y);
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
