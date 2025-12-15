import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExplorerApp extends JPanel {
    private Terrain terrain;
    private final Timer timer;
    public ExplorerApp() {
        terrain = new Terrain(3);
        setPreferredSize(new Dimension(500, 500));
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step();
            }
        });
    }
    public void step() {
        terrain.update();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = terrain.getSize();
        int cellSize = getWidth() / size;
        Cell[][] grid = terrain.getGrid();
        // Draw Terrain Cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid[i][j];
                if (cell.isRock()) {
                    g.setColor(Color.GRAY);
                } else if (cell.isEnergyCell()) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
        // Draw Robots
        for (Robot robot : terrain.getRobots()) {
            if (robot != null) {
                g.setColor(robot.isActive() ? Color.BLUE : Color.DARK_GRAY);
                g.fillOval(robot.getY() * cellSize + 5, robot.getX() * cellSize + 5, cellSize - 10, cellSize - 10);
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Robot Explorer Simulation");
        ExplorerApp app = new ExplorerApp();
        // Control Buttons
        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton restartButton = new JButton("Restart");
        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(saveButton);
        controlPanel.add(loadButton);
        controlPanel.add(restartButton);
        // Frame Layout
        frame.setLayout(new BorderLayout());
        frame.add(app, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Button Actions
        startButton.addActionListener(e -> app.timer.start());
        pauseButton.addActionListener(e -> app.timer.stop());
        saveButton.addActionListener(e -> app.terrain.saveToFile("terrain_save.txt"));
        loadButton.addActionListener(e -> {
            app.terrain.loadFromFile("terrain_save.txt");
            app.repaint();
        });
        restartButton.addActionListener(e -> {
            app.timer.stop();
            app.terrain = new Terrain(3);
            app.repaint();
        });
    }
}