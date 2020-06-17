public class MazeSolver {
    private Maze maze;
    private int count = 0;
    private int bread = 0;

    /**
     * Constructor for the MazeSolve class
     * @param maze
     */
    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    /**
     * Uses recursion to solve the maze
     * Checks for a valid position and moves and terminates
     * when the base case (the cheesse is found) is true
     * @param row int index for row
     * @param col int index for column
     * @return true or false if the maze can be solved
     */
    public boolean solveMaze(int row, int col) {
        boolean solved = false;
        if (maze.validPosition(row, col)) {
            count++;
            System.out.println("The rat is at position " + row + ", " + col);
            maze.breadcrumb(row, col);

            //base case
            if (maze.found(row, col)) {
                bread--;
                solved = true;
            } else {

                //check down
                if (!solved) {
                    solved = solveMaze(row + 1, col);
                }

                //check above
                if (!solved) {
                    solved = solveMaze(row - 1, col);
                }

                //check left
                if (!solved) {
                    solved = solveMaze(row, col - 1);
                }

                //check right
                if (!solved) {
                    solved = solveMaze(row, col + 1);
                }
                maze.tried(row,col);
            }

            if (solved) { //final path
                maze.breadcrumb(row, col);

                bread++;
                if (maze.found(row, col))
                    maze.cheese(row, col);
            }
        }
            //nowhere to go
            return solved;
    }

    /**
     * Print statements for how many times solveMaze is called
     * and how many breadcrumbs are in the maze
     */
    public void counter() {
            System.out.println("solveMaze was called " + count + " times.");
            System.out.println("There are " + bread + " breakcrumbs.");
    }
}
