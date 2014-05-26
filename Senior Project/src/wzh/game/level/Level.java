package wzh.game.level;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.Grid;
import wzh.game.entity.Entity;
import wzh.game.entity.building.Building;
import wzh.game.entity.building.Castle;
import wzh.game.entity.building.Village;
import wzh.game.hud.MoneyHud;
import wzh.game.hud.TerrainHud;
import wzh.game.hud.UnitHud;
import wzh.game.input.Cursor;

public class Level extends BasicGameState {
	protected String path;

	protected Grid grid;
	protected int turn;
	protected int turnNumber;
	protected int treasury1;
	protected int treasury2;
	
	protected TerrainHud terrain;
	protected MoneyHud money;
	protected UnitHud unit;
	protected UnitHud attack;
	
	protected int levelNum;
	

	public Level(String path, int levelNum) {
		super();
		this.path = path;
		this.levelNum=levelNum;
	}
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		turn = 1;
		turnNumber=1;
		treasury1=20;
		treasury2=20;
		
		grid = new Grid(new TiledMap(path));
		grid.setCursor(new Cursor(7,7,grid,turn));
		grid.getCursor().setLoc(grid.getBlueSpawn());
		grid.centerOnCursor(gc.getWidth(), gc.getHeight());
		grid.getCursor().setFocus(true);
		
		terrain = new TerrainHud(grid.getCursor(),150,52);
		money = new MoneyHud(grid.getCursor(),150,52);
		unit = new UnitHud(grid.getCursor(),150,75, false);
		attack = new UnitHud(grid.getCursor(),150,75, true);
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(2, 2);
		grid.render(gc, game, g);
		g.setColor(Color.white);
		renderMenus(gc, game, g);
		terrain.render(gc, game, g);
		money.render(gc, game, g);
		unit.render(gc, game, g);
		attack.render(gc, game, g);
		if(grid.getVictory()) {
			g.setFont(new TrueTypeFont(new Font("Euphemia", Font.BOLD , 36), false));
			g.scale(.5f, .5f);
			if(grid.getVictor()==1) {
				g.setColor(Color.blue);
				g.drawString("Blue Wins!", gc.getWidth()/2-100, gc.getHeight()/2-20);
			}
			else {
				g.setColor(Color.red);
				g.drawString("Red Wins!", gc.getWidth()/2-100, gc.getHeight()/2-20);
			}
			g.scale(2, 2);
		}
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
		terrain.update(gc, game, delta);
		money.update(gc, game, delta);
		unit.update(gc, game, delta);
		attack.update(gc, game, delta);
		if(grid.getVictory()) {
			grid.getCursor().setFocus(false);
			Input in = gc.getInput();
			if (in.isKeyPressed(Input.KEY_SPACE) || in.isKeyPressed(Input.KEY_ENTER) || in.isKeyPressed(Input.KEY_ESCAPE) || in.isKeyPressed(Input.KEY_BACK)) {
				init(gc, game);
				game.enterState(0);
			}
		}
	}
	public void changeTurn(GameContainer gc) {
		if(turn==1) {
			turn=2;
			grid.getCursor().setLoc(grid.getRedSpawn());
		}
		else  {
			turn=1;
			grid.getCursor().setLoc(grid.getBlueSpawn());
			turnNumber++;
			calculateGold();
		}
		grid.centerOnCursor(gc.getWidth(),gc.getHeight());
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
	public int gpt(int faction) {
		int num=50;
		for(Building b:grid.getAllBuildings()) {
			if(b instanceof Village) {
				if(b.getFaction()==faction)
					num+=20;
			}
		}
		return num;
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
	public int getTurn() {
		return turn;
	}
	public Grid getGrid() {
		return grid;
	}
	public int getID() {
		return levelNum;
	}

}