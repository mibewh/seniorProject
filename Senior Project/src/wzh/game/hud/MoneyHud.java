package wzh.game.hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.input.Cursor;
import wzh.game.level.Level;

public class MoneyHud extends Hud{
	
	private Image coin;

	public MoneyHud(Cursor c, int width, int height) {
		super(c, width, height);
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			coin = ss.getSubImage(4, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if(c.getScreenX()*2>gc.getWidth()/2 && c.getScreenY()*2<gc.getWidth()/2) {
			x=10;
			y=gc.getHeight()-10-height;
		}
		else {
			x=gc.getWidth()-10-width;
			y=10;
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		if(!c.getMode().equals("Attack")) {
			super.render(gc, game, g);
			Level level = (Level)game.getCurrentState();
			g.drawString(level.getTreasury(level.getTurn())+"g",x+60, y+8);
			g.drawString(level.gpt(level.getTurn())+"g/turn", x+60, y+25);
			g.scale(2, 2);
			coin.draw((x+10)/2,(y+10)/2);
		}
	}

}
