import java.util.Scanner;
public class test {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map caves = new Map();
		boolean inGame = true;
		Player p = caves.getPlayer();
		while (inGame && p.getActive()) {
			p.checkOccupants();
			System.out.println(caves.getPlayer().getLoc());
			System.out.println("You have " + p.getArrowCount() + " arrows. Would you like to move (m) or shoot (s)?");
			if(scan.nextLine().equals("m")) {
				boolean valid = false;
				int num = -1;
				while(!valid) {
					System.out.println("Select a cave number to move to:");
					num = scan.nextInt();
					if (p.getLoc().isAdjacent(num)) {
						valid = true;
					}
					if (!valid) {
						System.out.println("Invalid Cave");
						System.out.println(p.getLoc());
					}
				}
				p.move(caves.getCave(num-1));
				scan.nextLine();
			}
			else {
				boolean shoot = p.shootArrow(caves);
				if (shoot) {
					System.out.println("You shot the Wumpus! You win!");
					inGame = false;
				}
				else {
					System.out.println("Miss!");
					caves.getWumpus().move();
				}
			}
			if (p.getArrowCount() <= 0) {
				p.killPlayer();
				System.out.println("You ran out of arrows ... be a little more careful next time, okay?");
			}
		
		}
		scan.close();
	}

}
