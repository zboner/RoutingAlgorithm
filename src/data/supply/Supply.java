package data.supply;

import java.util.Objects;

public class Supply {
    private final int id;
    private final String name;
    private int quantity;

    public Supply(int id, int quantity) {
        this.id = id;
        switch (id) {
            case 0:
                this.name = "Fuel";
                break;
            case 1:
                this.name = "Ammo";
                break;
            case 2:
                this.name = "Batteries";
                break;
            case 3:
                this.name = "Food";
                break;
            default:
                this.name = "";
                System.out.println("ID is invalid.");
        }
        this.quantity = quantity;
    }


    /*
    returns the ID of the Supply
    0 : Fuel
    1 : Ammo
    2 : Batteries
    3 : Food
     */
    public int getID() {
        return this.id;
    }

    /*
    Get the type of the Supply
    Fuel, Ammo, Batteries, or Food
    This is for front-facing uses. In general, prefer to use ID for backend dev.
     */
    public String getSupplyType() {
        return this.name;
    }

    /*
    Get the amount of the given Supply.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /*
    Add supplies
     */
    public void addSupplies(int amount) {
        if (amount < 0) {
            takeSupplies(-1 * amount);
        } else {
            quantity += amount;
        }
    }

    /*
    Take supplies
     */
    public void takeSupplies(int amount) {
        if (amount < 0) {
            addSupplies(-1 * amount);
        } else {
            quantity -= amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply supply = (Supply) o;
        return id == supply.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
