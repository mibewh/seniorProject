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
		Unit toAdd;
		int x = c.getLoc().getX();
		int y = c.getLoc().getY();
		if(commandName.equals("Spearman")) {
			toAdd = new Spearman(x, y, c.getGrid(), faction);
		}
		else if(commandName.equals("Swordsman")) {
			toAdd = new Swordsman(x, y, c.getGrid(), faction);
		}
		else if(commandName.equals("Archer")) {
			toAdd = new Archer(x, y, c.getGrid(), faction);
		}
		else  {
			toAdd = new Horseman(x, y, c.getGrid(), faction);
		}
		c.getGrid().add(toAdd);
	}
	
}
