package wzh.game.input.command;
import wzh.game.entity.Unit;
import wzh.game.input.Cursor;

public class Move extends Command {
	
	private Unit u;
	private Cursor c;
	
	public Move(Unit u, Cursor c){
	 super();
	 commandName="Move";
	}
	
	public void select(){
		u.setDisplayMoves(true);
		c.setMode("Move");
	}
}
