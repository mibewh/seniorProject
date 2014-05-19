package wzh.game.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Commands {

	protected String commandName; 
	
	public Commands(String commandName) {
		this.commandName = commandName;
	}
	
	public String getCommand(){
		return commandName; 
	}
	
	public void select(){
		//to be implemented later
	}
	
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
	}

    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
    	sprite.draw(x*size,y*size);
    }
}
