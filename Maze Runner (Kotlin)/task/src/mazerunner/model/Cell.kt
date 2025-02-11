package mazerunner.model

/**
 * Represents a single cell in the maze with row and column coordinates.
 * @property row The row position in the maze grid
 * @property col The column position in the maze grid
 */
data class Cell(val row: Int, val col: Int) {
    /**
     * Gets valid neighboring cells that are two steps away (to allow for walls between cells).
     * @param height The total height of the maze
     * @param width The total width of the maze
     * @return List of valid neighboring cells within maze boundaries
     */
    fun getNeighbors(height: Int, width: Int): List<Cell> {
        return listOf(
            Cell(row - 2, col), // Up
            Cell(row + 2, col), // Down
            Cell(row, col - 2), // Left
            Cell(row, col + 2)  // Right
        ).filter { it.row in 1 until height-1 && it.col in 1 until width-1 }
    }
}
