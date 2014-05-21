package wzh.game.input.command;

import wzh.game.entity.Entity;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Cancel extends Command {

	public Cancel(Entity e, Cursor c) {
		super(e, c);
		commandName = "Cancel";
	}

	public void select() {
		e.hideMenus();
		if(e instanceof Unit) {
			Unit u = (Unit)e;
			u.revertToLastLoc();
		}
		c.setFocus(true);
		c.setUnitSelect(false);
	}
	

}
