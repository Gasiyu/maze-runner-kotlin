package mazerunner.model

/**
 * Contains the maze data including dimensions and grid layout.
 * @property height The height of the maze
 * @property width The width of the maze
 * @property grid 2D array representing the maze where 1 is wall and 0 is path
 */
data class MazeData(val height: Int, val width: Int, val grid: Array<IntArray>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MazeData) return false
        return height == other.height &&
                width == other.width &&
                grid.contentDeepEquals(other.grid)
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + grid.contentDeepHashCode()
        return result
    }
}
