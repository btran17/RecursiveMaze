import java.util.*;
import java.io.*;

public class Maze
{
    private static final char TRIED = '.';
    private static final char PATH = 'o';
    private static final char CHEESE = 'C';
    //for the different sizes of txt files
    private static ArrayList<String> fileSize;
    private int startRow, startCol = -1;
    private int numRows, numCols = 0;
    private int cRow, cCol = -1;
    private char[][] map;

    /**
     * Reads the map file and places it into a 2d array
     * while loading the values into the array
     * It keeps track of the starting position and the goal
     * @param filename name of the file
     * @throws FileNotFoundException exception if file is not found
     */
    public Maze(String filename) throws FileNotFoundException
    {
        fileSize = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNext()) {
                String line = scan.nextLine(); //reading the entire lines
                fileSize.add(line); //add to ArrayList
                if (line.length() > numCols) {
                    numCols = line.length(); //resizing
                }
            }
        } catch(Exception e){
            System.err.println(filename + " has an issue");
        }
        numRows = fileSize.size();
        //initialize the maze
        map = new char[numRows][numCols];
        for(int r = 0; r < numRows; r++){
            String row = fileSize.get(r);
            for(int c = 0; c < numCols; c++){
                if(row.length() >= c){
                    map[r][c] = row.charAt(c);
                }
//                else{
//                    map[r][c] = '*';
//                }
                //finding the index of R to start
                if (map[r][c] == 'R'){
                    startRow = r;
                    startCol = c;
                }
                //finding the index of C
                if (map[r][c] == 'C'){
                    cRow = r;
                    cCol = c;
                }
            }
        }
        System.out.println("Maze loaded");
    }

    /**
     * Returns the row index for the starting posiion
     * @return int index of R
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * Returns the column index for the starting posiion
     * @return int index of R
     */
    public int getStartCol() {
        return startCol;
    }

    /**
     * Keeps the location of the cheese
     * @param row int index value at row
     * @param col int index value at column
     */
    public void cheese(int row, int col){
        map[row][col] = CHEESE;
    }

    /**
     *  Indicates the path that has been tried and
     *  picks up the breadcrumb if present
     * @param row int the index row
     * @param col int the index column
     */
    public void tried(int row, int col){
        map[row][col] = TRIED;
    }

    /**
     * Drops a breadcrumb after the path has been visited
     * @param row int index row
     * @param col int index column
     */
    public void breadcrumb(int row, int col){
        map[row][col] = PATH;

    }

    /**
     * Checks if the next posiiton is valid to move to
     * @param row int index of row
     * @param column int index of column
     * @return true or false whether or not the rat can move
     */
    public boolean validPosition(int row, int column) {
        boolean result = false;

        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < map.length && column >= 0 && column < map[row].length){
            //  check if cell is not blocked and not previously tried
            if(map[row][column] == '.'|| map[row][column] == 'R') {
                result = true;
            }
            if(map[row][column] == 'C'){
                return true;
            }
        }
        return result;
    }

    /**
     * Method returns true when the cheese if found
     * @param row int index for row
     * @param col int index for column
     * @return false if not found and true if found
     */
    public boolean found(int row,int col){
        int x = cRow, y = cCol;
        if (map[x][y] == map[row][col])
            return true;
        return false;
    }

    /**
     * Returns the string representation of the map
     * @return String representation of the map
     */
    public String toString()
    {
        String result = "\n";

        for (int row = 0; row < map.length; row++)
        {
            for (int column = 0; column < map[row].length; column++)
                result += map[row][column] + "";
            result += "\n";
        }

        return result;
    }
}

