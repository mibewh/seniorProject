package wzh.game.level;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {
	
	private static final int MAX_ENTRIES = 1;
	
	private TrueTypeFont font;
	private final String FONT_STYLE = "Euphemia";
	private int curIndex;
	private Rectangle cursor;
	
	public MainMenu() {
		super();
		font = new TrueTypeFont(new Font(FONT_STYLE, Font.BOLD , 20), false);
		curIndex = 0;
	}

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		cursor = new Rectangle(75, 185, 10, 10);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.drawRect(0, 0, gc.getWidth(), gc.getHeight());
		font.drawString(gc.getWidth()/2-175, 100, "Medieval Battle BETA (Working title)",Color.white);
		font.drawString(100, 175, "Play",Color.white);
		font.drawString(100, 225, "Exit",Color.white);
		g.setColor(Color.white);
		g.fill(cursor);
		g.draw(cursor);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S)) {
			curIndex++;
			if(curIndex>MAX_ENTRIES) curIndex=0;
		}
		else if(input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W)) {
			curIndex--;
			if(curIndex<0) curIndex=MAX_ENTRIES;
		}
		if(input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ENTER)) {
			switch(curIndex) {
			case 0:
				game.enterState(2);
				break;
			case 1:
				gc.exit();
				break;
			}
		}
		cursor.setY(185+curIndex*50);
	}

	public int getID() {
		return 0;
	}

}
