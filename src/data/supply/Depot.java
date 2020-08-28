package data.supply;

import data.Position;

import java.util.ArrayList;

public class Depot {
    private final Position position;
    // list of supplies in depot
    private ArrayList<Supply> supplies;

    public Depot() {
        this.position = new Position(0, 0);
        this.supplies = new ArrayList<>();
    }

    public Depot(int x, int y) {
        this.position = new Position(x, y);
        this.supplies = new ArrayList<>();
    }

    public Depot(int x, int y, ArrayList<Supply> supplies) {
        this.position = new Position(x, y);
        this.supplies = supplies;
    }

    public Depot(Position position, ArrayList<Supply> supplies) {
        this.position = position;
        this.supplies = supplies;
    }

    private static boolean validateID(int id) {
        return (id >= 0 && id < 4);
    }

    public Position getPosition() {
        return this.position;
    }

    public ArrayList<Supply> getSupplies() {
        return this.supplies;
    }

    public boolean addSupply(Supply supply) {
        if (!validateID(supply.getID())) {
            System.out.println("Invalid ID.");
            return false;
        }

        if (!supplies.contains(supply)) {
            supplies.add(supply);
            return true;
        }

        for (Supply sup : supplies) {
            if (supply.equals(sup)) {
                supply.addSupplies(sup.getQuantity());
                return true;
            }
        }

        return false;
    }

    public boolean takeSupply(Supply supply) {
        if (!validateID(supply.getID())) {
            System.out.println("Invalid ID.");
            return false;
        }

        if (!supplies.contains(supply)) {
            System.out.println("Depot does not contain that supply!");
            return false;
        }

        for (Supply sup : supplies) {
            if (supply.equals(sup)) {
                supply.takeSupplies(sup.getQuantity());
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "position=" + position +
                ", supplies=" + supplies +
                '}';
    }
}
