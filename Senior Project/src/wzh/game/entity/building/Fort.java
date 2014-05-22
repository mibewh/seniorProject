package wzh.game.entity.building;

import org.newdawn.slick.Image;

import wzh.game.Grid;

public class Fort extends Building {

	public Fort(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
		// TODO Auto-generated constructor stub
	}

	public void hideMenus() {
		// TODO Auto-generated method stub

	}

	public int getFortification() {
		return 2;
	}

}
