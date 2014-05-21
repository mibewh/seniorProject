package wzh.game.entity.unit;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import wzh.game.Grid;
import wzh.game.Location;

public class Archer extends Unit {
	
	public final double ATTACK_BUFF = 1.5;

	public Archer(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
	}
	@Override
	public void attack(Unit other) {
		if(other instanceof Archer) {
			super.attack(other);
		}
		else {
			other.setHp(other.getHp() - (int)((double)getAttack() * ATTACK_BUFF * (double)(10-other.getDefense())/10.0));
			other.checkKill();
		}
	}
	//TODO attack locations
	@Override
	public ArrayList<Location> getAttackLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		locs = grid.neighborsInRange(loc, 2);
		for(int i=locs.size()-1;i>=0;i--) {
			Location testLoc = locs.get(i);
			if(!grid.isValid(testLoc) || !(grid.get(testLoc) instanceof Unit) || testLoc.isIn(testLoc.getAdjacentLocations())) {
				locs.remove(i);
			}
		}
		return locs;
	}

}
