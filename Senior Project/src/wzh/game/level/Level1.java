package wzh.game.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.Grid;

public class Level1 extends BasicGameState {
	
	Grid grid;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		grid = new Grid(new TiledMap("res/map.tmx"));
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		//Change 3rd and 4th 0's to allow for screen scrolling
		g.scale(2, 2);
		grid.getMap().render(0, 0, 0, 0, 20, 20);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
