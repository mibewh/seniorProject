package wzh.game.input.command;

import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.Entity;
import wzh.game.input.Cursor;

public class Exit extends Command {
	
	private StateBasedGame game;

	public Exit(Entity e, Cursor c, StateBasedGame game) {
		super(e, c);
		commandName = "Exit";
		this.game=game;
	}
	
	public void select(){
		game.enterState(0);
	}
}