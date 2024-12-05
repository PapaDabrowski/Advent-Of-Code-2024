import java.io.File

class Solver {
    private val inputList = mutableListOf<String>()

    fun firstSolution(): Int {
        var value: Int = 0
        for(i in inputList) value += findMulAndCalculate(i)
        return value
    }

    fun secondSolution(): Int {
        var value: Int = 0
        var regexToDo_Dont = "do\\(\\)|don't\\(\\)".toRegex()
        var enabled = 1
        for (line in inputList) {
            var editableLine = line.toString()
            for (j in regexToDo_Dont.findAll(editableLine)) {
                value += enabled * findMulAndCalculate(editableLine.substringBefore(j.value))
                enabled = when (j.value) {
                    "don't()" -> 0
                    "do()" -> 1
                    else -> 0
                }
                editableLine = editableLine.substringAfter(j.value)
            }
            value += enabled * findMulAndCalculate(editableLine)
        }
        return value
    }

    private fun findMulAndCalculate(input: String) : Int {
        var value = 0
        var regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        for (match in regex.findAll(input)) {
            val result = match.value.split(",").map { Integer.parseInt(it.filter { it.isDigit() })}
            value += result.first() * result.get(1)
        }
        return value
    }

    fun readFileLineByLine(fileName : String) = File(fileName).forEachLine {
        inputList.add(it)
    }
}