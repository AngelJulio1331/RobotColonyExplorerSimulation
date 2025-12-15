import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Terrain {
    private final int SIZE = 10;
    private Cell[][] grid;
    private final Robot[] robots;

    public Terrain(int numRobots) {
        grid = new Cell[SIZE][SIZE];
        generateTerrain();
        robots = new Robot[numRobots];
        placeRobots();
    }

    private void generateTerrain() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int type = rand.nextInt(10);
                if (type < 2)
                    grid[i][j] = new Cell(Cell.ROCK);
                else if (type < 4)
                    grid[i][j] = new Cell(Cell.ENERGY);
                else
                    grid[i][j] = new Cell(Cell.EMPTY);
            }
        }
    }

    private void placeRobots() {
        Random rand = new Random();
        for (int i = 0; i < robots.length; i++) {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!grid[x][y].isEmpty());
            robots[i] = new Robot(x, y);
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Robot[] getRobots() {
        return robots;
    }

    public int getSize() {
        return SIZE;
    }

    // TODO: Implement update logic for all robots.
    public void update() {
        // Loop through Robots and Update their Movement
        for (Robot robot : robots) {
            if (robot != null && robot.isActive()) {
                robot.move(this);
            }
        }
    }

    // TODO: Implement file handling logic for saving and loading terrain or robot data.
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(SIZE);
            // Writing Terrain Grid
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    writer.print(grid[i][j].getType() + " ");
                }
                writer.println();
            }
            // Writing Robot Data
            for (Robot robot : robots) {
                if (robot != null) {
                    writer.println("ROBOT " + robot.getX() + " " + robot.getY() + " " +
                            robot.getEnergy() + " " + robot.isActive());
                }
            }
            System.out.println("Terrain Saved to " + filename);
            } catch (IOException e) {
            System.out.println("Error Saving Terrain: " + e.getMessage());
        }
    }
    public void loadFromFile(String filename) {
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            int size = sc.nextInt(); // Size of Grid
            // Reading Terrain
            grid = new Cell[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int type = sc.nextInt();
                    grid[i][j] = new Cell(type);
                }
            }
            // Reading Robot Data
            for (int i = 0; i < robots.length; i++) {
                if (sc.hasNext("ROBOT")) {
                    sc.next(); //Skipping the word "ROBOT"
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int energy = sc.nextInt();
                    boolean active = sc.nextBoolean();
                    robots[i] = new Robot(x, y);
                    robots[i].setEnergy(energy);
                    robots[i].setActive(active);
                }
            }
            System.out.println("Terrain Loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error Loading Terrain: " + e.getMessage());
        }
    }
}