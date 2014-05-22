package wzh.game.input.command;

import wzh.game.entity.building.Castle;
import wzh.game.input.Cursor;

public class BuyCommand extends Command {
	
	private int faction;

	public BuyCommand(Castle b, Cursor c, String command, int f) {
		super(b, c);
		commandName = command;
		faction = f;
	}

	public void select() {
		Castle b = (Castle)e;
		if(commandName.equals("Swordsman")) {
			
		}
		else if(commandName.equals("Swordsman")) {
			
		}
		else if(commandName.equals("Archer")) {
			
		}
		else if(commandName.equals("Horseman")) {
			
		}
		
	}
	
}
