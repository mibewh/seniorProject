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
		addState(new Level("maps/map.tmx",1));
		//addState(new Play());
	}
	public static void main(String[] args) throws SlickException {
		Main main = new Main("Medieval Battle");
		AppGameContainer gc = new AppGameContainer(main);
		gc.setIcon("sword.ico");
		gc.setShowFPS(false);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.start();
	}

}
