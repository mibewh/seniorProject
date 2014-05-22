package wzh.game.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.Grid;
import wzh.game.input.Cursor;

public class Level2 extends Level {
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		super.init(gc, game);
		grid = new Grid(new TiledMap("res/map2.tmx"));
		grid.setCursor(new Cursor(7,7,grid,turn));
		grid.getCursor().setFocus(true);
	}
	public int getID() {
		return 2;
	}

}
