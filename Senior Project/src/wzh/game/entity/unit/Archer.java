package wzh.game.entity.unit;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;
import wzh.game.Location;

public class Archer extends Unit {
	
	public final double ATTACK_BUFF = 1.5;

	public Archer(int x, int y, Grid g, int faction) {
		super(x, y, null, g, faction);
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 2);
			else
				sprite = ss.getSubImage(1, 2);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void attack(Unit other) {
		if(other instanceof Archer) {
			super.attack(other);
		}
		else {
			other.setHp(other.getHp() - (int)((double)getAttack() * ATTACK_BUFF * (double)((10-other.getDefense()))/10.0));
			other.checkKill();
		}
	}
	@Override
	public ArrayList<Location> getAttackLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		ArrayList<Location> all = grid.getFilledLocations();
		for(Location potential:all) {
			if(loc.getTileDistance(potential)==2) {
				Unit u = (Unit)grid.get(potential);
				if(u.getFaction()!=faction)
					locs.add(potential);
			}
		}
		return locs;
	}
	public void goGray() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			sprite = ss.getSubImage(0, 2);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void goColor() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 2);
			else
				sprite = ss.getSubImage(1, 2);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
