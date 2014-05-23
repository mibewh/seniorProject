package wzh.game.level;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;

import wzh.game.input.command.Command;

public class MainMenu extends BasicGameState {
	private TrueTypeFont font;
	private final String FONT_STYLE = "Euphemia";
	private int x,y;
	
	private ArrayList<Command> commands;
	
	public MainMenu() {
		super();
		font = new TrueTypeFont(new Font(FONT_STYLE, Font.BOLD , 16), false);
		x=50;
		y=150;
	}

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

		
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		
		font.drawString(gc.getWidth()/2, 100, "Medieval Battle",Color.white);
		int curY = y;
		for(Command c: commands){
			font.drawString(x, curY, c.getCommand(),Color.white);
			curY+=20;
		}
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
	}

	public int getID() {
		return 0;
	}

}
