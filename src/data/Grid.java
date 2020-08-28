package data;

import algorithm.AdjacentTile;
import data.supply.Depot;
import data.supply.Supply;

import java.util.ArrayList;


/*
Grid for routing
0 represents a passable space
-1 represents an obstacle
1 represents a supply depot

grid position (0, 0) is in the top left, as in a java array
grid is stored in row-major order
 */
public class Grid {
    private Cell[][] grid;

    public Grid() {
        this.grid = new Cell[10][10];
        fillGridWithDefaults();
    }

    public Grid(int xdim, int ydim) {
        this.grid = new Cell[xdim][ydim];
        fillGridWithDefaults();
    }

    public Grid(int xdim, int ydim, Depot depot) {
        this.grid = new Cell[xdim][ydim];
        fillGridWithDefaults();
        addDepot(depot);
    }

    public Grid(Cell[][] grid) {
        this.grid = grid;
    }

    public Grid(Depot depot) {
        this.grid = new Cell[10][10];
        fillGridWithDefaults();
        addDepot(depot);
    }

    public Grid(Cell[][] grid, Depot depot) {
        this.grid = grid;
        addDepot(depot);
    }

    public Cell getCellAtIndex(int i, int j) {
        return grid[i][j];
    }

    public void setCellAtIndex(int i, int j, int type) {
        grid[i][j] = new Cell(type, new Position(i, j));
    }

    private void fillGridWithDefaults() {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Cell(0, new Position(i, j), 0);
            }
        }
    }

    public void addDepot(Depot depot) {
        int x = depot.getPosition().getX();
        int y = depot.getPosition().getY();
        if ((x < 0 || x > grid[0].length) || (y < 0 || y > grid.length)) {
            System.out.println("Invalid depot location.");
            return;
        }
        Cell depotCell = new DepotCell(depot);
        grid[x][y] = depotCell;
    }

    public ArrayList<Depot> findAllDepots() {
        ArrayList<Depot> listOfDepots = new ArrayList<>();
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getType() == 1) {
                    Position position = grid[i][j].getPosition();
                    ArrayList<Supply> supplies = ((DepotCell) grid[i][j]).getSupplies();
                    Depot depot = new Depot(position, supplies);
                    listOfDepots.add(depot);
                }
            }
        }
        return listOfDepots;
    }

    public boolean gridLocationIsOccupiable(Position position) {
        int x = position.getX();
        int y = position.getY();
        if ((x < 0 || x >= grid[0].length) || (y < 0 || y >= grid.length)) {
            return false;
        }
        Cell cell = grid[x][y];
        return cell.isOccupiable();
    }

    public boolean gridLocationIsOccupiable(int x, int y) {
        return gridLocationIsOccupiable(new Position(x, y));
    }

    /*
    helper for getAdjacentTiles(int x, int y)
     */
    public ArrayList<AdjacentTile> getAdjacentTiles(Position position) {
        int x = position.getX();
        int y = position.getY();
        return getAdjacentTiles(x, y);
    }

    /*
    returns a hashmap key value pair of
    only returns the adjacent tiles that are able to be moved to
    Iterable will return N first, followed by each direction in clockwise order
     */
    public ArrayList<AdjacentTile> getAdjacentTiles(int x, int y) {
        ArrayList<AdjacentTile> listOfAdjacentTiles = new ArrayList<>();

        //N
        if (gridLocationIsOccupiable(x, y - 1)) {
            listOfAdjacentTiles.add(new AdjacentTile("N", grid[x][y - 1], 1.0));
        }

        //NE
        if (gridLocationIsOccupiable(x + 1, y - 1)) {
            listOfAdjacentTiles.add(new AdjacentTile("NE", grid[x + 1][y - 1], Math.sqrt(2)));
        }

        //E
        if (gridLocationIsOccupiable(x + 1, y)) {
            listOfAdjacentTiles.add(new AdjacentTile("E", grid[x + 1][y], 1.0));
        }

        //SE
        if (gridLocationIsOccupiable(x + 1, y + 1)) {
            listOfAdjacentTiles.add(new AdjacentTile("SE", grid[x + 1][y + 1], Math.sqrt(2)));
        }

        //S
        if (gridLocationIsOccupiable(x, y + 1)) {
            listOfAdjacentTiles.add(new AdjacentTile("S", grid[x][y + 1], 1.0));
        }

        //SW
        if (gridLocationIsOccupiable(x - 1, y + 1)) {
            listOfAdjacentTiles.add(new AdjacentTile("SW", grid[x - 1][y + 1], Math.sqrt(2)));
        }

        //W
        if (gridLocationIsOccupiable(x - 1, y)) {
            listOfAdjacentTiles.add(new AdjacentTile("W", grid[x - 1][y], 1.0));
        }

        //NW
        if (gridLocationIsOccupiable(x - 1, y - 1)) {
            listOfAdjacentTiles.add(new AdjacentTile("NW", grid[x - 1][y - 1], Math.sqrt(2)));
        }

        return listOfAdjacentTiles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Cell cell = grid[i][j];
                builder.append(cell.getType()).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
