package wzh.game.entity.building;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.Grid;
import wzh.game.entity.unit.Archer;
import wzh.game.entity.unit.Horseman;
import wzh.game.entity.unit.Spearman;
import wzh.game.entity.unit.Swordsman;
import wzh.game.input.Cursor;
import wzh.game.input.Menu;
import wzh.game.input.command.BuyCommand;
import wzh.game.input.command.Cancel;
import wzh.game.input.command.Command;
import wzh.game.level.Level;

/**
 * A Castle is a building that when selected by the cursor opens a window for purchasing different units 
 * also provides a defensive bonus(fortification) of 2
 * @author Harrison
 */
public class Castle extends Building {
	
	private Menu menu;
	
	public Castle(int x, int y, Image img, Grid g, int faction) {
		super(x, y, img, g, faction);
		active = true;
	}
	
	public int getFortification(){
		return 2;
	}
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException{
		super.update(gc, game, delta);
		if(menu!=null) {
			menu.update(gc, game, delta);
		}
	}
	public void displayPurchaseMenu(Cursor c, GameContainer gc, StateBasedGame game) {
		ArrayList<Command> commands = new ArrayList<Command>();
		Level level = (Level)game.getCurrentState();
		int money = level.getTreasury(faction);
		if(money>=Spearman.COST)
			commands.add(new BuyCommand(this, c, "Spearman-"+Spearman.COST+"g", faction));
		if(money>=Swordsman.COST)
			commands.add(new BuyCommand(this, c, "Swordsman-"+Swordsman.COST+"g", faction));
		if(money>=Archer.COST)
			commands.add(new BuyCommand(this, c, "Archer-"+Archer.COST+"g", faction));
		if(money>=Horseman.COST)
			commands.add(new BuyCommand(this, c, "Horseman-"+Horseman.COST+"g", faction));
		commands.add(new Cancel(this, c, gc));
		menu = new Menu(c, commands, gc);
		
	}
	public void setFaction(int f){
		super.setFaction(f);
		try {
			SpriteSheet ss = new SpriteSheet("SpriteSheetz.png",16,16);
			if(faction==1) {
				sprite = ss.getSubImage(2, 2);
			}
			else
				sprite = ss.getSubImage(1, 2);
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	public void hideMenus() {
		menu = null;
	}
	public Menu getMenu() {
		return menu;
	}
}

