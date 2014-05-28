package wzh.game.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	
	private static final int MAX_ENTRIES = 4;
	
	private int curIndex;

	private Image cursor;
	private Image menu;

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		SpriteSheet temp = new SpriteSheet("Unitz.png",16,16);
		cursor = temp.getSubImage(3, 0);
		menu = new Image("MenuLevels.png");
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		menu.draw();
		cursor.draw(170,115+77*curIndex,3);
		Level level = null;
		switch(curIndex) {
		case 0:
			level = (Level)game.getState(1);
			break;
		case 1:
			level = (Level)game.getState(2);
			break;
		case 2:
			level = (Level)game.getState(3);
			break;
		case 3:
			level = (Level)game.getState(4);
			break;
		case 4:
			level = (Level)game.getState(5);
			break;
		}
		if(level!=null) {
			g.scale(.25f, .25f);
			level.getGrid().getMap().render(0, (int) ((float)(gc.getHeight()*4)-(float)(level.getGrid().getMap().getHeight()*16)));
			g.scale(4, 4);
		}
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
			Main g = (Main)game;
			g.music1.stop();
			g.music2.loop(1, .2f);
			switch(curIndex) {
			case 0:
				game.getState(1).init(gc, game);;
				game.enterState(1);
				break;
			case 1:
				game.getState(2).init(gc, game);;
				game.enterState(2);
				break;
			case 2:
				game.getState(3).init(gc, game);;
				game.enterState(3);
				break;
			case 3:
				game.getState(4).init(gc, game);;
				game.enterState(4);
				break;
			case 4:
				game.getState(5).init(gc, game);;
				game.enterState(5);
				break;
			}
		}
		else if(input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) {
			game.enterState(0);
		}
		
	}

	public int getID() {
		return 10;
	}

}
