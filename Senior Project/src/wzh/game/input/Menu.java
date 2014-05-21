package wzh.game.input;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import wzh.game.Grid;
import wzh.game.input.command.Command;

public class Menu{
	private static final int TILE_HEIGHT = 32;


	private static final int COMMAND_HEIGHT = 16;
	
	
	private int x,y;
	private Cursor cursor;
	private TrueTypeFont font;
	
	private final String FONT_STYLE = "Euphemia";
	
	private int selected;	
	private ArrayList<Command> commands;
	
	public Menu(Cursor c , ArrayList<Command> commands, GameContainer gc){
		this.commands = commands;
		selected = 0;
		setPos(c, gc);
		cursor = c;
		font = new TrueTypeFont(new Font(FONT_STYLE, Font.BOLD , 12), false);
	}

	private void setPos(Cursor c, GameContainer gc) {
		int cursorX = c.getLoc().getX();
		int cursorY = c.getLoc().getY();
		int cursorSize = c.getSize();
		if(cursorX*cursorSize*2-TILE_HEIGHT*2 >= 0 && cursorY*cursorSize*2-32 >= 0 && gc.getWidth()/2 > cursorX*cursorSize*2){
			x = cursorX*cursorSize*2+TILE_HEIGHT;
			y = cursorY*cursorSize*2-TILE_HEIGHT;
			System.out.println("ugly");
		}
		else if(cursorX*cursorSize*2-TILE_HEIGHT*2 <= 0 && cursorY*cursorSize*2-32 >= 0){
			x = cursorX*cursorSize*2+TILE_HEIGHT;
			y = cursorY*cursorSize*2-TILE_HEIGHT;
		}
		else if(cursorX*cursorSize*2-TILE_HEIGHT*2 >= 0 && cursorY*cursorSize*2-32 <= 0){
			if(cursorX*cursorSize*2 < gc.getWidth()/2){
				x = cursorX*cursorSize*2+TILE_HEIGHT;
				y = cursorY*cursorSize*2+TILE_HEIGHT;
				System.out.println("good");
			}
			else{
				x = cursorX*cursorSize*2-TILE_HEIGHT*2;
				y = cursorY*cursorSize*2+TILE_HEIGHT;
			}
		}
		else if(cursorX*cursorSize*2-TILE_HEIGHT*2 <= 0 && cursorY*cursorSize*2-32 <= 0){
			x = cursorX*cursorSize*2+TILE_HEIGHT;
			y = cursorY*cursorSize*2+TILE_HEIGHT;
		}
		else if(gc.getWidth()/2 <= cursorX*cursorSize*2){
			if(cursorX*cursorSize*2+TILE_HEIGHT*2 < gc.getWidth()){
				x = cursorX*cursorSize*2-TILE_HEIGHT*2;
				y = cursorY*cursorSize*2-TILE_HEIGHT;
	//			System.out.println("good");
			}
			else{
				x = cursorX*cursorSize*2-TILE_HEIGHT*2;
				y = cursorY*cursorSize*2-TILE_HEIGHT;
				System.out.println("bad");
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		//TODO Determine what is selected
		if(gc.getInput().isKeyPressed(Input.KEY_UP) || gc.getInput().isKeyPressed(Input.KEY_W)) {
			selected--;
		}
		else if(gc.getInput().isKeyPressed(Input.KEY_DOWN) || gc.getInput().isKeyPressed(Input.KEY_S)) {
			selected++;
		}
		if(selected<0) selected=commands.size()-1;
		else if (selected>=commands.size()) selected=0;
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			commands.get(selected).select();
		}
	}
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.scale(.5f, .5f);
		g.fillRect(x, y, TILE_HEIGHT*2, commands.size()*COMMAND_HEIGHT);
		int curY = y;
		for(Command c: commands){
			//BE SERIF NOOOOOOOOOOOOOOW... or not
			font.drawString(x, curY, c.getCommand(),Color.black);
			curY+=16;
		}
		Color color = new Color(128, 128, 128, 128);
		g.setColor(color);
		g.fillRect(x, y+selected*COMMAND_HEIGHT,TILE_HEIGHT*2,COMMAND_HEIGHT);
		
		g.scale(2f, 2f);
	}
}