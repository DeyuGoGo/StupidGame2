package go.deyu.stupidgame2.presentation

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Solution {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val n = bombs.size
        var maxDetonated = 0
        val visited = BooleanArray(n)

        for (i in 0 until n) {
            val detonated = getDetonatedCount(bombs, i, visited)
            if (detonated > maxDetonated) {
                maxDetonated = detonated
            }
        }

        return maxDetonated
    }
    private fun getDetonatedCount(bombs: Array<IntArray>, index: Int, visited: BooleanArray): Int {
        if (visited[index]) {
            return 0
        }

        visited[index] = true
        var detonated = 1
        val currentBomb = bombs[index]

        for (i in bombs.indices) {
            if (i != index && isInRange(currentBomb, bombs[i])) {
                detonated += getDetonatedCount(bombs, i, visited)
            }
        }

        return detonated
    }
    private fun isInRange(bomb1: IntArray, bomb2: IntArray): Boolean {
        val x1 = bomb1[0]
        val y1 = bomb1[1]
        val r1 = bomb1[2]

        val x2 = bomb2[0]
        val y2 = bomb2[1]
        val r2 = bomb2[2]

        val distance = sqrt((x2 - x1).toDouble().pow(2) + (y2 - y1).toDouble().pow(2))
        return distance <= r1 || distance <= r2
    }
}
