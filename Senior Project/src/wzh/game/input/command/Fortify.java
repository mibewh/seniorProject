package wzh.game.input.command;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Fortify extends Command {

	//TODO MAKE FORTIFY HEAL UNIT AND ADD BONUS DEFENSE

	public Fortify(Unit u, Cursor c){
		super(u,c);
		commandName="Fortify";
	}
	
	public void select(){
		
	}
}
