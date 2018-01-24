import org.jfugue.Player

/**
 * Created by Aditya on January 24, 2018.
 */

fun main(args: Array<String>) {
    val target = arrayOf("C","C","D","C","F","E","C","C","D","C","G","F","C","C","A","F","E","D","Bb","Bb","A","F","G","F")

    val player = Player()
    val populationSize = 30000
    val mutationRate = 0.04f

    val population = Population(populationSize, target, mutationRate)
    var currentBest: DNA
    while (true) {
        population.addInMatingPool()
        population.createNewPopulation()
        population.calculateFitnessOfAll(target)
        currentBest = population.getCurrentBest()
        println(currentBest.genes.joinToString(" "))
        player.play(currentBest.genes.joinToString(" "))
        if (population.matches(target)) break
    }
}