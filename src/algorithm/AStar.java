/*
@author : Zack Boner
@date : 8/27/2020
@references : Wikipedia for the A* search algorithm pseudocode
                https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
 */

package algorithm;

import data.Cell;
import data.Grid;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar {

    Cell start;
    Cell goal;
    Grid grid;

    public AStar(Cell start, Cell goal, Grid grid) {
        this.start = start;
        this.goal = goal;
        this.grid = grid;
    }

    private Deque<Cell> reconstructPath(HashMap<Cell, Cell> cameFrom, Cell current) {
        Deque<Cell> totalPath = new LinkedList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.addFirst(current);
        }
        return totalPath;
    }

    public Deque<Cell> findOptimalPath(int func) {
        // The set of discovered nodes that may need to be (re-)expanded.
        // Initially, only the start node is known.
        // This is usually implemented as a min-heap or priority queue rather than a hash-set.
        PriorityQueue<Cell> openSet = new PriorityQueue<>();
        openSet.add(start);

        // For node n, cameFrom[n] is the node immediately preceding it on the cheapest path from start
        // to n currently known.
        HashMap<Cell, Cell> cameFrom = new HashMap<>();

        // For node n, gScore[n] is the cost of the cheapest path from start to n currently known.
        HashMap<Cell, Double> gScore = new HashMap<>();
        gScore.put(start, 0.0);

        // For node n, fScore[n] := gScore[n] + h(n). fScore[n] represents our current best guess as to
        // how short a path from start to finish can be if it goes through n.
//        HashMap<Cell, Double> fScore = new HashMap<>();
//        fScore.put(start, h(start, func));

        Cell tempGoal = new Cell(goal.getType(), goal.getPosition());

        while (!openSet.isEmpty()) {
            // Set current to be the node in openSet having the lowest fScore value.
            Cell current = openSet.poll();
            if (current.equals(tempGoal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            for (AdjacentTile neighboringTile : grid.getAdjacentTiles(current.getPosition())) {
                double tentativeGScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + neighboringTile.getDistance();
                Cell neighbor = neighboringTile.getCell();
                neighbor = new Cell(neighbor);
                neighbor.setScore(tentativeGScore + h(neighbor, func));
                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    //  fScore.put(neighbor, gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY) + h(neighbor, func));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return new LinkedList<>();
    }

    /*
    heuristic function for A*
    node is the cell to compute the heuristic for
    func is the heuristic function to use
        0 : euclidean distance to target
        1 : manhattan distance to target
        2 : tbd
     */
    public double h(Cell node, int func) {
        switch (func) {
            case 0:
                return euclidean(node);
            case 1:
                return manhattan(node);
            default:
                return 0;
        }
    }

    private double euclidean(Cell node) {
        int x_diff = goal.getPosition().getX() - node.getPosition().getX();
        int y_diff = goal.getPosition().getY() - node.getPosition().getY();
        double dist_squared = Math.pow(x_diff, 2) + Math.pow(y_diff, 2);
        return Math.sqrt(dist_squared);
    }

    private double manhattan(Cell node) {
        int x_diff = goal.getPosition().getX() - node.getPosition().getX();
        int y_diff = goal.getPosition().getY() - node.getPosition().getY();
        return Math.abs(x_diff) + Math.abs(y_diff);
    }
}
