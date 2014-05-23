package wzh.game.level;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.Grid;
import wzh.game.entity.Entity;
import wzh.game.entity.building.Building;
import wzh.game.entity.building.Castle;
import wzh.game.entity.building.Village;
import wzh.game.hud.TerrainHud;
import wzh.game.input.Cursor;

public abstract class Level extends BasicGameState {
	protected String path;

	protected Grid grid;
	protected int turn;
	protected int turnNumber;
	protected int treasury1;
	protected int treasury2;
	protected TerrainHud terrain;

	public Level(String path) {
		super();
		this.path = path;
	}
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		turn = 1;
		turnNumber=1;
		treasury1=20;
		treasury2=20;
		
		grid = new Grid(new TiledMap(path));
		grid.setCursor(new Cursor(7,7,grid,turn));
		grid.getCursor().setFocus(true);
		terrain = new TerrainHud(grid.getCursor());
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(2, 2);
		grid.render(gc, game, g);
		g.setColor(Color.white);
		renderMenus(gc, game, g);
	}
	private void renderMenus(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		ArrayList<Entity> ents = grid.getAllEntities();
		for(Entity e:ents) {
			if(e.getMenu()!=null)
				e.getMenu().render(gc, game, g);
		}
		ArrayList<Building> buildings = grid.getAllBuildings();
		for(Building b:buildings) {
			if(b instanceof Castle) {
				Castle c = (Castle)b;
				if(c.getMenu()!=null)
					c.getMenu().render(gc, game, g);
			}
		}
		if(grid.getCursor().getMenu()!=null)
			grid.getCursor().getMenu().render(gc, game, g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		grid.update(gc, game, delta);
	}
	public void changeTurn() {
		if(turn==1) {
			turn=2;
		}
		else  {
			turn=1;
			turnNumber++;
			calculateGold();
		}
		grid.getCursor().setFaction(turn);
	}
	private void calculateGold() {
		for(Building b:grid.getAllBuildings()) {
			if(b instanceof Village) {
				if(b.getFaction()==1)
					treasury1+=20;
				if(b.getFaction()==2)
					treasury2+=20;
			}
		}
		treasury1+=50;
		treasury2+=50;
	}
	public int getTreasury(int faction) {
		if(faction==1) return treasury1;
		else return treasury2;
	}
	public void setTreasury(int faction, int amount) {
		if(faction==1) {
			treasury1 = amount;
		}
		else {
			treasury2 = amount;
		}
	}
	public Grid getGrid() {
		return grid;
	}
	public abstract int getID();

}