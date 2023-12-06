package data_classes

import Settings.Settings.SPRING_CONSTANT
import kotlin.math.abs

data class Force(
    override var x: Double = 0.0,
    override var y: Double = 0.0
) : Direction {
    fun assign(vector: Vector) {
        this.x = vector.x
        this.y = vector.y
    }
    fun getAcceleration(mass: Float): Vector {
        return Vector(x / mass, y / mass)
    }
    fun damp(distance: Vector) {
        var dampingX = if(distance.x==0.0) 0.0 else distance.x * (-5) + (distance.x/abs(distance.x)) * 500//50000 / distance.x
        var dampingY = if(distance.y==0.0) 0.0 else distance.y * (-5) + (distance.y/abs(distance.y)) * 500//50000 / distance.y
        println("Damping Force: $dampingX $dampingY")
        println("Spring Constant: $SPRING_CONSTANT")
        if(dampingX.isInfinite()) {
            dampingX = 0.0
        }
        if(dampingY.isInfinite()) {
            dampingY = 0.0
        }
        this.x -= dampingX
        this.y -= dampingY
        println("Damping Force: $dampingX $dampingY")
    }
    fun getInvert(): Force {
        return Force(-x, -y)
    }
    override fun toString(): String {
        return "Force(x=$x, y=$y)"
    }
}

operator fun Double.div(vector: Vertex): Force {
    return Force(this / vector.x, this / vector.y)
}