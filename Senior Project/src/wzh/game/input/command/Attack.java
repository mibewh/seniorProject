package wzh.game.input.command;

import wzh.game.entity.Unit;
import wzh.game.input.Cursor;

public class Attack extends Command {
	

	public Attack(Unit u, Cursor c){
		super(u,c);
		commandName="Attack";
	}
	
	public void select(){
		
	}
}
