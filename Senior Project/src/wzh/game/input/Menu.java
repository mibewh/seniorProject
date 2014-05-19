package wzh.game.input;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import wzh.game.input.command.Command;

public class Menu{
	private int length;
	private int x,y;
	
	private ArrayList<Command> commands;
	
	public Menu(int x, int y, ArrayList<Command> commands){
		this.commands = commands;
		this.x = x;
		this.y = y;
		for(Command i: commands){
			length += 16;
		}
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		//Determine what is selected
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(x, y, 64, length);
		g.setColor(Color.black);
		TrueTypeFont f = new TrueTypeFont(new Font("", Font.PLAIN , 13), false);
		g.setFont(f);
		for(Command i: commands){
			g.drawString("TEST", x, y);
			y += 16;
		}
	}
}
