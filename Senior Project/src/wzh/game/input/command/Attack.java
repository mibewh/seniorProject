package wzh.game.input.command;

import wzh.game.entity.Unit;
import wzh.game.input.Cursor;

public class Attack extends Command {
	
	private Unit u;
	private Cursor c;

	public Attack(Unit u, Cursor c){
		super();
		this.u=u;
		this.c=c;
		commandName="Attack";
	}
	
	public void select(){
		
	}
}
