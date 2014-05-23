package wzh.game.input.command;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Attack extends Command {
	
	//TODO Attack functionality
	public Attack(Unit u, Cursor c){
		super(u,c);
		commandName="Attack";
	}
	
	public void select(){
		Unit u = (Unit)e;
		u.setDisplayAttacks(true);
		c.setMode("Attack");
		c.setLoc(u.getAttackLocations().get(0));
		u.hideMenus();
		c.setFocus(true);
		u.setFortified(false);
	}
}
