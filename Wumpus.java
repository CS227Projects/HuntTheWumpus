import java.util.Random;
public class Wumpus extends NPC {
	
	private Random r = new Random();
	public Wumpus(Cave loc) {
		super(loc, 1);
	}
	
	/*
	 * @param p
	 * the player object, if it is found in the same location as Wumpus collision method occurs
	 * Player is "killed", where active state is set to false and game ends.
	 */
	public void collision(Player p) {
		p.killPlayer();
		System.out.println("Player found the Wumpus... rest in peace.");
	}
	
	/*
	 * Randomly moves the Wumpus to one of 3 adjacent caves, or has a 25% of staying put
	 * Wumpus performs this method everytime an arrow shot from player misses.
	 * 
	 * Sets its new cave as occupied and previous back to null
	 */
	public void move() {
		int loc = r.nextInt(4);
		if (loc == 3) {
			return;
		}
		Cave c = super.getLoc();
		Cave[] caves = c.getAdjacents();
		c.setOccupant(null, 1);
		super.setLoc(caves[loc]);
		this.getLoc().setOccupant(this, 1);
	}
	
	/*
	 * Used by the player to alert to a Wumpus in adjacent caves.
	 */
	public String toString() {
		return "You smell something terrible nearby.";
	}
	
}
