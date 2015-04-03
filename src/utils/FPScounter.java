/*
 * Did by Whizzpered. 
 * All code is mine.
 */
package utils;

import static game.main.Game.fontRender;
import java.util.Calendar;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

/**
 *
 * @author Юрий Whizzpered
 */
public class FPScounter {
    static public int fps, frames;
    static int old = Calendar.getInstance().get(Calendar.SECOND);
    
    public static void render() {
        frames++;
            if (old != Calendar.getInstance().get(Calendar.SECOND)) {
                fps = frames;
                frames = 0;
                old = Calendar.getInstance().get(Calendar.SECOND);
            }
        fontRender.drawString("FPS:"+fps, Display.getWidth()/2-80,10, fps<100?Color.yellow:Color.green);
    }
}
