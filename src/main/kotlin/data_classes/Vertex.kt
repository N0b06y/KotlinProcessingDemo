package data_classes

import Settings.Settings.ABSTOSSKONSTANTE
import Settings.Settings.DEBUG_ENABLED
import Settings.Settings.DEFAULT_MASS
import Settings.Settings.DEFAULT_RADIUS
import Settings.Settings.SPRING_CONSTANT
import Settings.Settings.STRECKUNG_X
import Settings.Settings.STRECKUNG_Y
import Settings.Settings.WINDOW_HEIGHT
import processing.core.PApplet
import processing.core.PApplet.pow
import kotlin.math.abs
import kotlin.math.sqrt

class Vertex(
    xPos: Float, yPos: Float,
    mass: Float = DEFAULT_MASS,
) : Direction {
    override var x = xPos    // In Pixels
    override var y = yPos
    private var mass: Float = DEFAULT_MASS
    private var force: Force = Force(0f,0f)  // F_res on the vertex
    fun distanceTo(other: Vertex): Double {
        return sqrt(
            abs(pow(this.x.toFloat(),2F) - pow(other.x.toFloat(), 2F))
            +  abs(pow(this.y.toFloat(),2F) - pow(other.y.toFloat(),2F))
        ).toDouble()
    }

    /**
     * @param runtime Milliseconds
     */
    fun update(
        surroundVertices: List<Vertex?>,
        runtime: Double
    ) {
        updateForces(surroundVertices)
        updatePosition(runtime)
    }


    // PRIVATE
    private fun vectorTo(other: Vertex): Vector {
        return Vector(other.x - this.x, other.y - this.y)
    }

    // CALCULATIONS
    private fun updateForces(
        surroundingVertices: List<Vertex?>
    ) {
        val distanceSum = Vector(0f,0f)
        val gegFSum     = Vector(0f,0f)
        surroundingVertices.forEach { vertex ->
            vertex?.let {
                distanceSum.add(vectorTo(vertex))
                gegFSum.add(vertex.inverse())
            }
        }
        if(DEBUG_ENABLED) println("Winkelunterschied: ${compareAngles(gegFSum.getAngle(), distanceSum.getAngle())}")
        distanceSum *= SPRING_CONSTANT.toInt()
        gegFSum *= ABSTOSSKONSTANTE.toInt()
        this.force.assign(distanceSum-gegFSum)
    }

    /**
     * @param time Milliseconds
     */
    private fun updatePosition(time: Double) {
        val acceleration: Vector = force.getAcceleration(mass)
        val _time = time.toFloat() * 1000f // to seconds
        val positionChange = acceleration * (0.5f * _time * _time)
        this.x += positionChange.x
        this.y += positionChange.y
    }
    fun plot(scope: PApplet) {
        scope.ellipse(
            (x * STRECKUNG_X).toFloat(),
            (-y * STRECKUNG_Y).toFloat() + WINDOW_HEIGHT,
            DEFAULT_RADIUS, DEFAULT_RADIUS,
        )
    }
    fun inverse(): Vertex {
        return Vertex(-x, -y)
    }
}