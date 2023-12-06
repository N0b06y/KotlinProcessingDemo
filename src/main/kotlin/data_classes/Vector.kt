package data_classes

data class Vector(
    override var x: Double,
    override var y: Double
) : Direction {
    fun add(x: Int, y: Int): Vector {
        this.x += x
        this.y += y
        return this
    }
    private fun sum(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }

    override operator fun timesAssign(scalar: Double) {
        this.x *= scalar
        this.y *= scalar
    }
    operator fun times(scalar: Double): Vector {
        return Vector(x * scalar, y * scalar)
    }
}