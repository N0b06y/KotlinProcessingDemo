import Globals.E
import Settings.WINDOW_HEIGHT
import Settings.WINDOW_WIDTH
import Settings.graph
import Settings.printSettings
import processing.core.PApplet
import processing.core.PApplet.pow

object Settings {
    // Fenstereinstellungen
    const val           WINDOW_HEIGHT     = 1000
    const val           WINDOW_WIDTH      = 2000
    private const val   DEFAULT_HEIGHT    = 200F
    const val           DEFAULT_RADIUS    = 10F
    const val           RESOLUTION        = 100

    // Streckung der Werte auf Fenstergröße
    const val           MIN_X = 0
    const val           MAX_X = 5

    const val           STRECKUNG_X = WINDOW_WIDTH/(MAX_X - MIN_X)
    private const val   MIN_Y = 0
    private const val   MAX_Y = 1
    const val           STRECKUNG_Y = WINDOW_WIDTH/(MAX_Y*2 - MIN_Y)

    // Konfiguration
    const val           RESISTANCE = 1000       // [Ω]
    private const val   VOLTAGE    = 5          // [V]
    const val           CAPACITY   = 0.001      // [µF]
    const val           Q_0        = 1          // [C]

    // Graph with the plotted function for the simulation
    val graph = Graph { t ->
        return@Graph Q_0 * pow(E, -t / (RESISTANCE * CAPACITY.toFloat())).toFloat()
    }

    fun printSettings() {
        println(MAX_X - MIN_X)
        println("WINDOW_HEIGHT: $WINDOW_HEIGHT")
        println("WINDOW_WIDTH: $WINDOW_WIDTH")
        println("DEFAULT_HEIGHT: $DEFAULT_HEIGHT")
        println("DEFAULT_RADIUS: $DEFAULT_RADIUS")
        println("STRECKUNG_X: $STRECKUNG_X")
        println("STRECKUNG_Y: $STRECKUNG_Y")

        println("RESISTANCE: $RESISTANCE")
        println("VOLTAGE: $VOLTAGE")
        println("CAPACITY: $CAPACITY")
    }
}
class Simulatron : PApplet() {
    private var rStroke = 0F
    private var gStroke = 0F
    private var bStroke = 0F
    var isOnTop = false

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

        graph.curve(this)

    }

    override fun draw() {
        //clear()
    }
}