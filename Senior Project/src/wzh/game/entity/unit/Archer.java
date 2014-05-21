package wzh.game.entity.unit;

import org.newdawn.slick.Image;

import wzh.game.Grid;

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

}
