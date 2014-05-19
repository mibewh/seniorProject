package wzh.game.level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new Level1());
		
	}
	public static void main(String[] args) throws SlickException {
		System.out.println("MAIN METHOD");
		AppGameContainer gc = new AppGameContainer(new Main("Test"));
		gc.setDisplayMode(640, 640, false);
		gc.start();
	}

}
