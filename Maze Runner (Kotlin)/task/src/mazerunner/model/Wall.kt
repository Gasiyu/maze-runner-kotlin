package mazerunner.model

/**
 * Represents a wall between two cells in the maze.
 * @property from The starting cell
 * @property to The destination cell
 * @property wallCell The cell representing the wall between [from] and [to]
 */
data class Wall(val from: Cell, val to: Cell, val wallCell: Cell)
