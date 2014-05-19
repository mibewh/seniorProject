package wzh.game.input;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.input.command.Command;

public class Menu{
	private int length = 0;
	ArrayList<Command> commands = new ArrayList();
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		for(Command i: commands){
			length += 16;
		}
		
		g.drawString()
	}
}
