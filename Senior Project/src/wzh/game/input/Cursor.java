package wzh.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.Entity;

public class Cursor extends Entity{
	
	public Cursor(int x, int y) throws SlickException{
		super(x,y,new SpriteSheet("SpriteSheetz.png",16,16).getSubImage(6, 0),null);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		super.update(gc, game, delta);
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_RIGHT)){
			x++;
		}
		if(input.isKeyPressed(Input.KEY_LEFT)){
			x--;
		}
		if(input.isKeyPressed(Input.KEY_DOWN)){
			y++;
		}
		if(input.isKeyPressed(Input.KEY_UP)){
			y--;
		}
		if(input.isKeyPressed(Input.KEY_SPACE)){
			//Select
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
	}
}
