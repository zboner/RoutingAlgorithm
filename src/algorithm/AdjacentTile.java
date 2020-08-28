package algorithm;

import data.Cell;

public class AdjacentTile {
    private String direction;
    private Cell cell;
    private double distance;

    public AdjacentTile(String direction, Cell cell, double distance) {
        this.direction = direction;
        this.cell = cell;
        this.distance = distance;
    }

    public String getDirection() {
        return this.direction;
    }

    public Cell getCell() {
        return this.cell;
    }

    public double getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        return "AdjacentTile{" +
                "direction='" + direction + '\'' +
                ", cell=" + cell +
                ", distance=" + distance +
                '}';
    }
}
