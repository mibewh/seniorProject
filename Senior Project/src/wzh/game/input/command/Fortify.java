package wzh.game.input.command;

import wzh.game.entity.Unit;
import wzh.game.input.Cursor;

public class Fortify extends Command {

	private Unit u;
	private Cursor c;

	public Fortify(Unit u, Cursor c){
		super();
		this.u=u;
		this.c=c;
		commandName="Fortify";
	}
	
	public void select(){
		
	}
}
