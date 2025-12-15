import java.util.Random;

public class Robot {
    private int x, y;
    private int energy;
    private boolean active;

    public Robot(int x, int y) {
        this.x = x;
        this.y = y;
        this.energy = 100;
        this.active = true;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getEnergy() { return energy; }
    public boolean isActive() { return active; }

    // TODO: Implement robot movement and energy handling logic.
    public int moveCost = 5;
    public int energyGain = 20;
    Random rand = new Random();
    public void move(Terrain terrain) {
        if (!isActive()) return;
        int size = terrain.getSize();
        Cell[][] grid = terrain.getGrid();
        // (0 = up, 1 = down, 2 = left, 3 = right)
        int[] directions = {0, 1, 2, 3};
        for (int i = 0; i < directions.length; i++) {
            int j = rand.nextInt(directions.length);
            int temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }
        boolean moved = false;
        for (int direction : directions) {
            int newX = x;
            int newY = y;
            if (direction == 0) newX = x - 1;      // UP
            else if (direction == 1) newX = x + 1; // DOWN
            else if (direction == 2) newY = y - 1; // LEFT
            else if (direction == 3) newY = y + 1; // RIGHT
            // Check boundaries
            if (newX < 0 || newX >= size || newY < 0 || newY >= size)
                continue;
            // Check if Cell is a Rock
            if (grid[newX][newY].isRock())
                continue;
            // Valid Move Found
            x = newX;
            y = newY;
            moved = true;
            energy -= moveCost;
            // Recharge if Found Energy Cell
            if (grid[x][y].isEnergyCell()) {
                energy += energyGain;
                grid[x][y].setType(Cell.EMPTY);
            }
            // Become Inactive if Energy Runs Out
            if (energy <= 0) {
                energy = 0;
                active = false;
            }
            break; // Stop After Successful Move
        }
    }
}