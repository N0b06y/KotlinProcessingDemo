import Settings.Settings.WINDOW_HEIGHT
import Settings.Settings.WINDOW_WIDTH
import Settings.Settings.printSettings
import data_classes.Force
import data_classes.Vertex
import processing.core.PApplet


class Simulatron : PApplet() {
    private var rStroke = 0F
    private var gStroke = 0F
    private var bStroke = 0F
    var isOnTop = false

    private var start = 0
    private var runtime = -1

    private val vertex1 = Vertex(500.0, 500.0)
    private val vertex2 = Vertex(550.0, 550.0)
    private val vertex3 = Vertex(600.0, 600.0)
    private val vertex4 = Vertex(550.0, 450.0)

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

    var firstController = true
    override fun draw() {
        background(80)

        runtime = 15 //if(runtime==-1) 15 else millis() - start
        start = millis()



        if(keyPressed)
            firstController = !firstController
        if(mousePressed) {
            if(firstController) {
                vertex1.x = mouseX.toDouble()
                vertex1.y = -mouseY.toDouble() + WINDOW_HEIGHT
            }else{
                vertex3.x = mouseX.toDouble()
                vertex3.y = mouseY.toDouble()
            }
        }

        println("VERTEX1: $vertex1 _____________")
        vertex1.force = Force()
        vertex1.updateSpringForceTo(vertex2)
        //vertex1.updateSpringForceTo(vertex3)
        vertex1.updateSpringForceTo(vertex4)

        println("VERTEX2= $vertex2 _____________")
        vertex2.force = Force()
        vertex2.updateSpringForceTo(vertex1)
        vertex2.updateSpringForceTo(vertex3)
        //vertex2.updateSpringForceTo(vertex4)

        println("VERTEX3= $vertex3 _____________")
        vertex3.force = Force()
        //vertex3.updateSpringForceTo(vertex1)
        vertex3.updateSpringForceTo(vertex2)
        //vertex3.updateSpringForceTo(vertex4)

        println("VERTEX4= $vertex4 _____________")
        vertex4.force = Force()
        vertex4.updateSpringForceTo(vertex1)
        //vertex4.updateSpringForceTo(vertex2)
        //vertex4.updateSpringForceTo(vertex3)
        vertex1.updateAccel(runtime)
        vertex1.plot(this)
        vertex2.updateAccel(runtime)
        vertex2.plot(this)
        vertex3.updateAccel(runtime)
        vertex3.plot(this)
        vertex4.updateAccel(runtime)
        vertex4.plot(this)
        println(runtime)
    }
    private fun updateVertices() {

    }
}