package wzh.game.entity.unit;

import org.newdawn.slick.Image;

import wzh.game.Grid;

public class Spearman extends Unit {

	public Spearman(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
	}
	@Override
	public void attack(Unit other) {
		double startAttack = attack;
		double otherStartAttack = other.getAttackMult();
		if(other instanceof Horseman) {
			attack *= 2;
			other.setAttack(other.getAttackMult()*2);
		}
		else if(other instanceof Swordsman) {
			attack /= 2;
			other.setAttack(other.getAttackMult()/2);
		}
		super.attack(other);
		attack = startAttack;
		other.setAttack(otherStartAttack);
	}
}
