fun winnerCheck(ch : Char, matrix: MutableList<MutableList<Char>>): Boolean {
    return (matrix[0][0] == matrix[0][1] && matrix[0][0] == matrix[0][2] && matrix[0][0] == ch ||
        matrix[1][0] == matrix[1][1] && matrix[1][0] == matrix[1][2] && matrix[1][0] == ch ||
        matrix[2][0] == matrix[2][1] && matrix[2][0] == matrix[2][2] && matrix[2][0] == ch ||
        matrix[0][0] == matrix[1][0] && matrix[0][0] == matrix[2][0] && matrix[0][0] == ch ||
        matrix[0][1] == matrix[1][1] && matrix[0][1] == matrix[2][1] && matrix[0][1] == ch ||
        matrix[0][2] == matrix[1][2] && matrix[0][2] == matrix[2][2] && matrix[0][2] == ch ||
        matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[0][0] == ch ||
        matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[0][2] == ch)

}

fun gridPrinter(matrix: MutableList<MutableList<Char>>) {
    println("---------")
    println("| ${matrix[0][0]} ${matrix[0][1]} ${matrix[0][2]} |")
    println("| ${matrix[1][0]} ${matrix[1][1]} ${matrix[1][2]} |")
    println("| ${matrix[2][0]} ${matrix[2][1]} ${matrix[2][2]} |")
    println("---------")
}

fun isValidCoordinate(matrix: MutableList<MutableList<Char>>, input: String, user: Char): Boolean {
    val coordinates = input.split(" ")
    for (i in coordinates) {
        if (i.matches("^[a-zA-Z ]*$".toRegex())) {
            println("You should enter numbers!")
            return false
        }
    }
    if (coordinates.size < 2 || coordinates.size > 2) {
        println("Invalid coordinates supplied!")
        return false
    } else {
        val x = coordinates[0].toInt() - 1
        val y = coordinates[1].toInt() - 1
        return if (x > 2 || x < 0 || y > 2 || y < 0) {
            println("Coordinates should be from 1 to 3!")
            false
        } else if (matrix[x][y] == '_') {
            matrix[x][y] = user
            true
        } else {
            println("This cell is occupied! Choose another one!")
            false
        }
    }
}

fun gameStatus(matrix: MutableList<MutableList<Char>>, counter: MutableList<Int>): Boolean {
    return if (winnerCheck('X', matrix) && !winnerCheck('O', matrix)) {
        println("X wins")
        true
    } else if (winnerCheck('O', matrix) && !winnerCheck('X', matrix)) {
        println("O wins")
        true
    } else {
        if (counter[0] + counter[1] == 9) {
            println("Draw")
            true
        } else {
            false
        }
    }
}

fun main() {
    val matrix = mutableListOf(
        mutableListOf('_', '_','_'),
        mutableListOf('_', '_','_'),
        mutableListOf('_', '_','_'),
    )
    var turn = 0
    var player: Char
    val counter = mutableListOf(0, 0)
    gridPrinter(matrix)
    while (true) {
        println("Enter the coordinates:")
        val coordinates = readLine()!!
        player = when (turn) {
            0 -> {
                counter[turn]++
                turn = 1
                'X'
            }
            else -> {
                counter[turn]++
                turn = 0
                'O'
            }
        }
        if (isValidCoordinate(matrix, coordinates, player)) {
            gridPrinter(matrix)
            if (gameStatus(matrix, counter)) {
                break
            }
        } else {
            continue
        }
    }
}
