import java.util.Arrays;
import java.util.Random;
public class Map {
	private Cave[] c = new Cave[20];
	private NPC[] npcs = new NPC[5];
	private Player p;
	private Random r = new Random();
	
	/*
	 * Set up of map. Creates 20 caves, all adjacent to 3 other caves. 
	 * Creates three rings of 5, 10, and 5 caves. Each cave in both of the 5 rings connects to one cave from the 10 ring. 
	 * All caves in each ring connect to two from their own ring.
	 * Randomly assigns NPCs to caves, making sure a cave isn't already occupied.
	 */
	public Map() {
		for (int i = 0; i < c.length; i++) {
			c[i] = new Cave(i+1);
		}
		//Separate into respective rings to do above mapping
		Cave[] outerCaves = Arrays.copyOfRange(c, 0, 5);
		Cave[] middleCaves = Arrays.copyOfRange(c, 5, 15);
		Cave[] innerCaves = Arrays.copyOfRange(c, 15, 20);
		
		for (int i = 0; i < outerCaves.length; i++) {
			outerCaves[i].addAdjacent(outerCaves[(i+1)%5]);
			innerCaves[i].addAdjacent(innerCaves[(i+1)%5]);
		}
		for (int i = 0; i < middleCaves.length; i++) {
			middleCaves[i].addAdjacent(middleCaves[(i+1)%10]);
		}
		for (int i = 0; i < middleCaves.length; i++) {
			if (i%2 == 0) {
				outerCaves[i/2].addAdjacent(middleCaves[i]);
			}
			
			else {
				innerCaves[i/2].addAdjacent(middleCaves[i]);
			}
		}
		int count = 0;
		while (count < 6) {
			int index = r.nextInt(20);
			if (c[index].getOccupant()[0] == null) {
				if (count < 2) {
					npcs[count] = new Superbat(c[index], c);
				}
				
				else if (count < 4) {
					npcs[count] = new Pit(c[index]);
				}
				
				else if (count < 5){
					npcs[count] = new Wumpus(c[index]);
				}
				
				else {
					p = new Player(c[index]);
				}
				count++;
			}
		}
		
	}
	
	/*
	 * Given a number, returns the cave of that index in the map array. Cave x would be index (x-1)
	 */
	public Cave getCave(int index) {
		return c[index%20];
	}
	
	/*
	 * returns the player object. 
	 */
	public Player getPlayer() {
		return p;
	}
	
	/*
	 * returns the wumpus object so the gameloop can move it when needed
	 */
	public NPC getWumpus() {
		return npcs[4];
	}
}
