package wzh.game.entity.unit;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Spearman extends Unit {
	
	public static final int COST = 20;

	public Spearman(int x, int y, Grid g, int faction) {
		super(x, y, null, g, faction);
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1){
				colored = ss.getSubImage(2, 1);
				standingAnimation=new Animation(ss,0,8,2,8,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,8,9,8,true,100,true);
				attackAnimation.setLooping(false);
			}
			else{
				colored = ss.getSubImage(1, 1);
				standingAnimation=new Animation(ss,0,7,2,7,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,7,9,7,true,100,true);
				attackAnimation.setLooping(false);
			}
			attackSound=new Sound("sounds/spear_attack.wav");
			moveSound=new Sound("sounds/unit_march.wav");
			selectSound=new Sound("sounds/spear_select.wav");
			sprite = colored;
			gray = ss.getSubImage(0, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void attack(Unit other) {
		if(other instanceof Swordsman) {
			allyAttModifier = 0.5;
			enemAttModifier = 1.25;
			super.attack(other);
		}
		else if(other instanceof Horseman){
			allyAttModifier = 1.25;
			enemAttModifier = 0.55;
			super.attack(other);
		}
		else if(other instanceof Archer){
			attacking=true;
			allyAttModifier = 1.25;
			other.setHp((int)(other.getHp() - getAttack()*allyAttModifier*(double)((10-other.getDefense()))/10));
		}
		else if(other instanceof Spearman){
			allyAttModifier = 1;
			enemAttModifier = 1;
			super.attack(other);
		}
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
