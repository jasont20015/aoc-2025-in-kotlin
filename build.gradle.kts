plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}
dependencies {
    implementation(kotlin("stdlib"))
}
repositories {
    mavenCentral()
}


task("generateNextDay") {
    doLast {
        val prevDayNum = fileTree("$projectDir/src").matching {
            include("Day*.kt")
        }.maxOf {
            val (prevDayNum) = Regex("Day(\\d\\d)").find(it.name)!!.destructured
            prevDayNum.toInt()
        }
        val newDayNum = String.format("%02d", prevDayNum + 1)
        File("$projectDir/src", "Day$newDayNum.kt").writeText(
            """fun main() {
                fun part1(input: List<String>): Int {
                    return input.size
                }

                fun part2(input: List<String>): Int {
                    return input.size
                }

                // Test if implementation meets criteria from the description, like:
                check(part1(listOf("test_input")) == 1)

                // Or read a large test input from the `src/Day01_test.txt` file:
                val testInput = readInput("Day${newDayNum}_test")
                check(part1(testInput) == 1)

                // Read the input from the `src/Day01.txt` file.
                val input = readInput("Day${newDayNum}")
                part1(input).println()
                part2(input).println()
            }
"""
        )
        File("$projectDir/resources", "Day${newDayNum}_test.txt").writeText("")
        File("$projectDir/resources", "Day${newDayNum}.txt").writeText("")
    }
}


