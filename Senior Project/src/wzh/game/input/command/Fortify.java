package wzh.game.input.command;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Fortify extends Command {


	public Fortify(Unit u, Cursor c){
		super(u,c);
		commandName="Fortify";
	}
	
	public void select(){
		
	}
}
