package wzh.game.level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		System.out.println("MAIN METHOD");
		try {
			AppGameContainer gc = new AppGameContainer(new Main("Test"));
			gc.setDisplayMode(500, 500, false);
			gc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
