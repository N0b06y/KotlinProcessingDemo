/**
 * After digging around I found that the internal Mac APIs that Processing has
 * been misusing to add a proper quithandler and set a dock icon has now been
 * removed and replaced by official AWT APIS. There is a class inside Processing
 * called ThinkDifferent that is loaded by reflection when Mac is detected and this
 * class was calling the now removed APIs. To solve this I reimplemented this class
 * using the new official APIs and just added a new processing.core.
 * ThinkDifferent class to my project that solved the issue.
 * @link https://stackoverflow.com/questions/47343612/cannot-compile-when-importing-processing-library-into-eclipse
 */
package processing.core;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;

public class ThinkDifferent {

    // True if user has tried to quit once. Prevents us from cancelling the quit
    // call if the sketch is held up for some reason, like an exception that's
    // managed to put the sketch in a bad state.
    static boolean attemptedQuit;

    public static void init(final PApplet sketch) {
        Desktop desktop = Desktop.getDesktop();
        desktop.setQuitHandler(
                new QuitHandler() {
                    @Override
                    public void handleQuitRequestWith(QuitEvent e, QuitResponse response) {
                        sketch.exit();
                        if (PApplet.uncaughtThrowable == null && !attemptedQuit) {
                            response.cancelQuit();
                            attemptedQuit = true;
                        } else {
                            response.performQuit();
                        }
                    }
                });
    }

    public static void cleanup() {
        Desktop.getDesktop().setQuitHandler(null);
    }

    // Called via reflection from PSurfaceAWT and others
    public static void setIconImage(Image image) {
        Taskbar.getTaskbar().setIconImage(image);
    }
}
