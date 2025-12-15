public class Cell {
    public static final int EMPTY = 0;
    public static final int ENERGY = 1;
    public static final int ROCK = 2;

    private int type;

    public Cell(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isEmpty() {
        return type == EMPTY;
    }

    public boolean isEnergyCell() {
        return type == ENERGY;
    }

    public boolean isRock() {
        return type == ROCK;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case EMPTY: return " ";
            case ENERGY: return "E";
            case ROCK: return "R";
            default: return "?";
        }
    }
}