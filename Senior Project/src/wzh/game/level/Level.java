package wzh.game.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;

public abstract class Level extends BasicGameState {

	protected Grid grid;

	public Level() {
		super();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		//Change 3rd and 4th 0's to allow for screen scrolling
		g.scale(2, 2);
		grid.render(gc, game, g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		grid.update(gc, game, delta);
	}
	public abstract int getID();

}