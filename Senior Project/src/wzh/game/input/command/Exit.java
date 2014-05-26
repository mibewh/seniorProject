package wzh.game.input.command;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.Entity;
import wzh.game.input.Cursor;

public class Exit extends Command {
	
	private StateBasedGame game;
	private boolean sure;

	public Exit(Entity e, Cursor c, GameContainer gc, StateBasedGame game) {
		super(e, c);
		commandName = "Exit";
		this.game=game;
		sure = false;
	}
	
	public void select(){
		if(!sure) {
			sure=true;
			commandName = "Sure?";
		}
		else {
			game.enterState(0);
		}
	}
}