fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        var highestNum: Int
        var secondHighest: Int
        for (line in input){
            var lineAsArr = mutableListOf<Int>()
            for(c in line){
                val num = c.digitToInt()
                lineAsArr.add(num)
            }
            highestNum = lineAsArr.max()
            if(lineAsArr.indexOf(highestNum) == lineAsArr.size - 1) {
                lineAsArr.removeLast()
                secondHighest = lineAsArr.max()
                total += (secondHighest * 10) + highestNum
            }else{
                while(lineAsArr.first() != highestNum) {
                    lineAsArr.removeFirst()
                }
                lineAsArr.removeFirst()
                secondHighest = lineAsArr.max()
                total += (highestNum * 10) + secondHighest
            }
        }

        return total
    }



    fun part2(input: List<String>): Long {
        var total = 0L
        for (line in input) {
            val choices = mutableListOf<Int>()
            val lineAsArr = mutableListOf<Int>()
            for (c in line) {
                val num = c.digitToInt()
                lineAsArr.add(num)
            }
            var start = 0
            var choiceNumber = 0
            while(choiceNumber <  12){
                val end = lineAsArr.size - (12 - choices.size)
                var currentMax = 0
                var maxIndex = -1
                for (i in start..end){
                    val digit = lineAsArr[i]
                    if(digit > currentMax){
                        currentMax = digit
                        maxIndex = i
                    }
                }
                choices.add(currentMax)
                start = maxIndex+1
                choiceNumber++
            }
            val joined = (choices.joinToString("")).toLong()
            total += joined

        }
        return total
    }
    
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 3121910778619L)
    
    
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
