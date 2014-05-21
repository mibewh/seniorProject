package wzh.game.input.command;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Move extends Command {
	
	
	public Move(Unit u, Cursor c){
	 super(u,c);;
	 commandName="Move";
	}
	
	public void select(){
		Unit u = (Unit)e;
		u.setDisplayMoves(true);
		c.setMode("Move");
		u.hideMenus();
		c.setFocus(true);
	}
}
