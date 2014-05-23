package wzh.game.input.command;

import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.building.Castle;
import wzh.game.entity.unit.Archer;
import wzh.game.entity.unit.Horseman;
import wzh.game.entity.unit.Spearman;
import wzh.game.entity.unit.Swordsman;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;
import wzh.game.level.Level;

public class BuyCommand extends Command {
	
	private int faction;
	private Level level;

	public BuyCommand(Castle b, Cursor c, String command, int f, StateBasedGame game) {
		super(b, c);
		commandName = command;
		faction = f;
		level = (Level)game.getCurrentState();
	}

	public void select() {
		Castle b = (Castle)e;
		Unit toAdd;
		int x = c.getLoc().getX();
		int y = c.getLoc().getY();
		int money = level.getTreasury(faction);
		if(commandName.equals("Spearman-"+Spearman.COST+"g")) {
			toAdd = new Spearman(x, y, c.getGrid(), faction);
			level.setTreasury(faction, money-Spearman.COST);
		}
		else if(commandName.equals("Swordsman-"+Swordsman.COST+"g")) {
			toAdd = new Swordsman(x, y, c.getGrid(), faction);
			level.setTreasury(faction, money-Swordsman.COST);
		}
		else if(commandName.equals("Archer-"+Archer.COST+"g")) {
			toAdd = new Archer(x, y, c.getGrid(), faction);
			level.setTreasury(faction, money-Archer.COST);
		}
		else  {
			toAdd = new Horseman(x, y, c.getGrid(), faction);
			level.setTreasury(faction, money-Horseman.COST);
		}
		toAdd.setActive(false);
		toAdd.goGray();
		c.getGrid().add(toAdd);
		c.setFocus(true);
		c.setMode("Normal");
		c.setBuildingSelect(false);
		b.hideMenus();
		b.setActive(false);
	}
	
}
