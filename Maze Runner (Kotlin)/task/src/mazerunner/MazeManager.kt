package mazerunner

import mazerunner.model.Cell
import mazerunner.model.MazeData
import java.io.*

/**
 * Manages maze operations including generation, saving, loading, and display.
 *
 * File Format Specification:
 * - Line 1: "<height> <width>" (space-separated integers)
 * - Following lines: Maze data where:
 *   - '0' represents a path
 *   - '1' represents a wall
 *   - Each line must be exactly [width] characters long
 *   - There must be exactly [height] lines of maze data
 *
 * Example file content:
 * 5 5
 * 11111
 * 10001
 * 10101
 * 10001
 * 11111
 */
class MazeManager {
    private var currentMaze: MazeData? = null
    private val mazeGenerator = MazeGraph()
    private val mazeSolver = MazeSolver()

    /**
     * Runs the maze manager interface loop.
     */
    fun run() {
        while (true) {
            displayMenu()
            when (readOption()) {
                1 -> generateNewMaze()
                2 -> loadMaze()
                3 -> if (currentMaze != null) saveMaze() else println("No maze to display!")
                4 -> if (currentMaze != null) displayMaze() else println("No maze to display!")
                5 -> if (currentMaze != null) findEscape() else println("No maze to solve!")
                0 -> {
                    println("Bye!")
                    break
                }
                else -> println("Incorrect option. Please try again")
            }
        }
    }

    /**
     * Displays the main menu options.
     * Shows save and display options only if a maze exists.
     */
    private fun displayMenu() {
        println("\n=== Menu ===")
        println("1. Generate a new maze")
        println("2. Load a maze")
        if (currentMaze != null) {
            println("3. Save the maze")
            println("4. Display the maze")
            println("5. Find the escape")
        }
        println("0. Exit")
    }

    /**
     * Reads and validates user menu choice.
     * @return The selected option number or -1 if invalid
     */
    private fun readOption(): Int {
        return try {
            readlnOrNull()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            -1
        }
    }

    /**
     * Generates a new maze based on user input size.
     */
    private fun generateNewMaze() {
        println("Enter the size of a new maze")
        try {
            val size = readlnOrNull()?.toInt() ?: return
            if (size < 3) {
                println("Size must be at least 3")
                return
            }
            currentMaze = mazeGenerator.generateMaze(size, size)
            displayMaze()
        } catch (e: NumberFormatException) {
            println("Invalid size. Please enter a number")
        }
    }

    /**
     * Saves the current maze to a file in the specified format.
     * Creates parent directories if they don't exist.
     */
    private fun saveMaze() {
        println("Enter file name:")
        val filename = readlnOrNull() ?: return
        val file = File(filename)

        try {
            // Create directories if they don't exist
            file.parentFile?.mkdirs()

            file.bufferedWriter().use { writer ->
                currentMaze?.let { maze ->
                    // Write dimensions on first line
                    writer.write("${maze.height} ${maze.width}\n")

                    // Write maze data, one row per line
                    for (row in maze.grid) {
                        writer.write(row.joinToString("") + "\n")
                    }
                }
            }
            println("Maze has been saved to $filename")
        } catch (e: SecurityException) {
            println("Cannot save the maze. Access denied to file $filename")
        } catch (e: IOException) {
            println("Cannot save the maze to file $filename")
        }
    }

    /**
     * Loads a maze from a file.
     * Performs extensive validation of file format and content.
     */
    private fun loadMaze() {
        println("Enter file name:")
        val filename = readlnOrNull() ?: return
        val file = File(filename)

        // Check file existence and readability
        if (!file.exists()) {
            println("The file $filename does not exist")
            return
        }

        if (!file.canRead()) {
            println("Cannot read the file $filename. Access denied")
            return
        }

        try {
            val lines = file.readLines()
            if (lines.isEmpty()) {
                throw IOException("File is empty")
            }

            // Parse and validate dimensions
            val (height, width) = lines[0].split(" ").map { it.toInt() }
            if (height < 3 || width < 3 || lines.size != height + 1) {
                throw IOException("Invalid maze dimensions")
            }

            // Parse and validate maze data
            val grid = Array(height) { IntArray(width) }
            for (i in 0 until height) {
                val line = lines[i + 1]
                if (line.length != width) {
                    throw IOException("Invalid line length")
                }
                for (j in 0 until width) {
                    when (line[j]) {
                        '0' -> grid[i][j] = 0
                        '1' -> grid[i][j] = 1
                        else -> throw IOException("Invalid character in maze")
                    }
                }
            }

            currentMaze = MazeData(height, width, grid)
            println("Maze has been loaded from $filename")
        } catch (e: Exception) {
            println("Cannot load the maze. It has an invalid format")
        }
    }

    /**
     * Displays the current maze using Unicode block characters.
     * Uses ██ for walls and spaces for paths.
     */
    private fun displayMaze() {
        currentMaze?.let { maze ->
            for (row in maze.grid) {
                for (cell in row) {
                    if (cell == 1) print("\u2588\u2588") else print("  ")
                }
                println()
            }
        }
    }

    /**
     * Finds and displays the escape path through the maze.
     */
    private fun findEscape() {
        currentMaze?.let { maze ->
            val escapePath = mazeSolver.findEscape(maze)
            val pathCells = escapePath.toSet()

            for (i in 0 until maze.height) {
                for (j in 0 until maze.width) {
                    when {
                        maze.grid[i][j] == 1 -> print("██")
                        Cell(i, j) in pathCells -> print("//")
                        else -> print("  ")
                    }
                }
                println()
            }
        }
    }
}
