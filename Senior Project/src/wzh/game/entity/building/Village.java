package wzh.game.entity.building;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public abstract class Village extends Building {
	public Village(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
	}
	
	/*
	 *public void addMoney()
	 */
	
	public void goGray() {
	 }
	
	public void goColor() {
		try {
			SpriteSheet ss = new SpriteSheet("SpriteSheetz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 1);
			else
				sprite = ss.getSubImage(1, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	
	public int getFortification(){
		return 1;
	}
	//TODO Check THE VILLAGE fortification of 0
}
