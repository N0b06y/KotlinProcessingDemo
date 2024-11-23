class Spring(
    private val length: Float,          // s_0
    point0: PointMass,
    point1: PointMass,
    private val springConstant: Float,  // D
) {
    private val points: ArrayList<PointMass> = arrayListOf(point0, point1)
    fun getForce(fromIndex: Int, distance: Float): Vector {
        // Calculate the amount of the force to apply
        val force = -(distance - length) * springConstant
        // Calculate the direction of the force
        val toIndex = (fromIndex + 1) % 2
        val dir =   (points[toIndex].position() - points[fromIndex].position()) /
                    (points[toIndex].position() - points[fromIndex].position()).length()

        return dir * force
    }
}