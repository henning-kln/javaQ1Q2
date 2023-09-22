package _test.backtracking;

public class MazeSolver {
	private Maze maze;
	
	public MazeSolver() {
		maze = new Maze(MazeWalls.field_20x20);
	    maze.playerX = 1; maze.playerY = 1;
	    maze.goalX = 15; maze.goalY = 16;
		maze.waitingTimeMilliSeconds = 10;

	}

	private boolean findGoal() {
		boolean erg = false;
		if (maze.isPlayerOnGoal()) {
			return true;
		}
		for (int i=0;4>i;i++) {
			if(maze.move(i)) {
				if (!maze.isPlayerOnVisited()) {
					erg = findGoal();
				}
				if(erg) {
					return erg;
				}
				maze.moveBack(i);
			}
		}
		
		return erg;
	}
	
	
	public static void main(String[] args) {
		MazeSolver m = new MazeSolver();
		boolean success = m.findGoal();
		if(success) {
			System.out.println("Am Ziel angekommen :)");
		}
		else {
			System.out.println("Nicht angekommen :(");
		}
	}

}
