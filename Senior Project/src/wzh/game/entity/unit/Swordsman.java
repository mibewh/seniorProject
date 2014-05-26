package wzh.game.entity.unit;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import wzh.game.Grid;

public class Swordsman extends Unit{ 
	
	public static final int COST = 30;

	public Swordsman(int x, int y, Grid g, int faction){
		super(x, y, null, g, faction);
		SpriteSheet ss;
		try {
			ss = new SpriteSheet("Unitz.png",16,16);
			if(faction==1){
				colored = ss.getSubImage(2, 0);
				standingAnimation=new Animation(ss,0,6,2,6,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,6,11,6,true,100,true);
				attackAnimation.setLooping(false);
			}
			else{
				colored = ss.getSubImage(1, 0);
				standingAnimation=new Animation(ss,0,5,2,5,true,500,true);
				standingAnimation.setPingPong(true);
				attackAnimation=new Animation(ss,5,5,11,5,true,100,true);
				attackAnimation.setLooping(false);
			}
			attackSound=new Sound("sounds/sword_attack.wav");
			moveSound=new Sound("sounds/unit_march.wav");
			selectSound=new Sound("sounds/sword_select.wav");
			sprite = colored;
			gray = ss.getSubImage(0,0);
			
		} catch (SlickException e){
			e.printStackTrace();
		}		
	}
	
	@Override 
	public void attack(Unit other){
		if(other instanceof Swordsman) {
			allyAttModifier = 1.1;
			enemAttModifier = 0.9;
			super.attack(other);
		}
		else if(other instanceof Horseman){
			allyAttModifier = 1;
			enemAttModifier = 1.1;
			super.attack(other);
		}
		else if(other instanceof Archer){
			attacking=true;
			allyAttModifier = 1.5;
			other.setHp((int)(other.getHp() - getAttack()*allyAttModifier*(double)((10-other.getDefense()))/10));
		}
		else if(other instanceof Spearman){
			allyAttModifier = 1.25;
			enemAttModifier = 0.4;
			super.attack(other);
		}
	}

	public String getName() {
		return "Swordsman";
	}
}
