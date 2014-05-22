package wzh.game.input.command;

import wzh.game.entity.Entity;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;
import wzh.game.level.Level;

public class End extends Command {
	
	private Level level;
	

	public End(Level level, Cursor c) {
		super(null, c);
		this.level = level;
		commandName = "End Turn";
	}
	
	public void select(){
		level.changeTurn();
		c.hideMenus();
		for(Entity e:level.getGrid().getAllEntities()) {
			if(e instanceof Unit) {
				Unit u = (Unit)e;
				u.goColor();
				u.setActive(true);
			}
		}
		c.setFocus(true);
	}
}