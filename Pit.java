
public class Pit extends NPC{
	
	public Pit(Cave loc) {
		super(loc, 0);
	}
	
	/*
	 * If player enters the pit's cave - activate collision - kills player
	 */
	public void collision(Player p) {
		p.killPlayer();
		System.out.println("You fell down a deep pit trap... should have watched your step.");
	}
	
	/*
	 * Pit's warning message if it is adjacent to player.
	 */
	public String toString() {
		return "You feel a cold wind blowing from a nearby cavern.";
	}
}
