package data_classes

data class Force(
    override var x: Double = 0f,
    override var y: Double = 0f
) : Direction {
    fun assign(vector: Vector) {
        this.x = vector.x
        this.y = vector.y
    }
    fun getAcceleration(mass: Float): Vector {
        return Vector(x / mass, y / mass)
    }
    fun getInvert(): Force {
        return Force(-x, -y)
    }
    override fun toString(): String {
        return "Force(x=$x, y=$y)"
    }
}
