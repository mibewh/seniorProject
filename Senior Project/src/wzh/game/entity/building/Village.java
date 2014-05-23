package wzh.game.entity.building;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Village extends Building {
	public Village(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
	}
	
	/*
	 *public void addMoney()
	 */
	
	public void goGray() {
	}
	public void setFaction(int f){
		super.setFaction(f);
		try {
			SpriteSheet ss = new SpriteSheet("SpriteSheetz.png",16,16);
			if(faction==1) {
				sprite = ss.getSubImage(2, 1);
			}
			else
				sprite = ss.getSubImage(1, 1);
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	public int getFortification(){
		return 0;
	}

	public void hideMenus() {
	}
}
