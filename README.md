# Maze Runner

A Kotlin program that generates and solves mazes using a randomized Prim's algorithm for generation and BFS (Breadth-First Search) for pathfinding.

## Features

- Generate random mazes with customizable dimensions
- Support for both horizontal (left-right) and vertical (top-bottom) maze orientations
- Save and load mazes from files
- Find the shortest escape path through the maze
- Visual representation using Unicode block characters

## Technical Details

The program consists of several key components:

- **MazeGraph**: Generates mazes using randomized Prim's algorithm
- **MazeSolver**: Finds escape paths using BFS algorithm
- **MazeManager**: Handles user interaction and maze operations
- **Model classes**: Cell, Wall, and MazeData for maze representation

## File Format

Mazes are saved in a text-based format:
- Line 1: `<height> <width>` (space-separated integers)
- Following lines: Maze data where:
  - '0' represents a path
  - '1' represents a wall
  - Each line must be exactly [width] characters long
  - There must be exactly [height] lines of maze data

Example file content:
```
5 5
11111
10001
10101
10001
11111
```

## Usage Example

```
=== Menu ===
1. Generate a new maze
2. Load a maze
0. Exit
>2
>maze.txt

=== Menu ===
1. Generate a new maze
2. Load a maze
3. Save the maze
4. Display the maze
5. Find the escape
0. Exit
>4
██████████████████████████████████████████████████  ██████████
██      ██                          ██  ██          ██  ██  ██
██  ██████████████  ██████████████████  ██  ██████████  ██  ██
██  ██                          ██      ██  ██          ██  ██
██  ██████████████████  ██████████  ██████  ██████████  ██  ██
██  ██  ██  ██  ██  ██  ██  ██              ██  ██          ██
██  ██  ██  ██  ██  ██  ██  ██  ██████████████  ██████  ██  ██
██  ██  ██          ██  ██              ██      ██      ██  ██
██  ██  ██████  ██████  ██  ██████████████  ██████████  ██████
██  ██      ██          ██              ██      ██      ██  ██
██  ██  ██████  ██████  ██████████  ██████  ██████  ██████  ██
██  ██  ██  ██  ██  ██  ██                  ██          ██  ██
██  ██  ██  ██████  ██  ██████████  ██████████  ██  ██████  ██
██              ██  ██      ██  ██          ██  ██  ██  ██  ██
██████  ██  ██████  ██████  ██  ██  ██████████  ██████  ██  ██
██      ██  ██  ██  ██  ██      ██  ██      ██  ██          ██
██████  ██████  ██  ██  ██  ██████  ██  ██████  ██████  ██████
██          ██  ██      ██      ██          ██          ██  ██
██  ██████  ██  ██████  ██  ██████████  ██████  ██████████  ██
██  ██      ██          ██  ██          ██      ██      ██  ██
██████████  ██████████  ██  ██  ██████████  ██████  ██████  ██
██                  ██          ██  ██      ██  ██      ██  ██
██████████████████  ██████  ██████  ██████  ██  ██  ██████  ██
██                          ██      ██  ██                  ██
██████████  ██████████████  ██  ██████  ██  ██████████████  ██
██          ██  ██      ██  ██  ██  ██              ██  ██  ██
██████  ██████  ██████  ██  ██  ██  ██  ██████████████  ██████
██      ██      ██                      ██  ██              ██
██████████████  ██████████████  ██████  ██  ██████  ██  ██████
██                                  ██              ██      ██
██████████████████████████  ██████████████████████████████████

=== Menu ===
1. Generate a new maze
2. Load a maze
3. Save the maze
4. Display the maze
5. Find the escape
0. Exit
>5
██████████████████████████████████████████████████//██████████
██      ██                          ██  ██//////////██  ██  ██
██  ██████████████  ██████████████████  ██//██████████  ██  ██
██  ██                          ██      ██//██          ██  ██
██  ██████████████████  ██████████  ██████//██████████  ██  ██
██  ██  ██  ██  ██  ██  ██  ██//////////////██  ██          ██
██  ██  ██  ██  ██  ██  ██  ██//██████████████  ██████  ██  ██
██  ██  ██          ██  ██//////        ██      ██      ██  ██
██  ██  ██████  ██████  ██//██████████████  ██████████  ██████
██  ██      ██          ██//////////    ██      ██      ██  ██
██  ██  ██████  ██████  ██████████//██████  ██████  ██████  ██
██  ██  ██  ██  ██  ██  ██        //        ██          ██  ██
██  ██  ██  ██████  ██  ██████████//██████████  ██  ██████  ██
██              ██  ██      ██  ██//        ██  ██  ██  ██  ██
██████  ██  ██████  ██████  ██  ██//██████████  ██████  ██  ██
██      ██  ██  ██  ██  ██      ██//██      ██  ██          ██
██████  ██████  ██  ██  ██  ██████//██  ██████  ██████  ██████
██          ██  ██      ██      ██//////    ██          ██  ██
██  ██████  ██  ██████  ██  ██████████//██████  ██████████  ██
██  ██      ██          ██  ██//////////██      ██      ██  ██
██████████  ██████████  ██  ██//██████████  ██████  ██████  ██
██                  ██    //////██  ██      ██  ██      ██  ██
██████████████████  ██████//██████  ██████  ██  ██  ██████  ██
██                        //██      ██  ██                  ██
██████████  ██████████████//██  ██████  ██  ██████████████  ██
██          ██  ██      ██//██  ██  ██              ██  ██  ██
██████  ██████  ██████  ██//██  ██  ██  ██████████████  ██████
██      ██      ██        //////        ██  ██              ██
██████████████  ██████████████//██████  ██  ██████  ██  ██████
██                        //////    ██              ██      ██
██████████████████████████//██████████████████████████████████

=== Menu ===
1. Generate a new maze
2. Load a maze
3. Save the maze
4. Display the maze
5. Find the escape
0. Exit
>0
Bye!
```

## Symbols Used

- `██`: Wall
- `  `: Path
- `//`: Escape path (when displaying solution)

## Implementation Details

### Maze Generation
- Uses randomized Prim's algorithm
- Ensures all paths are connected
- Creates random entrance and exit points

### Path Finding
- Uses Breadth-First Search (BFS) algorithm
- Finds the shortest possible path from entrance to exit

## Building and Running

1. Clone the repository
2. Build using Kotlin compiler:
   ```bash
   kotlinc src/*.kt -include-runtime -d maze-runner.jar
   ```
3. Run the program:
   ```bash
   java -jar maze-runner.jar
   ```

## Dependencies

- Kotlin Standard Library
- No additional dependencies required

## License

This project is released under the MIT License.
