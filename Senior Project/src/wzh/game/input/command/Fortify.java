package wzh.game.input.command;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Fortify extends Command {

	public Fortify(Unit u, Cursor c){
		super(u,c);
		commandName="Fortify";
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
		
		u.setFortified(true);
	}
}
