import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var ranges: MutableSet<ULongRange> = mutableSetOf()
        var inputPart = 1
        var freshIds = 0
        for(line in input){
            if(line == ""){
                inputPart = 2
                continue
            }
            if (inputPart == 1){
                val splitLine = line.split("-")
                val startRange = splitLine[0].toULong()
                val endRange = splitLine[1].toULong()
                ranges.add(ULongRange(startRange, endRange))
            }
            else {
                val currentNum = line.toULong()
                for(range in ranges){
                    if(currentNum in range){
                        freshIds += 1
                        break
                    }
                }
            }
        }
        return freshIds
    }
    
    fun part2(input: List<String>): ULong {
        val ranges: MutableSet<ULongRange> = mutableSetOf()
        var total: ULong = 0u
        for(line in input){
            if(line == ""){
                break
            }

            val splitLine = line.split("-")
            val startRange = splitLine[0].toULong()
            val endRange = splitLine[1].toULong()
            ranges.add(ULongRange(startRange, endRange))

        }
        val sortedList = ranges.toList().sortedBy{it.first}

        var currentRange = sortedList.first()
        for(range in sortedList){
            if(range.first > currentRange.endInclusive){
                total += (currentRange.last - currentRange.first + 1u)
                currentRange = range
            }
            else{
                currentRange = ULongRange(currentRange.first,  max(currentRange.last, range.last))
            }
        }
        total += (currentRange.last - currentRange.first + 1u)

        return total
    }
    
    val testInput = readInput("Day05_test")
    check(part2(testInput) == 14.toULong())
    
    
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
