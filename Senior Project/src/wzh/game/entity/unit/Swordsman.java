package wzh.game.entity.unit;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Swordsman extends Unit{ 
	
	public static final int COST = 30;

	public Swordsman(int x, int y, Grid g, int faction){
		super(x, y, null, g, faction);
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 0);
			else
				sprite = ss.getSubImage(1, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void attack(Unit other){
		double startAttack = attack;
		double otherStartAttack = other.getAttackMult();
		if(other instanceof Spearman) {
			attack *= 2;
			other.setAttack(other.getAttackMult()*2);
		}
		else if(other instanceof Horseman) {
			attack /= 2;
			other.setAttack(other.getAttackMult()/2);
		}
		else if(other instanceof Archer){
			attack *= 3;
		}
		super.attack(other);
		attack = startAttack;
		other.setAttack(otherStartAttack);
	}

	public void goGray() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			sprite = ss.getSubImage(0, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void goColor() {
		try {
			SpriteSheet ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1)
				sprite = ss.getSubImage(2, 0);
			else
				sprite = ss.getSubImage(1, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	public String getName() {
		return "Swordsman";
	}
}
