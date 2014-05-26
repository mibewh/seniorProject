package wzh.game.input.command;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Wait extends Command {
	

	public Wait(Unit u, Cursor c, String cn){
		 super(u,c);
		 commandName=cn;
		}

	public void select(){
		Unit u = (Unit)e;
		u.hideMenus();
		c.setUnitSelect(false);
		c.setFocus(true);
		u.setActive(false);
		u.goGray();
		c.setMode("Normal");
		if(c.getGrid().getB(c.getLoc())!=null) {
			c.getGrid().getB(c.getLoc()).setFaction(u.getFaction());
		}
	}
}