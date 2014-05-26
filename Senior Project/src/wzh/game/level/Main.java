package wzh.game.level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

	public Main(String title) {
		super(title);
	}
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainMenu());
		addState(new Level("maps/map2.tmx",1));
		addState(new Level("maps/map3.tmx",2));
		addState(new Level("maps/map4.tmx",3));
//		addState(new Level("maps/map5.tmx",4));
		addState(new Level("maps/map6.tmx",5));
//		addState(new Instructions());
		addState(new Play());
	}
	public static void main(String[] args) throws SlickException {
		Main main = new Main("Medieval Battle");
		AppGameContainer gc = new AppGameContainer(main);
		gc.setDisplayMode(640, 480, false);
		gc.setIcon("sword.ico");
		gc.setShowFPS(false);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.start();
	}
}
