import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Long {
        val inp = input.toMutableList()
        val listOfNums = mutableListOf<Long>()
        val operators = mutableListOf<Char>()
        for(c in inp.last()){
            if(c == ' ') {
                continue
            }
            operators.add(c)
        }
        inp.removeLast()


        var initialNums = inp.take(1)
        for(num in initialNums[0].split(" ")){
            if(num == ""){
                continue
            }
            listOfNums.add(num.toLong())
        }
        inp.removeFirst()
        for (line in inp){
            val numsSplit = line.split(" ")
            val numsSplitFiltered = numsSplit.filter{it != ""}
            for(num in 0..<numsSplitFiltered.size){
                if(numsSplitFiltered[num] == ""){
                    continue
                }
                val currentNum = numsSplitFiltered[num].toLong()
                if(operators[num] == '+'){
                    listOfNums[num] += currentNum
                }
                if(operators[num] == '*'){
                    listOfNums[num] *= currentNum
                }
            }
        }
        return listOfNums.sum()
    }
    
    fun part2(input: List<String>): Long {
        val inp = input.toMutableList()
        val listOfNums = mutableListOf< MutableList<Long>>()
        val operators = mutableListOf<Char>()
        for(c in inp.last()){
            if(c == ' ') {
                continue
            }
            operators.add(c)
        }
        inp.removeLast()
        listOfNums.add(mutableListOf())
        for (i in 0..<inp[0].length){ //assuming it's all padded to same char count
            var currentNum = 0L
            if(inp.all { it[i] == ' '}){
                listOfNums.add(mutableListOf())
                continue

            }
            for(j in 0..<inp.size){
                if(inp[j][i] == ' '){
                    if (currentNum != 0L){
                        currentNum /= 10
                    }
                    continue
                }
                val currentDigit = (inp[j][i].digitToInt() * 10.0.pow(inp.size-j-1)).toLong()
                currentNum += currentDigit
            }
            listOfNums.last().add(currentNum)
        }
        var total = 0L
        for (i in 0..<operators.size){
            if(operators[i] == '+'){
                total += listOfNums[i].sum()
            }
            if(operators[i] == '*'){
                total += listOfNums[i].reduce { acc, i ->  acc * i }
            }
        }
        return total
    }
    
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 3263827L)
    
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
