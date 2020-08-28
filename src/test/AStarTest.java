package test;

import algorithm.AStar;
import data.Cell;
import data.DepotCell;
import data.Grid;
import data.supply.Depot;
import data.supply.Supply;

import java.util.ArrayList;
import java.util.Deque;

public class AStarTest {

    static void print(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        Grid grid = new Grid();

        ArrayList<Supply> supplies = new ArrayList<>();
        Supply fuel = new Supply(0, 100);
        Supply ammo = new Supply(1, 100);
        Supply batt = new Supply(2, 100);
        Supply food = new Supply(3, 100);
        supplies.add(fuel);
        supplies.add(ammo);
        supplies.add(batt);
        supplies.add(food);

        Depot depot1 = new Depot(9, 9, supplies);
        grid.addDepot(depot1);

        Cell start = grid.getCellAtIndex(0, 0);
        Cell goal = new DepotCell(depot1);

        AStar optimalPathFinder = new AStar(start, goal, grid);
        Deque<Cell> optimalPath = optimalPathFinder.findOptimalPath(0);

        for (Cell cell : optimalPath) {
            int x = cell.getPosition().getX();
            int y = cell.getPosition().getY();
            grid.setCellAtIndex(x, y, 3);
        }

        print(grid);
    }
}
