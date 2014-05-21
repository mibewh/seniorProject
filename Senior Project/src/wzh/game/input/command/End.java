package wzh.game.input.command;

import wzh.game.entity.Entity;
import wzh.game.input.Cursor;

public class End extends Command {
	

	public End(Entity e, Cursor c) {
		super(e, c);
		commandName = "End";
	}
	
	public void select(){
		
	}
}