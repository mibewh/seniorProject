package wzh.game.level;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;

public abstract class Level extends BasicGameState {

	protected Grid grid;
	protected int turn;
	protected int turnNumber;

	public Level() {
		super();
	}
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		turn = 1;
		turnNumber=1;
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(2, 2);
		grid.render(gc, game, g);
		g.setColor(Color.white);
		//g.drawString("Turn: "+turnNumber, 0, 0);
		//g.drawString("Player "+turn+"'s turn", gc.getWidth()-50, 20);
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
		}
		grid.getCursor().setFaction(turn);
	}
	public Grid getGrid() {
		return grid;
	}
	public abstract int getID();

}