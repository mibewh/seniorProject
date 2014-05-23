package wzh.game.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class UnitHud extends Hud {

	public UnitHud(Cursor c, int w, int h) {
		super(c,w,h);
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		x=10;
		if(c.getScreenY()*2<gc.getHeight()/2 && c.getScreenX()*2<gc.getWidth()/2) {
			y=gc.getHeight()-10-height;
		}
		else {
			y=10;
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		if(getEntity()!=null && getEntity() instanceof Unit) {
			super.render(gc, game, g);
			g.drawString(getEntity().getName(),x+60, y+15);
			g.drawString("HP",x+10, y+40);
			Unit u = (Unit)getEntity();
			g.drawString(u.getHp()+"/100",x+90, y+55);
			//Health bar, Total Length:115
			float len = 115 * ((float)u.getHp()/100);
			Rectangle red = new Rectangle(x+30,y+45,len,10);
			Rectangle backing = new Rectangle(x+30+len,y+45,115-len,10);
			g.setColor(new Color(.2f,.2f,.2f,.9f)); //Gray
			g.fill(backing);
			g.draw(backing);
			g.setColor(new Color(1,0,0,.9f));
			g.fill(red);
			g.draw(red);
			g.scale(2, 2);
			u.getSprite().draw((x+10)/2, (y+10)/2);
		}
	}
}
