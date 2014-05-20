package wzh.game.entity;

import org.newdawn.slick.Image;
import wzh.game.Grid;

public class SwordGuy extends Unit{
	
	private int movePoints=5;
	private int hp=100;

	public SwordGuy(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
	}

}
