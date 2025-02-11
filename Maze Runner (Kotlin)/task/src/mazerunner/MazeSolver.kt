package mazerunner

import mazerunner.model.Cell
import mazerunner.model.MazeData
import java.util.*

/**
 * Finds escape paths in mazes using BFS algorithm.
 */
class MazeSolver {
    /**
     * Finds the shortest escape path from left to right in the maze.
     * @param maze The maze to solve
     * @return List of cells representing the escape path, or empty list if no path exists
     */
    fun findEscape(maze: MazeData): List<Cell> {
        // Find entrance (leftmost path cell)
        val entrance = findEntrance(maze) ?: return emptyList()

        // BFS queue and visited set
        val queue: Queue<Cell> = LinkedList()
        val visited = mutableSetOf<Cell>()
        val parent = mutableMapOf<Cell, Cell>()

        queue.add(entrance)
        visited.add(entrance)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            // Check if we reached the exit (rightmost column)
            if (current.col == maze.width - 1) {
                return reconstructPath(current, parent)
            }

            // Check all four adjacent cells
            val neighbors = getNeighbors(current, maze)
            for (next in neighbors) {
                if (next !in visited) {
                    queue.add(next)
                    visited.add(next)
                    parent[next] = current
                }
            }
        }

        return emptyList()
    }

    /**
     * Finds the entrance cell in the leftmost column.
     */
    private fun findEntrance(maze: MazeData): Cell? {
        for (row in 0 until maze.height) {
            if (maze.grid[row][0] == 0) {
                return Cell(row, 0)
            }
        }
        return null
    }

    /**
     * Gets valid neighboring cells (up, down, left, right).
     */
    private fun getNeighbors(cell: Cell, maze: MazeData): List<Cell> {
        val directions = listOf(
            Cell(-1, 0), // up
            Cell(1, 0),  // down
            Cell(0, -1), // left
            Cell(0, 1)   // right
        )

        return directions.mapNotNull { dir ->
            val newRow = cell.row + dir.row
            val newCol = cell.col + dir.col

            if (newRow in 0 until maze.height &&
                newCol in 0 until maze.width &&
                maze.grid[newRow][newCol] == 0) {
                Cell(newRow, newCol)
            } else {
                null
            }
        }
    }

    /**
     * Reconstructs the path from the exit back to the entrance.
     */
    private fun reconstructPath(exit: Cell, parent: Map<Cell, Cell>): List<Cell> {
        val path = mutableListOf<Cell>()
        var current: Cell? = exit

        while (current != null) {
            path.add(0, current)
            current = parent[current]
        }

        return path
    }
}
