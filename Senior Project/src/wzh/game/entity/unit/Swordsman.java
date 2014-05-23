package wzh.game.entity.unit;

import org.newdawn.slick.Animation;
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
			if(faction==1){
				colored = ss.getSubImage(2, 0);
				standingAnimation=new Animation(ss,0,6,2,6,true,500,true);
				standingAnimation.setPingPong(true);
			}
			else{
				colored = ss.getSubImage(1, 0);
				standingAnimation=new Animation(ss,0,5,2,5,true,500,true);
				standingAnimation.setPingPong(true);
			}
			sprite = colored;
			gray = ss.getSubImage(0,0);
			
		} catch (SlickException e) {
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
			allyAttModifier = 1.5;
			other.setHp((int)(other.getHp() - this.getAttack()*allyAttModifier*(10-getDefense())));
		}
		else if(other instanceof Spearman){
			allyAttModifier = 1.25;
			enemAttModifier = 0.8;
			super.attack(other);
		}
	}

	public String getName() {
		return "Swordsman";
	}
}
