package wzh.game.level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Instructions extends BasicGameState {
	
	private int curPage;
	private Image page1;
	private Image page2;
	private Image page3;
	
	public int getID() {
		return 11;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		curPage=1;
		page1=new Image("MenuInstructions.png");
		page2=new Image("MenuInstructions2.png");
		page3=new Image("MenuInstructions3.png");
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		if(curPage==1)
			page1.draw();
		if(curPage==2)
			page2.draw();
		if(curPage==3)
			page3.draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE))
				curPage++;
		if(curPage>=4){
			curPage=1;
			game.enterState(0);
		}
	}
}
