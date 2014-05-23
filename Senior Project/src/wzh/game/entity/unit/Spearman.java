package wzh.game.entity.unit;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Spearman extends Unit {
	
	public static final int COST = 20;

	public Spearman(int x, int y, Grid g, int faction) {
		super(x, y, null, g, faction);
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				colored = ss.getSubImage(2, 1);
			else
				colored = ss.getSubImage(1, 1);
			sprite = colored;
			gray = ss.getSubImage(0, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
		else if(other instanceof Archer){
			attack *= 1.25;
		}
		super.attack(other);
		attack = startAttack;
		other.setAttack(otherStartAttack);
	}
	public void goGray() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			sprite = ss.getSubImage(0, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void goColor() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 1);
			else
				sprite = ss.getSubImage(1, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	public String getName() {
		return "Spearman";
	}
}
