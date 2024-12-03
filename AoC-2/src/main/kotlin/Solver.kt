import java.awt.Point
import java.io.File
import kotlin.math.abs
import kotlin.math.sign

class Solver {
    private val inputList = mutableListOf<String>()

    fun firstSolution(): Int {
        var value: Int = 0
        for (i in inputList)
            if (calculationFunction(i.split(" ").zipWithNext()).first) value++
        return value
    }

    fun secondSolution(): Int {
        var value: Int = 0
        for (i in inputList) {
            var list = i.split(" ").toMutableList()
            var result = calculationFunction(list.zipWithNext())
            if (result.first) value++
            else {
                var copyList = list.toMutableList()
                list.removeAt(result.second)
                copyList.removeAt(result.second - 1)
                result = calculationFunction(list.zipWithNext())
                var resultCopy = calculationFunction(copyList.zipWithNext())
                if (result.first || resultCopy.first) value++
            }
        }
        return value
    }

    private fun calculationFunction(list: List<Pair<String, String>>): Pair<Boolean, Int> {
        var ascOrDesc: Int = list.get(0).first.toInt() - list.get(0).second.toInt()
        for(pair in list) {
            val result = pair.first.toInt() - pair.second.toInt()
            if (!(abs(result) in 1..3) || sign(ascOrDesc.toFloat()) != sign(result.toFloat()) )
                return Pair(false, list.indexOf(pair) + 1)
            ascOrDesc = result
        }
        return Pair(true, -1)
    }

    fun readFileLineByLine(fileName : String) = File(fileName).forEachLine {
        inputList.add(it)
    }
}