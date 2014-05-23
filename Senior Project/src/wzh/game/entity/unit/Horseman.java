package wzh.game.entity.unit;

import org.newdawn.slick.Animation;
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
			if(faction==1){
				colored = ss.getSubImage(2, 3);
				standingAnimation=new Animation(ss,0,12,2,12,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,12,10,12,true,300,true);
			}
			else{
				colored = ss.getSubImage(1, 3);
				standingAnimation=new Animation(ss,0,11,2,11,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,12,10,12,true,300,true);
			}
			sprite = colored;
			gray = ss.getSubImage(0, 3);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void attack(Unit other) {
		if(other instanceof Swordsman) {
			allyAttModifier = 1.5;
			enemAttModifier = 0.75;
			super.attack(other);
		}
		else if(other instanceof Horseman){
			allyAttModifier = 1;
			enemAttModifier = 0.8;
			super.attack(other);
		}
		else if(other instanceof Archer){
			allyAttModifier = 2;
			other.setHp((int)(other.getHp() - this.getAttack()*allyAttModifier*(10-getDefense())));
		}
		else if(other instanceof Spearman){
			allyAttModifier = .80;
			enemAttModifier = 1.25;
			super.attack(other);
		}
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
	public String getName() {
		return "Horseman";
	}

}
