package wzh.game.level;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import wzh.game.Grid;
import wzh.game.entity.Unit;
import wzh.game.input.Cursor;
import wzh.game.input.Menu;
import wzh.game.input.command.Command;
import wzh.game.input.command.Move;
import wzh.game.input.command.Wait;

public class Level1 extends BasicGameState {
	
	private Grid grid;
	private Menu menu; //Temporary test menu

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		grid = new Grid(new TiledMap("res/map.tmx"));
		SpriteSheet s = new SpriteSheet("spriteSheetz.png",16,16);
		grid.add(new Unit(5,5,s.getSubImage(1, 0),grid,0));
		grid.setCursor(new Cursor(7,7,grid));
		grid.getCursor().setFocus(true);
		ArrayList<Command> commands = new ArrayList<Command>();
		commands.add(new Move());
		commands.add(new Wait());
		
		menu = new Menu(0,0, commands);
		
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		//Change 3rd and 4th 0's to allow for screen scrolling
		g.scale(2, 2);
		grid.render(gc, game, g);
		menu.render(gc,game,g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		grid.update(gc, game, delta);
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
