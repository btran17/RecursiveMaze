/*
# Name: Brittney Tran
# Date: 11/11/2019
# Class: CSC 1120
# Pledge: I have neither given nor received unauthorized aid on this program.
# Description: The program will use a recursive algorithm to have a rat find
                its way through the maze.
# Output: The solution will be printed and the number of steps it took
*/

import java.util.*;
import java.io.*;


public class MazeTest
{

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the name of the file containing the maze: ");
        String filename = scan.nextLine();

        Maze labyrinth = new Maze(filename);
//        MazeEx labyrinth = new MazeEx("maze1.txt");
//        MazeEx labyrinth = new MazeEx("C:\\Users\\LotusCake\\IdeaProjects\\Maze\\src\\maze1.txt");
//        MazeEx labyrinth = new MazeEx("C:\\Users\\Bibi\\Desktop\\ProjectFour\\src\\maze4.txt");

        System.out.println(labyrinth);

        MazeSolver solve = new MazeSolver(labyrinth);

        int x = labyrinth.getStartRow();
        int y = labyrinth.getStartCol();

        if (solve.solveMaze(x, y)){
            System.out.println("The maze was successfully traversed!");
            solve.counter();
            System.out.print("SOLUTION: ");
        }
        else
            System.out.println("There is no possible path.");

        System.out.println(labyrinth);
    }
}
