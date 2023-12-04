package data_classes

data class Vector(
    override var x: Float,
    override var y: Float
) : Direction {
    fun add(x: Int, y: Int): Vector {
        this.x += x
        this.y += y
        return this
    }
    private fun sum(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }
    operator fun times(scalar: Float): Direction {
        return Vector(x * scalar, y * scalar)
    }
}