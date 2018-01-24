import java.util.*

/**
 * Created by Aditya on January 24, 2018.
 */
class Population(populationSize: Int, private val target: Array<String>, private val mutationRate: Float) {
    private val population = Array(populationSize) {
        DNA(target.size, mutationRate)
    }

    var generation = 0
    private val matingPool = ArrayList<DNA>()

    init {
        calculateFitnessOfAll(target)
    }
    fun calculateFitnessOfAll(target: Array<String>) {
        population.forEach {
            val score = (0 until target.size).count { i -> it.genes[i] == target[i] }
            it.fitness = Math.pow((score.toFloat() / target.size.toFloat()).toDouble(), 2.0).toFloat()
        }
    }

    fun addInMatingPool() {
        matingPool.clear()
        val maxFitness = population.maxBy { it.fitness }!!.fitness
        population.forEach {
            val normalisedFitness = (it.fitness - 0) / (maxFitness - 0)
            for (i in 0 until normalisedFitness.toInt() * 100) matingPool.add(it)
        }
    }

    fun createNewPopulation() {
        population.forEachIndexed { index, _ ->
            val father = matingPool[Random(System.nanoTime()).nextInt(matingPool.size)]
            val mother = matingPool[Random(System.nanoTime()).nextInt(matingPool.size)]
            val child = father.crossover(mother)
            population[index] = child
        }
        generation++
    }

    fun getCurrentBest() = population.maxBy { it.fitness }!!
    fun matches(target: Array<String>): Boolean = population.any { it.genes.contentEquals(target) }
}