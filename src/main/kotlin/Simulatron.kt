import Settings.Settings.WINDOW_HEIGHT
import Settings.Settings.WINDOW_WIDTH
import Settings.Settings.printSettings
import processing.core.PApplet


class Simulatron : PApplet() {
    companion object {
        fun simulate() {
            printSettings()
            val sim = Simulatron()
            sim.runSketch()
        }
    }

    // Colors
    private var rStroke = 0F
    private var gStroke = 0F
    private var bStroke = 0F
    // Pointmasses
    val pointMasses = mutableListOf(
        PointMass(1f, Vector(100.0, 100.0), Vector(0.0, 0.0)),
        PointMass(1f, Vector(200.0, 100.0), Vector(0.0, 0.0))
    )

    override fun settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT)
    }

    override fun setup() {
        stroke(rStroke, gStroke, bStroke)
        // graphFunction( 0..5000, ::f )
        fill(255F)

        stroke(0F)
        stroke(0xFF0000)
    }

    var firstController = true
    override fun draw() {
        background(80)

//        runtime = 15 //if(runtime==-1) 15 else millis() - start
//        start = millis()

        for (pointMass in pointMasses) {
            pointMass.update(1f)
            pointMass.draw(this)
        }

    }
}