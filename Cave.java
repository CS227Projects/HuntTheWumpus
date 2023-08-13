public class Cave {
	private int name;
	private Cave[] adjCaves = new Cave[3];
	private NPC[] occupant = new NPC[2];
	
	/*
	 * @param name
	 * Used for the numbering system of caves.
	 */
	public Cave(int name) {
		this.name = name;
	}
	/*
	 * @param adj
	 * adj is added to this caves list of adjacent caves, 
	 * which are those that can be selected while in current cave
	 * 
	 * adj also adds this cave to its adjacent list. 
	 * To prevent recursive loop when doing so isAdjacent makes sure adj isn't already in the list,
	 * 
	 * Only used in map set up.
	 */
	public void addAdjacent(Cave adj) {
		if (isAdjacent(adj.getName())) {
			return;
		}
		adjCaves[2] = adjCaves[1];
		adjCaves[1] = adjCaves[0];
		adjCaves[0] = adj;
		adj.addAdjacent(this);
	}
	
	/*
	 * @param adjName
	 * the name of another cave, which is compared to the adjacent list to determine if its adjacent.
	 * Used in map set up of add adjacent and when determining if a move to another cave is valid
	 */
	public boolean isAdjacent(int adjName) {
		for (Cave i: adjCaves) {
			if(i != null && i.getName() == adjName) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * returns the list of adjacent caves, which is used by player and Wumpus class to select a random adjacent cave
	 */
	public Cave[] getAdjacents() {
		return adjCaves;
	}
	
	/*
	 * returns the number name of the cave
	 */
	public int getName() {
		return name;
	}
	
	/*
	 * returns the array of NPCs in the cave
	 */
	public NPC[] getOccupant() {
		return occupant;
	}
	
	/*
	 * @param c
	 * the NPC we wish to move to this cave
	 * @param index which of the two slots of the cave we want the NPC in.
	 * A cave can have both a superbat/pit and a Wumpus in it, 
	 * so the Wumpus is stored in slot 1 and other NPCs in slot 0
	 */
	public void setOccupant(NPC c, int index) {
		occupant[index] = c;
	}
	
	/*
	 * Prints the current cave and the adjacent caves
	 */
	public String toString() {
		String response = "You are in Cave " + getName() + ". Adjacent caves: ";
		for (Cave c: getAdjacents()) {
			if (c != null) {
				response += c.getName() + " ";
			}
		}
		return response;
	}
}
