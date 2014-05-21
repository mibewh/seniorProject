package wzh.game.input.command;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.Entity;
import wzh.game.input.Cursor;
import wzh.game.input.Menu;

public abstract class Command {

	protected String commandName; 
	protected Menu menu;
	protected Entity e;
	protected Cursor c;
	
	public Command(Entity e, Cursor c) {
		this.e = e;
		this.c = c;
		commandName = "default";
	}
	
	public String getCommand(){
		return commandName; 
	}
	
	public abstract void select();
	
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
	}

    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
    
    }
}
