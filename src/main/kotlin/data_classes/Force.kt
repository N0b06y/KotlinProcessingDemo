package data_classes

data class Force(
    override var x: Float = 0f,
    override var y: Float = 0f
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
}
