package wzh.game.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.Entity;
import wzh.game.entity.building.Building;
import wzh.game.input.Cursor;

public class Hud {
	
	protected final float opacity = .8f;
	protected final int width = 75;
	protected final int height = 45;
	protected Cursor c;
	protected int x,y;

	public Hud(Cursor c) {
		this.c=c;
	}
	public Building getBuilding() {
		return c.getGrid().getB(c.getLoc());
	}
	public Entity getEntity() {
		return c.getGrid().get(c.getLoc());
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		Rectangle r = new Rectangle(x, y, width, height);
		g.setColor(new Color(1,1,1,opacity));
		g.fill(r);
		g.draw(r);
	}
}
