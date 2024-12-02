import java.io.File
import kotlin.math.abs

class Solver {
    private val firstIDList = mutableListOf<Int>()
    private val secondIDList = mutableListOf<Int>()

    fun firstSolution(): Int {
        var value: Int = 0
        var tempList1 = firstIDList.toMutableList()
        var tempList2 = secondIDList.toMutableList()
        while (tempList1.minBy { it } != Int.MAX_VALUE) {
            value += abs(tempList1.minBy { it } - tempList2.minBy { it })
            tempList1.set(tempList1.indexOf(tempList1.minBy { it }), Int.MAX_VALUE)
            tempList2.set(tempList2.indexOf(tempList2.minBy { it }), Int.MAX_VALUE)
        }
        return value
    }

    fun secondSolution(): Int {
        var value: Int = 0
        firstIDList.forEach {
            value += it * secondIDList.indicesOf(it).size
        }
        return value
    }

    fun readFileLineByLine(fileName : String) = File(fileName).forEachLine {
        firstIDList.add(Integer.parseInt(it.substringBefore(" ")))
        secondIDList.add(Integer.parseInt(it.substringAfterLast(" ")))
    }

    private fun Iterable<Int>.indicesOf(i : Int) = mapIndexed { index, elem -> if (elem == i) index
    else -1 }.filter{it != -1}
}