package wzh.game.input;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import wzh.game.input.command.Command;

public class Menu{
	private int x,y;
	private TrueTypeFont font;
	
	private final String FONT_STYLE = "Minecraft";
	
	private ArrayList<Command> commands;
	
	public Menu(int x, int y, ArrayList<Command> commands){
		this.commands = commands;
		this.x = x;
		this.y = y;
		InputStream is = ResourceLoader.getResourceAsStream("Fonts/Minecraft.ttf");
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, is);
			font = new TrueTypeFont(f, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		font = new TrueTypeFont(new Font(FONT_STYLE, Font.PLAIN , 13), true);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		//TODO Determine what is selected
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(x, y, 64, commands.size()*16);
		int curY = y;
		for(Command c: commands){
			//BE SERIF NOOOOOOOOOOOOOOW... or not
			font.drawString(x+10, curY, c.getCommand(),Color.black);
			curY+=16;
		}
	}
}
