package wzh.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame{

	public Main(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		try {
			AppGameContainer gc = new AppGameContainer(new Main("Test"));
			gc.setDisplayMode(500, 500, false);
			gc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
