Introduction
The Robot Colony Explorer Simulation is a Java-based program that visually demonstrates how multiple robots explore a grid world. Each robot moves randomly, avoids rocks, collects energy, and becomes inactive once its energy runs out. The purpose of this simulation is to apply object-oriented programming concepts and GUI handling using Java Swing.

> Program Design
The program is organized into four main classes, each responsible for a specific task:
- Cell Class:
Represents a single block on the terrain grid. A cell can either be empty, contain energy, or be a rock.
It provides simple methods to identify and change cell types.
- Robot Class:
Each robot has an (x, y) position, an energy level, and an active/inactive status. The move() method randomly chooses a direction (up, down, left, or right) and moves if the space is valid.
Robots lose energy when they move, recharge when they land on an energy cell, and stop moving when energy reaches zero.
- Terrain Class:
Handles the creation of the 10×10 grid, random placement of energy cells, rocks, and robots. It also includes file handling methods to save and load terrain and robot data.
- ExplorerApp Class:
This is the main GUI class. It draws the grid, shows robots, and includes buttons like Start, Pause, Save, Load, and Restart.
The simulation updates automatically using a Timer that triggers every 0.5 seconds.

> How the Simulation Works?
When the program runs:
1.	A random grid (terrain) is generated with rocks, energy cells, and empty spaces.
2.	Three robots are placed randomly on empty cells.
3.	Clicking Start begins movement. Robots wander randomly and consume energy each time they move.
4.	Landing on an energy cell recharges the robot.
5.	Robots turn dark gray when inactive due to low energy.
6.	Pause, Save, Load, and Restart buttons control the simulation flow.

> Design Choices
- Kept logic simple and easy to follow by using arrays instead of advanced data structures.
- Used color-coding for clear visual feedback (green for energy, gray for rocks, blue/dark gray for robot states).
- Chose a Timer for smooth and regular updates instead of using manual loops.
- Used plain text files for saving and loading terrain data for simplicity and readability.

The simulation effectively demonstrates how multiple objects (robots) can interact within a dynamic environment using simple logic.
It shows the practical use of object-oriented principles such as encapsulation and modularity, as each component manages its own behavior.
Through the combination of random movement, energy management, and visual updates, the simulation feels lively and unpredictable.
Implementing file handling added persistence, allowing progress to be saved and reloaded, which enhances the learning value of the program.
Overall, the simulation serves as a great example of combining logic, GUI, and file handling in a cohesive way while keeping the code easy to understand and extend in future improvements.
