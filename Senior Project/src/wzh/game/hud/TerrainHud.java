package wzh.game.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.input.Cursor;

public class TerrainHud extends Hud {
	
	public TerrainHud(Cursor c,int w,int h) {
		super(c,w,h);
	}
	public int getDefense() {
		int def=0;
		if(c.getGrid().getB(c.getLoc())!=null) def+=c.getGrid().getB(c.getLoc()).getFortification();
		if(c.getGrid().getMoveCost(c.getLoc())==2) def++;
		return def;
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if(c.getScreenX()*2>gc.getWidth()/2 && c.getScreenY()*2>gc.getHeight()/2) {
			x=10;
			y=gc.getHeight()-10-height;
			System.out.println("True");
		}
		else {
			x=gc.getWidth()-10-width;
			y=gc.getHeight()-10-height;
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		super.render(gc, game, g);
		TiledMap map = c.getGrid().getMap();
		g.setColor(Color.black);
		g.setFont(font);
		if(getBuilding()!=null) {
			g.scale(2f, 2f);
			getBuilding().getSprite().draw((x+10)/2,(y+10)/2);
			g.scale(.5f, .5f);
			g.drawString(getBuilding().getName(),x+60, y+8);
		}
		else if(map.getTileId(c.getLoc().getX(), c.getLoc().getY(), map.getLayerIndex("Obstructions"))!=0) {
			g.scale(2f, 2f);
			map.getTileImage(c.getLoc().getX(), c.getLoc().getY(), map.getLayerIndex("Obstructions")).draw((x+10)/2,(y+10)/2);
			g.scale(.5f, .5f);
			g.drawString("Obstructing",x+60, y+8);
		}
		else if(map.getTileId(c.getLoc().getX(), c.getLoc().getY(), map.getLayerIndex("Difficult"))!=0){
			g.scale(2f, 2f);
			map.getTileImage(c.getLoc().getX(), c.getLoc().getY(), map.getLayerIndex("Difficult")).draw((x+10)/2,(y+10)/2);
			g.scale(.5f, .5f);
			g.drawString("Difficult",x+60, y+8);
		}
		else {
			g.scale(2f, 2f);
			map.getTileImage(c.getLoc().getX(), c.getLoc().getY(), map.getLayerIndex("Passable")).draw((x+10)/2,(y+10)/2);
			g.scale(.5f, .5f);
			g.drawString("Normal",x+60, y+8);
		}
		g.drawString("Defense: "+getDefense(), x+60, y+25);
		g.scale(2, 2);
	}
}
