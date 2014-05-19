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
	
	public Cursor(){
		//SpriteSheet s = new SpriteSheet("SpriteSheetz.png",16,16);
		//sprite = new Image.SpriteSheetz.getSubImage(7*16, 0, 16, 16);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
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
			
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		//g.d
	}

//	public SpriteSheet getCursor() {
		//return Cursor;
//	}

	public void setCursor(SpriteSheet cursor) {
		//Cursor = cursor;
	}
}
