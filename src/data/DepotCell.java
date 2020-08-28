package data;

import data.supply.Depot;
import data.supply.Supply;

import java.util.ArrayList;
import java.util.Objects;

public class DepotCell extends Cell {
    private ArrayList<Supply> supplies;

    public DepotCell(Depot depot) {
        super(1, depot.getPosition());
        supplies = depot.getSupplies();
    }

    public ArrayList<Supply> getSupplies() {
        return supplies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepotCell depotCell = (DepotCell) o;
        return supplies.equals(depotCell.supplies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supplies);
    }

    @Override
    public String toString() {
        return "DepotCell{" +
                "type=" + getType() +
                ", position=" + getPosition() +
                ", supplies=" + supplies +
                '}';
    }
}
