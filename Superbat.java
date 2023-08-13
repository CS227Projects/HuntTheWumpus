import java.util.Random;
public class Superbat extends NPC {
	
	private Cave[] caves;
	private Random r = new Random();
	
	public Superbat(Cave loc, Cave[] c) {
		super(loc, 0);
		caves = c;
	}
	
	/*
	 * If the player activates the Superbat's collision by entering its cave the player is then carried to a random cave
	 * The cave can be any cave including one already occupied by an NPC
	 */
	public void collision(Player p) {
		Cave loc = caves[r.nextInt(21)];
		p.move(loc);
		System.out.println("A bat whisked you away to another cave!");
	}
	
	/*
	 * The Superbat's warning message if it is within an adjacent cave.
	 */
	public String toString() {
		return "You hear a rustling.";
	}
}
