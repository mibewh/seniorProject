package wzh.game.entity.unit;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Horseman extends Unit {
	
	public static final int COST = 40;

	public Horseman(int x, int y, Grid g, int faction) {
		super(x, y, null, g, faction);
		movePoints = 6;
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 3);
			else
				sprite = ss.getSubImage(1, 3);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void attack(Unit other) {
		double startAttack = attack;
		double otherStartAttack = other.getAttackMult();
		if(other instanceof Swordsman) {
			attack *= 1.5;
			other.setAttack(other.getAttackMult()/2);
		}
		else if(other instanceof Horseman){
			attack *= 1.2;
			other.setAttack(other.getAttackMult()/2);
		}
		else if(other instanceof Archer){
			attack *= 2;
		}
		else if(other instanceof Spearman) {
			attack /= 1.25;
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
