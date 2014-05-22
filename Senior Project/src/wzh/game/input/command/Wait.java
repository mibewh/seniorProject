package wzh.game.input.command;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Wait extends Command {
	

	public Wait(Unit u, Cursor c){
		 super(u,c);
		 commandName="Wait";
		}

	public void select(){
		Unit u = (Unit)e;
		u.hideMenus();
		c.setUnitSelect(false);
		c.setFocus(true);
		u.setActive(false);
		u.goGray();
		c.setMode("Normal");
	}
}