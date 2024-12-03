import java.io.FileNotFoundException

fun main(args: Array<String>) {
    val example = Solver()
    val solver = Solver()
    try{
        example.readFileLineByLine("src/main/kotlin/providedExample.txt")
        solver.readFileLineByLine("src/main/kotlin/input.txt")
    }
    catch(e: FileNotFoundException)
    {
        println("$e")
    }

    var result = example.firstSolution()
    println("Example solution 1 = $result")

    result = example.secondSolution()
    println("Example solution 2 = $result")

    result = solver.firstSolution()
    println("Solution 1 = $result")

    result = solver.secondSolution()
    println("Solution 2 = $result")
}