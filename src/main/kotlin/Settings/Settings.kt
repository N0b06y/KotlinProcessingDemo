package Settings

import data_classes.Color

object Settings {
    const val DEBUG_ENABLED = true

    // Fenstereinstellungen
    const val           WINDOW_HEIGHT     = 1000
    const val           WINDOW_WIDTH      = 2000
    private const val   DEFAULT_HEIGHT    = 200f
    const val           DEFAULT_RADIUS    = 10f
    const val           RESOLUTION        = 100

    // Streckung der Werte auf Fenstergröße
    private const val           MIN_X = 0
    private const val           MAX_X = 5

    const val           STRECKUNG_X = 1 //WINDOW_WIDTH/(MAX_X - MIN_X)
    private const val   MIN_Y = 0
    private const val   MAX_Y = 1
    const val           STRECKUNG_Y = 1 //WINDOW_WIDTH/(MAX_Y*2 - MIN_Y)

    // Konfiguration
    const val PIXEL_LENGTH      = 1f        // One pixel corresponds to $PIXEL_LENGTH meters
    const val SPRING_CONSTANT   = 0.001f    // N/m
    const val DEFAULT_MASS      = 1f        // kg
    const val ABSTAND           = 100f      // m
    const val ABSTOSSKONSTANTE  = 10f       // N*m


    val DEFAULT_COLOR = Color()

    fun printSettings() {
        println(MAX_X - MIN_X)
        println("WINDOW_HEIGHT: $WINDOW_HEIGHT")
        println("WINDOW_WIDTH: $WINDOW_WIDTH")
        println("DEFAULT_HEIGHT: $DEFAULT_HEIGHT")
        println("DEFAULT_RADIUS: $DEFAULT_RADIUS")
        println("STRECKUNG_X: $STRECKUNG_X")
        println("STRECKUNG_Y: $STRECKUNG_Y")

    }
}