package data;

import java.util.Objects;

public class Cell implements Comparable<Cell> {
    private int type;
    private Position position;
    private double score;

    public Cell(int type, Position position) {
        this.type = type;
        this.position = position;
        this.score = 0;
    }

    public Cell(int type, Position position, double score) {
        this(type, position);
        this.score = score;
    }

    public Cell(Cell cell) {
        this.type = cell.type;
        this.position = cell.position;
        this.score = cell.score;
    }

    public Cell(DepotCell cell) {
        this.type = cell.getType();
        this.position = cell.getPosition();
        this.score = cell.getScore();
    }

    public int getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isOccupiable() {
        return (type == 0 || type == 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass() && getClass() != o.getClass().getSuperclass()) return false;
        Cell cell = (Cell) o;
        return type == cell.type &&
                position.equals(cell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, position);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "type=" + type +
                ", position=" + position +
                '}';
    }

    @Override
    public int compareTo(Cell o) {
        double score_diff = this.score - o.score;
        if (score_diff > 0) {
            return 1;
        } else if (score_diff < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
