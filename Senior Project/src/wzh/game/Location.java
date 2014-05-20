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
	public int getTileDistance(Location loc) {
		int xDif = loc.getX()-x;
		int yDif = loc.getY()-y;
		return Math.abs(xDif) + Math.abs(yDif);
	}
	public Location getLocationInDir(int dir) {
		if(dir==Location.LEFT) return new Location(x-1,y);
		else if(dir==Location.RIGHT) return new Location(x+1,y);
		else if(dir==Location.UP) return new Location(x,y-1);
		else return new Location(x,y+1);
	}
	public String toString() {
		return "("+x+","+y+")";
	}
	public boolean equals(Location other) {
		if(x==other.getX() && y==other.getY()) return true;
		else return false;
	}
	public boolean isIn(ArrayList<Location> arr) {
		for(Location other:arr) {
			if(other.equals(this)) return true;
		}
		return false;
	}
	
	public static ArrayList<Location> removeDuplicates(ArrayList<Location> locs) {
		for(int i=0;i<locs.size()-1;i++) {
			for(int r=locs.size()-1;r>i;r--) {
				if(locs.get(r).equals(locs.get(i))) 
					locs.remove(r);
			}
		}
		return locs;
	}
}
