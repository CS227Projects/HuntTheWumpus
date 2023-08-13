import java.util.Scanner;
import java.util.Random;

public class Player {
	private Cave loc;
	private boolean active = true;
	private Scanner scan = new Scanner(System.in);
	private Random r = new Random();
	private int arrowCount = 5;
	
	/*
	 * @param c
	 * the cave the player is currently in that loc is set to
	 */
	public Player(Cave c) {
		this.loc = c;
	}
	
	/*
	 * Checks loc's adjacent caves for NPCs, 
	 * and prints the warning messages of said NPCs
	 */
	public void checkOccupants() {
		Cave[] adj = loc.getAdjacents();
		for (int i = 0; i < adj.length; i++) {
			for (NPC n: adj[i].getOccupant()) {
				if (n != null) {
					System.out.println(n);
				}
			}
		}
	}
	
	/*
	 * @param m
	 * map object, which contains all caves and NPCs
	 * @param index
	 * the index of the cave we wish to check 
	 * 
	 * checks the map array of cave of index index for a Wumpus
	 */
	private boolean checkWumpus(int index, Map m) {
		return m.getCave(index).getOccupant()[1] != null;
	}
	
	/*
	 * returns the player state aka "alive or dead"
	 */
	public boolean getActive() {
		return active; 
	}
	
	/*
	 * How many arrows the player has, which is reduced by one each shot and will end game if reaches zero
	 */
	public int getArrowCount() {
		return arrowCount;
	}
	
	/*
	 * returns players current cave location
	 */
	public Cave getLoc() {
		return loc;
	}
	
	/*
	 * chances player active state, which kills player and ends game loop
	 */
	public void killPlayer() {
		active = false;
	}
	
	/*
	 * 
	 * @param c
	 * the cave player wants to move to
	 * 
	 * First checks to see if the cave the player is moving into is occupied by a wumpus or NPC
	 * If it is performs collision method for that NPC
	 * 
	 * Updates Player location to cave c
	 */
	public void move(Cave c) {
		loc = c;
		if (c.getOccupant()[1] != null) {
			c.getOccupant()[1].collision(this);
		}
		if (c.getOccupant()[0] != null) {
			c.getOccupant()[0].collision(this);
		}
	}
	
	/*
	 * @param m
	 * the object containing all caves and NPCs
	 * 
	 * method asks for x# of caves the player wishes to shoot through up to 5, 
	 * after which the player then must provide the numbers of said caves,
	 * arrows go through these caves checking for Wumpus. return true if wumpus is shot, false otherwise
	 * 
	 * If a cave number given is not adjacent to previous cave then a random adjacent cave is selected
	 * 
	 * If the arrow goes through the cave of the player the player is killed.
	 */
	public boolean shootArrow(Map m) {
		if (arrowCount < 1) {
			return false;
		}
		arrowCount --;
		System.out.println("Enter room count:");
		int count = scan.nextInt();
		if (count > 5) {
			count = 5;
		}
		int[] indices = new int[count];
		for (int i = 0; i < count; i++) {
			System.out.println("Enter room number " + (i+1) + ": ");
			indices[i] = scan.nextInt();
		}
		Cave current = this.getLoc();
		for (int i = 0; i < count; i++) {
			if (current.isAdjacent(indices[i])) {
				if (checkWumpus(indices[i]-1, m)) {
					System.out.println("Arrow traveling through cave " + indices[i]);
					return true;
				}
				current = m.getCave(indices[i]-1);
			}
			else {
				indices[i] = current.getAdjacents()[r.nextInt(3)].getName();
				if (checkWumpus(indices[i]-1, m)) {
					System.out.println("Arrow traveling through cave " + indices[i]);
					return true;
				}
				current = m.getCave(indices[i]-1);
			}
			System.out.println("Arrow traveling through cave " + indices[i]);
			if (m.getCave(indices[i]-1).equals(this.getLoc())) {
				System.out.println("You shot yourself ... aim a little better next time.");
				this.killPlayer();
				return false;
			}
		}
		return false;
	}
	
}
