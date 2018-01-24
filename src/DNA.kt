import java.util.*

/**
 * Created by Aditya on January 24, 2018.
 */
class DNA(private val geneLength: Int, private val mutationRate: Float) : Comparable<DNA> {
    override fun compareTo(other: DNA): Int {
        if (fitness - other.fitness == 0f) {
            return 0
        }
        if (fitness - other.fitness < 0f) {
            return -1
        }
        if (fitness - other.fitness > 0f) {
            return 1
        }
        return 0
    }

    private val notes = arrayOf("C", "D", "E", "F", "A", "B", "Bb","G")

    var genes = Array<String>(geneLength) {
        notes[Random(System.nanoTime()).nextInt(notes.size)]
    }
    var fitness = 0f

    init {
        for (i in 0 until geneLength) {
            genes[i] = notes[Random(System.nanoTime()).nextInt(notes.size)]
        }
    }


    fun crossover(partner: DNA): DNA {
        val crossoverPoint = Random(System.nanoTime()).nextInt(this.genes.size)
        val child = DNA(this.geneLength, this.mutationRate)
        for (i in 0 until crossoverPoint) {
            child.genes[i] = genes[i]
        }
        for (i in crossoverPoint until geneLength) {
            child.genes[i] = partner.genes[i]
        }
        child.mutate()
        return child

    }

    private fun mutate() {
        for (i in 0 until genes.size) {
            if (Random().nextFloat() < mutationRate) {
                genes[i] = notes[Random(System.nanoTime()).nextInt(notes.size)]
            }
        }
    }


}
