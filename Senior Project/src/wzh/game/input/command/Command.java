package wzh.game.input.command;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.Unit;
import wzh.game.input.Cursor;
import wzh.game.input.Menu;

public class Command {

	protected String commandName; 
	protected Menu menu;
	protected Unit u;
	protected Cursor c;
	
	public Command(Unit u, Cursor c) {
		this.u = u;
		this.c = c;
		commandName = "default";
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
    
    }
}
