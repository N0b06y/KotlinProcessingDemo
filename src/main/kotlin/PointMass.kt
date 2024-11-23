import Settings.Settings
import processing.core.PApplet

class PointMass(
    private val mass: Float,        // kg
    private var position: Vector,   // m
    private var velocity: Vector    // m/s
    ) {

    private var yLocked = true

    fun update(dt: Float) {
        position.x += velocity.x * dt
        if(!yLocked) position.y += velocity.y * dt
    }

    fun position(): Vector {
        return position
    }

    /**
     * @param force Newton
     */
    fun applyForce(force: Vector) {
        velocity += (force / mass)
    }

    /**
     * Decrease velocity by factor
     */
    fun applyFrictionFactor(factor: Float) {
        velocity *= (1-factor)
    }

    fun lockY(){ yLocked = true }
    fun unlockY(){ yLocked = false }
    /**
     * Decrease velocity by constant acceleration
     */
    fun applyFrictionKonstant(constant: Float) {

        if( (velocity - constant).normalize().x == velocity.normalize().x
            || (velocity - constant).normalize().y == velocity.normalize().y )
            velocity -= constant
        else
            velocity = Vector(0.0, 0.0)
    }

    fun draw(scope: PApplet) {
        scope.ellipse(
            (position.x * Settings.STRECKUNG_X).toFloat(),
            (-position.y * Settings.STRECKUNG_Y).toFloat() + Settings.WINDOW_HEIGHT,
            Settings.DEFAULT_RADIUS, Settings.DEFAULT_RADIUS,
        )
    }
}