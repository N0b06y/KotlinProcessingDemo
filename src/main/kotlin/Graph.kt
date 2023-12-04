import Globals.E
import Settings.CAPACITY
import Settings.DEFAULT_RADIUS
import Settings.MAX_X
import Settings.MIN_X
import Settings.RESISTANCE
import Settings.RESOLUTION
import Settings.STRECKUNG_X
import Settings.STRECKUNG_Y
import Settings.WINDOW_HEIGHT
import processing.core.PApplet
import processing.core.PApplet.pow

open class Graph( val f: (Float) -> Float) {
    fun curve(scope: PApplet) {
        val q0: Float = 1.0f
        for(t in MIN_X..(MAX_X*RESOLUTION)) {

            point(t.toFloat()/RESOLUTION, f(t.toFloat()/RESOLUTION), scope)
        }
    }

    private fun point(x: Float, y: Float, scope: PApplet) {
        //scope.point(x * STRECKUNG_X, ((-y) * STRECKUNG_Y)+ WINDOW_HEIGHT)
        scope.ellipse(x * STRECKUNG_X, ((-y) * STRECKUNG_Y)+ WINDOW_HEIGHT, DEFAULT_RADIUS, DEFAULT_RADIUS)
        println("x: $x, y: $y")
        print("Visual X: ")
        println(x * STRECKUNG_X)
        print("Visual Y: ")
        println(( ((-y) * STRECKUNG_Y)+ WINDOW_HEIGHT))
    }
}