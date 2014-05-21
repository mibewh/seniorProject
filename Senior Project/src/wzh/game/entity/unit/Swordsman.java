package wzh.game.entity.unit;

import org.newdawn.slick.Image;
import wzh.game.Grid;

public class Swordsman extends Unit{ 

	public Swordsman(int x, int y, Image img, Grid g, int faction){
		super(x, y, img, g, faction);
        movePoints=5;
        attack= hp/10+(int)Math.sqrt(hp)*2;
        
	}
}
