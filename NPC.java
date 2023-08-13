
public class NPC {
	private Cave loc;
	
	/*
	 * @param loc
	 * sets the cave that the NPC is in.
	 * @param index
	 * whether the NPC is a 0 or 1 means whether or not it is a Wumpus, since a Wumpus can share locations with the other NPCs.
	 */
	public NPC(Cave loc, int index) {
		this.loc = loc;
		loc.setOccupant(this, index);
	}
	
	/*
	 * Overided by all NPCs with varying responses
	 * Occurs when player moves into an NPC occupied cave
	 */
	public void collision(Player p) {
		return;
	}
	
	/*
	 * Returns the cave the NPC is in
	 */
	public Cave getLoc() {
		return loc;
	}
	
	/*
	 * overide by Wumpus move function
	 */
	public void move() {
		return;
	}
	
	/*
	 * @param c
	 * the cave that we want to change location of the NPC to.
	 * Used by the wumpus move function.
	 */
	public void setLoc(Cave c) {
		loc = c;
	}

}
