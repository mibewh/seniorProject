package wzh.game;

import java.util.ArrayList;

public class Location {
	
	public static final int UP = 0;
	public static final int LEFT = 90;
	public static final int DOWN = 180;
	public static final int RIGHT = 270;
	
	private int x,y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public ArrayList<Location> getAdjacentLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(x+1,y));
		locs.add(new Location(x-1,y));
		locs.add(new Location(x,y+1));
		locs.add(new Location(x,y-1));
		return locs;
	}
	public int getDirectionToward(Location loc) {
		int xDif = loc.getX()-x;
		int yDif = loc.getY()-y;
		if(xDif<0 && Math.abs(xDif)>Math.abs(yDif)) return Location.LEFT;
		else if(xDif>0 && Math.abs(xDif)>Math.abs(yDif)) return Location.RIGHT;
		else if(yDif<0 && Math.abs(yDif)>Math.abs(xDif)) return Location.UP;
		else return Location.DOWN;
	}
}
