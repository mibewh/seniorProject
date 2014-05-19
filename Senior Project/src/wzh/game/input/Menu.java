package wzh.game.input;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Menu{
	ArrayList<Command> commands = new ArrayList();
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		commands.add("Move");
		commands.add("Attack");
		commands.add("Wait");
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
	}
}
