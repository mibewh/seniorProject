package wzh.game.input.command;

import wzh.game.entity.building.Castle;
import wzh.game.entity.unit.Archer;
import wzh.game.entity.unit.Horseman;
import wzh.game.entity.unit.Spearman;
import wzh.game.entity.unit.Swordsman;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class BuyCommand extends Command {
	
	private int faction;

	public BuyCommand(Castle b, Cursor c, String command, int f) {
		super(b, c);
		commandName = command;
		faction = f;
	}

	public void select() {
		//TODO Make purchasing cost the player money
		Castle b = (Castle)e;
		Unit toAdd;
		int x = c.getLoc().getX();
		int y = c.getLoc().getY();
		if(commandName.equals("Buy Spearman")) {
			toAdd = new Spearman(x, y, c.getGrid(), faction);
		}
		else if(commandName.equals("Buy Swordsman")) {
			toAdd = new Swordsman(x, y, c.getGrid(), faction);
		}
		else if(commandName.equals("Buy Archer")) {
			toAdd = new Archer(x, y, c.getGrid(), faction);
		}
		else  {
			toAdd = new Horseman(x, y, c.getGrid(), faction);
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
