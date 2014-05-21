package wzh.game.entity.unit;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Horseman extends Unit {

	public Horseman(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
		movePoints = 6;
	}
	@Override
	public void attack(Unit other) {
		double startAttack = attack;
		double otherStartAttack = other.getAttackMult();
		if(other instanceof Swordsman) {
			attack *= 2;
			other.setAttack(other.getAttackMult()*2);
		}
		else if(other instanceof Spearman) {
			attack /= 2;
			other.setAttack(other.getAttackMult()/2);
		}
		super.attack(other);
		attack = startAttack;
		other.setAttack(otherStartAttack);
	}
	public void goGray() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			sprite = ss.getSubImage(0, 3);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void goColor() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 3);
			else
				sprite = ss.getSubImage(1, 3);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
