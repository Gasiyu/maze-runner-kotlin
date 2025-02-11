package mazerunner

import mazerunner.model.Cell
import mazerunner.model.MazeData
import mazerunner.model.Wall

/**
 * Generates maze layouts using a randomized Prim's algorithm.
 */
class MazeGraph {
    private val random = kotlin.random.Random

    /**
     * Generates a new maze with the specified dimensions.
     * @param height The height of the maze (must be odd)
     * @param width The width of the maze (must be odd)
     * @return MazeData containing the generated maze
     */
    fun generateMaze(height: Int, width: Int): MazeData {
        // Initialize maze with all walls
        val maze = Array(height) { IntArray(width) { 1 } }
        val inMaze = mutableSetOf<Cell>()
        val walls = mutableListOf<Wall>()

        // Create entrance on the left side
        val entranceRow = 1 + 2 * random.nextInt((height-3)/2)
        maze[entranceRow][0] = 0  // Open entrance
        maze[entranceRow][1] = 0  // First cell after entrance

        // Create exit on the right side
        val exitRow = 1 + 2 * random.nextInt((height-3)/2)
        maze[exitRow][width-1] = 0  // Open exit
        maze[exitRow][width-2] = 0  // Last cell before exit

        // Start maze generation from the entrance
        val start = Cell(entranceRow, 1)
        maze[start.row][start.col] = 0
        inMaze.add(start)
        addWalls(start, walls, inMaze, height, width)

        // Continue generating maze using randomized Prim's algorithm
        while (walls.isNotEmpty()) {
            val wallIndex = random.nextInt(walls.size)
            val wall = walls[wallIndex]
            walls.removeAt(wallIndex)

            // If the cell on the other side isn't in the maze yet
            if (wall.to !in inMaze) {
                // Remove the wall and add the new cell to the maze
                maze[wall.wallCell.row][wall.wallCell.col] = 0
                maze[wall.to.row][wall.to.col] = 0
                inMaze.add(wall.to)
                addWalls(wall.to, walls, inMaze, height, width)
            }
        }

        return MazeData(height, width, maze)
    }

    /**
     * Adds potential walls from the current cell to the walls list.
     * @param cell Current cell being processed
     * @param walls List of potential walls that could be removed
     * @param inMaze Set of cells already part of the maze
     * @param height Maze height for boundary checking
     * @param width Maze width for boundary checking
     */
    private fun addWalls(
        cell: Cell,
        walls: MutableList<Wall>,
        inMaze: Set<Cell>,
        height: Int,
        width: Int
    ) {
        for (neighbor in cell.getNeighbors(height, width)) {
            if (neighbor !in inMaze) {
                // Calculate the wall cell position between current cell and neighbor
                val wallCell = Cell(
                    (cell.row + neighbor.row) / 2,
                    (cell.col + neighbor.col) / 2
                )
                walls.add(Wall(cell, neighbor, wallCell))
            }
        }
    }
}
