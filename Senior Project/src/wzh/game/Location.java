package wzh.game;

import java.util.ArrayList;

public class Location {
	
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
}
