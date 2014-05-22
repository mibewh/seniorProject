package wzh.game.entity.building;

import org.newdawn.slick.Image;
import wzh.game.Grid;

/**
 * A MainCastle is the most important building for a player
 * If the MainCastle is captured by another player, the player will loose
 * The MainCastle provides a defensive bonus(fortification) of 3
 * @author Harrison
 *
 */

public class MainCastle extends Castle {

	public MainCastle(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
	}
	
	public int getFortification(){
		return 3;
	}

	public void hideMenus() {
		// TODO Buildings!
		
	}
}
