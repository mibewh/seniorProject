package wzh.game.input.command;

import wzh.game.entity.Entity;
import wzh.game.input.Cursor;

public class Exit extends Command {
	

	public Exit(Entity e, Cursor c) {
		super(e, c);
		commandName = "Exit";
	}
	
	public void select(){
		//TODO Exit Game
	}
}