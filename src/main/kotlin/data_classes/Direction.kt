package data_classes

import kotlin.math.atan2
import kotlin.math.sqrt

interface Direction {
    var x: Float
    var y: Float
    fun add(direction: Direction) {
        x += direction.x
        y += direction.y
    }
    fun getAngle(): Double {
        return atan2(
            y.toDouble(),
            x.toDouble()
        )
    }
    fun getLength(): Double {
        return sqrt(x.toDouble() * x + y.toDouble() * y)
    }

    // Operators
    operator fun plusAssign(direction: Direction) {
        this.x += direction.x
        this.y += direction.y
    }
    operator fun minusAssign(direction: Direction) {
        x -= direction.x
        y -= direction.y
    }
    operator fun timesAssign(scalar: Int) {
        x *= scalar
        y *= scalar
    }

    operator fun minus(gegFSum: Direction): Vector {
        return Vector(x - gegFSum.x, y - gegFSum.y)
    }

    fun compareAngles(angle1: Double, angle2: Double): Double {

        var diff = angle1 - angle2

        while(diff < -2*Math.PI) {
            diff += 2* java.lang.Math.PI
        }
        while(diff > 2*Math.PI) {
            diff -= 2* java.lang.Math.PI
        }
        return diff
    }
}