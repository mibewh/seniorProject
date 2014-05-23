package wzh.game.input.command;

import org.newdawn.slick.GameContainer;

import wzh.game.entity.Entity;
import wzh.game.entity.building.Castle;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Cancel extends Command {
	
	GameContainer gc;

	public Cancel(Entity e, Cursor c, GameContainer gc) {
		super(e, c);
		this.gc=gc;
		commandName = "Cancel";
	}

	public void select() {
		e.hideMenus();
		if(e instanceof Unit) {
			Unit u = (Unit)e;
			u.revertToLastLoc();
			c.setLoc(u.getLoc());
			if(c.isPostMove()){
				c.setFocus(false);
				u.displayPremoveMenu(c, gc);
				c.setPostMove(false);
			}
			else {
				c.setFocus(true);
				c.setUnitSelect(false);
			}
		}
		else if(e instanceof Cursor) {
			c.hideMenus();
			c.setMenuSelect(false);
			c.setFocus(true);
		}
		else if (e instanceof Castle) {
			Castle b = (Castle)e;
			b.hideMenus();
			c.setFocus(true);
			c.setBuildingSelect(false);
		}
	}
	

}
