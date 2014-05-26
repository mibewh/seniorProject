package wzh.game.level;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {
	
	private static final int MAX_ENTRIES = 2;
	
	private final String FONT_STYLE = "Euphemia";
	private int curIndex;
	private Music music;
	private Image menu;
	private Image cursor;
	
	public MainMenu() {
		super();
		new TrueTypeFont(new Font(FONT_STYLE, Font.BOLD , 20), false);
		curIndex = 0;
		try {
			music = new Music("music/menuMusic.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		SpriteSheet temp = new SpriteSheet("Unitz.png",16,16);
		cursor = temp.getSubImage(3, 0);
		music.loop();
		menu = new Image("MenuFront.png");
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.black);
//		g.drawRect(0, 0, gc.getWidth(), gc.getHeight());
//		font.drawString(gc.getWidth()/2-175, 100, "Medieval Battle BETA (Working title)",Color.white);
//		font.drawString(100, 175, "Play",Color.white);
//		font.drawString(100, 225, "Exit",Color.white);
//		g.setColor(Color.white);
//		g.fill(cursor);
//		g.draw(cursor);
		menu.draw();
		cursor.draw(170,170+80*curIndex,3);
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
				game.enterState(1);
				break;
			case 1:
				game.enterState(11);
				break;
			case 2:
				gc.exit();
				break;
			}
		}
	}
	public Music getMusic() {
		return music;
	}

	public int getID() {
		return 0;
	}

}
