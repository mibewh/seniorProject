package wzh.game.hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.input.Cursor;

public class TerrainHud extends Hud{
	
	public TerrainHud(Cursor c) {
		super(c);
	}
	public int getDefense() {
		int def=0;
		if(c.getGrid().getB(c.getLoc())!=null) def+=c.getGrid().getB(c.getLoc()).getFortification();
		if(c.getGrid().getMoveCost(c.getLoc())==2) def++;
		return def;
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
	}
}
