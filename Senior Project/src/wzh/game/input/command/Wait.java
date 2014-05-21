package wzh.game.input.command;

import wzh.game.entity.Unit;
import wzh.game.input.Cursor;

public class Wait extends Command {
	

	public Wait(Unit u, Cursor c){
		 super(u,c);
		 commandName="Wait";
		}

	public void select(){
		Unit u = (Unit)e;
		u.hideMenus();
		c.setFocus(true);
		u.setActive(false);
		c.setMode("Normal");
	  /*DOES NOT WORK
	   *u.getSprite().getSubImage(1, 0, 16, 16).draw((float)c.getLoc().getX(), (float)c.getLoc().getY(), org.newdawn.slick.Color.gray);
	   */
		//Comment
		//TODO FIX THE GRAYNESS
	}
}