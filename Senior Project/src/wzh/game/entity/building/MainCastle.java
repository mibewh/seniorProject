package wzh.game.entity.building;

import org.newdawn.slick.Image;
import wzh.game.Grid;

/**
 * A MainCastle is the most important building for a player
 * If the MainCastle is captured by another player, 
 * @author Harrison
 *
 */

public class MainCastle extends Building {

	public MainCastle(int x, int y, Image img, Grid g) {
		super(x, y, img, g);
	}
	
	public int getFortification(){
		return 3;
	}
}
