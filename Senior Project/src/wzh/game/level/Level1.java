package wzh.game.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.Grid;
import wzh.game.Location;
import wzh.game.entity.unit.Unit;
import wzh.game.input.Cursor;

public class Level1 extends Level {
	

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		grid = new Grid(new TiledMap("res/map.tmx"));
		SpriteSheet s = new SpriteSheet("spriteSheetz.png",16,16);
		//grid.add(new Unit(5,5,s.getSubImage(1, 0),grid,0));
		grid.setCursor(new Cursor(7,7,grid));
		grid.getCursor().setFocus(true);
		
		//TODO HUD (New class(es))
	}
	public int getID() {
		return 1;
	}

}
