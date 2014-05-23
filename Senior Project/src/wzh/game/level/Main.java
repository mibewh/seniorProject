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
		addState(new Level1("maps/map.tmx"));
		addState(new Level2("maps/map2.tmx"));
	}
	public static void main(String[] args) throws SlickException {
		System.out.println("MAIN METHOD");
		Main main = new Main("Test");
		AppGameContainer gc = new AppGameContainer(main);
		gc.setDisplayMode(640, 480, false);
		gc.start();
	}

}
