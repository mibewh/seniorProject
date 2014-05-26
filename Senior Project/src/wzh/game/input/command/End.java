package wzh.game.input.command;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import wzh.game.entity.Entity;
import wzh.game.entity.building.Building;
import wzh.game.entity.building.Castle;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;
import wzh.game.level.Level;

public class End extends Command {
	
	private Level level;
	private GameContainer gc;

	public End(Level level, Cursor c, GameContainer gc) {
		super(null, c);
		this.level = level;
		this.gc = gc;
		commandName = "End Turn";
	}
	
	public void select(){
		level.changeTurn(gc);
		c.hideMenus();
		ArrayList<Entity> el = level.getGrid().getAllEntities();
		for(Entity e:el) {
			if(e instanceof Unit) {
				Unit u = (Unit)e;
				u.goColor();
				if(u.isFortified()) {
					u.setHp(u.getHp()+10);
					if(u.getHp()>100) u.setHp(100);
				}
				u.setActive(false);
				u.setAttacking(false);
				u.getStandingAnim().restart();
				u.setActive(true);
			}
		}
		for(Building b:level.getGrid().getAllBuildings()) {
			if(b instanceof Castle) {
				Castle castle = (Castle)b;
				castle.setActive(true);
			}
		}
		c.setFocus(true);
	}
}