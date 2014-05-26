package wzh.game.entity.unit;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;
import wzh.game.Location;

public class Archer extends Unit {
	
	public static final int COST = 50;
	public final double ATTACK_BUFF = 1;

	public Archer(int x, int y, Grid g, int faction) {
		super(x, y, null, g, faction);
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1){
				colored = ss.getSubImage(2, 2);
				standingAnimation=new Animation(ss,0,10,2,10,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,10,8,10,true,300,true);
			}
			else{
				colored = ss.getSubImage(1, 2);
				standingAnimation=new Animation(ss,0,9,2,9,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,9,8,9,true,300,true);
			}
			sprite = colored;
			gray = ss.getSubImage(0, 2);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void attack(Unit other) {
		if(other instanceof Archer) {
			allyAttModifier = 1.5;
			enemAttModifier = 1.5;
			super.attack(other);
		}
		else{
			other.setHp((int)(other.getHp() - getAttack()*allyAttModifier*(double)((10-other.getDefense()))/10));
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
	public String getName() {
		return "Archer";
	}
}
