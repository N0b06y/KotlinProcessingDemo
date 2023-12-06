package data_classes

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
    xPos: Double, yPos: Double,
    mass: Float = DEFAULT_MASS,
) : Direction {
    override var x = xPos    // In Pixels
    override var y = yPos
    private var mass: Float = DEFAULT_MASS
    var force: Force = Force(0.0,0.0)  // F_res on the vertex
    private var frictionRes: Double = 0.96
    private var velocity: Vector = Vector(0.0, 0.0)
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
    ) {/*
        val distanceSum = Vector(0.0,0.0)
        val gegFSum     = Vector(0.0,0.0)
        surroundingVertices.forEach { vertex ->
            vertex?.let {v->
                distanceSum.add(vectorTo(v))
                gegFSum.add(vectorTo(v.inverse()))
            }
        }
        gegFSum -= gegFSum
        if(DEBUG_ENABLED) println("Winkelunterschied: ${compareAngles(gegFSum.getAngle(), distanceSum.getAngle())}")
        val some = distanceSum
        some += gegFSum
        if(DEBUG_ENABLED) println("Vertex+Vertex.Inverse: ${some.toString()}")
        distanceSum *= SPRING_CONSTANT
        gegFSum *= DAMP_CONSTANT.toDouble()
        gegFSum *= -1.0
        this.force.assign(distanceSum-gegFSum)
        */
    }

    /**
     * @param time Milliseconds
     */
    private fun updatePosition(time: Double) {
        val acceleration: Vector = force.getAcceleration(mass)
        if(DEBUG_ENABLED) println("Acceleration: ${acceleration.toString()}")
        val _time = time.toFloat() / 1000f // to seconds
        val positionChange = acceleration * (0.5 * _time * _time)
        if(DEBUG_ENABLED) println("PositionChange: ${positionChange.toString()}")
        this.x += positionChange.x
        this.y += positionChange.y
        if(DEBUG_ENABLED) println("Position: ${this.toString()}")
    }
    fun updateSpringForceTo(other: Vertex) {
        this.force *= 1/300.0
        val toOther = vectorTo(other)
                                                                                                                        if(DEBUG_ENABLED) println("ToOther: ${toOther.toString()}")
        val newForce: Force = Force()
        newForce += toOther
        newForce *= SPRING_CONSTANT
        this.force += newForce
                                                                                                                        println("Pulling Force: ${this.force}")
        val fromOther = vectorTo(other).inverse()
                                                                                                                        if(DEBUG_ENABLED) println("FromOther: ${fromOther.toString()}")
        force.damp(vectorTo(other))
                                                                                                                        println("   > Completed force: ${this.force}")
        this.force *= 300.0
    }
    fun updateAccel(_time: Int) {
        val time: Double = _time / 1000.0
                                                                                                                        if(DEBUG_ENABLED) println("Time: ${time.toString()}")

        val accel = force.getAcceleration(mass)
                                                                                                                        if(DEBUG_ENABLED) println("Accel: ${accel.toString()}")

        velocity += accel * (0.5 * time * time)
        velocity = velocity * frictionRes
                                                                                                                        if(DEBUG_ENABLED) println("Vel: ${velocity.toString()}")

        this.x += velocity.x
        this.y += velocity.y
                                                                                                                        if(DEBUG_ENABLED) println("Pos: ${this.toString()}")
    }
    fun plot(scope: PApplet) {
        scope.ellipse(
            (x * STRECKUNG_X).toFloat(),
            (-y * STRECKUNG_Y).toFloat() + WINDOW_HEIGHT,
            DEFAULT_RADIUS, DEFAULT_RADIUS,
        )
    }
    fun inverseVector(): Vector {
        return Vector(-x, -y)
    }

    override fun toString(): String {
        return "Vertex(x=$x, y=$y)"
    }
}