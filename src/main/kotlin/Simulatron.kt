import Settings.Data.vertices
import Settings.Settings.WINDOW_HEIGHT
import Settings.Settings.WINDOW_WIDTH
import Settings.Settings.printSettings
import data_classes.Vertex
import processing.core.PApplet


class Simulatron : PApplet() {
    private var rStroke = 0F
    private var gStroke = 0F
    private var bStroke = 0F
    var isOnTop = false

    private val vertex1 = Vertex(500f, 500f)
    private val vertex2 = Vertex(700f, 500f)

    companion object {
        fun simulate() {
            printSettings()
            val sim = Simulatron()
            sim.runSketch()
        }

    }

    override fun settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT)
    }

    override fun setup() {
        stroke(rStroke, gStroke, bStroke)
        // graphFunction( 0..5000, ::f )
        fill(255F)

        stroke(0F)

    }

    override fun draw() {
        vertex1.updateSpringForceTo(vertex2)
        vertex2.updateSpringForceTo(vertex1)

    }
    private fun updateVertices() {
        vertices.forEach { (key, value) ->
            value.update(
                listOf(
                    vertices[Pair(key.first + 1, key.second)],
                    if(key.first>0) vertices[Pair(key.first - 1, key.second)] else null,
                    vertices[Pair(key.first, key.second + 1)],
                    if(key.second>0) vertices[Pair(key.first, key.second - 1)] else null,
                ),
                0.000001
            )
            value.plot(this)
        }
    }
}